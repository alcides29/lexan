/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author marcos
 */
public class Transicion implements Comparable<Transicion> {

    private Estado origen;          //estado origen del enlace
    private Estado destino;         //estado destino del enlace
    private String etiqueta;        //la etiqueta, el simbolo del abecedario
    private boolean vacio;          //si es vacio, entonces no tiene etiqueta

    /**
     * Crea un nuevo enlace
     * @param origen estado inicial
     * @param destino estado final
     * @param label la etiqueta
     */
    public Transicion(Estado origen, Estado destino, String label) {
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = label;

        if (label.compareTo("€")==0) {
            this.vacio = true;
        } else {
            this.vacio = false;
        }
    }

    /**
     *
     * @return
     */
    public Estado getOrigen() {
        return origen;
    }

    /**
     *
     * @param origen
     */
    public void setOrigen(Estado origen) {
        this.origen = origen;
    }

    /**
     *
     * @return
     */
    public Estado getDestino() {
        return destino;
    }

    /**
     *
     * @param destino
     */
    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    /**
     *
     * @return
     */
    public String getEtiqueta() {
        return this.etiqueta;
    }

    /**
     *
     * @param label
     */
    public void setEtiqueta(String label) {
        this.etiqueta = label;
    }

    /**
     *
     * @param vacio
     */
    public void setVacio(boolean vacio) {
        this.vacio = vacio;
    }

    /**
     *
     * @return
     */
    public boolean isVacio() {
        return vacio;
    }

    public int compareTo(Transicion e) {

        Estado origi;
        Estado desti;
        String simbi;

        origi = e.getOrigen();
        desti = e.getDestino();
        simbi = e.getEtiqueta();
        if (origi == this.getOrigen()
                && desti == this.getDestino()
                && simbi.equals(this.getEtiqueta())
                ) {
            return 0;
        } else {
            return -1;
        }
    }


    public class ListaTransiciones extends ArrayList<Transicion> {
        private int id;                                     //id de la lista
        private HashMap<String, Integer> TablaEnlaces;      //tabla hash para la lista de enlaces, permite indexar para cada símbolo del alfabeto, el índice del array list
        private ArrayList<Transicion> vacios;                   //lista de enlaces cuyo label es el 'e'

        /**
         * Crea una nueva lista, setea la tabla y la lista de vacios
         */
        public ListaTransiciones(){
            this.TablaEnlaces = new HashMap<String, Integer>();
            this.vacios       = new ArrayList<Transicion>();
        }

        /**
         * Setter del identificador
         * @param id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Getter del identificador
         * @return
         */
        public int getId() {
            return this.id;
        }

        /**
         * Obtener la lista de enlaces
         * @return
         */
        public ArrayList<Transicion> getVacios() {
            return vacios;
        }

        /**
         * Obtenemos el enlace con id = index
         * @param index
         * @return
         */
        public Transicion getEnlace(int index) {
            return this.get(index);
        }

        /**
         * Obtenemos el Iterador sobre la lista
         * @return
         */
        public Iterator<Transicion> getIterator() {
            return this.iterator();
        }

        /**
         * Add un nuevo enlace e a la lista
         * @param e
         */
        public void insertar(Transicion e) {

            int     indexToInsert   = this.cantidad();
            String  simbolo         = e.getEtiqueta();

            this.add(e);

            if (e.isVacio()) {
                this.agregarEnlaceVacio(e);
            } else {
                this.TablaEnlaces.put(simbolo, indexToInsert);
            }
        }

        /**
         * Insertar un nuevo enlace en la posicion indicada por index
         * @param e
         * @param index
         */
        public void insertarAt(Transicion e, int index) {

            int     indexToInsert   = index;
            String  simbolo         = e.getEtiqueta();

            this.add(index,e);

            if (e.isVacio()) {
                this.agregarEnlaceVacio(e);
            } else {
                this.TablaEnlaces.put(simbolo, indexToInsert);
            }
        }

        /**
         * Obtenemos el enlace cuya etiqueta = symbol
         * @param symbol
         * @return
         */
        public Transicion getEnlaceSimbolo(String symbol) {
            Integer index = this.TablaEnlaces.get(symbol);
            Transicion result = null;

            if (index != null) {
                result = this.get(index);
            }
            return result;
        }

        /**
         * Concatenar una lista enlaces a esta al final.
         * @param l
         */
        public void insertarListaEnlaces(ListaTransiciones l) {
            Iterator <Transicion> i = l.getIterator();
            Transicion current;

            while(i.hasNext()) {
                current = i.next();
                this.insertar(current);
            }
        }
        /**
         * add un enlace cuya etiqueta = vacio
         * @param e
         */
        private void agregarEnlaceVacio(Transicion e) {
            this.getVacios().add(e);
        }

        /**
         * Borra el enlace e
         * @param e
         */
        public void borrar(Transicion e) {

            String simbolo = e.getEtiqueta();

            this.remove(e);

            if (e.isVacio()) {
                this.getVacios().remove(e);
            } else {
                TablaEnlaces.remove(simbolo);
            }
        }

        /**
         * Retorna la cantidad de enlaces de la lista
         * @return
         */
        public int cantidad() {
            return this.size();
        }

        /**
         * Si la lista contiene el enlace e
         * @param e
         * @return
         */
        @SuppressWarnings("element-type-mismatch")
        public boolean contiene(Estado e) {
            if (this.contains(e)) {
                    return true;
            }
            return false;
        }

        /**
         * Para comparar dos listas de enlaces
         * @param o
         * @return
         */
        public int compareTo(Object o) {

            int result = -1;
            ListaTransiciones otro = (ListaTransiciones) o;

            if (this.cantidad() == otro.cantidad()) {
                for (int i = 0; i < this.cantidad(); i++) {
                    Transicion a = this.getEnlace(i);
                    Transicion b = otro.getEnlace(i);
                    if (a.compareTo(b) != 0) {
                        return -1;
                    }
                }
                result = 0;
            }
            return result;
        }
    }

}
