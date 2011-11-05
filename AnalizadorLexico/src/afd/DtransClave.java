/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afd;

import afn.ListaEstados;
import afn.Token;

/**
 *
 * @author marcos
 */
public class DtransClave {
 /**
     * Filas de la tabla indicadas por una lista de estados
     */
    private ListaEstados indiceEstados;
    /**
     * Columna de la tabla, indicadas por un token del lenguaje
     */
    private Token indiceToken;

    /**
     * Para crear una nueva DtransClave
     * @param list lista de estados a los que se realizaria una transicion
     * @param tok el token que produce esa transicion
     */
    public DtransClave(ListaEstados list, Token tok) {
        this.indiceEstados = list;
        this.indiceToken = tok;
    }

    /**
     * Obtiene la lista de indices estados
     * @return lista de estados
     */
    public ListaEstados getIndiceEstados() {
        return this.indiceEstados;
    }

    /**
     * Setea los indices estados
     * @param indiceEstados
     */
    public void setIndiceEstados(ListaEstados indiceEstados) {
        this.indiceEstados = indiceEstados;
    }

    /**
     * Retorna el token especificado para esas transiciones
     * @return
     */
    public Token getIndiceToken() {
        return this.indiceToken;
    }

    /**
     * Setea el token
     * @param indiceToken
     */
    public void setIndiceToken(Token indiceToken) {
        this.indiceToken = indiceToken;
    }

    /**
     * Compara claves (2) de Dtrans
     * @param otro
     * @return
     */
    public int compareTo(Object otro){
        DtransClave o = (DtransClave) otro;
        if(indiceToken.getValor().compareTo(o.getIndiceToken().getValor()) == 0) {
            if(indiceEstados.compareTo(o.getIndiceEstados()) == 0){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
}
