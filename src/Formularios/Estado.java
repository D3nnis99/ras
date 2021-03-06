/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Mantenimiento.Mto_Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dennis
 */
public class Estado extends javax.swing.JFrame {

    //Variable pública
    String cod_estado;
    
    /**
     * Creates new form Estado
     */
    public Estado() {
        initComponents();
        Llenarcombos();
        this.setLocationRelativeTo(null);
    }
    
    public void Llenarcombos(){
    try{
        ResultSet res;
        Mto_Estado obj = new Mto_Estado();
        res = obj.LlenarAplicacion();
        while (res.next()){
            this.cmbAp.addItem(res.getString("aplicado_a"));
        }
        res = null;
    } catch (SQLException ex){
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_configuracion = new javax.swing.JPanel();
        lbl_usuario5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        cmbAp = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panel_configuracion.setBackground(new java.awt.Color(255, 255, 255));
        panel_configuracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        panel_configuracion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_configuracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuario5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_usuario5.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario5.setText("GESTION DE ESTADOS");
        panel_configuracion.add(lbl_usuario5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 210, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Aplicacion:");
        panel_configuracion.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        panel_configuracion.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 144, -1));

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel39.setText("Consultar");
        jLabel39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel39.setIconTextGap(-25);
        jLabel39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        panel_configuracion.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 80, 80));

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel40.setText("Agregar");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel40.setIconTextGap(-22);
        jLabel40.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });
        panel_configuracion.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 80, 80));

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel44.setText("Modificar");
        jLabel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel44.setIconTextGap(-22);
        jLabel44.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel44MouseEntered(evt);
            }
        });
        panel_configuracion.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 60, 80));

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Garbage-Closed.png"))); // NOI18N
        jLabel45.setText("Eliminar");
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel45.setIconTextGap(-22);
        jLabel45.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
        });
        panel_configuracion.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 80, 80));

        cmbAp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_configuracion.add(cmbAp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 120, 21));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        panel_configuracion.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 280, 230));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Estado:");
        panel_configuracion.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_configuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_configuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mthConsultar()
    {
        ResultSet res;
        Mto_Estado obj = new Mto_Estado();
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        res = obj.Consulta();
        dtm.setColumnIdentifiers(new Object[]{"Codigo", "Estado", "Aplicacion"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                dtm.addRow(v);
                jTable1.setModel(dtm);
                //oculta columnas innecesarias
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
    }
    
     public void mthLimpiarCampos(){
        jTextField2.setText("");
        cod_estado = null;
    }
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //Obteniendo los valores de las filas
        int row = jTable1.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable1.getValueAt(row, 0).toString();
        String estado = jTable1.getValueAt(row, 1).toString();
        String ap = jTable1.getValueAt(row, 2).toString();
        //mostrando en los jtexfield y jcombobox
        cod_estado = codigo;
        jTextField2.setText(estado);
        cmbAp.setSelectedItem(ap);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        
        mthConsultar();
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        if (jTextField2.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Estado obj = new Mto_Estado();
            //insertando los nuevos valores a los metodos de la clase
            obj.setEstado(jTextField2.getText());
            obj.setAplicacion((cmbAp.getSelectedIndex() + 1));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if(obj.MthGuardar()){
                JOptionPane.showMessageDialog(this, "Datos guardados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos");
            }
            //ejecuta el metodo de limpiar campos
            mthLimpiarCampos();
            mthConsultar();
        }
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        if (jTextField2.getText().equals("") || cod_estado == null){
            JOptionPane.showMessageDialog(this, "Campos vacios o registro no seleccionado.");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Estado obj = new Mto_Estado();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setEstado(jTextField2.getText());
            obj.setCodigo(Integer.parseInt(cod_estado));
            obj.setAplicacion((cmbAp.getSelectedIndex() + 1));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if (obj.mthModificar()){
                JOptionPane.showMessageDialog(this, "Datos modificados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar");
            }
            //ejecuta el metodo de limpiar campos
            mthLimpiarCampos();
            mthConsultar();
        }
        
        
    }//GEN-LAST:event_jLabel44MouseClicked

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
        if (cod_estado == null){
            JOptionPane.showMessageDialog(this, "No ha seleccionado un registro para borrar.");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Estado obj = new Mto_Estado();
            //insertando el codigo al metodo setCodigo() en la clase
            obj.setCodigo(Integer.parseInt(cod_estado));
            //muestra un mensaje al usuario de confirmacion
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Seguro?");
            //valida la respuesta del usuario
            if (eliminar == 0) {
                if (obj.mthEliminar()){
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar");
                }
            }
            //ejecuta el metodo de limpiar campos
            mthLimpiarCampos();
            mthConsultar();
        }
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jLabel44MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel44MouseEntered

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (jTextField2.getText().length() >= 20) {
            evt.consume();
        }    
          if(Character.isDigit(C))
     {
        
         evt.consume();
     }
     else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
             ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
             || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
             || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255)
    {
         
         evt.consume();
     }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Administrador.FEst = false;
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAp;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbl_usuario5;
    private javax.swing.JPanel panel_configuracion;
    // End of variables declaration//GEN-END:variables
}
