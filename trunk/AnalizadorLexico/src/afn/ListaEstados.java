/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author marcos
 */
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
