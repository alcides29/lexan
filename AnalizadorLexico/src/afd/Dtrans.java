/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afd;

import afn.AFN;
import afn.Estado;
import afn.ListaEstados;
import afn.Token;
import afn.Transicion;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author marcos
 */
public class Dtrans {
    Hashtable dtrans;

    /**
     *
     */
    public Dtrans() {
        dtrans = new Hashtable();
    }

    /**
     *
     * @param clave
     * @return
     */
    public ListaEstados obtenerValor(DtransClave clave){
        return obtenerValor(clave.getIndiceEstados(), clave.getIndiceToken());
    }

    /**
     *
     * @param lista
     * @param token
     * @return
     */
    public ListaEstados obtenerValor(ListaEstados lista, Token token){
        DtransClave comparar = new DtransClave(lista, token);
        Enumeration en = dtrans.keys();
        DtransClave clave;
        while(en.hasMoreElements()){
            clave = (DtransClave)en.nextElement();
            if(clave.compareTo(comparar) == 0){
                return (ListaEstados) dtrans.get(clave);
            }
        }
        return null;
    }


    /**
     *
     * @param clave
     * @param valor
     */
    public void setValor(DtransClave clave, ListaEstados valor){
        dtrans.put(clave, valor);
    }

    /**
     *
     * @return
     */
    public AFN convertAutomata(){
        AFN a = new AFN();

        Enumeration en = dtrans.keys();
        while(en.hasMoreElements()){
            DtransClave clave = (DtransClave) en.nextElement();
            ListaEstados valor = obtenerValor(clave);

            int id_new_origen = clave.getIndiceEstados().getId();
            int id_new_dest = valor.getId();
            Estado st_new_origen, st_new_dest;

            try{
                 st_new_origen = a.getEstadoById(id_new_origen);
            }catch(Exception ex){
                st_new_origen = new Estado(id_new_origen,
                                            clave.getIndiceEstados().contieneInicial(),
                                            clave.getIndiceEstados().contieneFinal(),
                                            false);
                a.addEstado(st_new_origen);
                if(clave.getIndiceEstados().contieneInicial()){
                    a.setInicial(st_new_origen);
                }
                if(clave.getIndiceEstados().contieneFinal()){
                    a.getFinales().insertar(st_new_origen);
                }

            }


            try{
                 st_new_dest = a.getEstadoById(id_new_dest);
            }catch(Exception ex){
                st_new_dest = new Estado(id_new_dest,
                                        valor.contieneInicial(),
                                        valor.contieneFinal(),
                                        false);
                a.addEstado(st_new_dest);
                if(valor.contieneInicial()){
                    a.setInicial(st_new_dest);
                }
                if(valor.contieneFinal()){
                    a.getFinales().insertar(st_new_dest);
                }
            }

            Transicion enlace_new = new Transicion( st_new_origen, st_new_dest,
                                            clave.getIndiceToken().getValor());

            st_new_origen.addEnlace(enlace_new);
        }

        return a;
    }
}

