/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Mantenimiento.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javafx.scene.layout.Border;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.edisoncor.gui.util.Avatar;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
//import ras.p.Validacion;
import Mantenimiento.Mto_Datos_Director;
import Mantenimiento.Mto_Director;
import foto_reporteria_2.Login_MT2_v2;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anderson R3F2
 */
public class Director extends javax.swing.JFrame {

    /**
     * Creates new form francisco_ganuza
     */
    public Director() {
        initComponents();
        llenarMenu();
        this.setLocationRelativeTo(null);
        jPanelBusqueda.setVisible(false);
        jPanelPresu.setVisible(false);
        jPanelNuevo.setVisible(false);
        jPanelGrafica.setVisible(false);
        jPanelSeg.setVisible(false);
        jpanelreporte.setVisible(false);
        setDate();
        Llenarcombo();
        llenarAnio();
        initValues();
        validar();
    }
    
    int cod_empleado;
    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
        Mto_Datos_Director obj = new Mto_Datos_Director();
        obj.setCodigo(cod_empleado);
        if(obj.Consulta()){
            cod_empleado = obj.getCodigo();
            jTextField1.setText(obj.getNombre());
            jTextField2.setText(obj.getApellido());
            jTextField3.setText(String.valueOf(obj.getEdad()));
            jTextField4.setText(obj.getCargo());
            jTextField5.setText(obj.getEstado());
         //  ImageIcon foto = new ImageIcon(getClass().getResource(obj.getFoto()));
          //  jLabel1.setIcon(foto);
        } else {
          //  JOptionPane.showMessageDialog(this, "Datos no encontrados");
        }
    }
    
    
    public void setDate(){
        Calendar gc = new GregorianCalendar();
        Date dia = gc.getTime();
        int dia1 = gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH);
        int anio = gc.get(gc.YEAR);
        jTextField22.setText(anio + "-" + (mes + 1) + "-" + dia1);
        jTextField23.setText(anio + "-" + (mes + 1) + "-" + dia1);
    }
    
    public void Llenarcombo(){
        try{
            this.jComboBox2.removeAllItems();
            ResultSet res;
            Mto_Director obj = new Mto_Director();
            res = obj.ConsultaRecurso();
            while (res.next()){
                this.jComboBox2.addItem(res.getString("recurso"));
            }
            jComboBox2.setSelectedIndex(-1);
        } catch (SQLException ex){
        
        }
    }

        public void llenarMenu(){
        List<Avatar> avatars=new ArrayList<Avatar>();
        avatars.add(new Avatar("Seguridad", loadImage("/PNG/Armor.png")));
        avatars.add(new Avatar("Busqueda", loadImage("/PNG/Document-Find.png")));
        avatars.add(new Avatar("Recursos", loadImage("/PNG/Document-New.png")));
        avatars.add(new Avatar("Presuspuestos", loadImage("/PNG/Graph-01.png")));
        //avatars.add(new Avatar("Informacion del usuario", loadImage("/PNG/User-Profile.png")));
        avatars.add(new Avatar("Configuración", loadImage("/PNG/User-Interaction.png")));
        //avatars.add(new Avatar("reporte", loadImage("/PNG/Align-Left.png")));
        avatars.add(new Avatar("ayuda", loadImage("/PNG/Help.png")));
        avatars.add(new Avatar("Cerrar sesión", loadImage("/PNG/Logout.png")));
     
        
      
        menu.setAvatars(avatars);
    }
         public static Image loadImage(String fileName){
        try {
            return ImageIO.read(Director.class.getResource(fileName));
        }
        catch (Exception e) {
            return null;
        }
    }
        public void llamarMenu(){
            if(menu.getSelectedtitulo().equals("Seguridad")){
                jPanelSeg.setVisible(true);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(false);
                jPanelPresu.setVisible(false);
                jpanelreporte.setVisible(false);
                jPanelInfo.setVisible(false);
            }
            else if(menu.getSelectedtitulo().equals("Busqueda")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(true);
                jpanelreporte.setVisible(false);
                jPanelPresu.setVisible(false);
                jPanelInfo.setVisible(false);
         
            }
            else if(menu.getSelectedtitulo().equals("Recursos")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(true);
                jPanelBusqueda.setVisible(false);
                jpanelreporte.setVisible(false);
                jPanelPresu.setVisible(false);
                jPanelInfo.setVisible(false);
       
            }
            else if(menu.getSelectedtitulo().equals("Presuspuestos")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(false);
                jpanelreporte.setVisible(false);
                jPanelPresu.setVisible(true);
                jPanelInfo.setVisible(false);
                
            }
            else if(menu.getSelectedtitulo().equals("Informacion del usuario")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(false);
                jpanelreporte.setVisible(false);
                jPanelPresu.setVisible(false);
                jPanelInfo.setVisible(true);
                
            }
            else if(menu.getSelectedtitulo().equals("Configuración")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(true);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(false);
                jpanelreporte.setVisible(false);
                jPanelPresu.setVisible(false);
                jPanelInfo.setVisible(false);
                
                        }
            else if(menu.getSelectedtitulo().equals("reporte")){
                jPanelSeg.setVisible(false);
                jPanelGrafica.setVisible(false);
                jPanelNuevo.setVisible(false);
                jPanelBusqueda.setVisible(false);
                jpanelreporte.setVisible(true);
                jPanelPresu.setVisible(false);
                jPanelInfo.setVisible(false);
                
            }
            else if(menu.getSelectedtitulo().equals("ayuda"))
            {
                
            } else if (menu.getSelectedtitulo().equals("Cerrar sesión")) {
                this.dispose();
                this.setVisible(false);
                Login_MT2_v2  login = new Login_MT2_v2();
                login.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        jPanelBusqueda = new javax.swing.JPanel();
        jTextField18 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel49 = new javax.swing.JLabel();
        jXDatePicker3 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        lbl_usuario10 = new javax.swing.JLabel();
        jPanelPresu = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField27 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        lbl_usuario4 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanelSeg = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        lbl_usuario7 = new javax.swing.JLabel();
        jPanelNuevo = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox();
        lbl_usuario2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        lbl_usuario3 = new javax.swing.JLabel();
        jpanelreporte = new javax.swing.JPanel();
        lbl_usuario11 = new javax.swing.JLabel();
        btngu2 = new javax.swing.JLabel();
        btncon2 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tabreporte = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanelInfo = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        lbl_usuario9 = new javax.swing.JLabel();
        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod2 = new org.edisoncor.gui.button.ButtonIpod();
        jPanelGrafica = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        lbl_usuario8 = new javax.swing.JLabel();
        panelCurves3 = new org.edisoncor.gui.panel.PanelCurves();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 756));
        setPreferredSize(new java.awt.Dimension(1280, 7456));
        setSize(new java.awt.Dimension(1280, 756));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(63, 57, 54));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBusqueda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 167, 157), 3, true));
        jPanelBusqueda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelBusqueda.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, 120, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Total");
        jPanelBusqueda.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 490, -1, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jPanelBusqueda.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 350, 250));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Printer.png"))); // NOI18N
        jLabel47.setText("Generar PDF");
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel47.setIconTextGap(-30);
        jLabel47.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel47MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel47MouseExited(evt);
            }
        });
        jPanelBusqueda.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 260, 110, 137));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jPanelBusqueda.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 450, 250));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("Seleccione un registro");
        jPanelBusqueda.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Búsqueda por fecha de creación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel49.setText("-");
        jLabel49.setToolTipText("");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Search.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        jPanelBusqueda.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 540, 90));

        lbl_usuario10.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario10.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario10.setText("SECCIÓN DE BÚSQUEDAS DE PRESUPUESTO");
        jPanelBusqueda.add(lbl_usuario10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 370, -1));

        jPanel1.add(jPanelBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 141, 1230, 530));

        jPanelPresu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPresu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        jPanelPresu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel31.setText("Guardar");
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel31.setIconTextGap(-22);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel31MouseExited(evt);
            }
        });
        jPanelPresu.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, -1, 90));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel35.setText("Modificar");
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel35.setIconTextGap(-22);
        jLabel35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel35MouseExited(evt);
            }
        });
        jPanelPresu.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 180, -1, 90));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Garbage-Closed.png"))); // NOI18N
        jLabel36.setText("Eliminar");
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel36.setIconTextGap(-22);
        jLabel36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel36MouseExited(evt);
            }
        });
        jPanelPresu.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, -1, 90));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Limpiar campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelPresu.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 425, 141, -1));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(794, 386, 142, -1));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 342, 141, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Recurso:");
        jPanelPresu.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 347, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Gasto:");
        jPanelPresu.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 388, -1, -1));
        jPanelPresu.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 240, 100, -1));

        jTextField28.setEditable(false);
        jTextField28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 125, 141, -1));

        jTextField25.setEditable(false);
        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 201, 139, -1));

        jTextField24.setEditable(false);
        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 162, 139, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Fecha:");
        jPanelPresu.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 127, 52, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("Año:");
        jPanelPresu.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 162, -1, 20));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("Mes:");
        jPanelPresu.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 201, -1, 20));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanelPresu.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 109, 584, 184));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanelPresu.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 316, 389, 159));

        jTextField27.setEditable(false);
        jTextField27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelPresu.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 481, 100, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText("Total:");
        jPanelPresu.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 483, -1, -1));

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jPanelPresu.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 62, 116, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Seleccione un año:");
        jPanelPresu.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 67, -1, -1));
        jPanelPresu.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 490, 100, -1));

        lbl_usuario4.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario4.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario4.setText("GESTIÓN DE PRESUPUESTO");
        jPanelPresu.add(lbl_usuario4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 18, 240, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("$");
        jPanelPresu.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 483, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("$");
        jPanelPresu.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(774, 388, -1, -1));

        jButton7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton7.setText("Generar reporte del presupuesto");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanelPresu.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, -1, -1));

        jPanel1.add(jPanelPresu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1230, 530));

        jPanelSeg.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSeg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Ver ultimos reportes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lbl_usuario7.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario7.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario7.setText("CONTROL DE REPORTES DE CONDUCTA");

        javax.swing.GroupLayout jPanelSegLayout = new javax.swing.GroupLayout(jPanelSeg);
        jPanelSeg.setLayout(jPanelSegLayout);
        jPanelSegLayout.setHorizontalGroup(
            jPanelSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSegLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSegLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_usuario7, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        jPanelSegLayout.setVerticalGroup(
            jPanelSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSegLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_usuario7)
                .addGap(24, 24, 24)
                .addGroup(jPanelSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSegLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1240, 530));

        jPanelNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 167, 157), 3, true));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Fecha de creacion:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Año:");

        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel46.setText("Consultar");
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel46.setIconTextGap(-25);
        jLabel46.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel46MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel46MouseExited(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel55.setText("Guardar");
        jLabel55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel55.setIconTextGap(-22);
        jLabel55.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel55MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel55MouseExited(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel57.setText("Modificar");
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel57.setIconTextGap(-22);
        jLabel57.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel57MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel57MouseExited(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Garbage-Closed.png"))); // NOI18N
        jLabel58.setText("Eliminar");
        jLabel58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel58.setIconTextGap(-22);
        jLabel58.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel58MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel58MouseExited(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Mes:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Limpiar campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbl_usuario2.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario2.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario2.setText("CREACIÓN DE PRESUPUESTO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbl_usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField22)
                                    .addComponent(jComboBox1, 0, 100, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel46)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel55))
                .addGap(29, 29, 29))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbl_usuario2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Recurso:");

        jTextField29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel62.setText("Consultar");
        jLabel62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel62.setIconTextGap(-25);
        jLabel62.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel62MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel62MouseExited(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel64.setText("Modificar");
        jLabel64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel64.setIconTextGap(-22);
        jLabel64.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel64MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel64MouseExited(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel63.setText("Guardar");
        jLabel63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel63.setIconTextGap(-22);
        jLabel63.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel63MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel63MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel63MouseExited(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Limpiar campos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable11);

        lbl_usuario3.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario3.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario3.setText("CREACIÓN DE RECURSOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel63)
                                .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_usuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_usuario3)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(132, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );

        javax.swing.GroupLayout jPanelNuevoLayout = new javax.swing.GroupLayout(jPanelNuevo);
        jPanelNuevo.setLayout(jPanelNuevoLayout);
        jPanelNuevoLayout.setHorizontalGroup(
            jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanelNuevoLayout.setVerticalGroup(
            jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanelNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1220, 530));

        jpanelreporte.setBackground(new java.awt.Color(255, 255, 255));
        jpanelreporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        jpanelreporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelreporteMouseClicked(evt);
            }
        });
        jpanelreporte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuario11.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario11.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario11.setText("REPORTE COMUN");
        jpanelreporte.add(lbl_usuario11, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 38, 220, -1));

        btngu2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btngu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btngu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        btngu2.setText("Agregar");
        btngu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btngu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btngu2.setIconTextGap(-20);
        btngu2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btngu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btngu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btngu2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btngu2MouseExited(evt);
            }
        });
        jpanelreporte.add(btngu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 313, 90, 110));

        btncon2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btncon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btncon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        btncon2.setText("Consultar");
        btncon2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btncon2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btncon2.setIconTextGap(-20);
        btncon2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btncon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncon2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncon2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncon2MouseExited(evt);
            }
        });
        jpanelreporte.add(btncon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 313, 90, 110));

        tabreporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabreporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabreporteMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tabreporte);

        jpanelreporte.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 101, 488, 387));
        jpanelreporte.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 163, 239, 144));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("Descripcion:");
        jpanelreporte.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 132, 168, -1));

        jTextField23.setEditable(false);
        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jpanelreporte.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 91, 152, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Fecha de creacion:");
        jpanelreporte.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 92, 168, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Printer_1.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jpanelreporte.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(998, 239, 117, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Imprimir");
        jpanelreporte.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1027, 352, -1, -1));

        jPanel1.add(jpanelreporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 145, 1210, 510));

        jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nombres:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Apellidos:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Edad:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Cargo: ");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/User-Login.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Estado:");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbl_usuario9.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario9.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario9.setText("INFORMACIÓN DEL USUARIO");

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(85, 85, 85)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(166, 166, 166))
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(502, 502, 502)
                        .addComponent(lbl_usuario9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_usuario9)
                    .addComponent(jLabel8))
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(40, 40, 40)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(43, 43, 43)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(47, 47, 47)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(39, 39, 39)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)))
                .addGap(129, 129, 129))
        );

        jPanel1.add(jPanelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 138, 1230, 530));

        menu.setColorPrimario(new java.awt.Color(0, 0, 0));
        menu.setMinimumSize(new java.awt.Dimension(300, 256));
        menu.setPreferredSize(new java.awt.Dimension(300, 200));

        buttonIpod2.setText(".");
        buttonIpod2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIpod2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(594, 594, 594)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(594, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 137));

        jPanelGrafica.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGrafica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Seleccione un año: ");

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbl_usuario8.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario8.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario8.setText("CONTROL DE PRESUPUESTO");

        javax.swing.GroupLayout jPanelGraficaLayout = new javax.swing.GroupLayout(jPanelGrafica);
        jPanelGrafica.setLayout(jPanelGraficaLayout);
        jPanelGraficaLayout.setHorizontalGroup(
            jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGraficaLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4)
                        .addGroup(jPanelGraficaLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(49, 49, 49)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(52, 52, 52)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(jPanelGraficaLayout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(lbl_usuario8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelGraficaLayout.setVerticalGroup(
            jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraficaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbl_usuario8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGraficaLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGraficaLayout.createSequentialGroup()
                        .addGroup(jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        jPanel1.add(jPanelGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1220, 530));

        panelCurves3.setForeground(new java.awt.Color(51, 255, 51));
        jPanel1.add(panelCurves3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 1270, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        //Obteniendo los valores de las filas
        int row = jTable10.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable10.getValueAt(row, 0).toString();
        String fecha = jTable10.getValueAt(row, 1).toString();
        int anio = Integer.parseInt(jTable10.getValueAt(row, 2).toString());
        String mes = jTable10.getValueAt(row, 3).toString();
        //mostrando en los jtexfield y jcombobox
        jTextField21.setText(codigo);
        jTextField22.setText(fecha);
        jComboBox4.setSelectedItem(anio);
        jComboBox1.setSelectedItem(mes);
        //deshabilitando el boton
        jLabel55.setVisible(false);
    }//GEN-LAST:event_jTable10MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        if (jComboBox1.getSelectedIndex() == -1 || jComboBox4.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else { 
            Calendar gc = new GregorianCalendar();
            int mes = gc.get(gc.MONTH);
            mes = mes + 1;
            if ((jComboBox1.getSelectedIndex() + 1) >= mes){
                //declarando variable de la clase que contiene los metodos
                Mto_Director obj = new Mto_Director();
                //insertando los nuevos valores a los metodos de la clase
                obj.setAnio(Integer.parseInt(jComboBox4.getSelectedItem().toString()));
                obj.setEmpleado(cod_empleado);
                obj.setMes(jComboBox1.getSelectedItem().toString());
                obj.MthValorPres();
                String validacion = obj.getValor1();
                if (validacion.equals("")){
                    if(obj.MthGuardar()){
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Este mes ya ha sido ingresado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe un ingresar un mes mayor o igual al actual");
            }
            
            
            //ejecuta el metodo y valida si se realizo satisfactoriamente
           
            mostrar();
            limpiar();
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCamps();
        }
        
    }//GEN-LAST:event_jLabel55MouseClicked
    
    public void llenarAnio(){
        Calendar gc = new GregorianCalendar();
        int anio = gc.get(gc.YEAR);
        for (int i = anio; i < (anio + 9); i++){
            jComboBox4.addItem(i - 1);
            jComboBox3.addItem(i - 1);
            jComboBox5.addItem(i - 1);
        }
        jComboBox4.setSelectedIndex(-1);
        jComboBox3.setSelectedIndex(-1);
        jComboBox1.setSelectedIndex(-1);
   
    }
    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        mostrar();
        limpiar();
        jLabel55.setVisible(true);
    }//GEN-LAST:event_jLabel46MouseClicked

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void buttonIpod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod2ActionPerformed
        llamarMenu();
    }//GEN-LAST:event_buttonIpod2ActionPerformed

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        if (jComboBox1.getSelectedIndex() == -1 || jComboBox4.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else { 
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setAnio(Integer.parseInt(jComboBox4.getSelectedItem().toString()));
            obj.setMes(jComboBox1.getSelectedItem().toString());
            obj.setCodigo(Integer.parseInt(jTextField21.getText()));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            obj.MthValorPres();
            String validacion = obj.getValor1();
            if (validacion.equals("")){
                if(obj.mthModificar()){
                    JOptionPane.showMessageDialog(this, "Datos modificados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Este mes ya ha sido ingresado");
            }            
            mostrar();
            limpiar();
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
        }
        
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        if (jTextField21.getText().equals("")){
            JOptionPane.showMessageDialog(this, "El campo de codigo esta vacio");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando el codigo al metodo setCodigo() en la clase
            obj.setCodigo(Integer.parseInt(jTextField21.getText()));
            //muestra un mensaje al usuario de confirmacion
            int eliminar = JOptionPane.showConfirmDialog(this, "Seguro?");
            //valida la respuesta del usuario
            if (eliminar == 0) {
                if (obj.mthEliminar()){
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar");
                }
            }
            mostrar();
            limpiar();
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
        }
    }//GEN-LAST:event_jLabel58MouseClicked

    public void suma(){
        Double suma = 0.0;
        int lengh = jTable4.getRowCount();
        for(int i = 0; i < lengh; i++){
            Double dato = Double.parseDouble(jTable4.getValueAt(i, 2).toString());
            suma = suma + dato;
        }
        jTextField27.setText(suma.toString());
    }
    
    public void sumaBusqueda(){
        Double suma = 0.0;
        int lengh = jTable5.getRowCount();
        for(int i = 0; i < lengh; i++){
            Double dato = Double.parseDouble(jTable5.getValueAt(i, 2).toString());
            suma = suma + dato;
        }
        jTextField18.setText(suma.toString());
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarPres();
        setDate();
        jLabel55.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
        mostrarRecurso();
        jLabel63.setVisible(true);
    }//GEN-LAST:event_jLabel62MouseClicked

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
        if (jTextField29.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
            obj.setRecursotxt(jTextField29.getText());
            obj.MthValorR();
            if (obj.getValor1().equals("")){
                int validar = JOptionPane.showConfirmDialog(this, "IMPORTANTE: Despues de guardar el registro, este no puede ser eliminado, ¿Desea continuar?");
                if (validar == 0) {
                    if(obj.MthGuardarRecurso()){
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El recurso que desea guardar ya se encuentra en el sistema");
            }
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            
            mostrarRecurso();
            limpiarRecurso();
            jComboBox2.removeAllItems();
            Llenarcombo();
            limpiar();
            
        }
    }//GEN-LAST:event_jLabel63MouseClicked

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        if (jTextField29.getText().equals("") || jTextField30.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setRecursotxt(jTextField29.getText());
            obj.setRecurso(Integer.parseInt(jTextField30.getText()));
            obj.MthValorR();
            if (obj.getValor1().equals("")){
                if (obj.mthModificarRecurso()){
                    JOptionPane.showMessageDialog(this, "Datos modificados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El valor ingresado ya se encuentra almacenado en el sistema");
            }
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            
            mostrarRecurso();
            limpiarRecurso();
            jComboBox2.removeAllItems();
            Llenarcombo();
            limpiar();
        }
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        int row = jTable11.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable11.getValueAt(row, 0).toString();
        String recurso = jTable11.getValueAt(row, 1).toString();

        //mostrando en los jtexfield y jcombobox
        jTextField30.setText(codigo);
        jTextField29.setText(recurso);
        
        //Deshabilitando guardar
        jLabel63.setVisible(false);
    }//GEN-LAST:event_jTable11MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpiarRecurso();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
 
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int anio = Integer.parseInt(jComboBox3.getSelectedItem().toString());
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        obj.setAnio(anio);
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaGrafica();
        dtm.setColumnIdentifiers(new Object[]{"Mes", "Total"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getDouble(2));
                dtm.addRow(v);
                jTable1.setModel(dtm);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
        
        jPanel8.removeAll();
      ChartPanel panel;
      JFreeChart chart = null;
        
      DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        String monto = "Monto";
        
        int row = jTable1.getRowCount();
        if (row == 0){
            //no se han ingresado registros para ese anio
        } else if (row == 1){
            String ene = jTable1.getValueAt(0,0).toString();
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
           data.addValue(ene1, monto, ene);
            
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 2){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
           data.addValue(ene1, monto, ene);
          data.addValue(feb1, monto, feb);
            
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
        } else if (row == 3){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
           data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 4){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
           data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 5){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            data.addValue(ene1, monto, ene);
            data.addValue(feb1, monto, feb);
            data.addValue(mar1, monto, mar);
            data.addValue(abr1, monto, abr);
            data.addValue(may1, monto, may);
            chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setDomainGridlinesVisible(true);
            panel = new ChartPanel(chart);
            panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 6){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
          data.addValue(mar1, monto, mar);
            data.addValue(abr1, monto, abr);
           data.addValue(may1, monto, may);
           data.addValue(jun1, monto, jun);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setDomainGridlinesVisible(true);
            panel = new ChartPanel(chart);
            panel.setBounds(5, 10, 700, 400);
            jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 7){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
           data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
           data.addValue(may1, monto, may);
           data.addValue(jun1, monto, jun);
           data.addValue(jul1, monto, jul);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
            panel = new ChartPanel(chart);
            panel.setBounds(5, 10, 700, 400);
            jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 8){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            String ago = jTable1.getValueAt(7,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
            Double ago1 = Double.parseDouble(jTable1.getValueAt(7,1).toString());
            data.addValue(ene1, monto, ene);
            data.addValue(feb1, monto, feb);
            data.addValue(mar1, monto, mar);
            data.addValue(abr1, monto, abr);
            data.addValue(may1, monto, may);
            data.addValue(jun1, monto, jun);
            data.addValue(jul1, monto, jul);
            data.addValue(ago1, monto, ago);
            chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setDomainGridlinesVisible(true);
            panel = new ChartPanel(chart);
            panel.setBounds(5, 10, 700, 400);
            jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 9){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            String ago = jTable1.getValueAt(7,0).toString();
            String sep = jTable1.getValueAt(8,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
            Double ago1 = Double.parseDouble(jTable1.getValueAt(7,1).toString());
            Double sep1 = Double.parseDouble(jTable1.getValueAt(8,1).toString());
            data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
           data.addValue(may1, monto, may);
            data.addValue(jun1, monto, jun);
            data.addValue(jul1, monto, jul);
            data.addValue(ago1, monto, ago);
            data.addValue(sep1, monto, sep);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 10){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            String ago = jTable1.getValueAt(7,0).toString();
            String sep = jTable1.getValueAt(8,0).toString();
            String oct = jTable1.getValueAt(9,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
            Double ago1 = Double.parseDouble(jTable1.getValueAt(7,1).toString());
            Double sep1 = Double.parseDouble(jTable1.getValueAt(8,1).toString());
            Double oct1 = Double.parseDouble(jTable1.getValueAt(9,1).toString());
            data.addValue(ene1, monto, ene);
          data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
           data.addValue(may1, monto, may);
           data.addValue(jun1, monto, jun);
           data.addValue(jul1, monto, jul);
           data.addValue(ago1, monto, ago);
           data.addValue(sep1, monto, sep);
           data.addValue(oct1, monto, oct);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 11){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            String ago = jTable1.getValueAt(7,0).toString();
            String sep = jTable1.getValueAt(8,0).toString();
            String oct = jTable1.getValueAt(9,0).toString();
            String nov = jTable1.getValueAt(10,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
            Double ago1 = Double.parseDouble(jTable1.getValueAt(7,1).toString());
            Double sep1 = Double.parseDouble(jTable1.getValueAt(8,1).toString());
            Double oct1 = Double.parseDouble(jTable1.getValueAt(9,1).toString());
            Double nov1 = Double.parseDouble(jTable1.getValueAt(10,1).toString());
           data.addValue(ene1, monto, ene);
           data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
            data.addValue(may1, monto, may);
           data.addValue(jun1, monto, jun);
           data.addValue(jul1, monto, jul);
            data.addValue(ago1, monto, ago);
            data.addValue(sep1, monto, sep);
           data.addValue(oct1, monto, oct);
           data.addValue(nov1, monto, nov);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        } else if (row == 12){
            //meses
            String ene = jTable1.getValueAt(0,0).toString();
            String feb = jTable1.getValueAt(1,0).toString();
            String mar = jTable1.getValueAt(2,0).toString();
            String abr = jTable1.getValueAt(3,0).toString();
            String may = jTable1.getValueAt(4,0).toString();
            String jun = jTable1.getValueAt(5,0).toString();
            String jul = jTable1.getValueAt(6,0).toString();
            String ago = jTable1.getValueAt(7,0).toString();
            String sep = jTable1.getValueAt(8,0).toString();
            String oct = jTable1.getValueAt(9,0).toString();
            String nov = jTable1.getValueAt(10,0).toString();
            String dic = jTable1.getValueAt(11,0).toString();
            //montos
            Double ene1 = Double.parseDouble(jTable1.getValueAt(0,1).toString());
            Double feb1 = Double.parseDouble(jTable1.getValueAt(1,1).toString());
            Double mar1 = Double.parseDouble(jTable1.getValueAt(2,1).toString());
            Double abr1 = Double.parseDouble(jTable1.getValueAt(3,1).toString());
            Double may1 = Double.parseDouble(jTable1.getValueAt(4,1).toString());
            Double jun1 = Double.parseDouble(jTable1.getValueAt(5,1).toString());
            Double jul1 = Double.parseDouble(jTable1.getValueAt(6,1).toString());
            Double ago1 = Double.parseDouble(jTable1.getValueAt(7,1).toString());
            Double sep1 = Double.parseDouble(jTable1.getValueAt(8,1).toString());
            Double oct1 = Double.parseDouble(jTable1.getValueAt(9,1).toString());
            Double nov1 = Double.parseDouble(jTable1.getValueAt(10,1).toString());
            Double dic1 = Double.parseDouble(jTable1.getValueAt(11,1).toString());
           data.addValue(ene1, monto, ene);
          data.addValue(feb1, monto, feb);
           data.addValue(mar1, monto, mar);
           data.addValue(abr1, monto, abr);
           data.addValue(may1, monto, may);
           data.addValue(jun1, monto, jun);
           data.addValue(jul1, monto, jul);
           data.addValue(ago1, monto, ago);
          data.addValue(sep1, monto, sep);
          data.addValue(oct1, monto, oct);
          data.addValue(nov1, monto, nov);
           data.addValue(dic1, monto, dic);
           chart = ChartFactory.createBarChart("Grafico", "Mes", "Monto total", data, PlotOrientation.VERTICAL, true, true, true);
           CategoryPlot plot = (CategoryPlot) chart.getPlot();
           plot.setDomainGridlinesVisible(true);
           panel = new ChartPanel(chart);
           panel.setBounds(5, 10, 700, 400);
           jPanel8.add(panel);
            jPanel8.repaint();
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar gc2 = new GregorianCalendar();
        DefaultTableModel dtm = (DefaultTableModel)jTable2.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaSeg();
        dtm.setColumnIdentifiers(new Object[]{"Fecha", "Reportes por dia"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getInt(2));
                dtm.addRow(v);
                jTable2.setModel(dtm);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
        
        jPanel10.removeAll();
        ChartPanel panel;
        JFreeChart chart = null;
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        String cant = "Reportes";
        //dias
        String dia7 = jTable2.getValueAt(0,0).toString();
        String dia6 = jTable2.getValueAt(1,0).toString();
        String dia5 = jTable2.getValueAt(2,0).toString();
        String dia4 = jTable2.getValueAt(3,0).toString();
        String dia3 = jTable2.getValueAt(4,0).toString();
        String dia2 = jTable2.getValueAt(5,0).toString();
        String dia1 = jTable2.getValueAt(6,0).toString();

        //cantidad
        int di7 = Integer.parseInt(jTable2.getValueAt(0,1).toString());
        int di6 = Integer.parseInt(jTable2.getValueAt(1,1).toString());
        int di5 = Integer.parseInt(jTable2.getValueAt(2,1).toString());
        int di4 = Integer.parseInt(jTable2.getValueAt(3,1).toString());
        int di3 = Integer.parseInt(jTable2.getValueAt(4,1).toString());
        int di2 = Integer.parseInt(jTable2.getValueAt(5,1).toString());
        int di1 = Integer.parseInt(jTable2.getValueAt(6,1).toString());

        data.addValue(di1, cant, dia1);
        data.addValue(di2, cant, dia2);
        data.addValue(di3, cant, dia3);
        data.addValue(di4, cant, dia4);
        data.addValue(di5, cant, dia5);
        data.addValue(di6, cant, dia6);
        data.addValue(di7, cant, dia7);

        chart = ChartFactory.createBarChart("Cantidad de Reportes por dia", "Dias", "Reportes", data, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        panel = new ChartPanel(chart);
        panel.setBounds(5, 10, 820, 400);
        jPanel10.add(panel);
        jPanel10.repaint();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel64MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseEntered
        Color cl = new Color(0,167,157);
        jLabel64.setForeground(cl);
    }//GEN-LAST:event_jLabel64MouseEntered

    private void jLabel46MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseEntered
        Color cl = new Color(0,167,157);
        jLabel46.setForeground(cl);
    }//GEN-LAST:event_jLabel46MouseEntered

    private void jLabel46MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseExited
        Color cl = new Color(0,0,0);
        jLabel46.setForeground(cl);
    }//GEN-LAST:event_jLabel46MouseExited

    private void jLabel55MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseEntered
        Color cl = new Color(0,167,157);
        jLabel55.setForeground(cl); 
    }//GEN-LAST:event_jLabel55MouseEntered

    private void jLabel55MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseExited
        Color cl = new Color(0,0,0);
        jLabel55.setForeground(cl);
    }//GEN-LAST:event_jLabel55MouseExited

    private void jLabel57MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseEntered
        Color cl = new Color(0,167,157);
        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel57MouseEntered

    private void jLabel57MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseExited
        Color cl = new Color(0,0,0);
        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel57MouseExited

    private void jLabel58MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseEntered
        Color cl = new Color(0,167,157);
        jLabel58.setForeground(cl);
    }//GEN-LAST:event_jLabel58MouseEntered

    private void jLabel58MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseExited
        Color cl = new Color(0,0,0);
        jLabel58.setForeground(cl);
    }//GEN-LAST:event_jLabel58MouseExited

    private void jLabel62MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseEntered
        Color cl = new Color(0,167,157);
        jLabel62.setForeground(cl);
    }//GEN-LAST:event_jLabel62MouseEntered

    private void jLabel62MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseExited
        Color cl = new Color(0,0,0);
        jLabel62.setForeground(cl);
    }//GEN-LAST:event_jLabel62MouseExited

    private void jLabel63MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseEntered
        Color cl = new Color(0,167,157);
        jLabel63.setForeground(cl);
    }//GEN-LAST:event_jLabel63MouseEntered

    private void jLabel63MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseExited
        Color cl = new Color(0,0,0);
        jLabel63.setForeground(cl);
    }//GEN-LAST:event_jLabel63MouseExited

    private void jLabel64MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseExited
        Color cl = new Color(0,0,0);
        jLabel64.setForeground(cl);
    }//GEN-LAST:event_jLabel64MouseExited

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        mostrar2();
        limpiarPres();
        setDate();
        limpiar();
        jLabel31.setVisible(true);
        Llenarcombo();
        setDisabledD();
        jButton1.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarDet();
        jLabel31.setVisible(true);
        Llenarcombo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseExited
        Color cl = new Color(0,0,0);
        jLabel36.setForeground(cl);
    }//GEN-LAST:event_jLabel36MouseExited

    private void jLabel36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseEntered
        Color cl = new Color(0,167,157);
        jLabel36.setForeground(cl);
    }//GEN-LAST:event_jLabel36MouseEntered

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        if (jTextField26.getText().equals("")){
            JOptionPane.showMessageDialog(this, "El campo de codigo esta vacio");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando el codigo al metodo setCodigo() en la clase
            obj.setDetalle(Integer.parseInt(jTextField26.getText()));
            //muestra un mensaje al usuario de confirmacion
            int eliminar = JOptionPane.showConfirmDialog(this, "Seguro?");
            //valida la respuesta del usuario
            if (eliminar == 0) {
                if (obj.mthEliminarD()){
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar");
                }
            }
            mostrarDetalle();
            mostrar2();
            suma();
            limpiarPres();
            setDate();
        }

        if (jTextField10.getText().equals("") || jTextField27.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
            obj.setTotal(Double.parseDouble(jTextField27.getText()));
            obj.setCodigo(Integer.parseInt(jTextField10.getText()));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if(obj.mthModificarTotal()){

            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos");
            }
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
            mostrarDetalle();
            mostrar2();
            suma();
            limpiarDet();
        }
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int row = jTable4.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable4.getValueAt(row, 0).toString();
        String recurso = jTable4.getValueAt(row, 1).toString();
        String costo = jTable4.getValueAt(row, 2).toString();

        //mostrando en los jtexfield y jcombobox
        jTextField26.setText(codigo);
        jComboBox2.setSelectedItem(recurso);
        jTextField11.setText(costo);

        //deshabilitando el boton
        jLabel31.setVisible(false);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jLabel35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseExited
        Color cl = new Color(0,0,0);
        jLabel35.setForeground(cl);
    }//GEN-LAST:event_jLabel35MouseExited

    private void jLabel35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseEntered
        Color cl = new Color(0,167,157);
        jLabel35.setForeground(cl);
    }//GEN-LAST:event_jLabel35MouseEntered

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        if (jTextField11.getText().equals("") || jComboBox2.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            if (jTextField26.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
            } else {
                //declarando variable de la clase que contiene los metodos
                Mto_Director obj = new Mto_Director();
                //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
                obj.setRecurso(jComboBox2.getSelectedIndex() + 1);
                obj.setRecursotxt(jComboBox2.getSelectedItem().toString());
                obj.setCosto(Double.parseDouble(jTextField11.getText()));
                obj.setDetalle(Integer.parseInt(jTextField26.getText()));
                obj.setCodigo(Integer.parseInt(jTextField10.getText()));
                obj.MthValorRecursotxt();
                if(obj.getValor1().equals(jComboBox2.getSelectedItem().toString())){
                    if (obj.mthModificarD()){
                        JOptionPane.showMessageDialog(this, "Datos modificados");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No puede modificar el recurso, solo el costo del mismo. Si se ha equivocado, borre este resgistro y vuelva a ingresarlo");
                }
                //ejecuta el metodo y valida si se realizo satisfactoriamente

                mostrarDetalle();
                mostrar2();
                suma();
            }

        }

        if (jTextField10.getText().equals("") || jTextField27.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
            obj.setTotal(Double.parseDouble(jTextField27.getText()));
            obj.setCodigo(Integer.parseInt(jTextField10.getText()));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if(obj.mthModificarTotal()){

            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos");
            }
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
            mostrarDetalle();
            mostrar2();
            suma();
            limpiarDet();
            limpiarPres();
            setDate();
            jLabel31.setVisible(true);

        }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseExited
        Color cl = new Color(0,0,0);
        jLabel31.setForeground(cl);
    }//GEN-LAST:event_jLabel31MouseExited

    private void jLabel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseEntered
        Color cl = new Color(0,167,157);
        jLabel31.setForeground(cl);
    }//GEN-LAST:event_jLabel31MouseEntered

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        if (jTextField11.getText().equals("") || jComboBox2.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
            obj.setCodigo(Integer.parseInt(jTextField10.getText()));
            obj.setRecurso(jComboBox2.getSelectedIndex() + 1);
            obj.setRecursotxt(jComboBox2.getSelectedItem().toString());
            obj.setCosto(Double.parseDouble(jTextField11.getText()));
            obj.MthValorRecurso();
            if (obj.getValor() == 0){
                //ejecuta el metodo y valida si se realizo satisfactoriamente
                if(obj.MthGuardarD()){
                    JOptionPane.showMessageDialog(this, "Datos guardados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El recurso que desea guardar ya se encuentra registrado en el presupuesto");
            }

            mostrarDetalle();
            mostrar2();
            suma();
            limpiarPres();
            setDate();
           

        }

        if (jTextField10.getText().equals("") || jTextField27.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
            obj.setTotal(Double.parseDouble(jTextField27.getText()));
            obj.setCodigo(Integer.parseInt(jTextField10.getText()));
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if(obj.mthModificarTotal()){

            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos");
            }
            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
            mostrarDetalle();
            mostrar2();
            suma();
            limpiarDet();
            limpiarPres();
            setDate();
        }
        //ejecuta el metodo de limpiar campos
        //mthLimpiarCampos();

    }//GEN-LAST:event_jLabel31MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int row = jTable3.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable3.getValueAt(row, 0).toString();
        String fecha = jTable3.getValueAt(row, 1).toString();
        String anio = jTable3.getValueAt(row, 2).toString();
        String mes = jTable3.getValueAt(row, 3).toString();
        String monto = jTable3.getValueAt(row, 4).toString();
        //mostrando en los jtexfield y jcombobox
        jTextField10.setText(codigo);
        jTextField24.setText(fecha);
        jTextField25.setText(anio);
        //jTextField27.setText(monto);
        jTextField28.setText(mes);
        jButton7.setEnabled(true);
        mostrarDetalle();
        suma();
        setEnabledD();

    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btngu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngu2MouseClicked
        if (jTextField8.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            //insertando los nuevos valores a los metodos de la clase
           
            obj.setFechas(jTextField23.getText());
            obj.setDescripcion(jTextField8.getText());
            obj.setEmpleado(cod_empleado);
            obj.MthGuardarReporte();
            if (obj.getValor1().equals("")){
                int validar = JOptionPane.showConfirmDialog(this, "IMPORTANTE: Despues de guardar el registro, este no puede ser eliminado, ¿Desea continuar?");
                if (validar == 0) {
                    if(obj.MthGuardarRecurso()){
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                    }
                }
            }
        }

    }//GEN-LAST:event_btngu2MouseClicked

    private void btngu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngu2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btngu2MouseEntered

    private void btngu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngu2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btngu2MouseExited

    private void btncon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncon2MouseClicked
         mostrarreporte();
        btngu2.setVisible(true);
    }//GEN-LAST:event_btncon2MouseClicked

    private void btncon2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncon2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btncon2MouseEntered

    private void btncon2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncon2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btncon2MouseExited

    private void tabreporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabreporteMouseClicked
        //Obteniendo los valores de las filas
        int row = tabreporte.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabreporte.getValueAt(row, 0).toString();
        String fecha = tabreporte.getValueAt(row, 2).toString();
        String empleado = tabreporte.getValueAt(row, 1).toString();
        String descripcion = tabreporte.getValueAt(row, 3).toString();
        //mostrando en los jtexfield y jcombobox
        jTextField23.setText(fecha);
        jTextField8.setText(descripcion);
        //deshabilitando el boton
        btngu2.setVisible(false);
    }//GEN-LAST:event_tabreporteMouseClicked

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jpanelreporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelreporteMouseClicked
        
    }//GEN-LAST:event_jpanelreporteMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       String path = ""; //Ubicacion del reporte
        try
        {
            /*//Llamamos la ubicación
            path = getClass().getResource("/Reporte/Reportecomun.jasper").getPath();
            //Decodificamos -esto es por si acaso un caracter especial nos falla
            path = URLDecoder.decode(path, "UTF-8");
            //Creamos la conexion
            Connection cn = new Conexion().conectar();
            //Creamos los parametros
            //Aunq no los necesitamos para este reporte
            Map parametros = new HashMap();
            
            //Creamos el Objeto Reporte
            JasperReport reporte = (JasperReport)JRLoader.loadObject(path);
            //Creamos el objeto de impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora solo falta crear el Visor-formulario donde se muestra el reporte-
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte comun"); //titulo a la ventana
            visor.setVisible(true); // mostramos el visor con el reporte*/
            
            
            String url = "src/Reportes/Reportecomun.jasper";
            Map parametro = new HashMap();
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametro, cn);
            JasperViewer ventana = new JasperViewer(reporte,false);
            ventana.setTitle("Reportes comunes");
            ventana.setVisible(true);
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        /*String path = ""; //Ubicacion del reporte
        try {
            int codigo = Integer.parseInt(jTextField10.getText());
            //Llamamos la ubicación
            path = getClass().getResource("/Reportes/Presupuesto.jasper").getPath();
            //Decodificamos -esto es por si acaso un caracter especial nos falla
            path = URLDecoder.decode(path, "UTF-8");
            //Creamos la conexion
            Connection cn = new Clases_Rasq.Conexion().conectar();
            //Creamos los parametros
            //Aunq no los necesitamos para este reporte
            Map parametros = new HashMap();
            parametros.put("cod_presupuesto", codigo);
            //Creamos el Objeto Reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Creamos el objeto de impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora solo falta crear el Visor-formulario donde se muestra el reporte-
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Presupuesto"); //titulo a la ventana
            visor.setVisible(true); // mostramos el visor con el reporte

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
        
        
        try {
            int codigo = Integer.parseInt(jTextField10.getText());
            String url = "src/Reportes/Presupuesto.jasper";
            Map parametros = new HashMap(); 
            parametros.put("cod_presupuesto", codigo);
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Presupuesto");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        BusquedaFecha();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel47MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseExited
        Color cl = new Color(0,0,0);
        jLabel47.setForeground(cl);
    }//GEN-LAST:event_jLabel47MouseExited

    private void jLabel47MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseEntered
        Color cl = new Color(0,167,157);
        jLabel47.setForeground(cl);
        //javax.swing.border.Border border = LineBorder.createBlackLineBorder();
        //jLabel47.setBorder(border);
    }//GEN-LAST:event_jLabel47MouseEntered
    
        int cod;
    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        mostrarDetalleB();
        
        int row = jTable7.getSelectedRow();
        cod = Integer.parseInt(jTable7.getValueAt(row, 0).toString());
    }//GEN-LAST:event_jTable7MouseClicked

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        try {
            String url = "src/Reportes/Presupuesto.jasper";
            Map parametros = new HashMap(); 
            parametros.put("cod_presupuesto", cod);
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Presupuesto");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jLabel47MouseClicked
    
    public void setEnabledD(){
        jTable4.setEnabled(true);
        jTextField27.setEnabled(true);
        jComboBox2.setEnabled(true);
        jTextField11.setEnabled(true);
        jButton2.setEnabled(true);
    }
    
    public void setDisabledD(){
        jTable4.setEnabled(false);
        jTextField27.setEnabled(false);
        jComboBox2.setEnabled(false);
        jTextField11.setEnabled(false);
        jButton2.setEnabled(false);
    }
    
    public void mostrarDetalle(){
        if (jTextField10.getText().equals("")){
            JOptionPane.showMessageDialog(this, "El campo de codigo esta vacio");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mto_Director obj = new Mto_Director();
            DefaultTableModel dtm = (DefaultTableModel)jTable4.getModel();
            dtm.setRowCount(0);
            //insertando el codigo al metodo setCodigo() en la clase
            obj.setCodigo(Integer.parseInt(jTextField10.getText()));
            //ejecuta el metodo y si es correcto muestra valores 
            if (obj.MthValorD()){
                int validacion = obj.getValor();
                if (validacion == 0){
                    dtm.setRowCount(0);
                } else {
                    ResultSet res = obj.MostrarDetalle();
                    dtm.setColumnIdentifiers(new Object[]{"Codigo", "Recurso", "Monto",});
                    try{
                        while(res.next()){
                            Vector v = new Vector();
                            v.add(res.getInt(1));
                            v.add(res.getString(2));
                            v.add(res.getDouble(3));
                            dtm.addRow(v);
                            jTable4.setModel(dtm);
                            jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
                            jTable4.getColumnModel().getColumn(0).setMinWidth(0);
                            jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
                        }
                        //mthLimpiarCampos();
                    }catch(Exception ex){

                    }
                }
            }    
        }
    }
    
    public void mostrarDetalleB(){
        //declarando variable de la clase que contiene los metodos
        Mto_Director obj = new Mto_Director();
        DefaultTableModel dtm = (DefaultTableModel)jTable5.getModel();
        dtm.setRowCount(0);
        //insertando el codigo al metodo setCodigo() en la clase
        int row = jTable7.getSelectedRow();
        obj.setCodigo(Integer.parseInt(jTable7.getValueAt(row, 0).toString()));
        //ejecuta el metodo y si es correcto muestra valores 
        if (obj.MthValorD()){
            int validacion = obj.getValor();
            if (validacion == 0){
                dtm.setRowCount(0);
            } else {
                ResultSet res = obj.MostrarDetalle();
                dtm.setColumnIdentifiers(new Object[]{"Codigo", "Recurso", "Monto",});
                try{
                    while(res.next()){
                        Vector v = new Vector();
                        v.add(res.getInt(1));
                        v.add(res.getString(2));
                        v.add(res.getDouble(3));
                        dtm.addRow(v);
                        jTable5.setModel(dtm);
                        jTable5.getColumnModel().getColumn(0).setMaxWidth(0);
                        jTable5.getColumnModel().getColumn(0).setMinWidth(0);
                        jTable5.getColumnModel().getColumn(0).setPreferredWidth(0);
                    }
                    //mthLimpiarCampos();
                }catch(Exception ex){

                }
            }
            sumaBusqueda();
        }    
    }
    
    public void mostrarreporte(){
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        DefaultTableModel dtm = (DefaultTableModel)tabreporte.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaReporte();
        dtm.setColumnIdentifiers(new Object[]{"codigo","Empleado","Fecha", "Descripcion"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                 v.add(res.getString(4));
                dtm.addRow(v);
                tabreporte.setModel(dtm);
                tabreporte.getColumnModel().getColumn(0).setMaxWidth(0);
                tabreporte.getColumnModel().getColumn(0).setMinWidth(0);
                tabreporte.getColumnModel().getColumn(0).setPreferredWidth(0);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
    }

        
    
    public void mostrarRecurso(){
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        DefaultTableModel dtm = (DefaultTableModel)jTable11.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaRecurso();
        dtm.setColumnIdentifiers(new Object[]{"Codigo", "Recurso"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                dtm.addRow(v);
                jTable11.setModel(dtm);
                jTable11.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable11.getColumnModel().getColumn(0).setMinWidth(0);
                jTable11.getColumnModel().getColumn(0).setPreferredWidth(0);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
    }
    
    public void mostrar(){
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        DefaultTableModel dtm = (DefaultTableModel)jTable10.getModel();
        dtm.setRowCount(0);
        res = obj.Consulta();
        dtm.setColumnIdentifiers(new Object[]{"Codigo", "Fecha", "Año", "Mes"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getInt(3));
                v.add(res.getString(4));
                dtm.addRow(v);
                jTable10.setModel(dtm);
                jTable10.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable10.getColumnModel().getColumn(0).setMinWidth(0);
                jTable10.getColumnModel().getColumn(0).setPreferredWidth(0);
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
    }
    
    public void BusquedaFecha(){
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        DefaultTableModel dtm = (DefaultTableModel)jTable7.getModel();
        dtm.setRowCount(0);

        Date fecha1 = jXDatePicker1.getDate();
        Date fecha2 = jXDatePicker3.getDate();
        if (fecha1 == null || fecha2 == null){
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {
            if (fecha1.getTime() > fecha2.getTime()){
                JOptionPane.showMessageDialog(null, "El primer campo debe contener una fecha menor que el segundo");
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Calendar gc = new GregorianCalendar();

                gc.setTime(fecha1);
                int dia1 = gc.get(gc.DAY_OF_MONTH);
                int mes1 = gc.get(gc.MONTH);
                int anio1 = gc.get(gc.YEAR);
                String fecha11 = dia1 + "-" + (mes1 + 1) + "-" + anio1;

                gc.setTime(fecha2);
                int dia2 = gc.get(gc.DAY_OF_MONTH);
                int mes2 = gc.get(gc.MONTH);
                int anio2 = gc.get(gc.YEAR);
                String fecha22 = dia2 + "-" + (mes2 + 1) + "-" + anio2;

                obj.setFecha1(fecha11);
                obj.setFecha2(fecha22);

                res = obj.Busqueda1();
                dtm.setColumnIdentifiers(new Object[]{"Codigo", "Fecha", "Año", "Mes", "Monto"});
                try{
                    while(res.next()){
                        Vector v = new Vector();
                        v.add(res.getInt(1));
                        v.add(res.getString(2));
                        v.add(res.getInt(3));
                        v.add(res.getString(4));
                        v.add(res.getDouble(5));
                        dtm.addRow(v);
                        jTable7.setModel(dtm);
                        jTable7.getColumnModel().getColumn(0).setMaxWidth(0);
                        jTable7.getColumnModel().getColumn(0).setMinWidth(0);
                        jTable7.getColumnModel().getColumn(0).setPreferredWidth(0);
                    }
                    //mthLimpiarCampos();
                }catch(Exception ex){

                }
            }
        }
    }
    
    public void limpiarPres(){
        jTextField21.setText("");
        jComboBox4.setSelectedIndex(-1);
        jComboBox1.setSelectedIndex(-1);
    }
    
    public void limpiarRecurso(){
        jTextField29.setText("");
        jTextField30.setText("");   
        jLabel63.setVisible(true);
    }
    
    public void limpiarDet(){
        jTextField26.setText("");
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.setSelectedIndex(-1);
        jTextField11.setText("");
        Llenarcombo();
    }
    
    public void limpiar(){
        jComboBox1.setSelectedIndex(-1);
        jTextField26.setText("");
        jTextField11.setText("");
        jTextField24.setText("");
        jTextField25.setText("");
        jTextField10.setText("");
        jTextField28.setText("");
        jTextField27.setText("");
        DefaultTableModel dtm = (DefaultTableModel)jTable4.getModel();
        dtm.setRowCount(0);
    }
    
    public void initValues(){
        jTextField21.setVisible(false);
        jTextField10.setVisible(false);
        jTextField26.setVisible(false);
        jTextField30.setVisible(false);
        jPanelInfo.setVisible(false);
        jPanelPresu.setVisible(true);
        jButton7.setEnabled(false);
        setDisabledD();
    }
    
    public void validar(){
//        Validacion v = new Validacion();
     //  v.SoloLetras(jTextField29);
      //  v.SoloNumeros(jTextField11);
    }
    public void mostrar2(){
        ResultSet res;
        Mto_Director obj = new Mto_Director();
        obj.setAnio(Integer.parseInt(jComboBox5.getSelectedItem().toString()));
        DefaultTableModel dtm = (DefaultTableModel)jTable3.getModel();
        dtm.setRowCount(0);
        res = obj.Consulta2();
        dtm.setColumnIdentifiers(new Object[]{"Codigo", "Fecha", "Año", "Mes", "Monto"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getInt(3));
                v.add(res.getString(4));
                v.add(res.getDouble(5));
                dtm.addRow(v);
                jTable3.setModel(dtm);
                jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable3.getColumnModel().getColumn(0).setMinWidth(0);
                jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);
                
            }
            //mthLimpiarCampos();
        }catch(Exception ex){

        }
    }
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
            java.util.logging.Logger.getLogger(Director.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Director.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Director.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Director.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Director form = new Director();
                form.setLocationRelativeTo(null);
                form.setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btncon2;
    private javax.swing.JLabel btngu2;
    private org.edisoncor.gui.button.ButtonIpod buttonIpod2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelGrafica;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelNuevo;
    private javax.swing.JPanel jPanelPresu;
    private javax.swing.JPanel jPanelSeg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker3;
    private javax.swing.JPanel jpanelreporte;
    private javax.swing.JLabel lbl_usuario10;
    private javax.swing.JLabel lbl_usuario11;
    private javax.swing.JLabel lbl_usuario2;
    private javax.swing.JLabel lbl_usuario3;
    private javax.swing.JLabel lbl_usuario4;
    private javax.swing.JLabel lbl_usuario7;
    private javax.swing.JLabel lbl_usuario8;
    private javax.swing.JLabel lbl_usuario9;
    private org.edisoncor.gui.panel.PanelAvatarChooser menu;
    private org.edisoncor.gui.panel.PanelCurves panelCurves3;
    private javax.swing.JTable tabreporte;
    // End of variables declaration//GEN-END:variables
}
