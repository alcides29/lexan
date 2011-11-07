/*
 * AnalizadorLexicoView.java
 */

package vista;

import afn.AFN;
import afn.AnalizadorLexico;
import afn.Thompson;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * The application's main frame.
 */
public class AnalizadorLexicoView extends FrameView {

    public AnalizadorLexicoView(SingleFrameApplication app) {
        super(app);
        initComponents();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AnalizadorLexicoApp.getApplication().getMainFrame();
            aboutBox = new AnalizadorLexicoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AnalizadorLexicoApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        expRePanel = new javax.swing.JPanel();
        alfabetoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        alfabetoTextField = new javax.swing.JTextField();
        alfabetoPredefinido = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        expRegPanel = new javax.swing.JPanel();
        expRegLabel = new javax.swing.JLabel();
        expRegTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cadEntradaPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cadenaTextField = new javax.swing.JTextField();
        validarCadenaButton = new javax.swing.JButton();
        validarEntrada = new javax.swing.JButton();
        afnPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAFN = new javax.swing.JTable();
        afnPasoAPasoButton = new javax.swing.JButton();
        afnPasoAPasoLabel = new javax.swing.JLabel();
        afdPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        afdminPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        salirMenu = new javax.swing.JMenu();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPane1.setName("exprRegPanel"); // NOI18N

        expRePanel.setName("expRePanel"); // NOI18N

        alfabetoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        alfabetoPanel.setName("alfabetoPane"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(vista.AnalizadorLexicoApp.class).getContext().getResourceMap(AnalizadorLexicoView.class);
        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        alfabetoPanel.add(jLabel2);

        alfabetoTextField.setText(resourceMap.getString("alfabTextField.text")); // NOI18N
        alfabetoTextField.setName("alfabTextField"); // NOI18N
        alfabetoTextField.setPreferredSize(new java.awt.Dimension(350, 20));
        alfabetoPanel.add(alfabetoTextField);

        alfabetoPredefinido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Letras [a-z]", "Digitos [0-9]" }));
        alfabetoPredefinido.setName("alfabetoPredefinido"); // NOI18N
        alfabetoPredefinido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alfabetoPredefinidoActionPerformed(evt);
            }
        });
        alfabetoPanel.add(alfabetoPredefinido);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        alfabetoPanel.add(jLabel4);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        expRegPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        expRegPanel.setName("expRegPanel"); // NOI18N

        expRegLabel.setFont(resourceMap.getFont("expRegLabel.font")); // NOI18N
        expRegLabel.setText(resourceMap.getString("expRegLabel.text")); // NOI18N
        expRegLabel.setName("expRegLabel"); // NOI18N

        expRegTextField.setText(resourceMap.getString("exprRegTextField.text")); // NOI18N
        expRegTextField.setName("exprRegTextField"); // NOI18N

        javax.swing.GroupLayout expRegPanelLayout = new javax.swing.GroupLayout(expRegPanel);
        expRegPanel.setLayout(expRegPanelLayout);
        expRegPanelLayout.setHorizontalGroup(
            expRegPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, expRegPanelLayout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(expRegLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expRegTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        expRegPanelLayout.setVerticalGroup(
            expRegPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expRegPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(expRegPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expRegLabel)
                    .addComponent(expRegTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        cadEntradaPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cadEntradaPanel.setFont(resourceMap.getFont("cadEntradaPanel.font")); // NOI18N
        cadEntradaPanel.setName("cadEntradaPanel"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        cadenaTextField.setText(resourceMap.getString("cadenaTextField.text")); // NOI18N
        cadenaTextField.setName("cadenaTextField"); // NOI18N

        validarCadenaButton.setText(resourceMap.getString("validarCadenaButton.text")); // NOI18N
        validarCadenaButton.setName("validarCadenaButton"); // NOI18N
        validarCadenaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarCadenaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cadEntradaPanelLayout = new javax.swing.GroupLayout(cadEntradaPanel);
        cadEntradaPanel.setLayout(cadEntradaPanelLayout);
        cadEntradaPanelLayout.setHorizontalGroup(
            cadEntradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadEntradaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadEntradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validarCadenaButton)
                    .addGroup(cadEntradaPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cadenaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        cadEntradaPanelLayout.setVerticalGroup(
            cadEntradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadEntradaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadEntradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cadenaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validarCadenaButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        validarEntrada.setText(resourceMap.getString("validarEntrada.text")); // NOI18N
        validarEntrada.setName("validarEntrada"); // NOI18N
        validarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout expRePanelLayout = new javax.swing.GroupLayout(expRePanel);
        expRePanel.setLayout(expRePanelLayout);
        expRePanelLayout.setHorizontalGroup(
            expRePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expRePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(expRePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(expRePanelLayout.createSequentialGroup()
                        .addGroup(expRePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(cadEntradaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(expRegPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alfabetoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, expRePanelLayout.createSequentialGroup()
                        .addComponent(validarEntrada)
                        .addGap(107, 107, 107))))
        );
        expRePanelLayout.setVerticalGroup(
            expRePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expRePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alfabetoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(expRegPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validarEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cadEntradaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        jTabbedPane1.addTab(resourceMap.getString("expRePanel.TabConstraints.tabTitle"), expRePanel); // NOI18N

        afnPanel.setName("afnPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaAFN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Estados", "ɛ"
            }
        ));
        tablaAFN.setName("tablaAFN"); // NOI18N
        jScrollPane1.setViewportView(tablaAFN);
        tablaAFN.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaAFN.columnModel.title0")); // NOI18N
        tablaAFN.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaAFN.columnModel.title1")); // NOI18N

        afnPasoAPasoButton.setText(resourceMap.getString("afnPasoAPasoButton.text")); // NOI18N
        afnPasoAPasoButton.setEnabled(false);
        afnPasoAPasoButton.setName("afnPasoAPasoButton"); // NOI18N

        afnPasoAPasoLabel.setFont(resourceMap.getFont("afnPasoAPasoLabel.font")); // NOI18N
        afnPasoAPasoLabel.setText(resourceMap.getString("afnPasoAPasoLabel.text")); // NOI18N
        afnPasoAPasoLabel.setName("afnPasoAPasoLabel"); // NOI18N

        javax.swing.GroupLayout afnPanelLayout = new javax.swing.GroupLayout(afnPanel);
        afnPanel.setLayout(afnPanelLayout);
        afnPanelLayout.setHorizontalGroup(
            afnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afnPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(afnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(afnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(afnPasoAPasoButton)
                        .addComponent(afnPasoAPasoLabel)))
                .addGap(27, 27, 27))
        );
        afnPanelLayout.setVerticalGroup(
            afnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afnPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(afnPasoAPasoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(afnPasoAPasoButton)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("afnPanel.TabConstraints.tabTitle"), afnPanel); // NOI18N

        afdPanel.setName("afdPanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setName("jTable2"); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout afdPanelLayout = new javax.swing.GroupLayout(afdPanel);
        afdPanel.setLayout(afdPanelLayout);
        afdPanelLayout.setHorizontalGroup(
            afdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, afdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );
        afdPanelLayout.setVerticalGroup(
            afdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afdPanelLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("afdPanel.TabConstraints.tabTitle"), afdPanel); // NOI18N

        afdminPanel.setName("afdminPanel"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.setName("jTable3"); // NOI18N
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout afdminPanelLayout = new javax.swing.GroupLayout(afdminPanel);
        afdminPanel.setLayout(afdminPanelLayout);
        afdminPanelLayout.setHorizontalGroup(
            afdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, afdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );
        afdminPanelLayout.setVerticalGroup(
            afdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afdminPanelLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("afdminPanel.TabConstraints.tabTitle"), afdminPanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(resourceMap.getString("exprRegPanel.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        menuBar.add(helpMenu);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(vista.AnalizadorLexicoApp.class).getContext().getActionMap(AnalizadorLexicoView.class, this);
        salirMenu.setAction(actionMap.get("quit")); // NOI18N
        salirMenu.setText(resourceMap.getString("salirMenu.text")); // NOI18N
        salirMenu.setName("salirMenu"); // NOI18N
        salirMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMenuMouseClicked(evt);
            }
        });
        salirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuActionPerformed(evt);
            }
        });
        menuBar.add(salirMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void validarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarEntradaActionPerformed
        boolean datosErroneos = this.validarExpReg();
        String abc = this.alfabetoTextField.getText();
        String expReg = this.expRegTextField.getText();

        /*
         * Si todo esta bien, generamos el AFN
         */
        if(!datosErroneos){
            JOptionPane.showMessageDialog(afnPanel, "Los datos se han verificado "
                    + "correctamente");

            Thompson analizadorLex = new Thompson(expReg, abc);
            datosErroneos = analizadorLex.isHayErrores();

            if (datosErroneos) {
                JOptionPane.showMessageDialog(expRePanel, analizadorLex.getErrMsg(),
                        "datosErroneos", JOptionPane.ERROR_MESSAGE);
                return;
            } else {

                // AFN
                analizadorLex.setAutomata(analizadorLex.traducir());
                TabladelAutomata test = new TabladelAutomata(analizadorLex.getAutomata());
                test.arreglarObjetosNulos();
                tablaAFN.setModel(test);
            }
            
        }

    }//GEN-LAST:event_validarEntradaActionPerformed

    private void alfabetoPredefinidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alfabetoPredefinidoActionPerformed

        int indice = this.alfabetoPredefinido.getSelectedIndex();

        if (indice == 1) {
            this.alfabetoTextField.setText("abcdefghijklmnopqrstuvwxyz");
            this.alfabetoTextField.setEnabled(false);
        } else if (indice == 2) {
            this.alfabetoTextField.setText("0123456789");
            this.alfabetoTextField.setEnabled(false);
        } else {
            this.alfabetoTextField.setEnabled(true);
        }
}//GEN-LAST:event_alfabetoPredefinidoActionPerformed

    private void salirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirMenuActionPerformed

    private void salirMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMenuMouseClicked
        this.salirMenuActionPerformed(null);
    }//GEN-LAST:event_salirMenuMouseClicked

    private void validarCadenaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarCadenaButtonActionPerformed

        String cadena = this.cadenaTextField.getText();
        //String alfabeto = this.alfabetoTextField.getText();

        this.afnPasoAPasoButton.setEnabled(true);
        

        /*
         * Verificamos si la cadena pertenece al alfabeto
         */
        /*
        if(cadena.matches(alfabeto)){
            JOptionPane.showMessageDialog(afnPanel, "La cadena corresponde al "
                    + "alfabeto");
            this.afnPasoAPasoButton.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(afnPanel, "La cadena no corresponde al "
                    + "alfabeto");
        }
         */
    }//GEN-LAST:event_validarCadenaButtonActionPerformed

    /**
     * Pinta los pasos del automata a la tabla de transiciones
     *
     * @param Tabla Jtable para el automata
     * @param AFN Automata (AFN, AFD o AFDMin)
     */
    public void pintarTabla(JTable Tabla, AFN automata) {
        TabladelAutomata tmodel = new TabladelAutomata(automata);
        Tabla.setModel(tmodel);
    }

    private boolean validarExpReg() {
        boolean error = false;

        if (this.alfabetoTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(alfabetoPanel, "El campo alfabeto no "
                    + "puede ser vacío");
            error = true;
        }else if (!this.alfabetoTextField.getText().matches("[a-z]*[0-9]*")){
            JOptionPane.showMessageDialog(alfabetoPanel, "El alfabeto solo puede "
                    + "contener letras [a-z] o digitos [0-9]");
            error = true;
        }else if (this.expRegTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(alfabetoPanel, "La expresión regular no "
                    + "puede ser vacía");
            error =true;
        }else if(this.parentesisImpar()){
            JOptionPane.showMessageDialog(alfabetoPanel, "Verificar la cantidad "
                    + "de parentesis en la expresion regular");
            error =true;
        }

        return error;
    }

    /*
     * Metodo para contar parentesis
     */
    private boolean parentesisImpar() {
        String expresion = this.expRegTextField.getText();
        int correcto = 0;
        int i = 0;

        for(i=0; i < expresion.length(); i++){
            if (expresion.charAt(i) == '('){
                correcto++;
            }else if (expresion.charAt(i) == ')')
                correcto--;
        }

        if (correcto == 0)
            return false;
        else
            return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel afdPanel;
    private javax.swing.JPanel afdminPanel;
    private javax.swing.JPanel afnPanel;
    private javax.swing.JButton afnPasoAPasoButton;
    private javax.swing.JLabel afnPasoAPasoLabel;
    private javax.swing.JPanel alfabetoPanel;
    private javax.swing.JComboBox alfabetoPredefinido;
    private javax.swing.JTextField alfabetoTextField;
    private javax.swing.JPanel cadEntradaPanel;
    private javax.swing.JTextField cadenaTextField;
    private javax.swing.JPanel expRePanel;
    private javax.swing.JLabel expRegLabel;
    private javax.swing.JPanel expRegPanel;
    private javax.swing.JTextField expRegTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu salirMenu;
    private javax.swing.JTable tablaAFN;
    private javax.swing.JButton validarCadenaButton;
    private javax.swing.JButton validarEntrada;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;
}
