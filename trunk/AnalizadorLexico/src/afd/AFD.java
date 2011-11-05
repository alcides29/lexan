/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afd;

import afn.AFN;

/**
 *
 * @author marcos
 */
public class AFD extends AFN{
    public AFD() {
        super();
    }

    /**
     * Constructor simple.
     * Dos estados y un solo enlace a través del simbolo especificado.
     * @param simbolo expresion regular simple formada por un solo caracter
     */
    public AFD(String simbolo) {
        super(simbolo);
    }

    /**
     * Constructor para automatas simples, se le especifica el tipo de automata
     * @param simbolo Expresion regular simple
     * @param tipo Tipo de automata en construcción
     */
    public AFD(String simbolo, String tipo) {
        super(simbolo, tipo);
    }
}
