/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

/**
 *
 * @author marcos
 */
public class AnalizadorLexico {

    private StringBuffer expReg; //variable donde se almacena la expresion regular a consumir
    private Alfabeto alfabeto; //conjunto de simbolos aceptados por la expresion
    private String operadores; //onjunto de operadores * + ? ( )

    /**
     * Constructor de la clase, carga la expresion regular y el alfabeto dado a las
     * variables conrrespondiente
     *
     * @param expresion String con la Expresion Regular
     * @param alfabeto String con el conjunto de simbolos del alfabeto
     */
    public AnalizadorLexico(String expresion, String alfabeto) {
        this.expReg = new StringBuffer(expresion);
        this.alfabeto = new Alfabeto(alfabeto);
        this.operadores = "*+?|()";
    }

    /**
     * Constructor de la clase, carga la expresion regular y el alfabeto dado a las
     * variables conrrespondiente
     *
     * @param expresion String con la Expresion Regular
     * @param alfabeto Clase Alfabeto ya creada
     */
    public AnalizadorLexico(String expresion, Alfabeto alfabeto) {
        this.expReg = new StringBuffer(expresion);
        this.alfabeto = alfabeto;
        this.operadores = "*+?|()";
    }

    /**
     * Comprueba el caracter tomado con el alfabeto
     * @return El caracter a evaluar
     * @throws Exception Excepcion en caso de haber error
     */
    public Token siguienteToken() throws Exception {

        String letra = consumir();
        Token sgte;
        //se ignora los espacios en blanco y los tabuladores
        if (letra.equalsIgnoreCase(" ") || letra.equalsIgnoreCase("\t")) {
            sgte = siguienteToken();
        //si es un operador o alfabeto
        } else if (this.operadores.indexOf(letra) >= 0 || this.alfabeto.contiene(letra) || letra.length() == 0) {
            sgte = new Token(letra);
        //sino es un error
        } else {
            throw new Exception("Error con la Expresion");
        }
        return sgte;
    }

    /*
     * Obtiene el primer caracter de la expresion y lo borra.
     */
    private String consumir() {

        String consumido = "";

        if (this.expReg.length() > 0) {
            consumido = Character.toString( this.expReg.charAt(0) );
            this.expReg.deleteCharAt(0);
        }

        return consumido;
    }
}

