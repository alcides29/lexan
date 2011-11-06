/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import afd.AFD;
import afn.AFN;
import afn.Estado;
import afn.ListaEstados;
import afn.ListaTransiciones;
import afn.Transicion;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marcos
 */
public class TabladelAutomata extends AbstractTableModel {

    private AFN automata;
    private int cantidadColumnas = 0;
    private int cantidadFilas = 0;
    private Object [][] datos;
    private String [] nombresColumnas;
    private int cantidadRealColumnas;

    /**
     * Constructor de la Clase.
     * @param automata Automata para generar la Tabla
     */
    public TabladelAutomata(AFN automata) {
        this.automata = automata;
        
        if (automata != null) {

            this.cantidadColumnas = this.automata.getAlphaUsed()+1; // La primera columna es de las etiquetas de estado

            // Si es AFN, se debe tener entre los elementos del alfabeto al vacio
            if (true){//this.automata.getTipo() == TipoAutomata.AFN) {
                this.cantidadColumnas++;
                this.cantidadRealColumnas++;
            }

            this.cantidadFilas = this.automata.getEstados().size();
            this.nombresColumnas = new String[this.cantidadColumnas];
            this.datos = new Object[this.cantidadFilas][this.cantidadColumnas];
            this.cantidadRealColumnas = 0;

            //fixed titulo
            this.nombresColumnas[0] = "ESTADOS";
            
            // Si es AFN, se debe tener entre los elementos del alfabeto al vacio
            if (true){//this.automata.getTipo() == TipoAutomata.AFN) {
                this.nombresColumnas[1] = "€";
                this.nombresColumnas[0] = "ESTADOS";
            }

            this.loadTable();
        }
    }
    
    /**
     * Constructor principal de la clase 
     * @param col Cantidad de columnas de la tabla
     * @param fil Cantidad de filas de la tabla
     */
    public TabladelAutomata(int fil, int col) {
        this.cantidadFilas       = fil;
        this.cantidadColumnas    = col;
        this.nombresColumnas    = new String[col];
        this.datos           = new Object[fil][col];
    }
    
    /**
     * Obtener la cantidad de columnas de la tabla
     * @return Cantidad de columnas de la tabla
     */
    public int getColumnCount() {
        return this.cantidadColumnas;
    }

    /**
     * Obtener la cantidad de filas de la tabla.
     * @return Cantidad de filas de la tabla
     */
    public int getRowCount() {
        return this.cantidadFilas;
    }

    /**
     * Obtener el nombre de una de las columnas de la tabla
     * @param col Número de columna cuyo nombre desea obtenerse.
     * @return El nombre de la columna.
     */
    @Override
    public String getColumnName(int col) {
        return this.nombresColumnas[col];
    }
    
    /**
     * Establecer el nombre de una columna de la Tabla
     * @param col Número de columna de la Tabla cuyo nombre desea establecerse.
     * @param nombre Nombre de la columna.
     */
    public void setColumnName(int col, String nombre) {
        this.nombresColumnas[col] = nombre;
    }

    /**
     * Obtener un valor almacenado en la Tabla.
     * @param row Número de fila de la Tabla.
     * @param col Número de columna de la Tabla.
     * @return Objeto almacenado en las posiciones [row,col]
     */
    public Object getValueAt(int row, int col) {
        return this.datos[row][col];
    }
    
    /**
     * Establecer un valor en la Tabla.
     * @param value Valor a almacenar en la Tabla en la posici�n [row,col].
     * @param row Número de columna en la Tabla.
     * @param col Número de columna en la Tabla.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        this.datos[row][col] = value;
        this.fireTableCellUpdated(row, col);
    }

    /**
     * Determinar el renderizador por defecto para cada celda.
     * @param c Número de columna cuyo tipo de Clase se quiere conocer.
     * @return Class de la columna en cuesti�n.
     */
    @Override
    public Class getColumnClass(int c) {
        return this.getValueAt(0, c).getClass();
    }
    
    /**
     * Arreglar las posiciones de la Tabla donde no se estableció ningún valor
     * (tiene objetos null). Los objetos null se reemplazan por Strings Vacíos
     */
    public void arreglarObjetosNulos() {
        String vacio = " ";
        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {
                Object o = this.datos[i][j];
                if (o == null) {
                    this.setValueAt(vacio, i, j);
                }
            }
        }
    }

    private void loadTable() {
        
        // Recorremos el automata estado a estado y en cada paso, cargamos la 
        // tabla en el índice que corresponde a la columna y fila del par 
        // etiqueta, estado procesado
        for (Iterator<Estado> it = this.automata.getEstados().getIterator(); it.hasNext();) {
            
            Estado current = it.next();                     // Obtenemos el estadoa actual a procesar
            ListaTransiciones enlaces = current.getEnlaces();    // Obtenemos sus enlaces
            int rowEstado = current.getId();                // La fila del estado es igual a su id
            
            String estadoLabel = rowEstado+"";
            if (current.isEstadoinicial()) {
                estadoLabel+="(ini)";
            }
            
            if (current.isEstadofinal()) {
                estadoLabel+="(fin)";
            }
            
            this.setValueAt(estadoLabel,rowEstado, 0);
            
            // Iteramos sobre los enlaces para agregar los destinos en las celdas
            // adecuadas de la matriz
            for (Iterator<Transicion> ite = enlaces.getIterator(); ite.hasNext();) {
                
                Transicion currentLink = ite.next();            // enlace actual a procesar
                String symbol = currentLink.getEtiqueta();  // simbolo del enlace                
                int indexCol = this.findColumn(symbol);     // obtenemos la columna de la etiqueta
                
                // Si la columna obtenida es -1, todavía no se cargó 
                // esta etiqueta al encabezado
                if (indexCol < 0) {                    
                   indexCol = this.cantidadRealColumnas+1;
                   this.nombresColumnas[indexCol] = symbol;
                   this.cantidadRealColumnas++;
                   
                }
                
                Estado destino = currentLink.getDestino();  // obtenemos el destino de esta enlace
                
                // vemos si para para este estado,simbolo ya habían destinos asociados
                Object estados = this.getValueAt(rowEstado, indexCol);
                
                if (estados == null) {
                    estados = new ListaEstados();
                }
                
                ((ListaEstados) estados).add(destino);                       // agregamos el nuevo destino a la lista
                
                // Cargamos la lista de nuevo en la matriz de objetos                
                this.setValueAt(estados,rowEstado, indexCol);
            }            
        }        
    }    

}

