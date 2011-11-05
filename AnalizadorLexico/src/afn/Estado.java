/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author marcos
 */
public class Estado {
    private int id;
    private ListaTransiciones enlaces;

    private boolean estadoinicial;
    private boolean estadofinal;
    private boolean visitado;

    /**
     * Crea un nuevo estado
     * @param id
     * @param esInicial
     * @param esFinal
     * @param visitado
     */
    public Estado(int id, boolean esInicial, boolean esFinal, boolean visitado) {
        this.id         = id;
        this.estadoinicial  = esInicial;
        this.estadofinal    = esFinal;
        this.visitado   = visitado;
        this.enlaces = new ListaTransiciones();
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public ListaTransiciones getEnlaces() {
        return enlaces;
    }

    /**
     *
     * @return
     */
    public boolean isEstadofinal() {
        return estadofinal;
    }

    /**
     *
     * @return
     */
    public boolean isEstadoinicial() {
        return estadoinicial;
    }

    /**
     *
     * @return
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param estadofinal
     */
    public void setEstadofinal(boolean estadofinal) {
        this.estadofinal = estadofinal;
    }

    /**
     *
     * @param estadoinicial
     */
    public void setEstadoinicial(boolean estadoinicial) {
        this.estadoinicial = estadoinicial;
    }

    /**
     *
     * @param visitado
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     *
     * @param e
     */
    public void addEnlace(Transicion e) {
        enlaces.insertar(e);
    }

    /**
     * Retorna el estado destino, se realiza una busqueda entre todos sus enlaces
     * @param a token de transicion (el que define la transicion).
     * @return estado destino al que va desde este estado por el token a
     */
    public Estado estadoDestino(Token a){
        return estadoDestinoString(a.getValor());
    }

    /**
     * Idem al anterior, solo que aqui recibimos una etiqueta
     * @param a
     * @return
     */
    public Estado estadoDestinoString(String a){
        for(Transicion x: enlaces){
            if(x.getEtiqueta().compareTo(a)== 0){
                return x.getDestino();
            }
        }
        return null;
    }

    /**
     * retorna el estado asociado al simbolo dado
     * @param simbolo
     * @return
     */
    public Estado getDestinoFromHash(String simbolo) {
        Transicion link = this.getEnlaceSimboloFromHash(simbolo);
        Estado result = null;

        if (link != null) {
            result =link.getDestino();
        }
        return result;
    }

    /**
     *
     * @param simbolo
     * @return
     */
    public Transicion getEnlaceSimboloFromHash(String simbolo) {
        return this.enlaces.getEnlaceSimbolo(simbolo);
    }

    /**
     *
     * @return
     */
    public ArrayList<Transicion> getEnlacesVacios() {
        return this.enlaces.getVacios();
    }


    /**
     *
     * @param e
     */
    public void eliminarEnlace(Transicion e){
        this.enlaces.borrar(e);
    }

    /**
     *
     * @return
     */
    public boolean esEstadoMuerto(){
        if(isEstadofinal()){
            return false;
        }

        boolean esMuerto = true;
        for(Transicion e: this.enlaces){
            if(e.getDestino().getId() != this.getId()){
                esMuerto = false;
            }
        }
        return esMuerto;
    }

    public int compareTo(Estado e) {
        if (this.getId() == e.getId()) {
            return 0;
        } else if (this.getId() > e.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String result = ""+id;
        if (this.isEstadofinal()) {
            result = result + "(fin)";
        }

        if (this.isEstadoinicial()){
            result = result + "(ini)";
        }
        return result;
    }    
}
