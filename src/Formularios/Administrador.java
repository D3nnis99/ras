/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

//importacion de librerias a utilizar
import Mantenimiento.Conexion;
import Mantenimiento.Mto_Administrador;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import foto_reporteria_2.Login_MT2_v2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.edisoncor.gui.util.Avatar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import java.util.Date;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_YEAR;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.view.JasperViewer;

public class Administrador extends javax.swing.JFrame {

    //VARIABLES CITAS
    String fecha;
    String cod_citas;
    
    
   //variables globales
    String cod_empleado;
    String cod_recluso;
    String cod_visita;
    static int cod_barra=0;
    int bb;
    int max_empleado;
    int max_visitante;
    
    //Variables
    String nombre_recluso;
    String edad;
    String apellido_recluso;
    String nombre_vis;
    String apellido_vis;
    String direccion;
    int cod_re;
    int cod_vis;
    int cod_cit;
    int asi;
    
    //variables de validacion para form
    
    public static boolean FPre = false;
    public static boolean FTez = false;
    public static boolean FHor = false;
    public static boolean FCri = false;
    public static boolean FAgr = false;
    public static boolean FSec = false;
    public static boolean FEst = false;
    public static boolean FTel = false;
    public static boolean FRci = false;
    public static boolean FRcir = false;
    //Variables para imagenes
    String[] url = {"", "", ""};
    
    //Variables para historial
    String nombre_r;
    String apellido_r;
    Date fecha_actual = new Date();
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    String FechaHis = formato.format(fecha_actual);
    /*Calendar gc = new GregorianCalendar();
    Date dia = gc.getTime();
    int dia1 = gc.get(gc.DAY_OF_MONTH);
    int mes = gc.get(gc.MONTH);
    int anio = gc.get(gc.YEAR);*/
    
    int cod_r;
    int cod_e;
    
    //Miscelanea o variables extra para consultas extra
    Connection cn = null;
    ResultSet rs1 = null;
    Statement st = null;
    
    public Administrador() {
        //CIYTAS FECHA
        
        
        
        initComponents();
        this.setLocationRelativeTo(null);
        // se inicia el formulario y se llaman los constructores para que se ejecuten
        jTextField6.setVisible(false);
        txtFecha.setText(fechaActual());
        fecha=this.txtFecha.getText();
        mthCitas();
        mthCitasActualizadas();
        llenarMenu();
        Llenarcombos();
        llenarCelda();
        verificarfecha();
        limpiarH();
        panel_historialR.setVisible(false);
        panel_citas.setVisible(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        //llenar combobox
//        if(jRadioButton7.isSelected()){
//        cmbMeses.setEnabled(true);
//        txtAños.setEnabled(false);
//       }
//        else if(jRadioButton9.isSelected()){
//        cmbMeses.setEnabled(false);
//        txtAños.setEnabled(true);
//        }
        //se ubica el formulario en el centro
        this.setLocationRelativeTo(null);
        
        //se llama la Conexion
         
    Conexion c = new Conexion();
    cn = c.conectar();
            
         //se ocultan los paneles
        panel_usuario.setVisible(false);
        panel_recluso.setVisible(false);
        panel_configuracion.setVisible(false);
        //panel_historial.setVisible(false);
        panel_visitante.setVisible(false);
        //OOCULTAR VISITAS
        panel_asistencia_citas.setVisible(false);
        
        //se manda a llamar el ultimo dato y luego se utiliza como un contados global
        Mto_Administrador obj = new Mto_Administrador();
        cod_barra = obj.getCodigo_Barra();

        if(obj.codBarra()){
            cod_barra = obj.getCodigo_Barra();
        }
        
        int b=cod_barra+1;
        txtBarraRe.setText(String.valueOf(b));
        bb=b;
        txtBarraRe.setText(null);
        jLabel_BarraRe.setIcon(null);
        
        
                
 addWindowListener(new java.awt.event.WindowAdapter() {
public void windowClosing(java.awt.event.WindowEvent evt) {
formWindowClosing(evt);
}

public void windowOpened(java.awt.event.WindowEvent evt) {
formWindowOpened(evt);
}
});
     
 
        
    }
    
private void formWindowClosing(java.awt.event.WindowEvent evt) {
stop();
} 

private void formWindowOpened(java.awt.event.WindowEvent evt) {
Iniciar();
start();
}
    
     Object[] comlumnas = { "Nombre recluso", "Apellido recluso", "Edad"};
    DefaultTableModel model = new DefaultTableModel (comlumnas,WIDTH); 
    
    Object[] comlumnas2 = { "Nombre visita", "Apellido visita", "Direccion"};
    DefaultTableModel other= new DefaultTableModel (comlumnas2,WIDTH); 
    
      Mto_Administrador mtdosadmin = new Mto_Administrador(); 
      String receptoR;
        String receptoA;
        String receptoZ;
    
    
    
    
    //constructores para cargar combobox----------------------------------------------------------------------------------------------------------------------------------------------
    public void Llenarcombos(){
    try{
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        
          res = obj.LlenarTelefono();
        while(res.next())
        {
           this.cmbTipoNumeroV.addItem(res.getString("tipo"));
        }
        res = null;
        
        res = obj.LlenarTelefono();
        while(res.next())
        {
           this.cmbTipoNumero.addItem(res.getString("tipo"));
        }
        res = null;
        
        res = obj.LlenarCargo();
        while (res.next()){
            this.cmbCargo.addItem(res.getString("cargo"));
        }
        res = null;
            
        res = obj.LlenarTez();
        while(res.next())
        {
            this.cmbTezRe.addItem(res.getString("tez"));
        }
        res = null;
            
        res = obj.LlenarSector();
        while(res.next())
        {
           this.cmbSectorRe.addItem(res.getString("nombre_sector"));
        }
        res = null;
        
      
        
        res = obj.LlenarPregunta();
        while(res.next())
        {
            this.cmbPregunta.addItem(res.getString("pregunta"));
        }
        res = null;
            
        res = obj.LlenarEstado();
        while (res.next()){
           this.cmbEstadoRe.addItem(res.getString("estado"));
        }
        res = null;
        
        res = obj.LlenarEstadoU();
        while (res.next()){
           this.cmbEstadoU.addItem(res.getString("estado"));
        }
        res = null;
        
        res = obj.LlenarCrimen();
        while(res.next())
        {
           this.jComboBox1.addItem(res.getString("crimen"));
        }
        res = null;
        
        
    } catch (SQLException ex){
        
    }
    }
    //finaliza constructor---------------------------------------------------------------------------------------------------------------------------------
    
    // se ingresan los datos para el menu interactivo como por ejemplo la imagen que tendra en cada icono y el nombre para identificar
        public void llenarMenu(){
        List<Avatar> avatars=new ArrayList<Avatar>();
        avatars.add(new Avatar("Informacion de Admin", loadImage("/PNG/Admin.png")));      
        avatars.add(new Avatar("Reclusos", loadImage("/PNG/Reclusos1.png")));
        avatars.add(new Avatar("Usuarios", loadImage("/PNG/Accounts-Book.png")));
        avatars.add(new Avatar("Visitantes", loadImage("/PNG/Visitante.png")));
        avatars.add(new Avatar("Configuracion", loadImage("/PNG/Configuracion2.png")));
        avatars.add(new Avatar("IngresoCitas",loadImage("/PNG/Couple-01.png")));
        avatars.add(new Avatar("Ayuda", loadImage("/PNG/Ayuda.png")));
        avatars.add(new Avatar("Cerrar sesión", loadImage("/PNG/Logout.png")));
        
     
        
      
        menu.setAvatars(avatars);
    }
         public static Image loadImage(String fileName){
        try {
            return ImageIO.read(Administrador.class.getResource(fileName));
        }
        catch (Exception e) {
            return null;
        }
    }
          public void llamarMenu(){
        if(menu.getSelectedtitulo().equals("Seguridad")){
           panel_usuario.setVisible(false);
        panel_recluso.setVisible(false);
        panel_configuracion.setVisible(false);
        panel_visitante.setVisible(false);
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
        
        } 
        else if(menu.getSelectedtitulo().equals("Busqueda"))
        {
       panel_usuario.setVisible(false);
        panel_recluso.setVisible(false);
        panel_configuracion.setVisible(false);
        panel_visitante.setVisible(false);
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
        } 
        
          else if(menu.getSelectedtitulo().equals("Reclusos"))
          {
        panel_usuario.setVisible(false);
        panel_recluso.setVisible(true);
        panel_configuracion.setVisible(false);
        panel_visitante.setVisible(false);
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
          }
          else if(menu.getSelectedtitulo().equals("Configuracion"))
          {
        panel_usuario.setVisible(false);
        panel_recluso.setVisible(false);
        panel_configuracion.setVisible(true);
        panel_visitante.setVisible(false);
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
          }
           else if(menu.getSelectedtitulo().equals("Usuarios"))
          {
        panel_usuario.setVisible(true);
        panel_recluso.setVisible(false);   
        panel_configuracion.setVisible(false); 
        panel_visitante.setVisible(false);
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
          }
           else if(menu.getSelectedtitulo().equals("Visitantes"))
          {
        panel_usuario.setVisible(false);
        panel_recluso.setVisible(false);   
        panel_configuracion.setVisible(false);
        panel_visitante.setVisible(true); 
        panel_citas.setVisible(false);
        //panel_historial.setVisible(false);
          }
        else if(menu.getSelectedtitulo().equals("Ayuda"))
          {
            //CODIGO PARA INICIAR EL JTABBED DE AYUDA 
            Ayuda abrir_ayuda = new Ayuda(); 
            abrir_ayuda.setVisible(true); 
          } 
        else if (menu.getSelectedtitulo().equals("Cerrar sesión")){
              this.dispose();
              this.setVisible(false);
              Login_MT2_v2 login = new Login_MT2_v2();
              login.setVisible(true);
              stop();
          }
        else if (menu.getSelectedtitulo().equals("IngresoCitas")){
            panel_usuario.setVisible(false);
            panel_recluso.setVisible(false);   
            panel_configuracion.setVisible(false);
            panel_visitante.setVisible(false); 
            panel_citas.setVisible(true);
        }
        
    }
    //Metodos de limpieza y actualizacion de campos

    public void mthLimpiarRE() {
        url[0] = "";
        url[1] = "";
        url[2] = "";
        iconos[0] = null;
        iconos[1] = null;
        iconos[2] = null;
        jLabelFoto1.setText("SIN IMAGEN");
        jLabelFoto1.setIcon(null);
        jLabelFoto2.setText("SIN IMAGEN");
        jLabelFoto2.setIcon(null);
        jLabelFoto3.setText("SIN IMAGEN");
        jLabelFoto3.setIcon(null);
        jLabel_BarraRe.setIcon(null);
        txtNombreRe.setText(null);
        txtApellidoRe.setText(null);
        txtEdadRe.setText(null);
        txtAlturaRe.setText(null);
        txtPesoRe.setText(null);
        txtAliasRe.setText(null);
        txtBarraRe.setText(null);
        
    }

    public void mthConsultarRE() {
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel) tabReclusos.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaRecluso();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Recluso", "Nombre", "Apellido", "Edad", "Estado", "Sector", "Color de Piel", "Altura", "Peso", "Alias", "Codigo de Barra", "Celda"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                v.add(res.getString(9));
                v.add(res.getString(10));
                v.add(res.getString(11));
                v.add(res.getString(12));
                dtm.addRow(v);
                tabReclusos.setModel(dtm);

                //Metodo para ocultar columnas
                mthOcultarColumRe();

            }
        } catch (Exception ex) {

        }
        
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
    }
    
    
    
    //CONSULTA CITAS     SE VA A MOVER AL FORMULARIO DE JEFE DE SEGURIDAD
    public void mthCitas() {
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel) tabCitasNo.getModel();
       // JOptionPane.showMessageDialog(null, "fecha"+fecha);
        dtm.setRowCount(0);
        obj.setFechaCita(fecha);
        res = obj.ConsultaCitas();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Citas", "Nombre de Visita", "Apellido de Visita", "Vinculo", "Motivos", "Nombre del Recluso", "Apellido del Recluso"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                dtm.addRow(v);
                tabCitasNo.setModel(dtm);

                //Metodo para ocultar columnas
                //mthOcultarColumRe();

            }
        } catch (Exception ex) {

        }
    }
    
    public void mthCitasActualizadas() {
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel) tabCitasSi.getModel();
       // JOptionPane.showMessageDialog(null, "fecha"+fecha);
        dtm.setRowCount(0);
        obj.setFechaCita(fecha);
        res = obj.ConsultaCitasActualizadas();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Citas", "Nombre de Visita", "Apellido de Visita", "Vinculo", "Motivos", "Nombre del Recluso", "Apellido del Recluso"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                dtm.addRow(v);
                tabCitasSi.setModel(dtm);

                //Metodo para ocultar columnas
                //mthOcultarColumRe();

            }
        } catch (Exception ex) {

        }
    }
    
    
    public static String fechaActual(){
    
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        
        return formatoFecha.format(fecha);
    }
    
    
    
    
    
    
    
    public void mthOcultarColumRe()
    {
        
                //SE OCULTAN LAS COLUMNAS INNESESARIAS
                tabReclusos.getColumnModel().getColumn(0).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(0).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(0).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(4).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(4).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(4).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(5).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(5).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(5).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(6).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(6).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(6).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(7).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(7).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(7).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(8).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(8).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(8).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(9).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(9).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(9).setPreferredWidth(0);

                tabReclusos.getColumnModel().getColumn(10).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(10).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(10).setPreferredWidth(0);
                
                tabReclusos.getColumnModel().getColumn(11).setMaxWidth(0);
                tabReclusos.getColumnModel().getColumn(11).setMinWidth(0);
                tabReclusos.getColumnModel().getColumn(11).setPreferredWidth(0);
    }

//inicio de ccodigos para todo los mantenimientos que hara el administrador---------------------------------------------------------------------------------------------------------------
          
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo_1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        panel_citas = new javax.swing.JPanel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabcitas = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        lbl_usuario5 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtNombreR = new javax.swing.JTextField();
        txtNombreV = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txtVinculo = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txtMotivos = new javax.swing.JTextField();
        panel_recluso = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtNombreRe = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtApellidoRe = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEdadRe = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtAlturaRe = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbl_usuario2 = new javax.swing.JLabel();
        cmbTezRe = new javax.swing.JComboBox<>();
        txtPesoRe = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cmbEstadoRe = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtBarraRe = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cmbSectorRe = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtAliasRe = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabReclusos = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel_BarraRe = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabelFoto3 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabelFoto2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabelFoto1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtFiltroRe = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cmbCelda = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        panel_historialR = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel98 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        lbl_usuario7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        panel_usuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreUsu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellidosUsu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox<>();
        cmbPregunta = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtRespuesta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblImagenHuella = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_usuario1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabEmpleado = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cmbEstadoU = new javax.swing.JComboBox();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel53 = new javax.swing.JLabel();
        cmbTipoNumero = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        txtnNumeroC = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabelFotoE = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txt_datos = new javax.swing.JTextField();
        panel_asistencia_citas = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabCitasSi = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabCitasNo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txtNombreCitas = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtApellidosCitas = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txtVinculosCitas = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txtMotivosCitas = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        txtNombreRe11 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        lbl_usuario6 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txtNombreReclusoCitas = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txtApellidosReclusoCitas = new javax.swing.JTextField();
        txtFecha = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        panel_configuracion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_usuario3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panel_visitante = new javax.swing.JPanel();
        txtNombreVis = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtApellidosVis = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDireccionVis = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lbl_usuario4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabVisita = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cmbTipoNumeroV = new javax.swing.JComboBox();
        txtnNumeroV = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod2 = new org.edisoncor.gui.button.ButtonIpod();
        panelCurves3 = new org.edisoncor.gui.panel.PanelCurves();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMinimumSize(new java.awt.Dimension(1290, 730));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(63, 57, 54));
        jPanel2.setFocusable(false);
        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel2.setPreferredSize(new java.awt.Dimension(1290, 730));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_citas.setBackground(new java.awt.Color(255, 255, 255));
        panel_citas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 167, 157), 3, true));
        panel_citas.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                panel_citasAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        panel_citas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jXDatePicker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker2ActionPerformed(evt);
            }
        });
        panel_citas.add(jXDatePicker2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, 170, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        panel_citas.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 60, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        panel_citas.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 270, 60, -1));

        tabcitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabcitas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabcitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabcitasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabcitas);

        panel_citas.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 660, 140));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel72.setText("Consultar");
        jLabel72.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel72.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel72.setIconTextGap(-20);
        jLabel72.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel72MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel72MouseExited(evt);
            }
        });
        panel_citas.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 78, 98));

        lbl_usuario5.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario5.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario5.setText("Citas");
        panel_citas.add(lbl_usuario5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 70, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel73.setText("Filtro por nombre de recluso");
        panel_citas.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 230, 20));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel74.setText("Hora:");
        panel_citas.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 65, -1));

        jLabel76.setFont(new java.awt.Font("Aharoni", 1, 16)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Garbage-Closed.png"))); // NOI18N
        jLabel76.setText("Eliminar");
        jLabel76.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel76.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel76.setIconTextGap(-20);
        jLabel76.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel76MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel76MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel76MouseExited(evt);
            }
        });
        panel_citas.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 340, 78, 100));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel78.setText("Fecha:");
        panel_citas.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 65, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel85.setText("Guardar");
        jLabel85.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel85.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel85.setIconTextGap(-22);
        jLabel85.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel85MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel85MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel85MouseExited(evt);
            }
        });
        panel_citas.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 140, -1, 90));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable2);

        panel_citas.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 320, 134));

        txtNombreR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRKeyTyped(evt);
            }
        });
        panel_citas.add(txtNombreR, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 279, -1));

        txtNombreV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreVKeyTyped(evt);
            }
        });
        panel_citas.add(txtNombreV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 279, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        panel_citas.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 18, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel86.setText("Modificar");
        jLabel86.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel86.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel86.setIconTextGap(-22);
        jLabel86.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel86MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel86MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel86MouseExited(evt);
            }
        });
        panel_citas.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 240, -1, 90));

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel112.setText("Citas:");
        panel_citas.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

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
        jScrollPane10.setViewportView(jTable3);

        panel_citas.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 320, 134));
        panel_citas.add(txtVinculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 200, 60));

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel113.setText("Vinculo:");
        panel_citas.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 167, 157));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Reporte de Citas por Recluso");
        jLabel57.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel57.setName(""); // NOI18N
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });
        panel_citas.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, 250, 30));

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel114.setText("Filtro por nombre de visitante");
        panel_citas.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 167, 157));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Reporte Citas por Fecha");
        jLabel71.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel71.setName(""); // NOI18N
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });
        panel_citas.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, 250, 30));

        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel115.setText("Motivos:");
        panel_citas.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));
        panel_citas.add(txtMotivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 200, 60));

        jPanel2.add(panel_citas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1200, 530));

        panel_recluso.setBackground(new java.awt.Color(255, 255, 255));
        panel_recluso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        panel_recluso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Altura(M):");
        panel_recluso.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        txtNombreRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreReActionPerformed(evt);
            }
        });
        txtNombreRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtNombreRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 430, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setText("Nombres:");
        panel_recluso.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtApellidoRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellidoRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtApellidoRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 430, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setText("Apellidos:");
        panel_recluso.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtEdadRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEdadRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtEdadRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setText("Edad(A):");
        panel_recluso.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        txtAlturaRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAlturaRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtAlturaRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 80, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setText("Peso(Lb):");
        panel_recluso.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 167, 157));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Imprimir Reporte");
        jLabel29.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel29.setName(""); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 230, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel30.setText("Estado del recluso:");
        panel_recluso.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        lbl_usuario2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario2.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario2.setText("RECLUSOS");
        panel_recluso.add(lbl_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 140, -1));

        cmbTezRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_recluso.add(cmbTezRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 110, -1));

        txtPesoRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesoRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtPesoRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 80, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel31.setText("Color de piel:");
        panel_recluso.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        cmbEstadoRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbEstadoRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoReActionPerformed(evt);
            }
        });
        panel_recluso.add(cmbEstadoRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 130, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel32.setText("Alias:");
        panel_recluso.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 60, -1));

        txtBarraRe.setEditable(false);
        txtBarraRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_recluso.add(txtBarraRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 490, 130, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel33.setText("Cod de barra:");
        panel_recluso.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 120, -1));

        cmbSectorRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbSectorRe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSectorReItemStateChanged(evt);
            }
        });
        cmbSectorRe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbSectorReMouseClicked(evt);
            }
        });
        panel_recluso.add(cmbSectorRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 90, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Perfil Izq.");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        panel_recluso.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 160, 120, -1));

        txtAliasRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAliasRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAliasReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtAliasRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 110, -1));

        tabReclusos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabReclusos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabReclusos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabReclusosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabReclusosMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tabReclusos);

        panel_recluso.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 510, 270));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel62.setText("Agregar");
        jLabel62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel62.setIconTextGap(-20);
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
        panel_recluso.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 90, 110));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel61.setText("Modificar");
        jLabel61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel61.setIconTextGap(-20);
        jLabel61.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel61MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel61MouseExited(evt);
            }
        });
        panel_recluso.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 90, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel59.setText("Consultar");
        jLabel59.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel59.setIconTextGap(-20);
        jLabel59.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel59MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel59MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel59MouseExited(evt);
            }
        });
        panel_recluso.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 90, 100));

        jLabel_BarraRe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_BarraRe.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 167, 157)));
        jLabel_BarraRe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BarraReMouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel_BarraRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 480, 150, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 167, 157));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel36.setText("huella ");
        jLabel36.setName(""); // NOI18N
        panel_recluso.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 60, 30));

        jLabelFoto3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFoto3.setForeground(new java.awt.Color(0, 167, 157));
        jLabelFoto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFoto3.setText("SIN IMAGEN");
        jLabelFoto3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        jLabelFoto3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelFoto3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelFoto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFoto3MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabelFoto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 120, 140));

        jLabel37.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 167, 157));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel37.setText("dactilar ");
        jLabel37.setName(""); // NOI18N
        panel_recluso.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 70, 30));

        jLabelFoto2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFoto2.setForeground(new java.awt.Color(0, 167, 157));
        jLabelFoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFoto2.setText("SIN IMAGEN");
        jLabelFoto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        jLabelFoto2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelFoto2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelFoto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFoto2MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabelFoto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 120, 140));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel39.setText("Sector:");
        panel_recluso.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 80, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Frontal");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        panel_recluso.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 120, -1));

        jLabelFoto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFoto1.setForeground(new java.awt.Color(0, 167, 157));
        jLabelFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFoto1.setText("SIN IMAGEN");
        jLabelFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        jLabelFoto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelFoto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelFoto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFoto1MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabelFoto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 120, 140));

        jLabel48.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 167, 157));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Quitar.png"))); // NOI18N
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.setName(""); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 20, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Perfil Der.");
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        panel_recluso.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 120, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 167, 157));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel46.setText("Colocar ");
        jLabel46.setName(""); // NOI18N
        panel_recluso.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 70, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 167, 157));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Quitar.png"))); // NOI18N
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.setName(""); // NOI18N
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 167, 157));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Quitar.png"))); // NOI18N
        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel50.setName(""); // NOI18N
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel35.setText("Filtrar por nombre:");
        panel_recluso.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 180, -1));

        txtFiltroRe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroReKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroReKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroReKeyTyped(evt);
            }
        });
        panel_recluso.add(txtFiltroRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 230, -1));

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Finger Print.png"))); // NOI18N
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 70, 90));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 167, 157));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Imprimir");
        jLabel52.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel52.setName(""); // NOI18N
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });
        panel_recluso.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 480, 150, 30));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel51.setText("Celda:");
        panel_recluso.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 120, -1));

        cmbCelda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_recluso.add(cmbCelda, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 70, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 167, 157));
        jButton4.setText("Ver historial");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panel_recluso.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 160, -1));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 167, 157));
        jButton5.setText("Reporte historial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panel_recluso.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 160, -1));

        jPanel2.add(panel_recluso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 137, 1200, 530));

        panel_historialR.setBackground(new java.awt.Color(255, 255, 255));
        panel_historialR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText("Fecha");
        jPanel6.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("Condena");
        jPanel6.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel88.setText("Fianza");
        jPanel6.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setText("Si");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jPanel6.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("No");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jPanel6.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 130, -1));

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton3.setText("Meses");
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });
        jPanel6.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton4.setText("Años");
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseClicked(evt);
            }
        });
        jPanel6.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 120, -1));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel6.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 120, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setText("Descripción (Opcional)");
        jPanel6.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane8.setViewportView(jTextArea1);

        jPanel6.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 420, 120));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setText("Crimen");
        jPanel6.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 59, -1, -1));
        jPanel6.add(jXDatePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 120, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Limpiar Campos");
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 160, -1));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setText("$");
        jPanel6.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel100.setText("Consultar");
        jLabel100.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel100.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel100.setIconTextGap(-25);
        jLabel100.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel100MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel100MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel100MouseExited(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel101.setText("Guardar");
        jLabel101.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel101.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel101.setIconTextGap(-22);
        jLabel101.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel101MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel101MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel101MouseExited(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel102.setText("Modificar");
        jLabel102.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel102.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel102.setIconTextGap(-22);
        jLabel102.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel102MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel102MouseExited(evt);
            }
        });

        lbl_usuario7.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario7.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario7.setText("Historial del recluso");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setText("Total dias");

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel104.setText("Dias restantes:");

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel105.setText("Dias:");

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel106.setText("0");

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel107.setText("Meses:");

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel108.setText("0");

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel109.setText("Años:");

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel110.setText("0");

        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_historialRLayout = new javax.swing.GroupLayout(panel_historialR);
        panel_historialR.setLayout(panel_historialRLayout);
        panel_historialRLayout.setHorizontalGroup(
            panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_historialRLayout.createSequentialGroup()
                .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                                .addComponent(jLabel104)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel105)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel106)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel107)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel108)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel109)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel110)
                                .addGap(67, 67, 67))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                        .addContainerGap(27, Short.MAX_VALUE)
                        .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_historialRLayout.createSequentialGroup()
                                .addComponent(lbl_usuario7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(183, 183, 183)))))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_historialRLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel100)
                                .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel101)))
                    .addGroup(panel_historialRLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton3)))
                .addGap(35, 35, 35))
        );
        panel_historialRLayout.setVerticalGroup(
            panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_historialRLayout.createSequentialGroup()
                .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_historialRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addGroup(panel_historialRLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbl_usuario7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103))
                        .addGap(32, 32, 32)
                        .addGroup(panel_historialRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel104)
                            .addComponent(jLabel105)
                            .addComponent(jLabel106)
                            .addComponent(jLabel109)
                            .addComponent(jLabel110)
                            .addComponent(jLabel107)
                            .addComponent(jLabel108))
                        .addGap(30, 30, 30)))
                .addContainerGap())
            .addGroup(panel_historialRLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(32, 32, 32))
        );

        jPanel2.add(panel_historialR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1220, 520));

        panel_usuario.setBackground(new java.awt.Color(255, 255, 255));
        panel_usuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));
        panel_usuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Cargo:");
        panel_usuario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        txtNombreUsu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuKeyTyped(evt);
            }
        });
        panel_usuario.add(txtNombreUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Nombres:");
        panel_usuario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtApellidosUsu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellidosUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosUsuKeyTyped(evt);
            }
        });
        panel_usuario.add(txtApellidosUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Apellidos:");
        panel_usuario.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, -1));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        panel_usuario.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Usuario:");
        panel_usuario.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 80, -1));

        cmbCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_usuario.add(cmbCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 140, -1));

        cmbPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_usuario.add(cmbPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 320, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("Pregunta de seguridad:");
        panel_usuario.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 220, 20));

        txtRespuesta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRespuestaActionPerformed(evt);
            }
        });
        txtRespuesta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRespuestaKeyTyped(evt);
            }
        });
        panel_usuario.add(txtRespuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, 400, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Respuesta:");
        panel_usuario.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 100, 20));

        txtEdad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });
        panel_usuario.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 70, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Estado:");
        panel_usuario.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 70, 20));

        lblImagenHuella.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Finger Print.png"))); // NOI18N
        lblImagenHuella.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenHuellaMouseClicked(evt);
            }
        });
        panel_usuario.add(lblImagenHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, 90, 80));

        jLabel18.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 167, 157));
        jLabel18.setText("dactilar");
        panel_usuario.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 50, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("Contraseña:");
        panel_usuario.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        lbl_usuario1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario1.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario1.setText("USUARIOS");
        panel_usuario.add(lbl_usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 140, -1));

        tabEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabEmpleado);

        panel_usuario.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 650, 320));

        jLabel63.setFont(new java.awt.Font("Aharoni", 1, 16)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel63.setText("Consultar");
        jLabel63.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel63.setIconTextGap(-20);
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
        panel_usuario.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 90, 110));

        jLabel65.setFont(new java.awt.Font("Aharoni", 1, 16)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel65.setText("Modificar");
        jLabel65.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel65.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel65.setIconTextGap(-20);
        jLabel65.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel65MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel65MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel65MouseExited(evt);
            }
        });
        panel_usuario.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 80, 100));

        jLabel66.setFont(new java.awt.Font("Aharoni", 1, 16)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel66.setText("Agregar");
        jLabel66.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel66.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel66.setIconTextGap(-20);
        jLabel66.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel66MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel66MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel66MouseExited(evt);
            }
        });
        panel_usuario.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 90, 110));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel27.setText("Edad(A):");
        panel_usuario.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, -1, -1));

        cmbEstadoU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoUActionPerformed(evt);
            }
        });
        panel_usuario.add(cmbEstadoU, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 130, 20));

        txtContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });
        panel_usuario.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 180, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel53.setText("Tipo de número:");
        panel_usuario.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        cmbTipoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoNumeroActionPerformed(evt);
            }
        });
        panel_usuario.add(cmbTipoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 140, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel54.setText("Número de contacto:");
        panel_usuario.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        txtnNumeroC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnNumeroC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnNumeroCKeyTyped(evt);
            }
        });
        panel_usuario.add(txtnNumeroC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 180, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 167, 157));
        jLabel77.setText("Colocar");
        panel_usuario.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 50, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 167, 157));
        jLabel79.setText("huella");
        panel_usuario.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 460, 40, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 167, 157));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Quitar.png"))); // NOI18N
        jLabel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel80.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel80.setName(""); // NOI18N
        jLabel80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel80MouseClicked(evt);
            }
        });
        panel_usuario.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 30, 30));

        jLabelFotoE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFotoE.setForeground(new java.awt.Color(0, 167, 157));
        jLabelFotoE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFotoE.setText("SIN IMAGEN");
        jLabelFotoE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        jLabelFotoE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelFotoE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelFotoE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFotoEMouseClicked(evt);
            }
        });
        panel_usuario.add(jLabelFotoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 170, 200));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Foto de Perfil");
        jLabel87.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157)));
        panel_usuario.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 170, 30));

        txt_datos.setForeground(new java.awt.Color(51, 51, 55));
        txt_datos.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));
        txt_datos.setCaretColor(new java.awt.Color(51, 51, 55));
        txt_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_datosActionPerformed(evt);
            }
        });
        panel_usuario.add(txt_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 290, 60));

        jPanel2.add(panel_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1220, 530));

        panel_asistencia_citas.setBackground(new java.awt.Color(255, 255, 255));
        panel_asistencia_citas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        panel_asistencia_citas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabCitasSi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabCitasSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabCitasSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCitasSiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabCitasSi);

        panel_asistencia_citas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 520, 180));

        tabCitasNo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabCitasNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabCitasNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCitasNoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabCitasNo);

        panel_asistencia_citas.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 510, 190));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel38.setText("VISITAS");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel81.setText("Nombres:");

        txtNombreCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCitasActionPerformed(evt);
            }
        });
        txtNombreCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCitasKeyTyped(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel82.setText("Apellidos:");

        txtApellidosCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellidosCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosCitasActionPerformed(evt);
            }
        });
        txtApellidosCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosCitasKeyTyped(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel83.setText("Vinculo:");

        txtVinculosCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVinculosCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVinculosCitasActionPerformed(evt);
            }
        });
        txtVinculosCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVinculosCitasKeyTyped(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel84.setText("Motivos:");

        txtMotivosCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMotivosCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotivosCitasActionPerformed(evt);
            }
        });
        txtMotivosCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivosCitasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(txtApellidosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83)
                            .addComponent(jLabel84)
                            .addComponent(txtMotivosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVinculosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jLabel83))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVinculosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jLabel84))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotivosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        panel_asistencia_citas.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 530, 170));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel93.setText("Buscar por nombre:");

        txtNombreRe11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreRe11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreRe11ActionPerformed(evt);
            }
        });
        txtNombreRe11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRe11KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreRe11, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNombreRe11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_asistencia_citas.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 70));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 167, 157));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("AGREGAR ASISTENCIA");
        jLabel90.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel90.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel90.setName(""); // NOI18N
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel90MouseClicked(evt);
            }
        });
        panel_asistencia_citas.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 270, 30));

        lbl_usuario6.setBackground(new java.awt.Color(255, 255, 255));
        lbl_usuario6.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario6.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario6.setText("CITAS");
        panel_asistencia_citas.add(lbl_usuario6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel91.setText("SI HAN ASISTIDO:");
        panel_asistencia_citas.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 170, -1));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel92.setText("Citas del día:");
        panel_asistencia_citas.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 110, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel94.setText("RECLUSO:");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel95.setText("Nombres:");

        txtNombreReclusoCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreReclusoCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreReclusoCitasActionPerformed(evt);
            }
        });
        txtNombreReclusoCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreReclusoCitasKeyTyped(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel96.setText("Apellidos:");

        txtApellidosReclusoCitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellidosReclusoCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosReclusoCitasActionPerformed(evt);
            }
        });
        txtApellidosReclusoCitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosReclusoCitasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtApellidosReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel96)
                        .addComponent(jLabel95)
                        .addComponent(txtNombreReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addGap(202, 202, 202)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel95)
                .addGap(10, 10, 10)
                .addComponent(txtNombreReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidosReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        panel_asistencia_citas.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 330, 170));

        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        panel_asistencia_citas.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 180, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel97.setText("NO HAN ASISTIDO:");
        panel_asistencia_citas.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 170, -1));

        jPanel2.add(panel_asistencia_citas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1210, 520));

        panel_configuracion.setBackground(new java.awt.Color(255, 255, 255));
        panel_configuracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Boy-01.png"))); // NOI18N
        jLabel8.setText("Tez");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setIconTextGap(-40);
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/User-Shield.png"))); // NOI18N
        jLabel4.setText("Pregunta seguridad");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setIconTextGap(-40);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Clock-02.png"))); // NOI18N
        jLabel6.setText("Horarios");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setIconTextGap(-40);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/User-Group.png"))); // NOI18N
        jLabel7.setText("Agrupaciones");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setIconTextGap(-50);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Gun.png"))); // NOI18N
        jLabel11.setText("Crimenes");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setIconTextGap(-40);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Chart-Customization.png"))); // NOI18N
        jLabel9.setText("Sectores");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setIconTextGap(-40);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        lbl_usuario3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_usuario3.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario3.setText("CONFIGURACION DE DATOS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Estado.png"))); // NOI18N
        jLabel10.setText("Estado");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setIconTextGap(-50);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Phone.png"))); // NOI18N
        jLabel24.setText("Teléfono");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel24.setIconTextGap(-50);
        jLabel24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_configuracionLayout = new javax.swing.GroupLayout(panel_configuracion);
        panel_configuracion.setLayout(panel_configuracionLayout);
        panel_configuracionLayout.setHorizontalGroup(
            panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_configuracionLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_configuracionLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(panel_configuracionLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_configuracionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_usuario3)
                .addGap(305, 305, 305))
        );
        panel_configuracionLayout.setVerticalGroup(
            panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_configuracionLayout.createSequentialGroup()
                .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_configuracionLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbl_usuario3)
                        .addGap(42, 42, 42)
                        .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panel_configuracionLayout.createSequentialGroup()
                        .addContainerGap(92, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(48, 48, 48))
        );

        jPanel2.add(panel_configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, 510));

        panel_visitante.setBackground(new java.awt.Color(255, 255, 255));
        panel_visitante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));
        panel_visitante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreVis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreVis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreVisKeyTyped(evt);
            }
        });
        panel_visitante.add(txtNombreVis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Nombres:");
        panel_visitante.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, -1));

        txtApellidosVis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellidosVis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosVisKeyTyped(evt);
            }
        });
        panel_visitante.add(txtApellidosVis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Apellidos:");
        panel_visitante.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtDireccionVis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccionVis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionVisKeyTyped(evt);
            }
        });
        panel_visitante.add(txtDireccionVis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 370, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setText("Dirección:");
        panel_visitante.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, -1));

        lbl_usuario4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_usuario4.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario4.setText("VISITANTES");
        panel_visitante.add(lbl_usuario4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        tabVisita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabVisita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabVisitaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabVisita);

        panel_visitante.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 740, 330));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Find.png"))); // NOI18N
        jLabel67.setText("Consultar");
        jLabel67.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel67.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel67.setIconTextGap(-20);
        jLabel67.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel67MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel67MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel67MouseExited(evt);
            }
        });
        panel_visitante.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 90, 110));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Garbage-Closed.png"))); // NOI18N
        jLabel68.setText("Eliminar");
        jLabel68.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel68.setIconTextGap(-20);
        jLabel68.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel68MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel68MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel68MouseExited(evt);
            }
        });
        panel_visitante.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 90, 110));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Edit.png"))); // NOI18N
        jLabel69.setText("Modificar");
        jLabel69.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel69.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel69.setIconTextGap(-20);
        jLabel69.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel69MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel69MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel69MouseExited(evt);
            }
        });
        panel_visitante.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 80, 110));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel70.setText("Agregar");
        jLabel70.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel70.setIconTextGap(-20);
        jLabel70.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel70MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel70MouseExited(evt);
            }
        });
        panel_visitante.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 90, 110));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Finger Print.png"))); // NOI18N
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        panel_visitante.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 70, 80));

        jLabel42.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 167, 157));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("huella ");
        jLabel42.setName(""); // NOI18N
        panel_visitante.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 60, 30));

        jLabel43.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 167, 157));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("Colocar ");
        jLabel43.setName(""); // NOI18N
        panel_visitante.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 70, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 167, 157));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel44.setText("dactilar ");
        jLabel44.setName(""); // NOI18N
        panel_visitante.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 70, 30));

        cmbTipoNumeroV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoNumeroVActionPerformed(evt);
            }
        });
        panel_visitante.add(cmbTipoNumeroV, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 130, -1));

        txtnNumeroV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnNumeroV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnNumeroVKeyTyped(evt);
            }
        });
        panel_visitante.add(txtnNumeroV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 180, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel55.setText("Número de contacto:");
        panel_visitante.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel56.setText("Tipo de número:");
        panel_visitante.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        jPanel2.add(panel_visitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 204, 1190, 470));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(590, Short.MAX_VALUE)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(592, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 122));

        panelCurves3.setForeground(new java.awt.Color(51, 255, 51));
        jPanel2.add(panelCurves3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 669, 1290, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIpod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod2ActionPerformed
        llamarMenu();
    }//GEN-LAST:event_buttonIpod2ActionPerformed

    private void cmbEstadoReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoReActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoReActionPerformed
//campos vacios o datos incompletos
    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
    Mto_Administrador obj = new Mto_Administrador();
        if (txtNombreRe.getText().equals("") || txtApellidoRe.getText().equals("") || txtEdadRe.getText().equals("") || txtAlturaRe.getText().equals("") || txtPesoRe.getText().equals("") || txtAliasRe.getText().equals("") || cod_recluso == null) {
            JOptionPane.showMessageDialog(this, "Campos vacios o registro no seleccionado.");
        } else {
             //validacion de datos incorrectos
            String dato = this.txtEdadRe.getText();
            int edad = Integer.parseInt(dato);

            if (edad > 18 && edad < 100) {
                String dato2 = this.txtPesoRe.getText();
                double peso = Double.parseDouble(dato2);
                if (peso > 70 && peso < 400) {
                    
                    String se = Integer.toString(cmbSectorRe.getSelectedIndex() + 1);
                    String te = Integer.toString(cmbTezRe.getSelectedIndex() + 1);
                    String ed = txtEdadRe.getText();
                    String BarraRe = bb + se + te + ed;
                    obj.setNombre(txtNombreRe.getText());
                    obj.setApellido(txtApellidoRe.getText());
                    obj.setEdad(txtEdadRe.getText());
                    obj.setEstado((cmbEstadoRe.getSelectedIndex() + 1));
                    obj.setSector((cmbSectorRe.getSelectedIndex() + 1));
                    obj.setTez((cmbTezRe.getSelectedIndex() + 1));
                    obj.setAltura(txtAlturaRe.getText());
                    obj.setPeso(peso);
                    obj.setAlias(txtAliasRe.getText());
                    obj.setCodigo_Barra(Integer.parseInt(BarraRe));
                    obj.setCelda(Integer.parseInt(cmbCelda.getSelectedItem().toString()));
                    obj.setCodigo_R(Integer.parseInt(cod_recluso));
                    obj.setCodigo_r(Integer.parseInt((cod_recluso)));
                    obj.Obtener_estado();
                    int numero = obj.getNumero();
                    if (numero == 10){
                        int estado = obj.getEstado();
                        if (estado != 10){
                            Calendar gc = new GregorianCalendar();
                            int dia = gc.get(gc.DAY_OF_MONTH);
                            int mes = gc.get(gc.MONTH);
                            int anio = gc.get(gc.YEAR);
                            String fecha = dia + "-" + (mes + 1)+ "-" + anio;
                            obj.setFecha_2(fecha);
                            obj.modificarFecha();
                        }
                    }
                    if (obj.modificarRecluso()) {

                        JOptionPane.showMessageDialog(this, "Datos modificados");
                       // mthConsultarRE();
                        
                        /*if(jRadioButton1.isSelected()){
                            int meses=cmbMeses.getSelectedIndex()+1;
                              int dias_m=(30*meses);
                            int cantMeses=((cmbMeses.getSelectedIndex()+1));
                            ConsultarCodR();
                            obj.setcondena_R(dias_m);
                            obj.setCodigo_R(Integer.parseInt(cod_recluso));
                            if(obj.modificarReclusoCondena()){
                              //ingreso con meses
                             }
                            else{
                            //error
                            JOptionPane.showMessageDialog(this, "Error sduhkdsjhfk");
                            }
                        }
                        else if(jRadioButton2.isSelected()){
                            int anios;
                            int diasA;
                            anios=Integer.parseInt(txtAños.getText());
                            diasA=365*anios;
                            obj.setCodigo_R(Integer.parseInt(cod_recluso));
                            obj.setcondena_R(diasA);
                            if(obj.modificarReclusoCondena()){
                            //ingreso condena a;os
                             }
                            else{
                                JOptionPane.showMessageDialog(this, "Error sduhkdsjhfk");
                            }
                            
                        }*/
                        
                        
                        
                        for (int c = 0; c < 3; c = c + 1) {
                            int cod = c + 1;
                            if(url[c].equals("insert") == false)
                            {
                               // JOptionPane.showMessageDialog(this, "URL: " + url[c]);
                            obj.setTipo_Foto(cod);
                             obj.setURL(url[c]);
                            if (obj.ModificarImagenR()) {
                                if ((url[0].equals("") && url[1].equals("") && url[2].equals(""))
                                        || (url[0].equals("") && url[1].equals(""))
                                        || (url[1].equals("") && url[2].equals(""))
                                        || (url[0].equals("") && url[2].equals(""))
                                        || url[0].equals("") || url[1].equals("") || url[2].equals("")) {
                                    if (cod == 3) {
                                        JOptionPane.showMessageDialog(this, "Quedaron una o mas imagenes vacías.");
                                    }
                                } else if (cod == 3) {
                                    JOptionPane.showMessageDialog(this, "Imagenes guardadas.");
                                } else {
                                    if (cod == 3) {
                                        JOptionPane.showMessageDialog(this, "Error al modificar imagenes.");
                                    }
                                }
                            } else {

                                if (cod == 3) {
                                    JOptionPane.showMessageDialog(this, "Error al guardar imagenes.");
                                }
                            }
                            }
                            
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar");
                    }
                    mthLimpiarRE();
                        mthConsultarRE();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Peso incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No pueden haber reclusos menores de edad ni con datos falsos.");
            }
            
        }
        obj.CerrarConexion();
    }//GEN-LAST:event_jLabel61MouseClicked

    private void jLabel61MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel61MouseEntered

    private void jLabel61MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel61MouseExited

    public void ConsultarCodR() {
        try {
            st = cn.createStatement();
            rs1 = st.executeQuery("SELECT MAX(cod_recluso) FROM Reclusos");
            if (rs1.next()) {
                cod_r = rs1.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
//     public Date sumaDiasFecha(Date fecha,int dias){
//        
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(fecha);
//            calendar.add(Calendar. DAY_OF_YEAR,dias);
//            
//            return calendar.getTime();
//        }
    
    /*public void mthCargarHistorial()
    {
        panel_recluso.setVisible(false);
                        panel_usuario.setVisible(false);
                        panel_configuracion.setVisible(false);
                        panel_visitante.setVisible(false);
                        panel_historial.setVisible(true);
                        nombre_r = txtNombreRe.getText();
                    apellido_r = txtApellidoRe.getText();
                        txtNombreHis.setText(nombre_r);
                        txtApellidoHis.setText(apellido_r);
                        txtFechaHis.setText(FechaHis);
                        mthConsultarHis();
                        ConsultarCodR();
                        
    }*/
    
    
    
    
    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
        // TODO add your handling code here:
        
        /*
        if(!ruta.equals("")){
            if(bd.guardarImagen(ruta,nombre)){
                JOptionPane.showMessageDialog(null, "La Imagen fue Guardada Exitosamente en la Base de Datos", "Hecho", JOptionPane.INFORMATION_MESSAGE);
                jTextField1.setText("");
                jLabel1.setIcon(null);
                refrescarCarrusel(0);
            }else{
                JOptionPane.showMessageDialog(null, "La Imagen no puedo ser Guardada en la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        */
        
        //validacion de datos nulos
        Calendar gc= new GregorianCalendar();
        Date dia=gc.getTime();
        int dia1=gc.get(gc.DAY_OF_MONTH);
        int mes= gc.get(gc.MONTH);
        int anio= gc.get(gc.YEAR);
        String fecha_actual=(anio+"-"+(mes+1)+"2"+dia1);
//        String[] dataSeparada=dataString.split("/")

        
        
//        
//        int messss=cmbMeses.getSelectedIndex()+1;
//        int dias=(30*messss);
        
//        SimpleDateFormat String=new SimpleDateFormat("yyyy-MM-dd");
//        
//        Date fecha=null;
//        try{
//        fecha=String.parse(fecha_actual);
//       
//        
//        }catch(ParseException ex){
//        
//        }
//        
//        sumaDiasFecha(fecha, dias);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
//        String fechaCadena = sdf.format(sumaDiasFecha);
        int cod_estado=0;
        int cod_tez=0;
        Mto_Administrador obj = new Mto_Administrador();
        if (txtNombreRe.getText().equals("") || txtApellidoRe.getText().equals("") || txtEdadRe.getText().equals("") || txtAlturaRe.getText().equals("") ||  txtPesoRe.getText().equals("") || txtAliasRe.getText().equals("") || fecha_actual.equals("") ) {
            JOptionPane.showMessageDialog(this, "Campos vacios");
        } 
        else {
            if ((url[0].equals("insert") || url[1].equals("insert") || url[2].equals("insert"))) {
                JOptionPane.showMessageDialog(this, "Inserte o deje en blaco las imagenes previamente cargadas.");
            } else {
                String dato = this.txtEdadRe.getText();
            int edad = Integer.parseInt(dato);
            //validacion de datos incorrectos
            if (edad >=18 && edad <= 100) {
                String dato2 = this.txtPesoRe.getText();
                double peso = Double.parseDouble(dato2);
                if (peso > 70 && peso < 400) {
                    obj.setEstadoIndex( cmbEstadoRe.getSelectedItem().toString());
                    if(obj.Obtener_estadoIndex()){
                        cod_estado = obj.getCod_estado();
                        
                        obj.setTezIndex( cmbTezRe.getSelectedItem().toString());
                        if(obj.Obtener_tezIndex()){
                        cod_tez = obj.getTez();
                       
                   //     JOptionPane.showMessageDialog(this, "Tez "+cod_tez);
                        
                    String se = Integer.toString(cmbSectorRe.getSelectedIndex() + 1);
                    String te = Integer.toString(cmbTezRe.getSelectedIndex() + 1);
                    String ed = txtEdadRe.getText();

                    
                    
                    obj.setNombre(txtNombreRe.getText());
                    obj.setApellido(txtApellidoRe.getText());
                    obj.setEdad(ed);
                    obj.setEstado(cod_estado);
                    obj.setSector((cmbSectorRe.getSelectedIndex() + 1));
                    obj.setTez(cod_tez);
                    obj.setAltura(txtAlturaRe.getText());
                    obj.setPeso(peso);
                    obj.setAlias(txtAliasRe.getText());
                    obj.setFechaIngreso(fecha_actual);
                    String BarraRe = bb + se + te + ed;
                    obj.setCodigo_Barra(Integer.parseInt(BarraRe));
                    obj.setCelda(Integer.parseInt(cmbCelda.getSelectedItem().toString()));
                    if (obj.GuardarRecluso()) {

                        //mthCargarHistorial();
                        
                        obj.setCodigo_R(cod_r);
                        
                        bb = bb + 1;
                       
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                        ConsultarCodR();
                        for (int i = 0; i < 3; i = i + 1) {
                                int cod = i + 1;
                                obj.setCodigo_R(cod_r);
                                obj.setURL(url[i]);
                                //JOptionPane.showMessageDialog(this, "URL: " + url[i]);
                                obj.setTipo_Foto(cod);
                                if (obj.GuardarImagenR()) {
                                    if ((url[0].equals("") && url[1].equals("") && url[2].equals(""))
                                            || (url[0].equals("") && url[1].equals(""))
                                            || (url[1].equals("") && url[2].equals(""))
                                            || (url[0].equals("") && url[2].equals("")) 
                                            || url[0].equals("") || url[1].equals("") || url[2].equals("")) {
                                        if (cod == 3) {
                                            JOptionPane.showMessageDialog(this, "Quedaron una o mas imagenes vacías.");
                                        }
                                    } else if (cod == 3) {
                                        JOptionPane.showMessageDialog(this, "Imagenes guardadas.");
                                    } else {
                                        if (cod == 3) {
                                            JOptionPane.showMessageDialog(this, "Error al guardar imagenes.");
                                        }
                                    }
                                }
                            }
                    }
                         else {
                        JOptionPane.showMessageDialog(this, "Error al guardar datos");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Error al consultar Index Tez");
                        }
                    }else {
                        JOptionPane.showMessageDialog(this, "Error al consultar Index Estado");
                        }
                    mthLimpiarRE();
                    mthConsultarRE();
                    }  
                    else{
                    JOptionPane.showMessageDialog(this, "Peso incorrecto");
                    }
            
            }
                else{
                JOptionPane.showMessageDialog(this, "No pueden haber reclusos menores de edad ni con datos falsos.");
                }
            }
            
            
        }
        //obj.CerrarConexion();
        
    }//GEN-LAST:event_jLabel62MouseClicked

    private void jLabel62MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel62MouseEntered

    private void jLabel62MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel62MouseExited

    private void jLabel59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseClicked
        mthConsultarRE();
    }//GEN-LAST:event_jLabel59MouseClicked

    private void jLabel59MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel59MouseEntered

    private void jLabel59MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel59MouseExited

    public void mthLimpiarEm()
    {
        txtContraseña.setText(null);
        txtNombreUsu.setText(null);
        txtApellidosUsu.setText(null);
        txtUsuario.setText(null);
        txtnNumeroC.setText(null);
        txtEdad.setText(null);
        txtRespuesta.setText(null);
        url[0] = "";
        iconos[0] = null;
        jLabelFotoE.setText("SIN IMAGEN");
        jLabelFotoE.setIcon(null);
    }
    
    public void mthOcultarColumEm()
    {
        //SE OCULTAN LAS COLUMNAS INNESESARIAS
                 tabEmpleado.getColumnModel().getColumn(0).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(0).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(0).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(4).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(4).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(4).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(5).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(5).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(5).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(6).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(6).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(6).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(7).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(7).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(7).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(8).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(8).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(8).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(9).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(9).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(9).setPreferredWidth(0);
                
                tabEmpleado.getColumnModel().getColumn(10).setMaxWidth(0);
                tabEmpleado.getColumnModel().getColumn(10).setMinWidth(0);
                tabEmpleado.getColumnModel().getColumn(10).setPreferredWidth(0);
    }
    
    public void mthConsultarEm()
    {
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel)tabEmpleado.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaEmpleado();
        //ENCABEZADOS DE COLUMNAS
        dtm.setColumnIdentifiers(new Object[]{"cod_empleado", "Nombre", "Apellido", "Usuario","Contraseña","Cargo","Pregunta","Respuesta","Edad","Huella","Estado","Telefono"});
        try{
            while(res.next()){
                //DATOS QUE SE CARGARAN A COLUMNAS
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                v.add(res.getString(9));
                v.add(res.getString(10));
                v.add(res.getString(11));
                v.add(res.getString(12));
                dtm.addRow(v);
                tabEmpleado.setModel(dtm);
                
                mthOcultarColumEm();
            }
        }catch(Exception ex){
            
        }
    }
    
    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
       mthConsultarEm();
    }//GEN-LAST:event_jLabel63MouseClicked

    private void jLabel63MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel63MouseEntered

    private void jLabel63MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel63MouseExited

    private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseClicked
        //VALIDACION DE DATOS NULOS
        if (txtNombreUsu.getText().equals("") || txtApellidosUsu.getText().equals("") || txtUsuario.getText().equals("") || txtContraseña.getText().equals("") || txtRespuesta.getText().equals("") || txtEdad.getText().equals("") || txtNombreUsu.getText().length() <= 4 || txtApellidosUsu.getText().length() <= 4 || txtUsuario.getText().equals("") || txtContraseña.getText().length() <= 5 || txtRespuesta.getText().length() <= 4 || txtEdad.getText().length() <= 1) {
            JOptionPane.showMessageDialog(this, "Campos vacios o datos incompletos.");
        } else {
            String dato = this.txtEdad.getText();
            int edad = Integer.parseInt(dato);
            //VALIDACION DE DATOS ERRONEOS
            if (edad >= 18 && edad <= 70) {
                Mto_Administrador obj = new Mto_Administrador();
                obj.setNombre(txtNombreUsu.getText());
                obj.setApellido(txtApellidosUsu.getText());
                obj.setUsuario(txtUsuario.getText());
                obj.setContraseña(txtContraseña.getText());
                obj.setCargo((cmbCargo.getSelectedIndex()) + 1);
                obj.setPregunta((cmbPregunta.getSelectedIndex()) + 1);
                obj.setRespuesta(txtRespuesta.getText());
                obj.setEdad(txtEdad.getText());
                obj.setEstado((cmbEstadoU.getSelectedIndex() + 1));

                obj.setCodigo(Integer.parseInt(cod_empleado));
                if (url[0].equals("insert") == false) {
                    obj.setURL(url[0]);
                    if (obj.modificarEmpleado()) {

                        JOptionPane.showMessageDialog(this, "Datos modificados");

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar");
                    }
                } else {
                    obj.setURL("");
                    if (obj.modificarEmpleado()) {

                        JOptionPane.showMessageDialog(this, "Datos modificados");

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "El usuario tiene que ser mayor de edad y sin datos falsos");
            }
        }
    }//GEN-LAST:event_jLabel65MouseClicked

    private void jLabel65MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel65MouseEntered

    private void jLabel65MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel65MouseExited
     String usuario ;
    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked
        // TODO add your handling code here:
        //VALIDACION DE DATOS NULOS
        if (txtNombreUsu.getText().equals("") || txtApellidosUsu.getText().equals("")|| txtUsuario.getText().equals("")|| txtContraseña.getText().equals("")|| txtRespuesta.getText().equals("")|| txtEdad.getText().equals("") || txtNombreUsu.getText().length() <= 4 || txtApellidosUsu.getText().length() <= 4 || txtUsuario.getText().equals("")|| txtContraseña.getText().length() <= 8 || txtRespuesta.getText().length() <= 4|| txtEdad.getText().length() <= 1 ){
            JOptionPane.showMessageDialog(this, "Campos vacios o datos incompletos.");
        } 
        else {
            if ((url[0].equals("insert"))) {
                JOptionPane.showMessageDialog(this, "Inserte o deje en blaco la imagen previamente cargada.");
            } else {
                
            String dato=this.txtEdad.getText();
            int edad = Integer.parseInt(dato);
            //VALIDACION DE DATOS ERRONEOS
            if(edad>=18 && edad<=70){
                        Mto_Administrador obj = new Mto_Administrador(); 
                       obj.setNombre(txtNombreUsu.getText());
                       obj.setApellido(txtApellidosUsu.getText());
                       obj.setUsuario(txtUsuario.getText());
                       obj.setContraseña(txtContraseña.getText());
                       obj.setCargo((cmbCargo.getSelectedIndex() + 1));
                       obj.setPregunta((cmbPregunta.getSelectedIndex() + 1));
                       obj.setRespuesta(txtRespuesta.getText());
                       obj.setEdad(txtEdad.getText());
                       obj.setEstado((cmbEstadoU.getSelectedIndex() + 1));
                       obj.setURL(url[0]);

                       if(obj.GuardarEmpleado())     {
                           JOptionPane.showMessageDialog(this, "Datos guardados");
                            usuario = txtUsuario.getText();
                               guardarHuella();
                           
                             max_empleado = obj.getUltimoEmpleado();
                             if(obj.UEmpleado()){
                              max_empleado = obj.getUltimoEmpleado();
                            }
                             
                          
                           obj.setTelefono(txtnNumeroC.getText());
                           obj.setCodTipoTelefono((cmbTipoNumero.getSelectedIndex()+1));
                           obj.setCodigo(max_empleado);
                           if(obj.GuardarContactoEmpleado()){
                               obj.setTelefono(txtnNumeroC.getText());
                               obj.setCodTipoTelefono((cmbTipoNumero.getSelectedIndex()+1));
                               obj.setCodigo(max_empleado);
                           
                           }
                           else{
                               JOptionPane.showMessageDialog(this, "Error al guardar datos"); 
                           }
                           
                           
                       }
                       else     {   
                           JOptionPane.showMessageDialog(this, "Error al guardar datos"); 

                            }
                       mthLimpiarEm();
                        mthConsultarEm();
            }
            else{
                JOptionPane.showMessageDialog(this, "El usuario tiene que ser mayor de edad y sin datos falsos");
            }
            }
        }
    }//GEN-LAST:event_jLabel66MouseClicked

    private void jLabel66MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel66MouseEntered

    private void jLabel66MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel66MouseExited

    public void CargarURLE() {
        try {
                
                st = cn.createStatement();
                rs1 = st.executeQuery("SELECT url_bit FROM Empleado WHERE cod_empleado = " + cod_empleado);
                if (rs1.next()) {
                    ctf = rs1.getString(1);
                    if (ctf == null) {
                        img = null;
                        ctf = "";
                    } else {
                        Blob blob = rs1.getBlob("url_bit");
                        byte[] data = blob.getBytes(1, (int) blob.length());
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(data));

                        } catch (IOException ex) {
                            System.out.println("ERROR: " + ex);
                        }
                        ctf = "insert";
                    }

                }
            } catch (Exception e) {
                System.out.println("ERROR: Imposible cargar el formato bit de la imagen - "+e);
            }
            url[0] = ctf;
            iconos[0] = img;
    }
    //METODO PARA CARGAR LAS IMAGENES A LOS LABELS
    public void CargarImagenE()
    {
            if(iconos[0] != null){
                jLabelFotoE.setText("");
                ImageIcon icon = new ImageIcon(iconos[0].getScaledInstance(jLabelFotoE.getWidth(), jLabelFoto1.getHeight(), Image.SCALE_DEFAULT));
                jLabelFotoE.setIcon(icon);
            }
            else
            {
                jLabelFotoE.setText("SIN IMAGEN");
                jLabelFotoE.setIcon(null);
            }
    }
    
    public void Mth_CargarUsuario()
    {
    //SE CARGAN LOS VALORES DE TABLA A LOS TEXTBOX
        //Obteniendo los valores de las filas 
        int row = tabEmpleado.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabEmpleado.getValueAt(row, 0).toString();
        String nombre = tabEmpleado.getValueAt(row, 1).toString();
        String apellido = tabEmpleado.getValueAt(row, 2).toString();
        String usuario = tabEmpleado.getValueAt(row, 3).toString();
        String contraseña = tabEmpleado.getValueAt(row, 4).toString();
        String cargo = tabEmpleado.getValueAt(row, 5).toString();
        String pregunta = tabEmpleado.getValueAt(row, 6).toString();
        String respuesta = tabEmpleado.getValueAt(row, 7).toString();
        String edad = tabEmpleado.getValueAt(row, 8).toString();
//        String huella = tabEmpleado.getValueAt(row, 9).toString();
        String estado = tabEmpleado.getValueAt(row, 10).toString();
        String telefono = tabEmpleado.getValueAt(row, 11).toString();
        //mostrando en los jtexfield y jcombobox
        cod_empleado=codigo;
        txtNombreUsu.setText(nombre);
        txtApellidosUsu.setText(apellido);
        txtUsuario.setText(usuario);
        txtContraseña.setText(contraseña);
        cmbCargo.setSelectedItem(cargo);
        cmbPregunta.setSelectedItem(pregunta);
        txtRespuesta.setText(respuesta);
        txtEdad.setText(edad);
        cmbEstadoU.setSelectedItem(estado);
        txtnNumeroC.setText(telefono);
        CargarURLE();
        CargarImagenE();
    }
    
    private void tabEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabEmpleadoMouseClicked
    
        Mth_CargarUsuario();
         Mth_CargarUsuario();
         Mth_CargarUsuario();
    }//GEN-LAST:event_tabEmpleadoMouseClicked

    //Variables Extra para la carga de imagenes en labels
    String ctf;
    int ct;
    BufferedImage img = null;
    BufferedImage[] iconos = {null, null, null};
    
    /*public void ValidarImagenR()
    {
        for (int c = 0; c < 3; c = c + 1) {
            FileInputStream fis = null;
                
            try {
                ct = c + 1;
                st = cn.createStatement();
                File file = new File(url[c]);
                fis = new FileInputStream(file);
                rs1 = st.executeQuery("SELECT url_bit FROM Foto_recluso WHERE cod_tipo_foto = " + ct + " AND url_bit = " + fis);
                
                if (rs1.next()) {
                    ctf = rs1.getString(1);
                    if (ctf == null) {
                        img = null;
                        ctf = "";
                    } else {
                        Blob blob = rs1.getBlob("url_bit");
                        byte[] data = blob.getBytes(1, (int) blob.length());
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(data));
                        } catch (IOException ex) {
                            System.out.println("ERROR: " + ex);
                        }
                        ctf = "insert";
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: Imposible cargar el formato bit de la imagen - " + e);
            }
            url[c] = ctf;
            iconos[c] = img;
        }
    }*/
    
    //METODO PARA CARGAR URLS DE LAS IMAGENES DE LOS RECLUSOS
    public void CargarURLR() {
        for (int c = 0; c < 3; c = c + 1) {
            try {
                ct = c + 1;
                st = cn.createStatement();
                rs1 = st.executeQuery("SELECT url_bit FROM Foto_recluso WHERE cod_recluso = " + cod_recluso + " AND cod_tipo_foto = " + ct);
                if (rs1.next()) {
                    ctf = rs1.getString(1);
                    if (ctf == null) {
                        img = null;
                        ctf = "";
                    } else {
                        Blob blob = rs1.getBlob("url_bit");
                        byte[] data = blob.getBytes(1, (int) blob.length());
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(data));

                        } catch (IOException ex) {
                            System.out.println("ERROR: " + ex);
                        }
                        ctf = "insert";
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: Imposible cargar el formato bit de la imagen - " + e);
            }
            url[c] = ctf;
            iconos[c] = img;
        }
    }
    //METODO PARA CARGAR LAS IMAGENES A LOS LABELS
    public void CargarImagenR()
    {
            if(iconos[0] != null){
                jLabelFoto1.setText("");
                ImageIcon icon = new ImageIcon(iconos[0].getScaledInstance(jLabelFoto1.getWidth(), jLabelFoto1.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto1.setIcon(icon);
                              
            }
            else
            {
                jLabelFoto1.setText("SIN IMAGEN");
                jLabelFoto1.setIcon(null);
            }
            if(iconos[1] != null){
                jLabelFoto2.setText("");
                ImageIcon icon = new ImageIcon(iconos[1].getScaledInstance(jLabelFoto2.getWidth(), jLabelFoto2.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto2.setIcon(icon);
            }
            else
            {
                jLabelFoto2.setText("SIN IMAGEN");
                jLabelFoto2.setIcon(null);
            }
            if(iconos[2] != null){
                jLabelFoto3.setText("");
                ImageIcon icon = new ImageIcon(iconos[2].getScaledInstance(jLabelFoto3.getWidth(), jLabelFoto3.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto3.setIcon(icon);
            }
            else
            {
                jLabelFoto3.setText("SIN IMAGEN");
                jLabelFoto3.setIcon(null);
            }
    }
    
    public void Mth_CargarReclusos()
    {
    
        
//SE CARGAN LOS VALORES DE TABLA A LOS TEXTBOX
//Obteniendo los valores de las filas
        int row = tabReclusos.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabReclusos.getValueAt(row, 0).toString();
        String nombre = tabReclusos.getValueAt(row, 1).toString();
        String apellido = tabReclusos.getValueAt(row, 2).toString();
        String edad = tabReclusos.getValueAt(row, 3).toString();
        String estado = tabReclusos.getValueAt(row, 4).toString();
        String sector = tabReclusos.getValueAt(row, 5).toString();
        String tez = tabReclusos.getValueAt(row, 6).toString();
        String altura = tabReclusos.getValueAt(row, 7).toString();
        String peso = tabReclusos.getValueAt(row, 8).toString();
        String alias = tabReclusos.getValueAt(row, 9).toString();
        String barrare = tabReclusos.getValueAt(row, 10).toString();
        String celda = tabReclusos.getValueAt(row, 11).toString();
        //String huella = tabEmpleado.getValueAt(row, 9).toString();
        //mostrando en los jtexfield y jcombobox
        cod_recluso=codigo;
        txtNombreRe.setText(nombre);
        txtApellidoRe.setText(apellido);
        txtEdadRe.setText(edad);
        cmbEstadoRe.setSelectedItem(estado);
        cmbSectorRe.setSelectedItem(sector);
        cmbTezRe.setSelectedItem(tez);
        txtAlturaRe.setText(altura);
        txtPesoRe.setText(peso);
        txtAliasRe.setText(alias);
        txtBarraRe.setText(barrare);
        cmbCelda.setSelectedItem(celda);
        //Cargando IMAGENES
        
        CargarURLR();
        CargarImagenR();
        //generando codigo de barras
        Barcode barcode = null;
         try {
            barcode = BarcodeFactory.createCode39(txtBarraRe.getText(), true);
        } catch (Exception e) {
        }
          barcode.setDrawingText(false);
         
            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
            BufferedImage image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) image.getGraphics();
            
            try {
            barcode.draw(g, 5, 20);
        } catch (Exception e) {
        }
        ImageIcon icon = new ImageIcon(image);
        jLabel_BarraRe.setIcon(icon);
        jButton4.setEnabled(true);
        jButton5.setEnabled(true);
        
        
    }
    
    private void tabReclusosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabReclusosMouseClicked
        Mth_CargarReclusos();
        Mth_CargarReclusos();
        Mth_CargarReclusos();
        Mth_CargarReclusos();
    
    }//GEN-LAST:event_tabReclusosMouseClicked
// SE ESTAN ABRIENDO LOS FORMULARIOS EN CADA BOTON
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Pregunta_Seg form = new Pregunta_Seg();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FPre == false)
        {
            form.setVisible(true);
            FPre = true;
        }
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Horario form = new Horario();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FHor == false)
        {
            form.setVisible(true);
            FHor = true;
            
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Agrupacion form = new Agrupacion();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FAgr == false)
        {
            form.setVisible(true);
            FAgr = true;
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Tez form = new Tez();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FTez == false)
        {
            form.setVisible(true);
            FTez = true;
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Crimen form = new Crimen();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FCri == false)
        {
            form.setVisible(true);
            FCri = true;
                    }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Sector form = new Sector();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FSec == false)
        {
            form.setVisible(true);
            FSec = true;
                    }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void lblImagenHuellaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenHuellaMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblImagenHuellaMouseClicked

    private void jLabel_BarraReMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BarraReMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel_BarraReMouseClicked

    private void txtRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRespuestaActionPerformed

    
    private void jLabelFoto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFoto3MouseClicked
        // TODO add your handling code here:
                //SELECCION DE IMAGENES JPG PNG Y GIF CON JFILECHOOSER
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        elegirImagen.setFileFilter(filtroImagen);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            
            url[2] = elegirImagen.getSelectedFile().getAbsolutePath();
            Image preview = Toolkit.getDefaultToolkit().getImage(url[2]);
            if(preview != null){
                jLabelFoto3.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelFoto3.getWidth(), jLabelFoto3.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto3.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jLabelFoto3MouseClicked

    private void tabVisitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabVisitaMouseClicked
        // TODO add your handling code here:
         //SE CARGAN LOS VALORES DE TABLA A LOS TEXTBOX
        //Obteniendo los valores de las filas
        int row = tabVisita.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabVisita.getValueAt(row, 0).toString();
        String nombre = tabVisita.getValueAt(row, 1).toString();
        String apellido = tabVisita.getValueAt(row, 2).toString();
        String direccion = tabVisita.getValueAt(row, 3).toString();
//        String huella = tabEmpleado.getValueAt(row, 9).toString();
        //mostrando en los jtexfield y jcombobox
        cod_visita=codigo;
        txtNombreVis.setText(nombre);
        txtApellidosVis.setText(apellido);
        txtDireccionVis.setText(direccion);
    }//GEN-LAST:event_tabVisitaMouseClicked

    private void jLabel67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel67MouseClicked
        // TODO add your handling code here:
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel)tabVisita.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaVisita();
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Visita", "Nombre", "Apellido", "Direccion"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                dtm.addRow(v);
                tabVisita.setModel(dtm);
                
                tabVisita.getColumnModel().getColumn(0).setMaxWidth(0);
                tabVisita.getColumnModel().getColumn(0).setMinWidth(0);
                tabVisita.getColumnModel().getColumn(0).setPreferredWidth(0);
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_jLabel67MouseClicked

    private void jLabel67MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel67MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel67MouseEntered

    private void jLabel67MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel67MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel67MouseExited

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        // TODO add your handling code here:
        Mto_Administrador obj = new Mto_Administrador();
        obj.setCodigo_Vis(Integer.parseInt(cod_visita));
        int eliminar = JOptionPane.showConfirmDialog(this, "Seguro?");
        if (eliminar == 0) {
            if (obj.eliminarVisita()){
                JOptionPane.showMessageDialog(this, "Datos eliminados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }//GEN-LAST:event_jLabel68MouseClicked

    private void jLabel68MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel68MouseEntered

    private void jLabel68MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel68MouseExited

    private void jLabel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseClicked
        // TODO add your handling code here:
        if (txtNombreVis.getText().equals("") || txtApellidosVis.getText().equals("")|| txtDireccionVis.getText().equals("") || txtNombreVis.getText().length() <= 10 || txtApellidosVis.getText().length() <= 10 || txtDireccionVis.getText().length() <= 10){
            JOptionPane.showMessageDialog(this, "Campos vacios o datos incompletos.");
        } else {
            Mto_Administrador obj = new Mto_Administrador(); 
           obj.setNombre_Vis(txtNombreVis.getText());
           obj.setApellido_Vis(txtApellidosVis.getText());
           obj.setDireccion(txtDireccionVis.getText());
        
        obj.setCodigo_Vis(Integer.parseInt(cod_visita));
        
        if (obj.modificarVisita()){
            obj.setTelefono(txtnNumeroV.getText());
            obj.setCodTipoTelefono((cmbTipoNumeroV.getSelectedIndex()+1));
            obj.setCodigo_Vis(Integer.parseInt(cod_visita));
            JOptionPane.showMessageDialog(this, "Datos modificados.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar.");
        }
        }
        
    }//GEN-LAST:event_jLabel69MouseClicked

    private void jLabel69MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel69MouseEntered

    private void jLabel69MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel69MouseExited

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        // TODO add your handling code here:
        if (txtNombreVis.getText().equals("") || txtApellidosVis.getText().equals("")|| txtDireccionVis.getText().equals("") || txtNombreVis.getText().length() <= 10 || txtApellidosVis.getText().length() <= 10 || txtDireccionVis.getText().length() <= 10){
            JOptionPane.showMessageDialog(this, "Campos vacios o datos incompletos.");
        } else {
           Mto_Administrador obj = new Mto_Administrador(); 
           obj.setNombre_Vis(txtNombreVis.getText());
           obj.setApellido_Vis(txtApellidosVis.getText());
           obj.setDireccion(txtDireccionVis.getText());
           
           if(obj.GuardarVisita())     {
               JOptionPane.showMessageDialog(this, "Datos guardados.");
               
               max_visitante = obj.getUltimoVisita();
                             if(obj.UVisita()){
                              max_visitante = obj.getUltimoVisita();
                         }
                            obj.setTelefono(txtnNumeroV.getText());
                           obj.setCodTipoTelefono((cmbTipoNumero.getSelectedIndex()+1));
                           obj.setCodigo(max_visitante);
                           if(obj.GuardarContactoVisita()){
                               obj.setTelefono(txtnNumeroV.getText());
                               obj.setCodTipoTelefono((cmbTipoNumeroV.getSelectedIndex()+1));
                               obj.setCodigo(max_visitante);
                           
                           }
                          
                           
                           else{
                               JOptionPane.showMessageDialog(this, "Error al guardar datos"); 
                           }
               
           }
           else     {   
               JOptionPane.showMessageDialog(this, "Error al guardar datos."); 
           } 
        }
    }//GEN-LAST:event_jLabel70MouseClicked

    private void jLabel70MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel70MouseEntered

    private void jLabel70MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel70MouseExited

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MouseClicked

    Map parameters = new HashMap();
        JasperDesign jasperDesign;
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        
        private static class JRException extends Exception {
 
        public JRException() {
        }
    }
    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        /*JasperReport jasperReport;
        JasperPrint jasperPrint;                
        try
        {
          //se carga el reporte
          URL  in=this.getClass().getResource( "reporte.jasper" );
          jasperReport=(JasperReport)JRLoader.loadObject(in);
          //se procesa el archivo jasper
          jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), this.cn );
          //se crea el archivo PDF
          JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/Dennis/Desktop/reporte.pdf");
        }
        catch (Exception ex)
        {
          System.err.println( "Error iReport: " + ex.getMessage() );
        }*/
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream ("src/prueba1.jrxml");
            jasperDesign = JRXmlLoader.load(inputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (net.sf.jasperreports.engine.JRException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }try {
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/reporte.pdf");
        } catch (net.sf.jasperreports.engine.JRException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel29MouseClicked

    
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        Estado form = new Estado();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FEst == false)
        {
            form.setVisible(true);
            FEst = true;
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void cmbEstadoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoUActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void jLabelFoto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFoto1MouseClicked
        // TODO add your handling code here:
        //SELECCION DE IMAGENES JPG PNG Y GIF CON JFILECHOOSER
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
    elegirImagen.setFileFilter(filtroImagen);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            url[0] = elegirImagen.getSelectedFile().getAbsolutePath();
            //nombre = elegirImagen.getSelectedFile().getName();
            //jTextField1.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(url[0]);
            if(preview != null){
                jLabelFoto1.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelFoto1.getWidth(), jLabelFoto1.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto1.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jLabelFoto1MouseClicked

    private void jLabelFoto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFoto2MouseClicked
        // TODO add your handling code here:
                //SELECCION DE IMAGENES JPG PNG Y GIF CON JFILECHOOSER
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
    elegirImagen.setFileFilter(filtroImagen);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            url[1] = elegirImagen.getSelectedFile().getAbsolutePath();
            //nombre = elegirImagen.getSelectedFile().getName();
            //jTextField1.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(url[1]);
            if(preview != null){
                jLabelFoto2.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelFoto2.getWidth(), jLabelFoto2.getHeight(), Image.SCALE_DEFAULT));
                jLabelFoto2.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jLabelFoto2MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        // TODO add your handling code here:
        url[0] = "";
        iconos[0] = null;
        jLabelFoto1.setText("SIN IMAGEN");
        jLabelFoto1.setIcon(null);
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        // TODO add your handling code here:
        url[1] = "";
        iconos[1] = null;
        jLabelFoto2.setText("SIN IMAGEN");
        jLabelFoto2.setIcon(null);
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
        url[2] = "";
        iconos[2] = null;
        jLabelFoto3.setText("SIN IMAGEN");
        jLabelFoto3.setIcon(null);
    }//GEN-LAST:event_jLabel48MouseClicked

    /*public ResultSet mthFiltrar()
    {
        String f = txtFiltro.getText();
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso, estado, nombre_sector, tez, altura, peso, alias, codigo_barra from ((Reclusos as r inner join Estado as e on r.cod_estado = e.cod_estado) inner join Sector as s on r.cod_sector = s.cod_sector inner join Tez as t on r.cod_tez = t.cod_tez) WHERE nombre_recluso LIKE '"+f+"%'");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
        }
        return null;
    }*/
    
    private void txtFiltroReKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroReKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtFiltroReKeyPressed

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel47MouseClicked

    private void txtFiltroReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        //Dennis Alberto Benavides Chavarria
        if (txtFiltroRe.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtFiltroReKeyTyped

    private void txtNombreReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        //Dennis Alberto Benavides Chavarria
        if (txtNombreRe.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtNombreReKeyTyped

    private void txtApellidoReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        //Dennis Alberto Benavides Chavarria
        if (txtApellidoRe.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtApellidoReKeyTyped

    private void txtEdadReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida el no dejar entrar caracteres alfabeticos
        char car = evt.getKeyChar();
        if (txtEdadRe.getText().length() >= 2) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadReKeyTyped

    private void txtAlturaReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtAlturaRe.getText().length() >= 4) {
            evt.consume();
        }
        if(Character.isAlphabetic(C))
        {

            evt.consume();
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=45 && (int)evt.getKeyChar()==47
            ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
            || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
            || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255)
        {

            evt.consume();
        }
    }//GEN-LAST:event_txtAlturaReKeyTyped

    private void txtPesoReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtPesoRe.getText().length() >= 4) {
            evt.consume();
        }
        if(Character.isAlphabetic(C))
        {

            evt.consume();
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=45 && (int)evt.getKeyChar()==47
            ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
            || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
            || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255)
        {

            evt.consume();
        }
    }//GEN-LAST:event_txtPesoReKeyTyped

    private void txtAliasReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAliasReKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtAliasRe.getText().length() >= 20) {
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
    }//GEN-LAST:event_txtAliasReKeyTyped

    private void txtNombreVisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreVisKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtNombreVis.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtNombreVisKeyTyped

    private void txtApellidosVisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosVisKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtApellidosVis.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtApellidosVisKeyTyped

    private void txtDireccionVisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionVisKeyTyped
        // TODO add your handling code here:
        if (txtDireccionVis.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionVisKeyTyped

    private void txtNombreUsuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtNombreUsu.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtNombreUsuKeyTyped

    private void txtApellidosUsuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosUsuKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtApellidosUsu.getText().length() >= 30) {
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
    }//GEN-LAST:event_txtApellidosUsuKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
        //Codigo que valida el no dejar entrar caracteres alfabeticos
        char car = evt.getKeyChar();
        if (txtEdad.getText().length() >= 3) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtUsuario.getText().length() >= 30) {
            evt.consume();
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
            ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
            || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
            || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255)
        {

            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtUsuario.getText().length() >= 30) {
            evt.consume();
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
            ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
            || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
            || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255)
        {

            evt.consume();
        }
    }//GEN-LAST:event_txtContraseñaKeyTyped

    private void txtRespuestaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyTyped
        // TODO add your handling code here:
        //Codigo que valida y no deja escribir numeros y caracteres especiales
        char C= evt.getKeyChar();
        if (txtRespuesta.getText().length() >= 50) {
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
    }//GEN-LAST:event_txtRespuestaKeyTyped

    /*private void FiltrarRegistrosRe() {
        
        DefaultTableModel dtm = (DefaultTableModel) tabReclusos.getModel();
        dtm.setRowCount(0);
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Recluso", "Nombre", "Apellido", "Edad", "Estado", "Sector", "Color de Piel", "Altura", "Peso", "Alias", "Codigo de Barra"});
        
        try {
            Filt.beforeFirst();
            while (Filt.next()) {
                Vector v = new Vector();
                v.add(Filt.getInt(1));
                v.add(Filt.getString(2));
                v.add(Filt.getString(3));
                v.add(Filt.getString(4));
                v.add(Filt.getString(5));
                v.add(Filt.getString(6));
                v.add(Filt.getString(7));
                v.add(Filt.getString(8));
                v.add(Filt.getString(9));
                v.add(Filt.getString(10));
                v.add(Filt.getString(11));
                dtm.addRow(v);
                tabReclusos.setModel(dtm);
                //Se ocultan columnas innecesarias
                mthOcultarColumRe();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
        }
    } */
       
    
    //ResultSet Filt = null;
    private void txtFiltroReKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroReKeyReleased
        // TODO add your handling code here:
        /*String cmdSQL = "select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso, estado, nombre_sector, tez, altura, peso, alias, codigo_barra from ((Reclusos as r inner join Estado as e on r.cod_estado = e.cod_estado) inner join Sector as s on r.cod_sector = s.cod_sector inner join Tez as t on r.cod_tez = t.cod_tez) WHERE nombre_recluso Like '"
                + txtFiltroRe.getText().trim() + "%'";
        Statement declara;
        try{
            declara = cn.createStatement();
            Filt = declara.executeQuery(cmdSQL);
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex+cmdSQL);
        }
        JOptionPane.showMessageDialog(null, "MCACMAS " + Filt);
        FiltrarRegistrosRe();
        mthOcultarColumRe();*/
        
    }//GEN-LAST:event_txtFiltroReKeyReleased

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        Telefono form = new Telefono();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FTel == false)
        {
            form.setVisible(true);
            FTel = true;
        }
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void cmbTipoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoNumeroActionPerformed

    private void txtnNumeroCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnNumeroCKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if (txtnNumeroC.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnNumeroCKeyTyped

    private void cmbTipoNumeroVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoNumeroVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoNumeroVActionPerformed

    private void txtnNumeroVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnNumeroVKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnNumeroVKeyTyped

    private void txtNombreReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreReActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreReActionPerformed

    /*public void mthConsultarHis() {
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel) tabHistorial.getModel();
        dtm.setRowCount(0);
        ConsultarCodR();
            obj.setCodigo_R(cod_r);
        res = obj.ConsultaHistorial();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Crimen" , "Descripcion" , "Fecha"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                dtm.addRow(v);
                tabHistorial.setModel(dtm);
                txtDescripcionHis.setText(null);
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "ERROR: " + ex);
             txtDescripcionHis.setText(null);

        }
        txtDescripcionHis.setText(null);
    }*/
    
    private void tabCitasSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCitasSiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabCitasSiMouseClicked

    private void tabCitasNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCitasNoMouseClicked
        int row = tabCitasNo.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabCitasNo.getValueAt(row, 0).toString();
        String nombres_visitas = tabCitasNo.getValueAt(row, 1).toString();
        String apellidos_visitas = tabCitasNo.getValueAt(row, 2).toString();
        String vinculos = tabCitasNo.getValueAt(row, 3).toString();
        String motivos = tabCitasNo.getValueAt(row, 4).toString();
        String nombres_recluso = tabCitasNo.getValueAt(row, 5).toString();
        String apellidos_reclusos = tabCitasNo.getValueAt(row, 6).toString();
        //mostrando en los jtexfield y jcombobox
        cod_citas = codigo;
        txtNombreCitas.setText(nombres_visitas);
        txtApellidosCitas.setText(apellidos_visitas);
        txtVinculosCitas.setText(vinculos);
        txtMotivosCitas.setText(motivos);
        txtNombreReclusoCitas.setText(nombres_recluso);
        txtApellidosReclusoCitas.setText(apellidos_reclusos);
    }//GEN-LAST:event_tabCitasNoMouseClicked

    private void jLabel90MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MouseClicked
        //declarando variable de la clase que contiene los metodos
        if (txtNombreReclusoCitas.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "registro no seleccionado.");
        } else {
            Mto_Administrador obj = new Mto_Administrador();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setCod_Citas(cod_citas);
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if (obj.modificarCitas()){
                JOptionPane.showMessageDialog(this, "Asistencia actualizada");
                mthCitas();
                mthCitasActualizadas();
            } else {
                JOptionPane.showMessageDialog(this, "Error en asistencia");
            }
        }
    }//GEN-LAST:event_jLabel90MouseClicked

    private void txtMotivosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivosCitasKeyTyped

    private void txtMotivosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotivosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivosCitasActionPerformed

    private void txtVinculosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVinculosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVinculosCitasKeyTyped

    private void txtVinculosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVinculosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVinculosCitasActionPerformed

    private void txtApellidosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosCitasKeyTyped

    private void txtApellidosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosCitasActionPerformed

    private void txtNombreCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCitasKeyTyped

    private void txtNombreCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCitasActionPerformed

    private void txtNombreRe11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreRe11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRe11ActionPerformed

    private void txtNombreRe11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRe11KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRe11KeyTyped

    private void txtNombreReclusoCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreReclusoCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreReclusoCitasActionPerformed

    private void txtNombreReclusoCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreReclusoCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreReclusoCitasKeyTyped

    private void txtApellidosReclusoCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosReclusoCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosReclusoCitasActionPerformed

    private void txtApellidosReclusoCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosReclusoCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosReclusoCitasKeyTyped

    private void jLabelFotoEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFotoEMouseClicked
        // TODO add your handling code here:
        //SELECCION DE IMAGENES JPG PNG Y GIF CON JFILECHOOSER
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        elegirImagen.setFileFilter(filtroImagen);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            url[0] = elegirImagen.getSelectedFile().getAbsolutePath();
            //nombre = elegirImagen.getSelectedFile().getName();
            //jTextField1.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(url[0]);
            if(preview != null){
                jLabelFotoE.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelFotoE.getWidth(), jLabelFoto1.getHeight(), Image.SCALE_DEFAULT));
                jLabelFotoE.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jLabelFotoEMouseClicked

    private void jLabel80MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel80MouseClicked
        // TODO add your handling code here:
        url[0] = "";
        iconos[0] = null;
        jLabelFotoE.setText("SIN IMAGEN");
        jLabelFotoE.setIcon(null);
    }//GEN-LAST:event_jLabel80MouseClicked

    public void llenarCelda(){
        
    try{
        cmbCelda.removeAllItems();
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        obj.setSector((cmbSectorRe.getSelectedIndex() + 1));;
            res = obj.LlenarCelda();
            while(res.next())
             {
           this.cmbCelda.addItem(res.getString("cod_celda"));
             }
              res = null;     
        }        
         catch (SQLException ex){
        
         }   
    }
    
    private void cmbSectorReMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbSectorReMouseClicked
           
    }//GEN-LAST:event_cmbSectorReMouseClicked

    private void cmbSectorReItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSectorReItemStateChanged
        llenarCelda();
    }//GEN-LAST:event_cmbSectorReItemStateChanged

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        jTextField1.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(true);
        jTextField1.setEnabled(false);
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
        jRadioButton3.setSelected(true);
        jRadioButton4.setSelected(false);
    }//GEN-LAST:event_jRadioButton3MouseClicked

    private void jRadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseClicked
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(true);
    }//GEN-LAST:event_jRadioButton4MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char C= evt.getKeyChar();
        if (jTextField1.getText().length() >= 10) {
            evt.consume();
        }
        if(Character.isAlphabetic(C))
        {
            evt.consume();
        }
        else if((int)evt.getKeyChar()>=32 && (int)evt.getKeyChar()<=45 //&& (int)evt.getKeyChar()==47
            ||(int)evt.getKeyChar()>=58 &&  (int)evt.getKeyChar()<=255
            || (int)evt.getKeyChar()==47)
        {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char C= evt.getKeyChar();
        if (jTextField2.getText().length() >= 4) {
            evt.consume();
        }
        if(Character.isAlphabetic(C))
        {
            evt.consume();
        }
        else if((int)evt.getKeyChar()>=32 && (int)evt.getKeyChar()<=46 //&& (int)evt.getKeyChar()==47
            ||(int)evt.getKeyChar()>=58 &&  (int)evt.getKeyChar()<=255
            || (int)evt.getKeyChar()==47)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped
    int codigo_historial;
    int codigo_r;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable1.getValueAt(row, 0).toString();
        String codigo_r = jTable1.getValueAt(row, 1).toString();
        String crimen = jTable1.getValueAt(row, 2).toString();
        String condena = jTable1.getValueAt(row, 3).toString();
        String descripcion = jTable1.getValueAt(row, 4).toString();
        String fecha = jTable1.getValueAt(row, 5).toString();
        String estado = jTable1.getValueAt(row, 6).toString();
        String fianza = jTable1.getValueAt(row, 7).toString();
        String tipo = jTable1.getValueAt(row, 8).toString();

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(fecha);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Pinche fecha TT_TT");
        }

        this.codigo_historial = Integer.parseInt(codigo);
        this.codigo_r = Integer.parseInt(codigo_r);
        jComboBox1.setSelectedItem(crimen);
        jTextField2.setText(condena);
        jTextArea1.setText(descripcion);
        jXDatePicker1.setDate(date);
        if (Double.parseDouble(fianza) == 0) {
            jRadioButton2.setSelected(true);
            jRadioButton1.setSelected(false);
            jTextField1.setEnabled(false);
            jTextField1.setText("");
        } else if (Double.parseDouble(fianza) != 0){
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(true);
            jTextField1.setEnabled(true);
            jTextField1.setText(fianza);
        }
        if (Integer.parseInt(tipo) == 0) {
            jRadioButton3.setSelected(true);
            jRadioButton4.setSelected(false);
        } else if (Integer.parseInt(tipo) == 1){
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(true);
        }

        //mostrando en los jtexfield y jcombobox
        //this.cod_empleado = codigo; -----------------------------------------------Deshabilitado temporalmente--------------------

        //Deshabilitando guardar
        //jLabel63.setVisible(false);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
        mostrarHistorialR();
        suma();
        limpiarH();
        DiasFaltantes();
        //jTextField4.setVisible(false);
        //limpiar();
        //jLabel55.setVisible(true);
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jLabel100MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseEntered
        //Color cl = new Color(0,167,157);
        //jLabel46.setForeground(cl);
    }//GEN-LAST:event_jLabel100MouseEntered

    private void jLabel100MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseExited
        Color cl = new Color(0,0,0);
        //jLabel46.setForeground(cl);
    }//GEN-LAST:event_jLabel100MouseExited

    private void jLabel101MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseClicked
        if (jComboBox1.getSelectedIndex() == -1 || jTextField2.getText().equals("") || jXDatePicker1.equals("")){
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {
            int codigo_r = Integer.parseInt(this.cod_recluso);
            //int codigo_r = Integer.parseInt(jTextField4.getText());
            int crimen = jComboBox1.getSelectedIndex() + 1;
            Double fianza = null;
            if (jRadioButton1.isSelected()){
                if (jTextField1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Campos vacios");
                } else {
                    fianza = Double.parseDouble(jTextField1.getText());
                    String fecha = null;
                    Calendar gc = new GregorianCalendar();
                    Date fecha_actual = gc.getTime();
                    Date date = jXDatePicker1.getDate();
                    if (date.getTime() > fecha_actual.getTime()){
                        //error
                        JOptionPane.showMessageDialog(this, "La fecha debe ser menor o igual a la actual");
                    } else {
                        gc.setTime(date);
                        int dia = gc.get(gc.DAY_OF_MONTH);
                        int mes = gc.get(gc.MONTH);
                        int anio = gc.get(gc.YEAR);
                        fecha = dia + "-" + (mes + 1)   + "-" + anio;
                        int tipo_condena = 0;
                        if (jRadioButton3.isSelected()){
                            tipo_condena = 0; //mes
                        } else if (jRadioButton4.isSelected()){
                            tipo_condena = 1; //anio
                        }
                        int condena = (Integer.parseInt(jTextField2.getText()));
                        String descripcion = jTextArea1.getText();

                        Mto_Administrador obj = new Mto_Administrador();
                        obj.setCodigo_r(codigo_r);
                        obj.setCod_crimen(crimen);
                        obj.setCondena(condena);
                        obj.setDescripcion(descripcion);
                        obj.setFecha(fecha);
                        obj.setEstado_condena(0);
                        obj.setFianza(fianza);
                        obj.setTipo_condena(tipo_condena);

                        if (obj.GuardarHistorialR()){
                            JOptionPane.showMessageDialog(this, "Datos guardados");

                            mostrarHistorialR();
                            limpiarH();
                            suma();

                            //fecha salida
                            dia = dia + Integer.parseInt(jTextField3.getText());
                            String fecha_s = dia + "-" + (mes + 1) + "-" + anio;
                            Calendar gc2 = new GregorianCalendar();
                            SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
                            Date fecha_salida = null;
                            try {
                                fecha_salida = ft.parse(fecha_s);
                            } catch (ParseException ex) {
                                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            gc2.setTime(fecha_salida);
                            int dia1 = gc2.get(gc.DAY_OF_MONTH);
                            int mes1 = gc2.get(gc.MONTH);
                            int anio1 = gc2.get(gc.YEAR);
                            String fecha_out = dia1 + "-" + (mes1 + 1)+ "-" + anio1;
                            obj.setFecha_2(fecha_out);
                            obj.setCodigo_r(codigo_r);
                            obj.modificarFechaSalida();

                        } else {
                            JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                        }
                    }
                }
            } else if (jRadioButton2.isSelected()){
                fianza = 0.0;
                String fecha = null;
                Calendar gc = new GregorianCalendar();
                Date fecha_actual = gc.getTime();
                Date date = jXDatePicker1.getDate();
                if (date.getTime() > fecha_actual.getTime()){
                    //error
                    JOptionPane.showMessageDialog(this, "La fecha debe ser menor o igual a la actual");
                } else {
                    gc.setTime(date);
                    int dia = gc.get(gc.DAY_OF_MONTH);
                    int mes = gc.get(gc.MONTH);
                    int anio = gc.get(gc.YEAR);
                    fecha = dia + "-" + (mes + 1)+ "-" + anio;
                    int tipo_condena = 0;
                    if (jRadioButton3.isSelected()){
                        tipo_condena = 0; //mes
                    } else if (jRadioButton4.isSelected()){
                        tipo_condena = 1; //anio
                    }
                    int condena = (Integer.parseInt(jTextField2.getText()));
                    String descripcion = jTextArea1.getText();

                    Mto_Administrador obj = new Mto_Administrador();
                    obj.setCodigo_r(codigo_r);
                    obj.setCod_crimen(crimen);
                    obj.setCondena(condena);
                    obj.setDescripcion(descripcion);
                    obj.setFecha(fecha);
                    obj.setEstado_condena(0);
                    obj.setFianza(fianza);
                    obj.setTipo_condena(tipo_condena);

                    if (obj.GuardarHistorialR()){
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                        mostrarHistorialR();
                        limpiarH();
                        suma();

                        //fecha salida
                        dia = dia + Integer.parseInt(jTextField3.getText());
                        String fecha_s = dia + "-" + (mes + 1) + "-" + anio;
                        Calendar gc2 = new GregorianCalendar();
                        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
                        Date fecha_salida = null;
                        try {
                            fecha_salida = ft.parse(fecha_s);

                        } catch (ParseException ex) {
                            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gc2.setTime(fecha_salida);
                        int dia1 = gc2.get(gc.DAY_OF_MONTH);
                        int mes1 = gc2.get(gc.MONTH);
                        int anio1 = gc2.get(gc.YEAR);
                        String fecha_out = dia1 + "-" + (mes1 + 1)+ "-" + anio1;
                        obj.setFecha_2(fecha_out);
                        obj.setCodigo_r(codigo_r);
                        obj.modificarFechaSalida();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                    }
                }
            }
            mostrarHistorialR();
            limpiarH();
            suma();
            DiasFaltantes();
        }
    }//GEN-LAST:event_jLabel101MouseClicked

    private void jLabel101MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseEntered
        Color cl = new Color(0,167,157);
        jLabel55.setForeground(cl);
    }//GEN-LAST:event_jLabel101MouseEntered

    private void jLabel101MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseExited
        Color cl = new Color(0,0,0);
        jLabel55.setForeground(cl);
    }//GEN-LAST:event_jLabel101MouseExited

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
        if (jComboBox1.getSelectedIndex() == -1 || jTextField2.getText().equals("") || jXDatePicker1.equals("")){
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {
            //int codigo_r = Integer.parseInt(this.cod_recluso);  -----------------------------------Temporalmente deshabilitada---------------
            int historial = this.codigo_historial;
            int codigo_re = codigo_r;
            int crimen = jComboBox1.getSelectedIndex() + 1;
            Double fianza = null;
            if (jRadioButton1.isSelected()){
                if (jTextField1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Campos vacios");
                } else {
                    fianza = Double.parseDouble(jTextField1.getText());
                    String fecha = null;
                    Calendar gc = new GregorianCalendar();
                    Date fecha_actual = gc.getTime();
                    Date date = jXDatePicker1.getDate();
                    if (date.getTime() > fecha_actual.getTime()){
                        //error
                        JOptionPane.showMessageDialog(this, "La fecha debe ser menor o igual a la actual");
                    } else {
                        gc.setTime(date);
                        int dia = gc.get(gc.DAY_OF_MONTH);
                        int mes = gc.get(gc.MONTH);
                        int anio = gc.get(gc.YEAR);
                        fecha = dia + "-" + (mes + 1) + "-" + anio;
                        int tipo_condena = 0;
                        if (jRadioButton3.isSelected()){
                            tipo_condena = 0; //mes
                        } else if (jRadioButton4.isSelected()){
                            tipo_condena = 1; //anio
                        }
                        int condena = (Integer.parseInt(jTextField2.getText()));
                        String descripcion = jTextArea1.getText();

                        Mto_Administrador obj = new Mto_Administrador();
                        obj.setCodigo_r(codigo_re);
                        obj.setCod_crimen(crimen);
                        obj.setCondena(condena);
                        obj.setDescripcion(descripcion);
                        obj.setFecha(fecha);
                        obj.setEstado_condena(0);
                        obj.setFianza(fianza);
                        obj.setTipo_condena(tipo_condena);
                        obj.setCod_historial_r(codigo_historial);

                        if (obj.modificarHistorial()){
                            JOptionPane.showMessageDialog(this, "Datos guardados");

                            mostrarHistorialR();
                            limpiarH();
                            suma();

                            //fecha salida
                            dia = dia + Integer.parseInt(jTextField3.getText());
                            String fecha_s = dia + "-" + (mes + 1) + "-" + anio;
                            Calendar gc2 = new GregorianCalendar();
                            SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
                            Date fecha_salida = null;
                            try {
                                fecha_salida = ft.parse(fecha_s);

                            } catch (ParseException ex) {
                                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            gc2.setTime(fecha_salida);
                            int dia1 = gc2.get(gc.DAY_OF_MONTH);
                            int mes1 = gc2.get(gc.MONTH);
                            int anio1 = gc2.get(gc.YEAR);
                            String fecha_out = dia1 + "-" + (mes1 + 1)+ "-" + anio1;
                            obj.setFecha_2(fecha_out);
                            obj.setCodigo_r(codigo_r);
                            obj.modificarFechaSalida();
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                        }
                    }
                }
            } else if (jRadioButton2.isSelected()){
                fianza = 0.0;
                String fecha = null;
                Calendar gc = new GregorianCalendar();
                Date fecha_actual = gc.getTime();
                Date date = jXDatePicker1.getDate();
                if (date.getTime() > fecha_actual.getTime()){
                    //error
                    JOptionPane.showMessageDialog(this, "La fecha debe ser menor o igual a la actual");
                } else {
                    gc.setTime(date);
                    int dia = gc.get(gc.DAY_OF_MONTH);
                    int mes = gc.get(gc.MONTH);
                    int anio = gc.get(gc.YEAR);
                    fecha = dia + "-" + (mes + 1)+ "-" + anio;
                    int tipo_condena = 0;
                    if (jRadioButton3.isSelected()){
                        tipo_condena = 0; //mes
                    } else if (jRadioButton4.isSelected()){
                        tipo_condena = 1; //anio
                    }
                    int condena = (Integer.parseInt(jTextField2.getText()));
                    String descripcion = jTextArea1.getText();

                    Mto_Administrador obj = new Mto_Administrador();
                    obj.setCodigo_r(codigo_re);
                    obj.setCod_crimen(crimen);
                    obj.setCondena(condena);
                    obj.setDescripcion(descripcion);
                    obj.setFecha(fecha);
                    obj.setEstado_condena(0);
                    obj.setFianza(fianza);
                    obj.setTipo_condena(tipo_condena);
                    obj.setCod_historial_r(codigo_historial);

                    if (obj.modificarHistorial()){
                        JOptionPane.showMessageDialog(this, "Datos guardados");
                        mostrarHistorialR();
                        limpiarH();
                        suma();

                        //fecha salida
                        dia = dia + Integer.parseInt(jTextField3.getText());
                        String fecha_s = dia + "-" + (mes + 1) + "-" + anio;
                        Calendar gc2 = new GregorianCalendar();
                        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
                        Date fecha_salida = null;
                        try {
                            fecha_salida = ft.parse(fecha_s);

                        } catch (ParseException ex) {
                            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gc2.setTime(fecha_salida);
                        int dia1 = gc2.get(gc.DAY_OF_MONTH);
                        int mes1 = gc2.get(gc.MONTH);
                        int anio1 = gc2.get(gc.YEAR);
                        String fecha_out = dia1 + "-" + (mes1 + 1)+ "-" + anio1;
                        obj.setFecha_2(fecha_out);
                        obj.setCodigo_r(codigo_r);
                        obj.modificarFechaSalida();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                    }
                }
            }
            limpiarH();
            mostrarHistorialR();
            suma();
            DiasFaltantes();
        }
    }//GEN-LAST:event_jLabel102MouseClicked

    private void jLabel102MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseEntered
//        Color cl = new Color(0,167,157);
//        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel102MouseEntered

    private void jLabel102MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseExited
//        Color cl = new Color(0,0,0);
//        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel102MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        panel_historialR.setVisible(false);
        panel_recluso.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jXDatePicker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void tabcitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabcitasMouseClicked
        int row = tabcitas.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = tabcitas.getValueAt(row, 0).toString();
        String nombre = tabcitas.getValueAt(row, 1).toString();
        String apellido = tabcitas.getValueAt(row, 2).toString();
        String nombrer = tabcitas.getValueAt(row, 3).toString();
        String apellidor = tabcitas.getValueAt(row, 4).toString();
        String fecha = tabcitas.getValueAt(row, 5).toString();
        String hora = tabcitas.getValueAt(row, 6).toString();
        String Vinculo = tabcitas.getValueAt(row, 7).toString();
        String Motivos = tabcitas.getValueAt(row, 8).toString();

        //mostrando en los jtexfield y jcombobox
        txtNombreV.setText(nombre );
        txtNombreR.setText(nombrer);
        jTextField6.setText(codigo);
        txtVinculo.setText(Vinculo);
        txtMotivos.setText(Motivos);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = df.parse("2016-05-30 " + hora + ":00");
            Calendar gc = new GregorianCalendar();
            gc.setTime(d);
            int h1 = gc.get(gc.HOUR_OF_DAY);
            int m1 = gc.get(gc.MINUTE);
            jComboBox2.setSelectedIndex(h1);
            jComboBox3.setSelectedIndex(m1);

        } catch (ParseException ex) {
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date=null;
        SimpleDateFormat des = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = des.parse(fecha);
        } catch (ParseException ex){
            JOptionPane.showMessageDialog(null, "fecha");
        }

        jXDatePicker2.setDate(date);
        //jTextField27.setText(monto);
        // jTextField28.setText(mes);
        // mostrarDetalle();
        //suma();
        //setEnabledD();

    }//GEN-LAST:event_tabcitasMouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        mostraras();
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel72MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel72MouseEntered

    private void jLabel72MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel72MouseExited

    private void jLabel76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseClicked
        Mto_Administrador obj = new Mto_Administrador();
        obj.setCod_citass(Integer.parseInt(jTextField3.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Seguro?");
        if (eliminar == 0) {
            if (obj.eliminarcitas()){
                JOptionPane.showMessageDialog(this, "Datos eliminados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
            mostraras();
        }
    }//GEN-LAST:event_jLabel76MouseClicked

    private void jLabel76MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel76MouseEntered

    private void jLabel76MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel76MouseExited

    private void jLabel85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseClicked
        Mto_Administrador obj = new Mto_Administrador();
        obj.setCod_vis(cod_vis);
        obj.setCod_rec(cod_re);
        obj.setCod_asis(1);
        obj.setVinculo((txtVinculo.getText()));
        obj.setmotivos(txtMotivos.getText());
        String hora1 = (jComboBox2.getSelectedItem() + ":" + jComboBox3.getSelectedItem());
        obj.setHour(hora1);
        String fecha = null;
        Calendar gc = new GregorianCalendar();
        Date date = jXDatePicker2.getDate();
        gc.setTime(date);
        int dia =gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH);
        int anio = gc.get(gc.YEAR);
        fecha = dia + "-" + (mes + 1) + "-" + anio;
        obj.setFechs(fecha);

        if(obj.Guardarcitas())     {
            JOptionPane.showMessageDialog(this, "Datos guardados");
        }
        else     {
            JOptionPane.showMessageDialog(this, "Error al guardar datos");
        }
        //ejecuta el metodo de limpiar campos

    }//GEN-LAST:event_jLabel85MouseClicked

    private void jLabel85MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseEntered
        Color cl = new Color(0,167,157);
        jLabel55.setForeground(cl);
    }//GEN-LAST:event_jLabel85MouseEntered

    private void jLabel85MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseExited
        Color cl = new Color(0,0,0);
        jLabel55.setForeground(cl);
    }//GEN-LAST:event_jLabel85MouseExited

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow();
        //guardando los valores de la tabla

        int codigo = Integer.parseInt(jTable2.getValueAt(row, 0).toString());
        String nombre = jTable2.getValueAt(row, 1).toString();
        String apellido = jTable2.getValueAt(row, 2).toString();
        String edad = jTable2.getValueAt(row, 3).toString();
        //mostrando en los jtexfield y jcombobox
        txtNombreV.setText(nombre);
        //txtApellidoV.setText(apellido);
        this.cod_vis = codigo;

    }//GEN-LAST:event_jTable2MouseClicked

    private void txtNombreRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRKeyTyped

        //rece();

        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        obj.setNombre_Vis(txtNombreR.getText());
        DefaultTableModel dtm = (DefaultTableModel)jTable3.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaVisitas();
        //ENCABEZADOS DE COLUMNAS
        dtm.setColumnIdentifiers(new Object[]{"codigo recluso", "Nombre", "Apellido", "Edad"});
        try{
            while(res.next()){
                //DATOS QUE SE CARGARAN A COLUMNAS
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                dtm.addRow(v);
                jTable3.setModel(dtm);

                //SE OCULTAN LAS COLUMNAS INNESESARIAS
                jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable3.getColumnModel().getColumn(0).setMinWidth(0);
                jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);

            }
        }catch(Exception ex){

        }
    }//GEN-LAST:event_txtNombreRKeyTyped

    private void txtNombreVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreVKeyTyped

        // recept();
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        obj.setNombre_recluso(txtNombreV.getText());
        DefaultTableModel dtm = (DefaultTableModel)jTable2.getModel();
        dtm.setRowCount(0);
        res = obj.Consultarecluso();
        //ENCABEZADOS DE COLUMNAS
        dtm.setColumnIdentifiers(new Object[]{"codigo visita", "Nombre", "Apellido", "Direccion"});
        try{
            while(res.next()){
                //DATOS QUE SE CARGARAN A COLUMNAS
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                dtm.addRow(v);
                jTable2.setModel(dtm);

                //SE OCULTAN LAS COLUMNAS INNESESARIAS
                jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable2.getColumnModel().getColumn(0).setMinWidth(0);
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);

            }
        }catch(Exception ex){

        }

    }//GEN-LAST:event_txtNombreVKeyTyped

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jLabel86MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel86MouseClicked
        {
            //declarando variable de la clase que contiene los metodos
            Mto_Administrador obj = new Mto_Administrador();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setCod_citass(Integer.parseInt(jTextField6.getText()));
            obj.setCod_vis(cod_vis);
            obj.setCod_rec(cod_re);
            obj.setVinculo(txtVinculo.getText());
            obj.setVinculo(txtMotivos.getText());
            int juan = 1;
            obj.setCod_asis(juan);

            String fecha = null;
            Calendar gc = new GregorianCalendar();
            Date date = jXDatePicker2.getDate();
            gc.setTime(date);
            int dia =gc.get(gc.DAY_OF_MONTH);
            int mes = gc.get(gc.MONTH);
            int anio = gc.get(gc.YEAR);
            fecha = dia + "-" + (mes + 1) + "-" + anio;
            obj.setFechs(fecha);

            String hora1 = (jComboBox2.getSelectedItem() + ":" + jComboBox3.getSelectedItem());
            obj.setHour(hora1);

            {
                if(obj.modificarcitas()){
                    JOptionPane.showMessageDialog(this, "Datos modificados");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar los datos");
                }

            }

            //ejecuta el metodo de limpiar campos
            //mthLimpiarCampos();
        }

    }//GEN-LAST:event_jLabel86MouseClicked

    private void jLabel86MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel86MouseEntered
//        Color cl = new Color(0,167,157);
//        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel86MouseEntered

    private void jLabel86MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel86MouseExited
//        Color cl = new Color(0,0,0);
//        jLabel57.setForeground(cl);
    }//GEN-LAST:event_jLabel86MouseExited

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int row = jTable3.getSelectedRow();
        //guardando los valores de la tabla

        int codigo = Integer.parseInt(jTable3.getValueAt(row, 0).toString());
        String nombre = jTable3.getValueAt(row, 1).toString();

        String apellido = jTable3.getValueAt(row, 2).toString();
        String direccion = jTable3.getValueAt(row, 3).toString();
        //mostrando en los jtexfield y jcombobox
        txtNombreR.setText(nombre );
        //txtApellidoR.setText(apellido);
        this.cod_re = codigo;
    }//GEN-LAST:event_jTable3MouseClicked

    private void panel_citasAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_panel_citasAncestorAdded

    }//GEN-LAST:event_panel_citasAncestorAdded

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        
        Reporte_Citas_Recluso form = new Reporte_Citas_Recluso();
        //Se valida la cantidad de veces que se puede abrir este form
        if(FRcir == false)
        {
            form.setVisible(true);
            FRcir = true;
        }
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        panel_historialR.setVisible(true);
        panel_recluso.setVisible(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        Reporte_Citas obj = new Reporte_Citas();
        obj.setVisible(true);
    }//GEN-LAST:event_jLabel71MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Reporte_historial obj = new Reporte_historial(Integer.parseInt(cod_recluso));
        obj.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabReclusosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabReclusosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabReclusosMouseEntered

    private void txt_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_datosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_datosActionPerformed

    public void mostrarHistorialR(){
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        //obj.setCodigo_r(Integer.parseInt(cod_empleado)); ----------------------------------temporalmente deshabilitada--------------------------.
        obj.setCodigo_r(Integer.parseInt(cod_recluso));
        res = obj.ConsultaHistorial();
        dtm.setColumnIdentifiers(new Object[]{"Codigo", "Cod recluso", "Crimen", "Condena", "Descripcion", "Fecha", "Estado", "Fianza","Tipo_condena", "Condena (dias)"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getInt(2));
                v.add(res.getString(3));
                v.add(res.getInt(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getInt(7));
                v.add(res.getDouble(8));
                v.add(res.getInt(9));
                int tipo = res.getInt(9);
                int condena = res.getInt(4);
                int dato = 0;
                if (tipo == 0){
                    dato = condena * 30;
                } else if (tipo == 1){
                    dato = condena * 365;
                }
                v.add(dato);
                
                dtm.addRow(v);
                jTable1.setModel(dtm);
                
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(1).setMinWidth(0);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(4).setMinWidth(0);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(3).setMinWidth(0);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(6).setMinWidth(0);
                jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(7).setMinWidth(0);
                jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
                
                jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(8).setMinWidth(0);
                jTable1.getColumnModel().getColumn(8).setPreferredWidth(0);
            }
        }catch(Exception ex){
            
        }
    }
    
     String fo;
    public void reus(){
        
        String xas= txtNombreR.getText();
        mtdosadmin.setApellido_Vis(xas);
        try {
   
        if(mtdosadmin.mostrar_visitaape()) 
        {
            nombre_vis = mtdosadmin.getNombre_Vis();
            apellido_vis = mtdosadmin.getApellido_Vis();
            direccion = mtdosadmin.getDireccion();
            cod_vis = mtdosadmin.getCodigo_Vis();
            try{
                DefaultTableModel temp = (DefaultTableModel)jTable2.getModel();
                temp.setRowCount(0);
            /*int a = temp.getRowCount()-1;
            for(int i=0; i<a; i++)
                temp.removeRow(i);*/
            }catch (Exception e){
                //System.out.println(e);
            }
            
            System.out.println(nombre_recluso);
            System.out.println(apellido_recluso);
            System.out.println(edad );
            
            receptoA = apellido_recluso;
            receptoR = nombre_recluso;
            receptoZ = edad;
            
            model.addRow(new Object[]{nombre_recluso, apellido_recluso, edad});
            fo = xas;
            txtNombreR.setText("");
          
         
         }
        } catch (Exception e) {
        }
    }
    
    public void mostraras(){
        ResultSet res;
        Mto_Administrador obj = new Mto_Administrador();
        DefaultTableModel dtm = (DefaultTableModel)tabcitas.getModel();
        dtm.setRowCount(0);
        res = obj.Consultacitas();
        dtm.setColumnIdentifiers(new Object[]{"cod","Nombre del visitante", "Apellido del visitante", "Nombre del recluso", "apellido del recluso","Fecha", "Hora","Vinculo","Motivos"});
        try{
            while(res.next()){
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                v.add(res.getString(9));
                
                dtm.addRow(v);
                tabcitas.setModel(dtm);
                
                tabcitas.getColumnModel().getColumn(0).setMaxWidth(0);
                tabcitas.getColumnModel().getColumn(0).setMinWidth(0);
                tabcitas.getColumnModel().getColumn(0).setPreferredWidth(0);
                
                tabcitas.getColumnModel().getColumn(6).setMaxWidth(0);
                tabcitas.getColumnModel().getColumn(6).setMinWidth(0);
                tabcitas.getColumnModel().getColumn(6).setPreferredWidth(0);
            }
        }catch(Exception ex){
            
        
            
        }
    }
    
    public void suma(){
        //columna 9
        int suma = 0;
        int filas = jTable1.getRowCount();
        for (int i = 0; i < filas; i++){
            suma = suma + Integer.parseInt(jTable1.getValueAt(i, 9).toString());
        }
        jTextField3.setText(String.valueOf(suma));
        Mto_Administrador obj = new Mto_Administrador();
        obj.setCondena(suma);
        obj.setCodigo_r(Integer.parseInt(cod_recluso));
        obj.modificarCondena();
    }
    
    public void limpiarH(){
        jComboBox1.setSelectedIndex(0);
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(true);
        jRadioButton4.setSelected(false);
        jTextField1.setText("");
        jTextField2.setText("");
        jTextArea1.setText("");
        Calendar gc = new GregorianCalendar();
        Date fecha = gc.getTime();
        jXDatePicker1.setDate(fecha);
        //suma();
    }
    
    public void DiasFaltantes(){
        Mto_Administrador obj = new Mto_Administrador();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //fecha actual
        Calendar gc = new GregorianCalendar();
        int dia = gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH);
        int anio = gc.get(gc.YEAR);
        String fecha_ahora = dia  + "-" + (mes + 1)+ "-" + anio;
        
        //fecha final
        obj.setCodigo_r(Integer.parseInt(cod_recluso));
        obj.Obtener_fecha();
        String fecha_txt = obj.getFecha();
        Date fecha_final = null;
        try {
            fecha_final = df.parse(fecha_txt);
        } catch (ParseException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        gc.setTime(fecha_final);
        int dia1 = gc.get(gc.DAY_OF_MONTH);
        int mes1 = gc.get(gc.MONTH);
        int anio1 = gc.get(gc.YEAR);
        String fecha_salida = dia1  + "-" + (mes1 + 1)+ "-" + anio1;
        
        //DateFormat df = new DateFormat(DateFormat.MEDIUM);
        String[] fechaFinal = fecha_salida.split("-"); //fecha menor
        Integer diaActual = Integer.parseInt(fechaFinal[0]);
        Integer mesActual = Integer.parseInt(fechaFinal[1]);
        Integer anioActual = Integer.parseInt(fechaFinal[2]);
        

        String[] fechaActual = fecha_ahora.split("-"); //fecha mayor        
        Integer diaInicio = Integer.parseInt(fechaActual[0]);
        Integer mesInicio = Integer.parseInt(fechaActual[1]);
        Integer anioInicio = Integer.parseInt(fechaActual[2]);
        

        int b = 0;
        int dias = 0;
        int mes_ = 0;
        int anios = 0;
        int meses = 0;
        mes_ = mesInicio - 1;
        if (mes_ == 2) {
            if ((anioActual % 4 == 0) && ((anioActual % 100 != 0) || (anioActual % 400 == 0))) {
                b = 29;
            } else {
                b = 28;
            }
        } else if (mes_ <= 7) {
            if (mes_ == 0) {
                b = 31;
            } else if (mes_ % 2 == 0) {
                b = 30;
            } else {
                b = 31;
            }
        } else if (mes_ > 7) {
            if (mes_ % 2 == 0) {
                b = 31;
            } else {
                b = 30;
            }
        }
        if ((anioInicio > anioActual) || (anioInicio == anioActual && mesInicio > mesActual)
                || (anioInicio == anioActual && mesInicio == mesActual && diaInicio > diaActual)) {
            System.out.println("La fecha de inicio debe ser anterior a la fecha Actual");
        } else {
            if (mesInicio <= mesActual) {
                anios = anioActual - anioInicio;
                if (diaInicio <= diaActual) {
                    meses = mesActual - mesInicio;
                    dias = b - (diaInicio - diaActual);
                } else {
                    if (mesActual == mesInicio) {
                        anios = anios - 1;
                    }
                    meses = (mesActual - mesInicio - 1 + 12) % 12;
                    dias = b - (diaInicio - diaActual);
                }
            } else {
                anios = anioActual - anioInicio - 1;
                System.out.println(anios);
                if (diaInicio > diaActual) {
                    meses = mesActual - mesInicio - 1 + 12;
                    dias = b - (diaInicio - diaActual);
                } else {
                    meses = mesActual - mesInicio + 12;
                    dias = diaActual - diaInicio;
                }
            }
        }

        System.out.println("Años: " + anios);
        System.out.println("Meses: " + meses);
        System.out.println("Días: " + dias);
        jLabel106.setText(String.valueOf(dias));
        jLabel108.setText(String.valueOf(meses));
        jLabel110.setText(String.valueOf(anios));
    }
    
    public void verificarfecha(){
        Calendar gc = new GregorianCalendar();
        Date fecha_s = gc.getTime();
        int dia = gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH);
        int anio = gc.get(gc.YEAR);
        String fecha_sistema = dia + "-" + (mes + 1) + "-" + anio;
        Mto_Administrador obj = new Mto_Administrador();
        obj.setFecha(fecha_sistema);
        obj.ObtenerReclusos();
        int cant = obj.getNumero();
        if (cant != 0){
            obj.modificarLibertad();
            for (int i = 0; i < cant; i++){
                obj.ObtenerR();
                int codigo = obj.getCodigo_r();
                obj.modificarEstadoCrimen();
            }           
        }
  
    }
    
        
   //--------------------------------------------HUELLERO FOR LOL-----------------------------------------------------//
    
Conexion conex = new Conexion();
 public DPFPTemplate getTemplate() {
return template;
}
 private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

//Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
// y poder estimar la creacion de un template de la huella para luego poder guardarla
private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
//Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
// o verificarla con alguna guardada en la BD
private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
// necesarias de la huella si no ha ocurrido ningun problema

private DPFPTemplate template;
public static String TEMPLATE_PROPERTY = "template";   

public void EnviarTexto(String string) {
txt_datos.setText(string + "\n");
} 
    

    
public DPFPFeatureSet featuresinscripcion;
public DPFPFeatureSet featuresverificacion;

public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
try {
return extractor.createFeatureSet(sample, purpose);
}
catch (DPFPImageQualityException e) {
return null;
}
}


protected void Iniciar(){
Lector.addDataListener(new DPFPDataAdapter() {
@Override public void dataAcquired(final DPFPDataEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("La Huella Digital ha sido Capturada");
ProcesarCaptura(e.getSample());
}
});
}
});

Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
@Override public void readerConnected(final DPFPReaderStatusEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
}
});
}

@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
}
});
}
});

Lector.addSensorListener(new DPFPSensorAdapter() {
@Override public void fingerTouched(final DPFPSensorEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
}
});
}

@Override public void fingerGone(final DPFPSensorEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El dedo ha sido quitado del Lector de Huella");
}
});
}
});

Lector.addErrorListener(new DPFPErrorAdapter(){
public void errorReader(final DPFPErrorEvent e){
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("Error: "+e.getError());
}
});
}
});
}


 
public void identificarHuella() throws IOException{
   // AudioClip sonido;
   // sonido= java.applet.Applet.newAudioClip(getClass().getResource(""));
    //sonido.play();
try{
//Establece los valores para la sentencia SQL
Connection c= conex.conectar();

//Obtiene todas las huellas de la bd
PreparedStatement identificarStmt = c.prepareStatement("SELECT nombre_usuario , huella FROM Empleado ");
//Obtiene todas las huellas de la bd
ResultSet rsIdentificar = identificarStmt.executeQuery();

//Si se encuentra el nombre en la base de datos
//byte templateBuffer[] = null;
int i=0;
while(rsIdentificar.next()){
i++;
System.out.println("SQL:"+rsIdentificar.getString(1)+"\n");
System.out.println("Contador:"+i+"\n");

byte templateBuffer[] = rsIdentificar.getBytes("huella");
//Crea una nueva plantilla a partir de la guardada en la base de datos
DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
//Envia la plantilla creada al objeto contendor de Template del componente de huella digital
setTemplate(referenceTemplate);

// Compara las caracteriticas de la huella recientemente capturda con la
// alguna plantilla guardada en la base de datos que coincide con ese tipo
DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

//compara las plantilas (actual vs bd)
//Si encuentra correspondencia dibuja el mapa
//e indica el nombre de la persona que coincidió.
if (result.isVerified()){
//crea la imagen de los datos guardado de las huellas guardadas en la base de datos
JOptionPane.showMessageDialog(null, "Bienvenido "+rsIdentificar.getString("nombre_usuario"));
return;
}
}
//Si no encuentra alguna huella que coincida lo indica con un mensaje
JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella.");
}
catch (SQLException e) {
System.out.println("Se produjo el siguiente error: "+e.getMessage());
e.printStackTrace();
}
finally{
//con.desconectar();
}
}

public void ProcesarCaptura(DPFPSample sample){
// Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

// Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

// Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
if (featuresinscripcion != null){
try{
System.out.println("Las Caracteristicas de la Huella han sido creada");
Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

// Dibuja la huella dactilar capturada.
Image image=CrearImagenHuella(sample);
DibujarHuella(image);

//btnIdentificar.setEnabled(true);
}
catch (DPFPImageQualityException ex) {
System.err.println("Error: "+ex.getMessage());
}

finally {
 EstadoHuellas();

// Comprueba si la plantilla se ha creado.
switch(Reclutador.getTemplateStatus()){
case TEMPLATE_STATUS_READY:    // informe de éxito y detiene  la captura de huellas
stop();
setTemplate(Reclutador.getTemplate());
EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla");
//btnIdentificar.setEnabled(true);
//btnGuardar.setEnabled(true);
//btnGuardar.grabFocus();
break;

case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
Reclutador.clear();
stop();
//EstadoHuellas();
setTemplate(null);
JOptionPane.showMessageDialog(Administrador.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
start();
break;
}
}
}
}

public void DibujarHuella(Image image) {
lblImagenHuella.setIcon(new ImageIcon(
image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
repaint();
}

public void start(){
Lector.startCapture();
EnviarTexto("Utilizando el Lector de Huella Dactilar ");
}

public void setTemplate(DPFPTemplate template) {
DPFPTemplate old = this.template;
this.template = template;
firePropertyChange(TEMPLATE_PROPERTY, old, template);
}

public Image CrearImagenHuella(DPFPSample sample) {
return DPFPGlobal.getSampleConversionFactory().createImage(sample);
}

public void stop(){
Lector.stopCapture();
EnviarTexto("No se está usando el Lector de Huella Dactilar ");
}

public  void EstadoHuellas(){
EnviarTexto("Muestra de Huellas Necesarias para Guardar Template "+ Reclutador.getFeaturesNeeded());
}



public void guardarHuella(){
//Obtiene los datos del template de la huella actual
ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
Integer tamañoHuella=template.serialize().length;

//Capturamos los datos del usuario
//String cedula = JOptionPane.showInputDialog("No. Identificacion:");
try {
//Establece los valores para la sentencia SQL
Connection c= conex.conectar(); //establece la conexion con la BD
PreparedStatement verificaCedulaUsuario = c.prepareStatement("select nombre_usuario, huella from Empleado where nombre_usuario=?");
verificaCedulaUsuario.setString(1, usuario);
ResultSet rs = verificaCedulaUsuario.executeQuery();

if(rs.next()){

PreparedStatement guardarStmt2 = c.prepareStatement("update Empleado set huella=? where nombre_usuario=?");
guardarStmt2.setBinaryStream(1, datosHuella,tamañoHuella);
guardarStmt2.setString(2, usuario);

//Ejecuta la sentencia
guardarStmt2.execute();
guardarStmt2.close();
JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
}







//con.desconectar();
//btnGuardar.setEnabled(false);

}
catch (SQLException ex) {
//Si ocurre un error lo indica en la consola
System.err.println("Error al guardar los datos de la huella.");
ex.printStackTrace();
}
/*finally{
con.desconectar();
}*/
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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo_1;
    private org.edisoncor.gui.button.ButtonIpod buttonIpod2;
    private javax.swing.JComboBox<String> cmbCargo;
    private javax.swing.JComboBox<String> cmbCelda;
    private javax.swing.JComboBox<String> cmbEstadoRe;
    private javax.swing.JComboBox cmbEstadoU;
    private javax.swing.JComboBox<String> cmbPregunta;
    private javax.swing.JComboBox<String> cmbSectorRe;
    private javax.swing.JComboBox<String> cmbTezRe;
    private javax.swing.JComboBox cmbTipoNumero;
    private javax.swing.JComboBox cmbTipoNumeroV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelFoto1;
    private javax.swing.JLabel jLabelFoto2;
    private javax.swing.JLabel jLabelFoto3;
    private javax.swing.JLabel jLabelFotoE;
    private javax.swing.JLabel jLabel_BarraRe;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JLabel lbl_usuario1;
    private javax.swing.JLabel lbl_usuario2;
    private javax.swing.JLabel lbl_usuario3;
    private javax.swing.JLabel lbl_usuario4;
    private javax.swing.JLabel lbl_usuario5;
    private javax.swing.JLabel lbl_usuario6;
    private javax.swing.JLabel lbl_usuario7;
    private org.edisoncor.gui.panel.PanelAvatarChooser menu;
    private org.edisoncor.gui.panel.PanelCurves panelCurves3;
    private javax.swing.JPanel panel_asistencia_citas;
    private javax.swing.JPanel panel_citas;
    private javax.swing.JPanel panel_configuracion;
    private javax.swing.JPanel panel_historialR;
    private javax.swing.JPanel panel_recluso;
    private javax.swing.JPanel panel_usuario;
    private javax.swing.JPanel panel_visitante;
    private javax.swing.JTable tabCitasNo;
    private javax.swing.JTable tabCitasSi;
    private javax.swing.JTable tabEmpleado;
    private javax.swing.JTable tabReclusos;
    private javax.swing.JTable tabVisita;
    private javax.swing.JTable tabcitas;
    private javax.swing.JTextField txtAliasRe;
    private javax.swing.JTextField txtAlturaRe;
    private javax.swing.JTextField txtApellidoRe;
    private javax.swing.JTextField txtApellidosCitas;
    private javax.swing.JTextField txtApellidosReclusoCitas;
    private javax.swing.JTextField txtApellidosUsu;
    private javax.swing.JTextField txtApellidosVis;
    private javax.swing.JTextField txtBarraRe;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtDireccionVis;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEdadRe;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtFiltroRe;
    private javax.swing.JTextField txtMotivos;
    private javax.swing.JTextField txtMotivosCitas;
    private javax.swing.JTextField txtNombreCitas;
    private javax.swing.JTextField txtNombreR;
    private javax.swing.JTextField txtNombreRe;
    private javax.swing.JTextField txtNombreRe11;
    private javax.swing.JTextField txtNombreReclusoCitas;
    private javax.swing.JTextField txtNombreUsu;
    private javax.swing.JTextField txtNombreV;
    private javax.swing.JTextField txtNombreVis;
    private javax.swing.JTextField txtPesoRe;
    private javax.swing.JTextField txtRespuesta;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtVinculo;
    private javax.swing.JTextField txtVinculosCitas;
    private javax.swing.JTextField txt_datos;
    private javax.swing.JTextField txtnNumeroC;
    private javax.swing.JTextField txtnNumeroV;
    // End of variables declaration//GEN-END:variables
}
