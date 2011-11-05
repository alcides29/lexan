/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

import java.util.Iterator;

/**
 *
 * @author marcos
 */
public class Thompson {
    //private Log log;

    //private AnalizadorLexico lexico;
    private String expresionRegular;
    private StringBuffer exprReg;
    private Alfabeto alfabeto;
    private AFN automata;
    //token
    private Token actual;
    private String errMsg = "";
    private boolean error = false;
    public final String operadores = "*+?|()";

    public Thompson(){

    }
    
    public Thompson(String expReg, String alfabeto) {
        this.expresionRegular = expReg;
        this.exprReg = new StringBuffer(expReg);
        this.alfabeto = new Alfabeto(alfabeto);
        //this.lexico = new AnalizadorLexico(expReg, alfabeto);
        try {
            this.actual = sgteCaracter();
        } catch (Exception ex) {
            this.errMsg = "La Expresion no coincide con el Alfabeto";
            this.error = true;
        }
        automata = new AFN();
        automata.setTipo("AFN");
    }

    /*
     * Matchea el simbolo obtenido con el esperado. Lanza una Excepcion en caso
     * de fallo
     */
    private void Match(String simbolo) throws Exception {
        Token tok = new Token(simbolo);
        if ( ObtenerActual().compareTo(tok) == 0 ) {
            this.setActual(this.sgteCaracter());
        } else {
            //this.errMsg = "No se pudo consumir la entrada";
            throw new Exception("No se pudo consumir la entrada");

        }
    }

    /*
     * Obtiene del Analizador Lexico el siguiente token a evaluar
     */
    private Token sgteCaracter() throws Exception {
        /*Token sgte = null;
        sgte = this.lexico.siguienteToken();
        return sgte;*/
        Token sgte = null;
        //sgte = this.lexico.siguienteToken();

        String letra = "";
        String consumido = "";

        if (this.exprReg.length() > 0) {
            consumido = Character.toString( this.exprReg.charAt(0) );
            this.exprReg.deleteCharAt(0);
        }

        letra = consumido;
        //se ignora los espacios en blanco y los tabuladores
        if (letra.equalsIgnoreCase(" ") || letra.equalsIgnoreCase("\t")) {
            sgte = sgteCaracter();
        //si es un operador o alfabeto
        } else if (operadores.indexOf(letra) >= 0 || this.alfabeto.contiene(letra) || letra.length() == 0) {
            sgte = new Token(letra);
        //sino es un error
        } else {
            throw new Exception("Error con la Expresion");
        }
        return sgte;
    }

    /**
     * Metodo que se encarga del analisis sintactico de la expresion. Utiliza el
     * siguiente BNF para realizar la evaluacion:
     * So -> EXP1 OR
     * EXP1 -> EXP2 EXP1 | €
     * OR -> '|' EXP1 OR | €
     * EXP 2 -> EXP3 OPERACION
     * EXP3 -> PARENTESIS | alfabeto
     * OPERACION -> * | + | ? | €
     * PARENTESIS -> '(' So ')'
     *
     * @return El automata finito no determinista (AFN) de la expresion
     */
    public AFN traducir() {
        this.automata = this.So();
        if (!this.isHayErrores()) {
            if (actual.getTipo() != Token.TipoToken.FIN) {
                this.error = true;
                this.errMsg = "Aun hay entrada por analizar";
                return null;
            }
        } else {
            this.error = true;
            //this.errMsg = "La expresion contiene simbolos que no estan en el alfabeto";
            return null;
        }
        this.automata.setAlfabeto(this.alfabeto);
        this.automata.setExpresion(this.expresionRegular);
        return this.automata;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * So -> EXP1 OR
     */
    private AFN So() {
        AFN automataIzq = null;
        AFN automataDer;
        try {
            automataIzq = this.EXP1();
            automataDer = this.OR();
            if (automataDer != null) {
                thompson_or(automataIzq, automataDer); //automata equivalente a (r|s)
            }
        } catch (Exception ex) {
            this.error = true;
            this.errMsg = "Error en la generacion del AFN";
        }
        return automataIzq;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * OR -> '|' EXP1 OR | €
     */
    private AFN OR() throws Exception {
        try {
            Token or = new Token("|");

            if (actual.compareTo(or) == 0) {
                this.Match("|");
                return So();
            } else {
                return null;
            }
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("Se esperaba el simbolo '|'");
        }
    }

    /*
     * Metodo correspondiente a la Produccion:
     * EXP1 -> EXP2 EXP1 | €
     */
    private AFN EXP1() throws Exception {
        AFN automataIzq = this.EXP2();
        AFN automataDer = this.preEXP1();

        if (automataDer != null) {
            thompson_concat(automataIzq, automataDer); //automata equivalente a rs
        }

        return automataIzq;
    }

    private AFN EXP2() throws Exception {

        AFN a = EXP3();

        if (a != null) {
            char op = OPERACION();

            switch (op) {
                case '*':
                    thompsonCerraduraKleene(a); //automata equivalente a r*
                    break;
                case '+':
                    thompsonCerraduraPositiva(a); //automata equivalente a r+
                    break;
                case '?':
                    thompsonCeroUno(a); //automata equivalente a r?
                    break;
                case '€':
                    break;
            }
        }
        return a;
    }

    /*
     * Si el valor actual es un simbolo del alfabeto o el caracter '('. Se llama
     * a EXP1 sino se retorna null
     */
    private AFN preEXP1() throws Exception {

        String current = actual.getValor();
        AFN a = null;

        if ( (actual.getTipo() != Token.TipoToken.FIN) &&
             (this.alfabeto.contiene(current) || current.compareTo("(")==0)
           ) {
            a = this.EXP1();
        }

        return a;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * EXP3 -> PARENTESIS | alfabeto
     */
    private AFN EXP3() throws Exception {

        Token parentesisAbierto = new Token("(");

        if(actual.compareTo(parentesisAbierto) == 0) {
            return this.PARENTESIS();
        } else {
            return this.alfabeto();
        }
    }

    /*
     * Metodo correspondiente a la Produccion:
     * OPERACION -> * | + | ? | €
     */
    private char OPERACION() throws Exception {
        char operador = '€';

        if (actual.getValor().compareTo("") != 0) {
            operador = actual.getValor().charAt(0);

            switch (operador) {
                case '*':
                    this.Match("*");
                    break;
                case '+':
                    this.Match("+");
                    break;
                case '?':
                    this.Match("?");
                    break;
                default:
                    return '€';
            }
        }
        return operador;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * PARENTESIS -> '(' So ')'
     */
    private AFN PARENTESIS() throws Exception {
        try {
            this.Match("(");
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("No se pudo consumir '('");
        }

        AFN a = this.So();

        try {
            this.Match(")");
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("No se pudo consumir ')'");
        }

        return a;
    }

    /*
     * Crea el automata simple para cada entrada del simbolo del alfabeto
     */
    private AFN alfabeto() throws Exception {
        AFN automataSimple = null;
        try {
            if (actual.getTipo() != Token.TipoToken.FIN) {
                automataSimple = new AFN(actual.getValor());
                this.Match(actual.getValor());
            }
        } catch (Exception ex) {
            this.error = true;
            throw new Exception(ex.getMessage());
        }
        return automataSimple;
    }

    /**
     * Obtiene el Token evaluado
     * @return Token evaluado
     */
    public Token ObtenerActual() {
        return actual;
    }

    /**
     * Setea el Token a evaluar
     * @param act Token a setear
     */
    public void setActual(Token act) {
        this.actual = act;
    }

    /**
     * Retorna si hubo o no errores en el Analizador
     * @return true o false
     */
    public boolean isHayErrores() {
        return error;
    }

    /**
     * Obtiene el mensaje que se cargo al haber un error
     * @return El mensaje de error
     */
    public String getErrMsg() {
        return errMsg;
    }
    /**
     * Thompson para la operación "|"
     * @param A2 Automata a seguir
     */
    public void thompson_or(AFN A1, AFN A2){

        //Automata A1 = this;

        Estado final_A1 = A1.getFinales().getEstado(0);
        Estado final_A2 = A2.getFinales().getEstado(0);
        Estado inicial_A1 = A1.getInicial();
        Estado inicial_A2 = A2.getInicial();

        final_A1.setEstadofinal(false);
        final_A2.setEstadofinal(false);

        Estado estado_inicial = new Estado(0, true, false, false);
        Estado estado_final = new Estado(A1.getEstados().size()+A2.getEstados().size()+1, false, true, false);

        A1.getInicial().setEstadoinicial(false);
        A2.getInicial().setEstadoinicial(false);

        A1.renumerar(1);
        A2.renumerar(A1.getEstados().size()+1);

        estado_inicial.addEnlace(new Transicion(estado_inicial, inicial_A1, A1.empty));
        estado_inicial.addEnlace(new Transicion(estado_inicial, inicial_A2, A1.empty));
        final_A1.addEnlace( new Transicion( final_A1, estado_final, A1.empty) );
        final_A2.addEnlace( new Transicion( final_A2, estado_final, A1.empty) );

        Iterator it = A2.getEstados().getIterator();
        while(it.hasNext()){
            A1.getEstados().insertar((Estado)it.next());
        }
        A1.getEstados().insertar(estado_inicial);
        A1.getEstados().insertar(estado_final);

        A1.setInicial(estado_inicial);
        A1.getFinales().set(0, estado_final);
    }

   /**
     * Thompson para la operación de concatenación
     * @param A2 Automata siguiente al actual
     */
    public void thompson_concat(AFN A1, AFN A2){
        //Automata A1 = this;

        Estado final_A1   = A1.getFinales().getEstado(0);
        Estado inicial_A2 = A2.getInicial();

        inicial_A2.setEstadoinicial(false);
        final_A1.setEstadofinal(false);

        int a1_estado_final = A1.getEstados().size() - 1;
        A2.renumerar(a1_estado_final);

        Iterator <Transicion> enlaces_a2_inicio = inicial_A2.getEnlaces().getIterator();

        while(enlaces_a2_inicio.hasNext()){
            Transicion current = enlaces_a2_inicio.next();
            current.setOrigen(final_A1);
            final_A1.addEnlace(current);
        }

        Iterator <Estado> estados_a2 = A2.getEstados().getIterator();

        while(estados_a2.hasNext()){
            Estado est_a2 = estados_a2.next();
            Iterator <Transicion> enlaces = est_a2.getEnlaces().getIterator();
            while(enlaces.hasNext()){
                Transicion current = enlaces.next();
                Estado current_destino = current.getDestino();
                if (current_destino.getId() == inicial_A2.getId()) {
                    current.setDestino(final_A1);
                }
            }
            if(est_a2.getId() != inicial_A2.getId()){
                A1.getEstados().insertar(est_a2);
            }
        }
        A1.getFinales().set(0, A2.getFinales().getEstado(0));
    }

    /**
     * implementación de las operaciones de kleene (*), plus (+), cerouno(?)
     */
    public void thompson_common(AFN A1) {

        //Automata A1 = this;
        A1.renumerar(1);
        Estado estado_inicial = new Estado(0, true, false, false);
        Estado estado_final   = new Estado(A1.getEstados().size()+1, false, true, false);
        Estado ex_estado_inicial = A1.getInicial();
        Estado ex_estado_final   = A1.getFinales().getEstado(0);
        ex_estado_inicial.setEstadoinicial(false);
        ex_estado_final.setEstadofinal(false);
        estado_inicial.addEnlace(new Transicion(estado_inicial, ex_estado_inicial, A1.empty));
        ex_estado_final.addEnlace(new Transicion(ex_estado_final, estado_final, A1.empty));
        A1.setInicial(estado_inicial);
        A1.getFinales().set(0, estado_final);
        A1.getEstados().insertar(estado_inicial);
        A1.getEstados().insertar(estado_final);
    }

    /**
     * operación '?' sobre el automata actual.
     * se le agrega enlaces vacios al comienzo del mismo y al final, y un enlace
     * vacio entrel el estado de ini y el de fin para permitir el recorrido
     */
    public void thompsonCeroUno(AFN A1) {
        this.thompson_common(A1);
        A1.getInicial().addEnlace(new Transicion(A1.getInicial(), A1.getFinales().getEstado(0), A1.empty));
    }

    /**
     *
     */
    public void thompsonCerraduraPositiva(AFN A1) {
        Estado inicio_original = A1.getInicial();
        Estado fin_original    = A1.getFinales().getEstado(0);
        this.thompson_common(A1);
        fin_original.addEnlace(new Transicion(fin_original, inicio_original, A1.empty));
    }

    /**
     *
     */
    public void thompsonCerraduraKleene(AFN A1){
        Estado inicio_original = A1.getInicial();
        Estado fin_original    = A1.getFinales().get(0);
        this.thompson_common(A1);
        fin_original.addEnlace(new Transicion(fin_original, inicio_original, A1.empty));
        A1.getInicial().addEnlace(new Transicion(A1.getInicial(), A1.getFinales().getEstado(0), A1.empty));
    }

    public void setAutomata(AFN afn){
        this.automata = afn;
    }

    public AFN getAutomata(){
        return automata;
    }
}
