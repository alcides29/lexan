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
public class Alfabeto extends ArrayList<String> {

    /**
     * Constructor de la clase, toma un String y almacena cada caracter en el Alfabeto
     * @param simbolos Alfabeto a guardar
     */
    public Alfabeto(String simbolos) {
        for (int i = 0; i < simbolos.length(); i++) {
            String tmp = "" + simbolos.charAt(i);
            if (! this.contains(tmp)) {
                this.add(tmp);
            }
        }
        this.ordenar();
    }

    /**
     * Metodo para determinar si un simbolo esta en el alfabeto
     * @param simbolo Caracter a buscar dentro de Alfabeto
     * @return true en caso de exito, false sino
     */
    public boolean contiene(String simbolo) {
        if ( this.contains(simbolo) ) return true;
        return false;
    }

    /**
     * Metodo que ordena el alfabeto
     */
    private void ordenar() {
        String a[] = new String[1];
        a = this.toArray(a);
        java.util.Arrays.sort(a);

        this.removeAll(this);
        for(int i = 0; i < a.length; i++) {
            this.add(a[i]);
        }
    }

    /**
     * Obtiene el tamaño del Alfabeto
     * @return Cantidad de simbolos del Alfabeto
     */
    public int ObtenerTamano() {
        return this.size();
    }

    /**
     * Obtiene un manejador de iteracion sobre el Alfabeto
     * @return el Iterador
     */
    public Iterator ObtenerIterador() {
        return this.iterator();
    }
}

