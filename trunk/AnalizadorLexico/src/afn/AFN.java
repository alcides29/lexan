/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author marcos
 */
public class AFN {

    /**
     * la expresion regular
     */
    private String regex;
    /**
     * el alfabeto que se utiliza
     */
    private ArrayList<String> alpha;
    /**
     * para representar epsilon, vacio
     */
    public String empty = "ɛ";
    /**
     * variable auxiliar utilizada para indicar el nivel
     */
    private int level = 0;
    /**
     * estados que formaran al automata en cuestion
     */
    private ListaEstados estados;
    /**
     * el estado inicial del automata
     */
    private Estado inicial;
    /**
     * los estados finales
     */
    private ListaEstados finales;
    /**
     * el id del tipo de automata que corresponde, AFN, AFD, AFDMin
     */
    private String tipo;

    /**
     * Constructor
     */
    public AFN() {
        this.estados = new ListaEstados();
        this.finales = new ListaEstados();
    }

    /**
     * Constructor simple.
     * Dos estados y un solo enlace a través del simbolo especificado.
     * @param simbolo expresion regular simple formada por un solo caracter
     */
    public AFN(String simbolo) {
        this.estados = new ListaEstados();

        Estado e1 = new Estado(0, true, false, false);
        Estado e2 = new Estado(1, false, true, false);
        Transicion enlace = new Transicion(e1, e2, simbolo);
        e1.addEnlace(enlace);

        this.estados.insertar(e1);
        this.estados.insertar(e2);

        this.inicial = e1;
        this.finales = new ListaEstados();
        this.finales.add(e2);
    }

    /**
     * Constructor para automatas simples, se le especifica el tipo de automata
     * @param simbolo Expresion regular simple
     * @param tipo Tipo de automata en construcción
     */
    public AFN(String simbolo, String tipo) {
        this(simbolo);
        this.tipo = tipo;
    }

    /**
     * Obtener el estado el cual esta referenciado por index
     * @param index
     * @return el Estado dado por index
     */
    public Estado getEstado(int index){
        return this.estados.getEstado(index);
    }

    /**
     * obtener la lista de estados del automata
     * @return Lista de estados
     */
    public ListaEstados getEstados() {
        return this.estados;
    }

    /**
     * Obtener un estado identificado por el id
     * @param id
     * @return el Estado con id = "id"
     */
    public Estado getEstadoById(int id) {
        return this.estados.getEstadoById(id);
    }

    /**
     * Obtener lista de estados que son finales
     * @return Lista de estados marcados como finales
     */
    public ListaEstados getFinales() {
        return finales;
    }

    /**
     * Obtener lista de estados que no son finales
     * @return Lista de estados no finales
     */
    public ListaEstados getNoFinales(){
        ListaEstados lista = new ListaEstados();
        for(Estado x : estados){
            if(!x.isEstadofinal()){
                lista.insertar(x);
            }
        }
        return lista;
    }

    /**
     * Obtiene el estado inicial (marcado como tal)
     * @return Estado inicial
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     * Marca un estado dado por ini como inicial
     * @param ini
     */
    public void setInicial(Estado ini) {
        this.inicial = ini;
    }

    /**
     * Se obtiene el abc
     * @return el abc (abecedario)
     */
    public ArrayList<String> getAlpha() {
        return this.alpha;
    }

    /**
     * Obtiene el abc que realmente se utiliza
     * @return abc realmente utilizado, se exclueyen aquellos caracteres que no
     * se utilizan
     */
    public int getAlphaUsed(){
        int alphaUsed = 0;
        ArrayList<String> alphaTemp = new ArrayList();
        String rex = this.regex;
        String c = "";

        for(int i = 0; i < rex.length(); i++){
            c += rex.charAt(i);

            if(!alphaTemp.contains(c)){
                if(this.alpha.contains(c)){
                    alphaTemp.add(c);
                    alphaUsed++;
                }
            }
            c = "";
        }
        return alphaUsed;
    }

    /**
     * Obtiene la expresion regular
     * @return regex
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Setea el abc a uno dado por alpha
     * @param alpha
     */
    public void setAlfabeto(ArrayList<String> alpha) {
        this.alpha = alpha;
    }

    /**
     * Setea la expresion regular a una dada por regex
     * @param regex
     */
    public void setExpresion(String regex) {
        this.regex = regex;
    }

    /**
     * Se renumera los ids de los estados de los autoamatas en un incremento dado
     * @param incremento
     */
    public void renumerar(int incremento){
        Iterator it = this.estados.getIterator();
        while (it.hasNext()){
            Estado e = (Estado) it.next();
            e.setId(e.getId()+incremento);
        }

    }
    /**
     * Elimina un estado dado, se realiza una busqueda
     * @param e el estado a buscar para eliminar
     */
    private void eliminarEstado(Estado e){

        for(Estado est: this.estados){
            for(Transicion enlace: est.getEnlaces()){
                if( e.getId() != est.getId() && enlace.getDestino().getId() == e.getId()){
                        est.eliminarEnlace(enlace);
                }
            }
        }
    }

    /**
     * Elimina los estados que quedan sin enlaces
     */
    public void eliminar_estados_muertos(){
       for(Estado e : this.getEstados()){
           if(e.esEstadoMuerto()){
               eliminarEstado(e);
           }
       }
   }


    /**
     * Obtiene todos los enlaces de un automata
     * @return Lista de enlaces
     */
    public ListaTransiciones getEnlaces(){
        ListaTransiciones ret = new ListaTransiciones();
        for(Estado est: getEstados()){
            for(Transicion enlace: est.getEnlaces()){
                ret.add(enlace);
            }
        }

        return ret;
    }

    /**
     * Obtiene el tipo de automata (AFN, AFD, AFDMin)
     * @return el tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Setea el tipo de automata
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Adhiere un estado e
     * @param e
     */
    public void addEstado(Estado e){
        this.estados.insertar(e);
    }

    /**
     * Obtiene el nivel
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setea el nivel
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
}
