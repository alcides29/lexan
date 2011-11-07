/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package afdmin;

import afd.AFD;
import afn.AFN;
import afn.Estado;
import afn.ListaEstados;
import afn.Transicion;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author marcos
 */
public class Minimizacion {

    AFN afd;

    /**
     *
     * @param automata
     */
    public Minimizacion(AFN automata){
        this.afd = automata;
    }

    /**
     * Algoritmo de minimizacion, explicado en el libro del dragon
     * cuidado, tiene mucho fuego!
     * @return
     * @throws Exception
     */
    public AFD minimizar() throws Exception{
       ArrayList<ListaEstados> anterior = new ArrayList<ListaEstados>();
       ArrayList<ListaEstados> actual = new ArrayList<ListaEstados>();

       int numeroEstado = 0;
       ListaEstados noFinales = afd.getNoFinales();
       ListaEstados finales = afd.getFinales();

       if(noFinales != null && noFinales.cantidad() > 0){
            noFinales.setId(numeroEstado++);
            anterior.add(noFinales);
       }

       if(finales != null && finales.cantidad() > 0){
            finales.setId(numeroEstado++);
            anterior.add(finales);
       }

       boolean seguir = true;
       while(seguir){
           int cant = 0;
           for(ListaEstados cadaLista: anterior){
                Iterator it = separarGrupos(anterior, cadaLista);
                while(it != null && it.hasNext()){
                    ListaEstados list= (ListaEstados)it.next();
                    list.setId(cant++);
                    actual.add(list);
                }
           }

           if(anterior.size() == actual.size()){
               seguir = false;
           }else{
               anterior = actual;
               actual = new ArrayList<ListaEstados>();
           }
       }

       AFD afdMin = new AFD();
       Iterator it = actual.iterator();
       while(it.hasNext()){
            ListaEstados lest = (ListaEstados) it.next();
            Estado nuevo = new Estado(lest.getId() , false, false,false);

            try{
                lest.getEstadoInicial();
                nuevo.setEstadoinicial(true);
                afdMin.setInicial(nuevo);
            }catch(Exception ex){
                nuevo.setEstadoinicial(false);
            }

            if(lest.getEstadosFinales().cantidad() > 0){
                nuevo.setEstadofinal(true);
                afdMin.getFinales().insertar(nuevo);
            }else{
                nuevo.setEstadofinal(false);
            }
            afdMin.addEstado(nuevo);
       }

       it = actual.iterator();
       while(it.hasNext()){
            ListaEstados lest = (ListaEstados) it.next();
            Estado estado_afdm  = afdMin.getEstadoById(lest.getId());
            Estado representante = lest.get(0);

            Iterator itenlaces = representante.getEnlaces().getIterator();
            while (itenlaces.hasNext()){
                Transicion e = (Transicion) itenlaces.next();
                ListaEstados lista_destino = enqueLista(actual, e.getDestino());
                Estado est_destino = afdMin.getEstadoById(lista_destino.getId());
                Transicion nuevo_enlace = new Transicion(estado_afdm, est_destino, e.getEtiqueta());
                estado_afdm.addEnlace(nuevo_enlace);
            }
       }
       return afdMin;
   }

   /**
    * Retorna el iterador de la separacion de todas las lista con la lista,
    * pasados como parametros
    * @param todas
    * @param lista
    * @return
    */
   public Iterator separarGrupos(ArrayList<ListaEstados> todas, ListaEstados lista) {
        Hashtable listasNuevas = new Hashtable();
        for(Estado estado : lista){
            String claveSimbolos = "";
            String claveEstados = "";

            for(Transicion enlace : estado.getEnlaces()){
                Estado dest = enlace.getDestino();
                ListaEstados tmp = enqueLista(todas, dest);
                claveSimbolos += enlace.getEtiqueta().trim();
                claveEstados += tmp.getId();

            }
            String clave = generarClaveHash(claveSimbolos, claveEstados);
            if(listasNuevas.containsKey(clave)){
                ((ListaEstados)listasNuevas.get(clave)).insertar(estado);
            }else{
                ListaEstados nueva = new ListaEstados();
                nueva.insertar(estado);
                listasNuevas.put(clave, nueva);
            }
        }
        return listasNuevas.values().iterator();
   }


   /**
    * Retorna el hash para el simbolo y el estado pasados como parametros
    * @param simbolos
    * @param estados
    * @return
    */
   public String generarClaveHash(String simbolos, String estados ){
       String cadenaFinal = "";

        char est[] = estados.toCharArray();
        char c[] = simbolos.toCharArray();
        boolean hayCambios = true;
        for (int i = 0; hayCambios ; i++) {
            hayCambios = false;
            for (int j = 0; j < c.length - 1; j++) {
              if (c[j] > c[j + 1]) {
                  char tmp = c[j+1];
                  c[j+1] = c[j];
                  c[j] = tmp;
                  char tmpEst = est[j+1];
                  est[j+1] = est[j];
                  est[j] = tmpEst;
                  hayCambios = true;
              }
            }
        }
       cadenaFinal = String.copyValueOf(c) + String.copyValueOf(est);
       return cadenaFinal;
   }

   /**
    * Para obtener las listas que se encuentran en un estado dado por Estado
    * @param listas
    * @param estado
    * @return
    */
   public ListaEstados enqueLista(ArrayList<ListaEstados> listas, Estado estado){
        for(ListaEstados lista : listas){
            try{
                lista.getEstadoById(estado.getId());
                return lista;
            }catch(Exception ex){}
        }
        return null;
   }
}

