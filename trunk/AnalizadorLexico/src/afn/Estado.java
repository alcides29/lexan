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
    
    public class ListaEstados extends ArrayList<Estado>{
        private int id;
        private boolean marcado;

        /**
         *
         * @param id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Obtenemos el identificador de la lista
         * @return
         */
        public int getId() {
            return this.id;
        }

        /**
         * Inserta el Estado e de la lista
         * @param e
         */
        public void insertar(Estado e) {
            this.add(e);
        }

        /**
         * quita el Estado e de la lista
         * @param e
         */
        public void borrar(Estado e) {
            this.remove(this.getEstadoById(e.getId()));
        }

        /**
         * Se obtiene el estado de una lista dado por su index, que tb es su id
         * @param index
         * @return
         */
        public Estado getEstado(int index){
            return this.get(index);
        }

        /**
         * Retorna el estado que tiene id = index
         * @param index
         * @return
         */
        public Estado getEstadoById(int index) {
            Iterator it = this.getIterator();
            while(it.hasNext()){
                Estado e = (Estado) it.next();
                if(e.getId() == index){
                    return e;
                }
            }
            throw new IndexOutOfBoundsException("En esta lista no existe un Estado con id = " + index);
        }

        /**
         * Retorna la cantidad de estados que contiene la lista
         * @return
         */
        public int cantidad() {
            return this.size();
        }

        /**
         * Obtenemos un iterador sobre la lista, modo practico
         * @return
         */
        public Iterator <Estado> getIterator() {
            return this.iterator();
        }

        /**
         * Marcamos como no visitados a todos los estados de la lista
         */
        public void resetVisitas() {
            for (int i = 0; i < cantidad(); i++) {
                getEstado(i).setVisitado(false);
            }
        }

        /**
         * Para ver si el Estado e pertenece a esta lista
         * @param e
         * @return
         */
        public boolean contiene(Estado e) {
            if (this.contains(e)) {
                    return true;
            }
            return false;
        }

        public Estado getEstadoInicial() throws Exception{
            int indice_ini = 0;
            int cant_iniciales = 0;
            for (int i = 0; i < cantidad(); i++) {
                if(getEstado(i).isEstadoinicial()){
                    indice_ini = i;
                    cant_iniciales++;
                }
            }
            if(cant_iniciales == 1){
                return getEstado(indice_ini);
            }else{
                throw new Exception("Error, Solo debe haber un estado incial, y en esta lista existen "+ cant_iniciales);
            }
        }

        public Estado getEstadoFinal() throws Exception{
            int indice_fin = 0;
            int cant_finales = 0;
            for (int i = 0; i < cantidad(); i++) {
                if(getEstado(i).isEstadofinal()){
                    indice_fin = i;
                    cant_finales++;
                }
            }
            if(cant_finales == 1){
                return getEstado(indice_fin);
            }else{
                throw new Exception("Este metodo se usa cuando existe un solo " +
                        "estado final y en esta lista existen " + cant_finales +
                        ". Utilize el metodo apropiado para obtener todos los estados finales");
            }
        }


        public ListaEstados getEstadosFinales() throws Exception{
            ListaEstados nuevaLista = new ListaEstados();
            for (int i = 0; i < cantidad(); i++) {
                if(getEstado(i).isEstadofinal()){
                    nuevaLista.insertar(getEstado(i));
                }
            }
            return nuevaLista;
        }

        public boolean contieneInicial(){
            Estado ini = null;
            try{
                ini = getEstadoInicial();
                return true;
            }catch (Exception ex){
                return false;
            }
        }

        public boolean contieneFinal() {
            ListaEstados fin;
            try {
                fin = getEstadosFinales();
            } catch (Exception ex) {
                return false;
            }

            if(fin.cantidad() > 0){
                return true;
            }else{
                return false;
            }
        }

        public void ordenar() {

            Estado a[] = new Estado[1];

            a = this.toArray(a);
            Comparator<Estado> c = null;

            Arrays.sort(a, c);

            this.removeAll(this);

            for(int i = 0; i < a.length; i++) {
                this.add(a[i]);
            }
        }

        public int compareTo(Object o) {

            int result = -1;
            ListaEstados otro = (ListaEstados) o;

            otro.ordenar();
            this.ordenar();
            if (this.cantidad() == otro.cantidad()) {
                for (int i = 0; i < this.cantidad(); i++) {
                    Estado a = this.getEstado(i);
                    try{
                        otro.getEstadoById(a.getId());
                    }catch(Exception ex){
                        return -1;
                    }
                }
                result = 0;
            }
            return result;
        }

        public boolean isMarcado() {
            return this.marcado;
        }

        public void setMarcado(boolean marcado) {
            this.marcado = marcado;
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
