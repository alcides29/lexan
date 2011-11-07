package vista;

// GraphViz.java - a simple API to call dot from Java programs

/*$Id$*/
/**
 ******************************************************************************
 *                                                                            *
 *              (c) Copyright 2003 Laszlo Szathmary                           *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms of the GNU Lesser General Public License as published by   *
 * the Free Software Foundation; either version 2.1 of the License, or        *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful, but        *
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public    *
 * License for more details.                                                  *
 *                                                                            *
 * You should have received a copy of the GNU Lesser General Public License   *
 * along with this program; if not, write to the Free Software Foundation,    *
 * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                              *
 *                                                                            *
 ******************************************************************************
 * 
 * Modificado por:
 * Marco Alvarez
 * Sebastian Lena
 */

import java.io.*;

/**
 * <dl>
 * <dt>Purpose: GraphViz Java API
 * <dd>
 *
 * <dt>Description:
 * <dd> With this Java class you can simply call dot
 *      from your Java programs
 * <dt>Example usage:
 * <dd>
 * <pre>
 *    GraphViz gv = new GraphViz();
 *    gv.addln(gv.start_graph());
 *    gv.addln("A -> B;");
 *    gv.addln("A -> C;");
 *    gv.addln(gv.end_graph());
 *    System.out.println(gv.getDotSource());
 *
 *    File out = new File("out.gif");
 *    gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
 * </pre>
 * </dd>
 *
 * </dl>
 *
 * @version v0.1, 2003/12/04 (Decembre)
 * @author  Laszlo Szathmary (<a href="szathml@delfin.unideb.hu">szathml@delfin.unideb.hu</a>)
 */
public class GraphViz {
   /**
    * Where is your dot program located? It will be called externally.
    * Modificar para cada PC!
    */
   private static String DOT = "C:\\Program Files (x86)\\Graphviz 2.28\\bin\\dot.exe ";
   //private static String DOT = "/usr/bin/dot";

   /**
    * The source of the graph written in dot language.
    */
    private StringBuffer graph = new StringBuffer();

   /**
    * Constructor: creates a new GraphViz object that will contain
    * a graph.
    */
   public GraphViz() {
   }

   /**
    * Adds a string to the graph's source (with newline).
    */
   public void addln(String line) {
      graph.append(line+"\n");
   }

   /**
    * Returns the graph as an image in binary format.
    * @param dot_source Source of the graph to be drawn.
    * @return A byte array containing the image of the graph.
    */
   public byte[] getGraph(String dot_source) {
      File dot;
      byte[] img_stream = null;
   
      try {
         dot = writeDotSourceToFile(dot_source);
         if (dot != null) {
            img_stream = get_img_stream(dot);
            if (dot.delete() == false)
               System.err.println("Warning: "+dot.getAbsolutePath()+" could not be deleted!");
            return img_stream;
         }
         return null;
      } catch (java.io.IOException ioe) { return null; }
   }

   /**
    * It will call the external dot program, and return the image in
    * binary format.
    * @param dot Source of the graph (in dot language).
    * @return The image of the graph in .gif format.
    */
   private byte[] get_img_stream(File dot) {
        File img;
        byte[] img_stream = null;

        try {
            img = new File("automata.gif");
            Runtime rt = Runtime.getRuntime();
            String cmd = DOT + " " + dot.getName()+" -o" +img.getName() + " -Tgif ";
            Process p = rt.exec(cmd);
            p.waitFor();

            FileInputStream in = new FileInputStream(img.getAbsolutePath());
            img_stream = new byte[in.available()];
            in.read(img_stream);
            // Close it if we need to
            if( in != null ) in.close();

            if (img.delete() == false)
                System.err.println("Warning: "+img.getAbsolutePath()+" could not be deleted!");

        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        } catch (java.lang.InterruptedException ie) {
            System.err.println("Error: the execution of the external program was interrupted");
            ie.printStackTrace();
        }
        return img_stream;
   }

   /**
    * Writes the source of the graph in a file, and returns the written file
    * as a File object.
    * @param str Source of the graph (in dot language).
    * @return The file (as a File object) that contains the source of the graph.
    */
   private File writeDotSourceToFile(String str) throws java.io.IOException {
      File temp;
      try {
         temp = new File("graph.dot");
         FileWriter fout = new FileWriter(temp);
         fout.write(str);
         fout.close();
      }
      catch (Exception e) {
         System.err.println("Error: I/O error while writing the dot source to file!");
         return null;
      }
      return temp;
   }

   
   /**
    * Clase especifica para dibujar directamente en un grafico de salida a partir
    * de un String que contiene toda la definición del Grafo.
    * @param dotString
    * @param where
    */
   public byte[] dibujar(String dotString) {
        this.addln(dotString);        
        return this.getGraph(this.getDotSource());
   }

   /**
    * Returns the graph's source description in dot language.
    * @return Source of the graph in dot language.
    */
   public String getDotSource() {
      return graph.toString();
   }
}
