/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foto_reporteria_2;

import Camaras.Camara1;
import Camaras.Camara2;
import Clases_Rasq.Conexion;
import Clases_Rasq.Mthod_Visitas_x;
import Clases_Rasq.Mtod_Control_historial_sistema;
import Clases_Rasq.Mtod_asistencia;
import Clases_Rasq.Mtod_conducta;
import Clases_Rasq.Mtod_control_celdas;
import Clases_Rasq.Mtod_foto;
import Clases_Rasq.Mtod_jefe_sector;
import Formularios.Administrador;
import static Formularios.Administrador.fechaActual;
import Mantenimiento.Mto_Administrador;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import nicon.notify.core.Notification;
import org.edisoncor.gui.util.Avatar;
import org.opencv.core.Core;
import org.opencv.objdetect.CascadeClassifier;


import Mantenimiento.Hora_Fecha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEventListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Works
 */
public class Sector extends javax.swing.JFrame {
    //arduino 
    PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino(); 
    String Bloque = "Block A"; // varable donde se almacenara el bloque del sector 
    int dias = 0; //variable del control de dias no se ocupa 
    int horas = 0; // varible del control comtinuo de las horas
    int minutos = 0; // varible del control comtinuo de los minutos
    int segundos = 0;  // varible del control comtinuo de los segundos
    String HoraApertura; // variable que almacenara la hora de la apertura
    String HoraCierre; // variable que almacenara la hora del cierre 
    String x;  // varible que comvierte  las horas en tipo string
    String y;   // varible que comvierte  los minutos en tipo  string
    String Fecha_continua; // la variable tipo string que estara cambiando 
    Timer tiempo;
    int el_arduino; // esta variable enviaran al serial del arduino indicando la apertura o el cierre de celdas 

    Hora_Fecha hora_Bd = new Hora_Fecha();  
    GregorianCalendar otroTime = new GregorianCalendar(); 
    
    
    
    
    //VARIABLES PARA SERVOMOTOR 2 Y CONSULTA DE HORARIO DEL SECTOR2
    String Bloque2 = "Block B"; // varable donde se almacenara el bloque del sector 
    int dias2 = 0; //variable del control de dias no se ocupa 
    int horas2 = 0; // varible del control comtinuo de las horas
    int minutos2 = 0; // varible del control comtinuo de los minutos
    int segundos2 = 0;  // varible del control comtinuo de los segundos
    String HoraApertura2; // variable que almacenara la hora de la apertura
    String HoraCierre2; // variable que almacenara la hora del cierre 
    String x2;  // varible que comvierte  las horas en tipo string
    String y2;   // varible que comvierte  los minutos en tipo  string
    String Fecha_continua2; // la variable tipo string que estara cambiando 
    Timer tiempo2;
    int el_arduino2; // esta variable enviaran al serial del arduino indicando la apertura o el cierre de celdas 

    Hora_Fecha hora_Bd2 = new Hora_Fecha(); 

    
    
    
    
    
    //VARIABLES PARA SERVOMOTOR 3 Y CONSULTA DE HORARIO DEL SECTOR3
    String Bloque3 = "Block C"; // varable donde se almacenara el bloque del sector 
    int dias3 = 0; //variable del control de dias no se ocupa 
    int horas3 = 0; // varible del control comtinuo de las horas
    int minutos3 = 0; // varible del control comtinuo de los minutos
    int segundos3 = 0;  // varible del control comtinuo de los segundos
    String HoraApertura3; // variable que almacenara la hora de la apertura
    String HoraCierre3; // variable que almacenara la hora del cierre 
    String x3;  // varible que comvierte  las horas en tipo string
    String y3;   // varible que comvierte  los minutos en tipo  string
    String Fecha_continua3; // la variable tipo string que estara cambiando 
    Timer tiempo3;
    int el_arduino3; // esta variable enviaran al serial del arduino indicando la apertura o el cierre de celdas 

    Hora_Fecha hora_Bd3 = new Hora_Fecha(); 
    
    
    
    
    //VARIABLES PARA SERVOMOTOR 4 Y CONSULTA DE HORARIO DEL SECTOR 4
    String Bloque4 = "Block D"; // varable donde se almacenara el bloque del sector 
    int dias4 = 0; //variable del control de dias no se ocupa 
    int horas4 = 0; // varible del control comtinuo de las horas
    int minutos4 = 0; // varible del control comtinuo de los minutos
    int segundos4 = 0;  // varible del control comtinuo de los segundos
    String HoraApertura4; // variable que almacenara la hora de la apertura
    String HoraCierre4; // variable que almacenara la hora del cierre 
    String x4;  // varible que comvierte  las horas en tipo string
    String y4;   // varible que comvierte  los minutos en tipo  string
    String Fecha_continua4; // la variable tipo string que estara cambiando 
    Timer tiempo4;
    int el_arduino4; // esta variable enviaran al serial del arduino indicando la apertura o el cierre de celdas 

    Hora_Fecha hora_Bd4 = new Hora_Fecha();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //VARIABLES CITAS
    String fecha;
    String cod_citas;
   
    // --------------------------------------- conexiones -------------------------------
    Mtod_jefe_sector mtx = new Mtod_jefe_sector();
    Conexion conex = new Conexion();
    Connection cn = conex.conectar();
    Mtod_foto mta = new Mtod_foto();    
    Mtod_asistencia mts = new Mtod_asistencia();
    Login_MT2_v2 log = new Login_MT2_v2();
    Mthod_Visitas_x mtv = new Mthod_Visitas_x();
    Mtod_Control_historial_sistema hqsq = new Mtod_Control_historial_sistema(); 

    // --------------------------------------- conexiones -------------------------------
    int Code_recluso;
    int codigox;
    
    
    int edadx;
    int alturax;
    int pesox;
    String tezx;
    String aliasx;
    String estado_reclusox;
 
   
    int total_r = 0;
    String Blocke;
    int contador_a = 0;
     
    int codigo_barraq;
    String sector;
    String asistencia;    
    
  
    int count = 0;
    int coint2 = 0;
    
    //variables para el bucador de reclusos por celda--------------------------------------
    
    String nombre_recluso;
    String Apellidos_recluso;
    int codigo_barra;
    String Sector;
    String Agrupacion;    
    int codigo_celda;
    String horario_salida;
    String horario_entrada;
    //terminan variables del  buscador--------------------------------------------------------
   
    //variables de asistencia-----------------------------------------------------------------
     int total_r_sector;
     int[] code_reclusos;  
     int total_reclusos_sector;
     int contador =0;
     int codex;
     boolean xas = false;
     int verificador_de_codigo2;
    //terminan variables de asistencia---------------------------------------------------------
     
    // variables de asistencia de visitas--------------------------------------------------------- 
     int codigo_barrax;
     String nombre_visitante;
     String Apellidos_visitante;
     String nombrex;
     String apellidosx;
     int  codigo_barraq2;
     Time hora;
     int contador2 =0;
     int verificador_de_codigo1;
     int total_de_visitas_sector;
     int []code_recluso_visitas;
     int total_v_sector;
     Boolean xas2 =false;
     
    // terminan variables de asistencia de visitas--------------------------------------------------------- 
    //variables deel control de historial de sistema------------------------------------------------------------
     
    String fecha_anterior;
    String fecha_fin;
     
      
    //Variables para historial
    String nombre_r;
    String apellido_r;
    Date fecha_actual = new Date();
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    String FechaHis = formato.format(fecha_actual);
     

    //------------------------------------------terminan variables------------------------
    //deaclaracnon de datos en las tablas ---------------------------------------------------------------
      
      
      
    Object[] comlumnas = {"Cod recluso", "Nombre Recluso", "Estado", "Codigo barra", "Apellido","Edad" ,"Altura", "Peso", "Tez","Alias"};
    DefaultTableModel model = new DefaultTableModel(comlumnas, WIDTH);
    
    Object[] comlumnas_asist = {"Cod recluso", "Nombre Recluso", "Estado", "Codigo barra", "Nombre sector "," Hora apertura" ,"Hora  cierre"};
    DefaultTableModel modelx = new DefaultTableModel(comlumnas_asist, WIDTH);
    
    
    Object[] comlumnas_final_asist = {"Cod recluso", "Nombre", "Apellido", "Codigo barra", "Asistencia"};
    DefaultTableModel modely = new DefaultTableModel(comlumnas_final_asist, WIDTH);
    
    
      Object[] comlumnas_visitas_dia = {"Visitante", "Recluso", "Codigo de Barra", "Asistencia","Hora ", "Apellido_visitante", "Apelldio_recluso" };
    DefaultTableModel modelo_visitas = new DefaultTableModel(comlumnas_visitas_dia, WIDTH);
     
    Object[] comlumnas_visitas_diax = {"Visitante", "Recluso", "Codigo de Barra", "Asistencia","Hora ", "Apellido_visitante", "Apelldio_recluso" };
    DefaultTableModel modelo_visitasx = new DefaultTableModel(comlumnas_visitas_dia, WIDTH);
    
    
    Object[] comlumnas_control_celdas = {"Nombre recluso", "Codigo de barras", "Codigo celda", "Sector", "Agrupacion", "Apellidos", "Horario Apertura", "Horario cierre"};
    DefaultTableModel modelo_control_celdas = new DefaultTableModel(comlumnas_control_celdas, WIDTH);
    
    Object[] comlumnas_control_celdas_bus = {"Nombre recluso", "Codigo de barras", "Codigo celda", "Sector", "Agrupacion", "Apellidos", "Horario Apertura", "Horario cierre"};
    DefaultTableModel modelo_control_celdas_bus = new DefaultTableModel(comlumnas_control_celdas_bus, WIDTH);

    
     Object[] comlumnas_control_historial = {"nombre empleado", "Fecha" , "Descripcion"};
     DefaultTableModel modelo_control_historial = new DefaultTableModel(comlumnas_control_historial , WIDTH);
     
     Object[] comlumnas_control_historial_x = {"nombre empleado", "Fecha" , "Descripcion"};
     DefaultTableModel modelo_control_historial_x = new DefaultTableModel(comlumnas_control_historial_x , WIDTH);
    
    //-------------------------------------------termina programacion-----------------------------------------
    /**
     * Creates new form Sector
     */
     
     //public void run
    public Sector(String sectors) {
        Blocke = sectors;
        //Blocke = "Block A"; /// campo temporal
        initComponents();
        this.setLocationRelativeTo(null);
        LlenarComboSector();
        txtFecha.setText(fechaActual());
        fecha=this.txtFecha.getText();
        mthCitas();
        mthCitasActualizadas();
        
        //conexion arduino y servomotores
        
        horas = otroTime.get(GregorianCalendar.HOUR_OF_DAY);
        minutos = otroTime.get(GregorianCalendar.MINUTE);
        segundos = otroTime.get(GregorianCalendar.SECOND);
        Timer tiempo = new Timer(1000, new timer2());
        tiempo.start();
        obtener_datos();
        try {
            Arduino.arduinoTX("COM3", 9600);
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //OBTENCION DE DATOS 2 PARA ARDUINO
      //  horas2 = otroTime.get(GregorianCalendar.HOUR_OF_DAY);
      //  minutos2 = otroTime.get(GregorianCalendar.MINUTE);
       // segundos2 = otroTime.get(GregorianCalendar.SECOND);
      //  Timer tiempo2 = new Timer(1000, new timer2());
       // tiempo2.start();
        obtener_datos2();
        
        //OBTENCION DE DATOS 3 PARA ARDUINO
      //  horas3 = otroTime.get(GregorianCalendar.HOUR_OF_DAY);
      //  minutos3 = otroTime.get(GregorianCalendar.MINUTE);
      //  segundos3 = otroTime.get(GregorianCalendar.SECOND);
       // Timer tiempo3 = new Timer(1000, new timer2());
       // tiempo3.start();
        obtener_datos3();
        
 
     
        obtener_datos4();

        
        
        
        
        //------------------------receptor de algunos datos---------
        
        CascadeClassifier faceDetector = new CascadeClassifier(Camara1.class.getResource("haarcascade_frontalface_alt2.xml").getPath().substring(1));
        //----------------------------------------------------------
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
        panel_conducta.setVisible(false);
        
         nombre_recluso="xas";
       //----------------------------------------------------------------
        fecha_actual();
        llenarMenu();
        control_de_tablas();
        consulta_general();
        obtener_reclusos_sector();
        //consulta_control_de_celdas();
        consult_asist_xas();
        consulta_visistas_dia();
        obtener_citas_reclusos();
        consulta_historial_sistema();
        carga_celdas();
        panel_horarios.setVisible(false);
        
    }
    
    public void obtener_datos() {
        hora_Bd.setSector(Bloque);
        if (hora_Bd.consult_fecha()) {

            HoraApertura = hora_Bd.getReceptor_horaA();
            HoraCierre = hora_Bd.getReceptor_horaC();
            txt_apertura.setText(HoraApertura);
            txt_cierre.setText(HoraCierre);
        }

    }
     
     public void obtener_datos2() {
        hora_Bd2.setSector2(Bloque2);
        if (hora_Bd2.consult_fecha2()) {

            HoraApertura2 = hora_Bd2.getReceptor_horaD();
            HoraCierre2 = hora_Bd2.getReceptor_horaE();
            txt_apertura2.setText(HoraApertura2);
            txt_cierre2.setText(HoraCierre2);
        }

    }
     
     public void obtener_datos3() {
        hora_Bd3.setSector3(Bloque3);
        if (hora_Bd3.consult_fecha3()) {

            HoraApertura3 = hora_Bd3.getReceptor_horaF();
            HoraCierre3 = hora_Bd3.getReceptor_horaG();
            txt_apertura3.setText(HoraApertura3);
            txt_cierre3.setText(HoraCierre3);
        }

    }
     
          public void obtener_datos4() {
        hora_Bd4.setSector4(Bloque4);
        if (hora_Bd4.consult_fecha4()) {

            HoraApertura4 = hora_Bd4.getReceptor_horaH();
            HoraCierre4 = hora_Bd4.getReceptor_horaI();
            txt_apertura4.setText(HoraApertura4);
            txt_cierre4.setText(HoraCierre4);
        }

    }
     
     
         public void comparador() {
        if (Fecha_continua.equals(HoraApertura)) {
//            el_arduino =1 ;
//             System.out.println(""+ el_arduino);
             try {
            Arduino.sendData("1");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
             

        } else if (Fecha_continua.equals(HoraCierre)) {
//            el_arduino =0;
//            System.out.println(""+ el_arduino);
            
           
            try {
            Arduino.sendData("0");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

    }
         
         
         //comparador 2
         public void comparador2() {
        if (Fecha_continua2.equals(HoraApertura2)) {
//            el_arduino =1 ;
//             System.out.println(""+ el_arduino);
             try {
            Arduino.sendData("2");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
             

        } else if (Fecha_continua2.equals(HoraCierre2)) {
//            el_arduino =0;
//            System.out.println(""+ el_arduino);
            
           
            try {
            Arduino.sendData("0");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

    }
         
         
       //comparador 3
         public void comparador3() {
        if (Fecha_continua3.equals(HoraApertura3)) {
//            el_arduino =1 ;
//             System.out.println(""+ el_arduino);
             try {
            Arduino.sendData("3");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
             

        } else if (Fecha_continua3.equals(HoraCierre3)) {
//            el_arduino =0;
//            System.out.println(""+ el_arduino);
            
            try {
            Arduino.sendData("0");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

    }
         
         
         public void comparador4() {
        if (Fecha_continua4.equals(HoraApertura4)) {
//            el_arduino =1 ;
//             System.out.println(""+ el_arduino);
             try {
            Arduino.sendData("4");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
             

        } else if (Fecha_continua4.equals(HoraCierre4)) {
//            el_arduino =0;
//            System.out.println(""+ el_arduino);
            
            try {
            Arduino.sendData("0");
        } catch (Exception ex) {
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

    }
         
      public class timer2 implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            segundos += 1;
            if (segundos > 59) {
                segundos = 0;
                minutos += 1;
            }
            if (minutos > 59) {
                horas += 1;
                minutos = 0;
                segundos = 0;
            }
            if (horas > 23) {
                horas = 0;
                minutos = 0;
                segundos = 0;
            }

            x = String.valueOf(horas);
            y = String.valueOf(minutos);
            String xas= String.valueOf(segundos);
          
            if(horas >= 0 && horas <=9 && minutos>=0 && minutos<=9)
            {
            Fecha_continua = "0"+ x + ":" +"0"+y ; 
            Fecha_continua2 = "0"+ x + ":" +"0"+y ; 
            Fecha_continua3 = "0"+ x + ":" +"0"+y ; 
            Fecha_continua4 = "0"+ x + ":" +"0"+y ; 
         
            txt_timer.setText(Fecha_continua+":" + xas);
            
            }
           else if(horas >= 0 && horas <=9 && minutos>=10)
            {
          
            Fecha_continua = "0"+ x + ":"+  y ;
            Fecha_continua2 = "0"+ x + ":"+  y ;
            Fecha_continua3 = "0"+ x + ":"+  y ;
            Fecha_continua4 = "0"+ x + ":"+  y ;
            txt_timer.setText(Fecha_continua+":" + xas);
            }
            
            else if(horas >=10 && minutos>=0 && minutos<=9)
            {
            Fecha_continua =  x + ":" + y ;
            Fecha_continua2 =  x + ":" + y ;
            Fecha_continua3 =  x + ":" + y ;
            Fecha_continua4 =  x + ":" + y ;
            txt_timer.setText(Fecha_continua+":" + xas);
            }
             else if(horas >= 10 && minutos>=10)
            {
          
            Fecha_continua =  x + ":"+  y ;
            Fecha_continua2 =  x + ":"+  y ;
            Fecha_continua3 =  x + ":"+  y ;
            Fecha_continua4 =  x + ":"+  y ;
            txt_timer.setText(Fecha_continua+":" + xas);
            }
            
       
           
           
            comparador();
            comparador2();
            comparador3();
            comparador4();
            
        }

    }
    //CONSULTA CITAS     SE VA A MOVER AL FORMULARIO DE JEFE SE SEGURIDAD
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
    
    public void carga_celdas(){
        try{
            ResultSet res;
            res = mtx.LlenarCelda();
            while(res.next())
            {   
                this.cmb_celda.addItem(res.getString("cod_celda"));
            }
        } catch (Exception ex){
            
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

    public void control_de_tablas() {
        
         jtable_reclusos.setModel(model); 
        
       jAsis_Reclusos_complete.setModel(modelx);
       Jtable_asistencia_R.setModel(modely);
       
        Jtable_visitas_totales_recluso.setModel(modelo_visitas);
        Jtable_visitas.setModel(modelo_visitasx);
       
       Jtable_control_celdas.setModel(modelo_control_celdas);
       //Jtable_buscador.setModel(modelo_control_celdas_bus);
       
        Jtable_his_sistema.setModel(modelo_control_historial);
        Jtable_fecha_buscator.setModel(modelo_control_historial_x);
       
       jAsis_Reclusos_complete.getColumnModel().getColumn(4).setMaxWidth(0);
       jAsis_Reclusos_complete.getColumnModel().getColumn(5).setMaxWidth(0);
       jAsis_Reclusos_complete.getColumnModel().getColumn(6).setMaxWidth(0);
      
       
        Jtable_visitas_totales_recluso.getColumnModel().getColumn(5).setMaxWidth(0);
        Jtable_visitas_totales_recluso.getColumnModel().getColumn(6).setMaxWidth(0);
         
        Jtable_visitas.getColumnModel().getColumn(4).setMaxWidth(0);
        Jtable_visitas.getColumnModel().getColumn(5).setMaxWidth(0);
        Jtable_visitas.getColumnModel().getColumn(6).setMaxWidth(0);
       
       Jtable_control_celdas.getColumnModel().getColumn(5).setMaxWidth(0);
       Jtable_control_celdas.getColumnModel().getColumn(6).setMaxWidth(0);
       Jtable_control_celdas.getColumnModel().getColumn(7).setMaxWidth(0);
       //Jtable_buscador.getColumnModel().getColumn(5).setMaxWidth(0);
       //Jtable_buscador.getColumnModel().getColumn(6).setMaxWidth(0);
       //Jtable_buscador.getColumnModel().getColumn(7).setMaxWidth(0);
       
            
         /// se impide que se vean mas atributos de la base de datos 
         jtable_reclusos.getColumnModel().getColumn(4).setMaxWidth(0);
         jtable_reclusos.getColumnModel().getColumn(5).setMaxWidth(0);
         jtable_reclusos.getColumnModel().getColumn(6).setMaxWidth(0);
         jtable_reclusos.getColumnModel().getColumn(7).setMaxWidth(0);
         jtable_reclusos.getColumnModel().getColumn(8).setMaxWidth(0);
         jtable_reclusos.getColumnModel().getColumn(9).setMaxWidth(0);
           
       
       
       
        
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod2 = new org.edisoncor.gui.button.ButtonIpod();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        lbl_foto1 = new javax.swing.JLabel();
        lbl_foto2 = new javax.swing.JLabel();
        lbl_foto3 = new javax.swing.JLabel();
        Buscador_reclusos = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txt_nombre_recluso = new org.edisoncor.gui.textField.TextFieldRound();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        txt_cod_recluso = new org.edisoncor.gui.textField.TextFieldRound();
        txt_edad = new org.edisoncor.gui.textField.TextFieldRound();
        txt_apellidos = new org.edisoncor.gui.textField.TextFieldRound();
        txt_altura = new org.edisoncor.gui.textField.TextFieldRound();
        txt_peso = new org.edisoncor.gui.textField.TextFieldRound();
        txt_alias = new org.edisoncor.gui.textField.TextFieldRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_reclusos = new javax.swing.JTable();
        txt_tez = new org.edisoncor.gui.textField.TextFieldRound();
        txt_estado_recluso = new org.edisoncor.gui.textField.TextFieldRound();
        labelMetric12 = new org.edisoncor.gui.label.LabelMetric();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_besqueda = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbl_usuario7 = new javax.swing.JLabel();
        Panel_control_datos = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_nombre_empleado_his = new org.edisoncor.gui.textField.TextFieldRound();
        txt_fecha_reporte_his = new org.edisoncor.gui.textField.TextFieldRound();
        txt_descripcion_his = new org.edisoncor.gui.textField.TextFieldRound();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Jtable_his_sistema = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Jtable_fecha_buscator = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton10 = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jLabel50 = new javax.swing.JLabel();
        Jyear_inicio = new com.toedter.calendar.JYearChooser();
        jLabel51 = new javax.swing.JLabel();
        Jyear_fin = new com.toedter.calendar.JYearChooser();
        btn_busqueda_año = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lbl_usuario9 = new javax.swing.JLabel();
        jPanel_Asistencia = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jAsis_Reclusos_complete = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Jtable_asistencia_R = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_codigo_barra = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel16 = new javax.swing.JLabel();
        lbl_busqueda = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_barra = new javax.swing.JLabel();
        txt_nombre_recluso_asis1 = new org.edisoncor.gui.textField.TextFieldRound();
        txt_sector = new org.edisoncor.gui.textField.TextFieldRound();
        txt_apellidos_recluso = new org.edisoncor.gui.textField.TextFieldRound();
        txt_alias1 = new org.edisoncor.gui.textField.TextFieldRound();
        txt_estado_recluso1 = new org.edisoncor.gui.textField.TextFieldRound();
        btn_ter_asist = new javax.swing.JButton();
        btn_ter_asist1 = new javax.swing.JButton();
        lbl_usuario10 = new javax.swing.JLabel();
        panel_horarios = new javax.swing.JPanel();
        txt_apertura = new javax.swing.JTextField();
        txt_cierre = new javax.swing.JTextField();
        txt_timer = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_apertura2 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txt_cierre2 = new javax.swing.JTextField();
        txt_cierre3 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txt_apertura3 = new javax.swing.JTextField();
        txt_cierre4 = new javax.swing.JTextField();
        txt_apertura4 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lbl_usuario11 = new javax.swing.JLabel();
        panelCurves4 = new org.edisoncor.gui.panel.PanelCurves();
        panel_conducta = new javax.swing.JPanel();
        lbl_usuario8 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jComboBox_Sector = new javax.swing.JComboBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable_Reclusos = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jTextField_Filtrar = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable_Conducta = new javax.swing.JTable();
        jButton_Reporte = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea_Descripcion = new javax.swing.JTextArea();
        Jpanel_Control_Camaras = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        Jpanel_control_celdas = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Jtable_control_celdas = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        cmb_celda = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txt_nombre_reclus = new org.edisoncor.gui.textField.TextFieldRound();
        txt_cierre_celdas = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_sector_celdas = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel32 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txt_agrupacion_celdas = new org.edisoncor.gui.textField.TextFieldRound();
        txt_apertura_celdas = new org.edisoncor.gui.textField.TextFieldRound();
        lbl_usuario12 = new javax.swing.JLabel();
        panel_asistencia_citas = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabCitasSi = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabCitasNo = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txtNombreCitas = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtApellidosCitas = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txtVinculosCitas = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txtMotivosCitas = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        txtNombreRe11 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        lbl_usuario6 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txtNombreReclusoCitas = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txtApellidosReclusoCitas = new javax.swing.JTextField();
        txtFecha = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        Jpanel_visitas = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Jtable_visitas = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Jtable_visitas_totales_recluso = new javax.swing.JTable();
        txt_hora_reclu_visit = new org.edisoncor.gui.textField.TextFieldRound();
        txt_crimen_reclu_visit = new org.edisoncor.gui.textField.TextFieldRound();
        txt_apellido_reclu_visit = new org.edisoncor.gui.textField.TextFieldRound();
        txt_nombre_reclu_visit = new org.edisoncor.gui.textField.TextFieldRound();
        txt_apellido_visitante = new org.edisoncor.gui.textField.TextFieldRound();
        txt_nombre_visitante_visit = new org.edisoncor.gui.textField.TextFieldRound();
        txt_codigo_barra_visitas = new org.edisoncor.gui.textField.TextFieldRound();
        jPanel5 = new javax.swing.JPanel();
        lbl_code_barra = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_usuario13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(63, 57, 54));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addContainerGap(604, Short.MAX_VALUE)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(603, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonIpod2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 1280, 110));

        panelNice1.setBackground(new java.awt.Color(51, 51, 55));

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_foto2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_foto3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lbl_foto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lbl_foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_foto3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lbl_foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panelNice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 142, -1, 510));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(51, 51, 55));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nombre_recluso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_reclusoActionPerformed(evt);
            }
        });
        txt_nombre_recluso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_reclusoKeyPressed(evt);
            }
        });
        jPanel12.add(txt_nombre_recluso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 349, -1));

        labelMetric2.setForeground(new java.awt.Color(0, 0, 0));
        labelMetric2.setColorDeSombra(new java.awt.Color(0, 204, 153));
        labelMetric2.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jPanel12.add(labelMetric2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 141, -1, -1));

        txt_cod_recluso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_reclusoKeyPressed(evt);
            }
        });
        jPanel12.add(txt_cod_recluso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 173, -1));
        jPanel12.add(txt_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 61, -1));
        jPanel12.add(txt_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 349, -1));
        jPanel12.add(txt_altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 75, 20));
        jPanel12.add(txt_peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 89, -1));
        jPanel12.add(txt_alias, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 340, 340, -1));

        jtable_reclusos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtable_reclusos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_reclusosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtable_reclusos);

        jPanel12.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 562, 397));
        jPanel12.add(txt_tez, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 340, -1));

        txt_estado_recluso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estado_reclusoActionPerformed(evt);
            }
        });
        jPanel12.add(txt_estado_recluso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 340, -1));
        jPanel12.add(labelMetric12, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 52, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Search-Find.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Findx.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        txt_besqueda.setText("                                              Busca aquí ");
        txt_besqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_besquedaMouseClicked(evt);
            }
        });
        txt_besqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_besquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_besquedaKeyTyped(evt);
            }
        });
        jPanel12.add(txt_besqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 363, -1));

        jLabel11.setText("Codigo Recluso:");
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel12.setText("Nombre Recluso:");
        jPanel12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel13.setText("Apellidos:");
        jPanel12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel14.setText("Edad:");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 50, -1));

        jLabel15.setText("Altura:");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        jLabel23.setText("Peso:");
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        jLabel24.setText("Tez:");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel25.setText("Alias:");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel39.setText("Estado Recluso:");
        jPanel12.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jLabel45.setText("Busqueda por nombre:");
        jPanel12.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 118, -1));

        jButton1.setText("Reclusos por salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 220, -1));

        lbl_usuario7.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario7.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario7.setText("BUSCADOR DE RECLUSOS");
        jPanel12.add(lbl_usuario7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 230, -1));

        javax.swing.GroupLayout Buscador_reclusosLayout = new javax.swing.GroupLayout(Buscador_reclusos);
        Buscador_reclusos.setLayout(Buscador_reclusosLayout);
        Buscador_reclusosLayout.setHorizontalGroup(
            Buscador_reclusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(Buscador_reclusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        Buscador_reclusosLayout.setVerticalGroup(
            Buscador_reclusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
            .addGroup(Buscador_reclusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        jPanel1.add(Buscador_reclusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 1090, 510));

        Panel_control_datos.setBackground(new java.awt.Color(255, 255, 255));
        Panel_control_datos.setForeground(new java.awt.Color(255, 255, 255));
        Panel_control_datos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Nombre_empleado:");
        Panel_control_datos.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        jLabel48.setBackground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Fecha :");
        Panel_control_datos.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 90, -1));

        jLabel49.setBackground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Descripcion:");
        Panel_control_datos.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 80, -1));

        txt_nombre_empleado_his.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre_empleado_hisKeyTyped(evt);
            }
        });
        Panel_control_datos.add(txt_nombre_empleado_his, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 310, 30));
        Panel_control_datos.add(txt_fecha_reporte_his, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 310, 30));
        Panel_control_datos.add(txt_descripcion_his, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 130, 310, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial del sistema"));

        Jtable_his_sistema.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(Jtable_his_sistema);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_control_datos.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 10, 630, 190));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador"));

        Jtable_fecha_buscator.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(Jtable_fecha_buscator);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panel_control_datos.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 210, 630, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscadores"));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel11.add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

        jButton10.setText("Búsqueda Especifica");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });
        jPanel11.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 400, 20));
        jPanel11.add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        jPanel11.add(jDayChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 400, 150));

        jLabel50.setText("Año fin");
        jPanel11.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));
        jPanel11.add(Jyear_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 110, -1));

        jLabel51.setText("Busqueda por años ");
        jPanel11.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));
        jPanel11.add(Jyear_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 110, -1));

        btn_busqueda_año.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Search-Find.png"))); // NOI18N
        btn_busqueda_año.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_busqueda_añoMouseClicked(evt);
            }
        });
        jPanel11.add(btn_busqueda_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, 60));

        jLabel52.setText("Año inicio");
        jPanel11.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        Panel_control_datos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 160, 430, 340));

        lbl_usuario9.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario9.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario9.setText("CONTROL DE DATOS");
        Panel_control_datos.add(lbl_usuario9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 180, -1));

        jPanel1.add(Panel_control_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, 510));

        jPanel_Asistencia.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Asistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_AsistenciaMouseClicked(evt);
            }
        });
        jPanel_Asistencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Reclusos de sector"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAsis_Reclusos_complete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jAsis_Reclusos_complete);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 570, 160));

        jPanel_Asistencia.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 590, 190));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Reclusos Asistidos"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_asistencia_R.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(Jtable_asistencia_R);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 570, 160));

        jPanel_Asistencia.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 590, 180));

        jLabel21.setText("Estado Recluso:");
        jPanel_Asistencia.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 299, -1, -1));

        jLabel19.setText("Alias:");
        jPanel_Asistencia.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 252, -1, -1));

        jLabel20.setText("Apellidos:");
        jPanel_Asistencia.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 206, -1, -1));

        jLabel18.setText("Sector:");
        jPanel_Asistencia.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 156, -1, 30));

        jLabel17.setText("Nombre recluso:");
        jPanel_Asistencia.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 119, 105, 23));

        txt_codigo_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codigo_barraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codigo_barraKeyTyped(evt);
            }
        });
        jPanel_Asistencia.add(txt_codigo_barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 79, 160, -1));

        jLabel16.setText("Codigo de barras:");
        jPanel_Asistencia.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 81, 127, -1));

        lbl_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Data-Findx.png"))); // NOI18N
        lbl_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_busquedaMouseClicked(evt);
            }
        });
        jPanel_Asistencia.add(lbl_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 50, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo de barras"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbl_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel_Asistencia.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, 230, 90));

        txt_nombre_recluso_asis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_recluso_asis1ActionPerformed(evt);
            }
        });
        txt_nombre_recluso_asis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_recluso_asis1KeyPressed(evt);
            }
        });
        jPanel_Asistencia.add(txt_nombre_recluso_asis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 121, 226, -1));

        txt_sector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sectorActionPerformed(evt);
            }
        });
        txt_sector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sectorKeyPressed(evt);
            }
        });
        jPanel_Asistencia.add(txt_sector, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 226, -1));

        txt_apellidos_recluso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidos_reclusoActionPerformed(evt);
            }
        });
        txt_apellidos_recluso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellidos_reclusoKeyPressed(evt);
            }
        });
        jPanel_Asistencia.add(txt_apellidos_recluso, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 226, -1));

        txt_alias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alias1ActionPerformed(evt);
            }
        });
        txt_alias1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_alias1KeyPressed(evt);
            }
        });
        jPanel_Asistencia.add(txt_alias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 226, -1));

        txt_estado_recluso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estado_recluso1ActionPerformed(evt);
            }
        });
        txt_estado_recluso1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_estado_recluso1KeyPressed(evt);
            }
        });
        jPanel_Asistencia.add(txt_estado_recluso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 226, -1));

        btn_ter_asist.setText("Terminar asistencia");
        btn_ter_asist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ter_asistActionPerformed(evt);
            }
        });
        jPanel_Asistencia.add(btn_ter_asist, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, -1, 40));

        btn_ter_asist1.setText("Reiniciar asistencia");
        btn_ter_asist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ter_asist1ActionPerformed(evt);
            }
        });
        jPanel_Asistencia.add(btn_ter_asist1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, -1, 40));

        lbl_usuario10.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario10.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario10.setText("ASISTENCIA DE RECLUSOS");
        jPanel_Asistencia.add(lbl_usuario10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 230, -1));

        jPanel1.add(jPanel_Asistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, 510));

        panel_horarios.setBackground(new java.awt.Color(255, 255, 255));
        panel_horarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_horarios.add(txt_apertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 104, -1));
        panel_horarios.add(txt_cierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 113, -1));
        panel_horarios.add(txt_timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 110, -1));

        jLabel22.setText("Horario Apertura");
        panel_horarios.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel27.setText("Horario Cierre");
        panel_horarios.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel28.setText("SECTOR 4");
        panel_horarios.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, -1, -1));

        jLabel40.setText("Horario Apertura");
        panel_horarios.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, -1, -1));
        panel_horarios.add(txt_apertura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 104, -1));

        jLabel58.setText("Horario Cierre");
        panel_horarios.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, -1));
        panel_horarios.add(txt_cierre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 160, 113, -1));
        panel_horarios.add(txt_cierre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 113, -1));

        jLabel61.setText("Horario Apertura");
        panel_horarios.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel62.setText("Horario Cierre");
        panel_horarios.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, -1, -1));
        panel_horarios.add(txt_apertura3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 104, -1));
        panel_horarios.add(txt_cierre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, 113, -1));
        panel_horarios.add(txt_apertura4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 104, -1));

        jLabel63.setText("Horario Apertura");
        panel_horarios.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, -1, -1));

        jLabel64.setText("Horario Cierre");
        panel_horarios.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, -1, -1));

        jLabel65.setText("Hora Actual");
        panel_horarios.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, -1));

        jLabel66.setText("SECTOR 1");
        panel_horarios.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        jLabel67.setText("SECTOR 2");
        panel_horarios.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        jLabel68.setText("SECTOR 3");
        panel_horarios.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        lbl_usuario11.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario11.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario11.setText("HORARIOS DE SECTORES");
        panel_horarios.add(lbl_usuario11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 220, -1));

        jPanel1.add(panel_horarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 930, 410));

        panelCurves4.setForeground(new java.awt.Color(51, 255, 51));
        jPanel1.add(panelCurves4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, 660, 1370, 60));

        panel_conducta.setBackground(new java.awt.Color(255, 255, 255));
        panel_conducta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 167, 157), 3));
        panel_conducta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_conducta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuario8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lbl_usuario8.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario8.setText("CONDUCTA DE RECLUSOS");
        panel_conducta.add(lbl_usuario8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 330, 30));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel53.setText("Seleccione el recluso:");
        panel_conducta.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel54.setText("Seleccione el Sector:");
        panel_conducta.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 190, -1));

        jComboBox_Sector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_Sector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SectorActionPerformed(evt);
            }
        });
        panel_conducta.add(jComboBox_Sector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 180, 30));

        jTable_Reclusos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Reclusos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Reclusos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ReclusosMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jTable_Reclusos);

        panel_conducta.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 530, 150));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Save.png"))); // NOI18N
        jLabel56.setText("Agregar");
        jLabel56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel56.setIconTextGap(-22);
        jLabel56.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });
        panel_conducta.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, -1, 90));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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
        });
        panel_conducta.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, -1, 90));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel59.setText("Filtrar por nombre:");
        panel_conducta.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 170, -1));

        jTextField_Filtrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FiltrarActionPerformed(evt);
            }
        });
        jTextField_Filtrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_FiltrarKeyTyped(evt);
            }
        });
        panel_conducta.add(jTextField_Filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 210, 30));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel60.setText("Descripción de Conducta:");
        panel_conducta.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel55.setText("Registros de Conducta del Recluso:");
        panel_conducta.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jTable_Conducta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Conducta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Conducta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ConductaMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTable_Conducta);

        panel_conducta.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 530, 150));

        jButton_Reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Reporte.setText("Imprimir Reporte");
        jButton_Reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ReporteMouseClicked(evt);
            }
        });
        jButton_Reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReporteActionPerformed(evt);
            }
        });
        panel_conducta.add(jButton_Reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 170, 40));

        jTextArea_Descripcion.setColumns(2);
        jTextArea_Descripcion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextArea_Descripcion.setLineWrap(true);
        jTextArea_Descripcion.setRows(1);
        jTextArea_Descripcion.setTabSize(2);
        jScrollPane15.setViewportView(jTextArea_Descripcion);

        panel_conducta.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 380, 120));

        jPanel1.add(panel_conducta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 1040, 520));

        Jpanel_Control_Camaras.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel_Control_Camaras.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 153)));
        Jpanel_Control_Camaras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/camara.png"))); // NOI18N
        jLabel35.setText("Camara 4");
        jLabel35.setFocusable(false);
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel35.setIconTextGap(10);
        jLabel35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        Jpanel_Control_Camaras.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 210, 200));

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/camara.png"))); // NOI18N
        jLabel36.setText("Camara 1");
        jLabel36.setFocusable(false);
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel36.setIconTextGap(10);
        jLabel36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        Jpanel_Control_Camaras.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 210, 200));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/camara.png"))); // NOI18N
        jLabel37.setText("Camara 2");
        jLabel37.setFocusable(false);
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel37.setIconTextGap(10);
        jLabel37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        Jpanel_Control_Camaras.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 210, 200));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/camara.png"))); // NOI18N
        jLabel38.setText("Camara 3");
        jLabel38.setFocusable(false);
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel38.setIconTextGap(10);
        jLabel38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        Jpanel_Control_Camaras.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 210, 200));

        jPanel1.add(Jpanel_Control_Camaras, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 1110, 510));

        Jpanel_control_celdas.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel_control_celdas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Agrupación:");
        Jpanel_control_celdas.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 80, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Reclusos_celdas"));
        jPanel4.setDoubleBuffered(false);
        jPanel4.setName(""); // NOI18N

        Jtable_control_celdas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(Jtable_control_celdas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel_control_celdas.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 1020, 240));

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Documents-01.png"))); // NOI18N
        jLabel34.setText("Generar reporte");
        jLabel34.setFocusable(false);
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel34.setIconTextGap(10);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        Jpanel_control_celdas.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 100, 90));

        cmb_celda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_celdaActionPerformed(evt);
            }
        });
        Jpanel_control_celdas.add(cmb_celda, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 90, -1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por nombre"));
        jPanel17.setToolTipText("");
        jPanel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nombre recluso:");

        txt_nombre_reclus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre_reclusKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txt_nombre_reclus, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre_reclus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Jpanel_control_celdas.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 460, -1));
        jPanel17.getAccessibleContext().setAccessibleName("Busqueda por nombre");

        txt_cierre_celdas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cierre_celdasKeyTyped(evt);
            }
        });
        Jpanel_control_celdas.add(txt_cierre_celdas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 250, 30));

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Seleccione la celda:");
        Jpanel_control_celdas.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 30));

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Hora cierre:");
        Jpanel_control_celdas.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 30));

        txt_sector_celdas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_sector_celdasKeyTyped(evt);
            }
        });
        Jpanel_control_celdas.add(txt_sector_celdas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 250, 30));

        jLabel32.setBackground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Sector:");
        Jpanel_control_celdas.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 30));

        jLabel46.setBackground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Hora apertura:");
        Jpanel_control_celdas.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 30));

        txt_agrupacion_celdas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_agrupacion_celdasKeyTyped(evt);
            }
        });
        Jpanel_control_celdas.add(txt_agrupacion_celdas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 250, 30));

        txt_apertura_celdas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apertura_celdasKeyTyped(evt);
            }
        });
        Jpanel_control_celdas.add(txt_apertura_celdas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 250, 30));

        lbl_usuario12.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario12.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario12.setText("GESTIÓN DE CELDAS");
        Jpanel_control_celdas.add(lbl_usuario12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 180, -1));

        jPanel1.add(Jpanel_control_celdas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 1080, 510));

        panel_asistencia_citas.setBackground(new java.awt.Color(255, 255, 255));
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
        jScrollPane7.setViewportView(tabCitasSi);

        panel_asistencia_citas.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 530, 190));

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
        jScrollPane12.setViewportView(tabCitasNo);

        panel_asistencia_citas.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 530, 170));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel47.setText("VISITAS");

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

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(txtApellidosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83)
                            .addComponent(jLabel84)
                            .addComponent(txtMotivosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVinculosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jLabel83))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVinculosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jLabel84))
                .addGap(6, 6, 6)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotivosCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        panel_asistencia_citas.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 530, 160));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

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

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreRe11, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNombreRe11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_asistencia_citas.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, 70));

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
        panel_asistencia_citas.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 240, 30));

        lbl_usuario6.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbl_usuario6.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario6.setText("CITAS");
        panel_asistencia_citas.add(lbl_usuario6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel91.setText("Asistencia del dia");
        panel_asistencia_citas.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 170, -1));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel92.setText("Fecha actual:");
        panel_asistencia_citas.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 110, -1));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157), new java.awt.Color(0, 167, 157)));

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

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtApellidosReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel96)
                        .addComponent(jLabel95)
                        .addComponent(txtNombreReclusoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addGap(202, 202, 202)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
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
                .addGap(0, 17, Short.MAX_VALUE))
        );

        panel_asistencia_citas.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 330, 170));

        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        panel_asistencia_citas.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 180, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel97.setText("Citas del dia:");
        panel_asistencia_citas.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 170, -1));

        jPanel1.add(panel_asistencia_citas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1210, 520));

        Jpanel_visitas.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel_visitas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Asistencia"));

        Jtable_visitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(Jtable_visitas);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel_visitas.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 230, 640, 180));

        jLabel44.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 167, 157));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel44.setText("dactilar ");
        jLabel44.setName(""); // NOI18N
        Jpanel_visitas.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 70, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 167, 157));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("huella ");
        jLabel42.setName(""); // NOI18N
        Jpanel_visitas.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 60, 30));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/Finger Print.png"))); // NOI18N
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        Jpanel_visitas.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 70, 80));

        jLabel43.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 167, 157));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("Colocar ");
        jLabel43.setName(""); // NOI18N
        Jpanel_visitas.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 70, 30));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Visitas  de este dia "));

        Jtable_visitas_totales_recluso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(Jtable_visitas_totales_recluso);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel_visitas.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 44, 640, 170));

        txt_hora_reclu_visit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_hora_reclu_visitKeyPressed(evt);
            }
        });
        Jpanel_visitas.add(txt_hora_reclu_visit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 147, -1));
        Jpanel_visitas.add(txt_crimen_reclu_visit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 205, -1));
        Jpanel_visitas.add(txt_apellido_reclu_visit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 205, -1));

        txt_nombre_reclu_visit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_reclu_visitActionPerformed(evt);
            }
        });
        Jpanel_visitas.add(txt_nombre_reclu_visit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 205, -1));

        txt_apellido_visitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellido_visitanteActionPerformed(evt);
            }
        });
        txt_apellido_visitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellido_visitanteKeyPressed(evt);
            }
        });
        Jpanel_visitas.add(txt_apellido_visitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 205, -1));

        txt_nombre_visitante_visit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_visitante_visitKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre_visitante_visitKeyTyped(evt);
            }
        });
        Jpanel_visitas.add(txt_nombre_visitante_visit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 204, 22));

        txt_codigo_barra_visitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codigo_barra_visitasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codigo_barra_visitasKeyTyped(evt);
            }
        });
        Jpanel_visitas.add(txt_codigo_barra_visitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 128, 22));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Codigo de barras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 18), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_code_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_code_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Jpanel_visitas.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 230, 100));

        jLabel2.setText("Hora de la cita");
        Jpanel_visitas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Codigo de barras:");
        Jpanel_visitas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setText("Nombre del visitante");
        Jpanel_visitas.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel5.setText("Apellido del visitante");
        Jpanel_visitas.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel6.setText("Nombre del recluso");
        Jpanel_visitas.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel7.setText("Apellidos del recluso");
        Jpanel_visitas.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel8.setText("Crimen");
        Jpanel_visitas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        lbl_usuario13.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        lbl_usuario13.setForeground(new java.awt.Color(0, 167, 157));
        lbl_usuario13.setText("Asistencia de Visitas");
        Jpanel_visitas.add(lbl_usuario13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jPanel1.add(Jpanel_visitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 1090, 510));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIpod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod2ActionPerformed
        llamarMenu();
    }//GEN-LAST:event_buttonIpod2ActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        /*String path = ""; //Ubicacion del reporte
        try {
            //Llamamos la ubicación
            path = getClass().getResource("/Reportes/Control_Reclusos_sector.jasper").getPath();
            //Decodificamos -esto es por si acaso un caracter especial nos falla
            path = URLDecoder.decode(path, "UTF-8");
            //Creamos la conexion
            Connection cn = new Conexion().conectar();
            //Creamos los parametros
            //Aunq no los necesitamos para este reporte
            Map parametros = new HashMap();
            parametros.put("celda", codigo_celda);
            //Creamos el Objeto Reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Creamos el objeto de impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora solo falta crear el Visor-formulario donde se muestra el reporte-
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de Usuarios"); //titulo a la ventana
            visor.setVisible(true); // mostramos el visor con el reporte

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
        
        try {
            String url = "src/Reportes/Control_Reclusos_sector.jasper";
            Map parametros = new HashMap(); 
            parametros.put("celda", codigo_celda);
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Reporte de Usuarios");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        Camara1 camera0 = new Camara1();
        camera0.setVisible(true);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        Camara2 camera1 = new Camara2();
        camera1.setVisible(true);
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel38MouseClicked

    private void txt_codigo_barraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_barraKeyPressed

    }//GEN-LAST:event_txt_codigo_barraKeyPressed

    private void txt_codigo_barraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_barraKeyTyped
        
        try {

            code(txt_codigo_barra.getText());
            comp_asistencia(Integer.parseInt(txt_codigo_barra.getText()));
            //   JOptionPane.showMessageDialog(null, "holo");
            

        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txt_codigo_barraKeyTyped

    private void lbl_busquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_busquedaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_busquedaMouseClicked

    private void txt_nombre_recluso_asis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_recluso_asis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_recluso_asis1ActionPerformed

    private void txt_nombre_recluso_asis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_recluso_asis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_recluso_asis1KeyPressed

    private void txt_sectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sectorActionPerformed

    private void txt_sectorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sectorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sectorKeyPressed

    private void txt_apellidos_reclusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidos_reclusoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidos_reclusoActionPerformed

    private void txt_apellidos_reclusoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidos_reclusoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidos_reclusoKeyPressed

    private void txt_alias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alias1ActionPerformed

    private void txt_alias1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_alias1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alias1KeyPressed

    private void txt_estado_recluso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estado_recluso1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estado_recluso1ActionPerformed

    private void txt_estado_recluso1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_estado_recluso1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estado_recluso1KeyPressed

    
    public static String fechaActual(){
    
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        
        return formatoFecha.format(fecha);
    }
     
    private void btn_ter_asistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ter_asistActionPerformed
        System.out.println("total = " +  total_reclusos_sector);
        mts.setCant_reclusos(total_reclusos_sector);
        mts.setBloke_sector(Blocke);
        System.out.println("bloque = " + Blocke);
        
        /*
        String path = ""; //Ubicacion del reporte
        try {
            //Llamamos la ubicación
            path = getClass().getResource("/Reportes/Asistencia.jasper").getPath();
            //Decodificamos -esto es por si acaso un caracter especial nos falla
            path = URLDecoder.decode(path, "UTF-8");
            //Creamos la conexion
            Connection cn = new Conexion().conectar();
            //Creamos los parametros
            //Aunq no los necesitamos para este reporte
            Map parametros = new HashMap();
            parametros.put("sector", Blocke);
            //Creamos el Objeto Reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Creamos el objeto de impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora solo falta crear el Visor-formulario donde se muestra el reporte-
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de Asistencia"); //titulo a la ventana
            visor.setVisible(true); // mostramos el visor con el reporte

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
        
        try {
            String url = "src/Reportes/Asistencia.jasper";
            Map parametros = new HashMap(); 
            parametros.put("sector", Blocke);
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Reporte de Asistencia");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        mts.scanning_asist();
        mts.setCode_barra(0);
        mts.setCant_reclusos(total_reclusos_sector);
        mts.modificar_asistencia();
        btn_ter_asist.setEnabled(false);
        
        
    }//GEN-LAST:event_btn_ter_asistActionPerformed

    private void txt_codigo_barra_visitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_barra_visitasKeyTyped

        try {
            code(txt_codigo_barra_visitas.getText());
            comp_asistencia_visitas(Integer.parseInt(txt_codigo_barra_visitas.getText()));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_codigo_barra_visitasKeyTyped

    private void txt_codigo_barra_visitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_barra_visitasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigo_barra_visitasKeyPressed

    private void txt_nombre_visitante_visitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_visitante_visitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_visitante_visitKeyTyped

    private void txt_nombre_visitante_visitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_visitante_visitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_visitante_visitKeyPressed

    private void txt_apellido_visitanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_visitanteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellido_visitanteKeyPressed

    private void txt_apellido_visitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellido_visitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellido_visitanteActionPerformed

    private void txt_hora_reclu_visitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hora_reclu_visitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hora_reclu_visitKeyPressed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MouseClicked

    private void txt_nombre_reclu_visitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_reclu_visitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_reclu_visitActionPerformed

    private void txt_nombre_empleado_hisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_empleado_hisKeyTyped
        receptorx2();
    }//GEN-LAST:event_txt_nombre_empleado_hisKeyTyped

    private void btn_busqueda_añoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_busqueda_añoMouseClicked

    }//GEN-LAST:event_btn_busqueda_añoMouseClicked
        String fechax;
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int  x = 0+jDayChooser1.getDay();
        int  y = jMonthChooser1.getMonth()+1;
        int  z =jYearChooser1.getYear();
        System.out.println("" + z +"-"+"0"+y+"-"+ x);

        fechax = "" + z +"-"+"0"+y+"-"+ x;
        buscador_histoty_sistema();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10KeyPressed

    private void txt_nombre_reclusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_reclusoActionPerformed

    }//GEN-LAST:event_txt_nombre_reclusoActionPerformed

    private void txt_nombre_reclusoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_reclusoKeyPressed

    }//GEN-LAST:event_txt_nombre_reclusoKeyPressed

    private void txt_cod_reclusoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_reclusoKeyPressed

    }//GEN-LAST:event_txt_cod_reclusoKeyPressed

    private void txt_estado_reclusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estado_reclusoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estado_reclusoActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

        reiniciarJTable(jtable_reclusos);

        if (txt_cod_recluso.getText().length() == 0) {
            Notification.show("Error", "Campo en blanco", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
        } else {

            Code_recluso = Integer.valueOf(txt_cod_recluso.getText());
            mtx.setCodigo(Code_recluso);
            if (mtx.consult_data()) {
                codigox = mtx.getCodigo();
                nombrex = mtx.getNombre();
                apellidosx = mtx.getApellidos();
                edadx = mtx.getEdad();
                alturax = mtx.getAltura();
                pesox = mtx.getPeso();
                tezx = mtx.getTez();
                aliasx = mtx.getAlias();
                estado_reclusox = mtx.getEstado_recluso();
                codigo_barrax = mtx.getCodigo_barra();
                //Object[] comlumnas = {"Cod recluso","Nombre Recluso","Estado","Codigo barra"};
                //DefaultTableModel model = new DefaultTableModel(comlumnas, WIDTH);
                // jtable_reclusos.setModel(model);
                model.addRow(new Object[]{codigox, nombrex, estado_reclusox, codigo_barrax,apellidosx,edadx ,alturax, pesox, tezx,aliasx }); // agratar terminos a la tabla
                captura_datos();

                mta.receptor_data(apellidosx);
                lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
                lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label

                lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
                lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label

                lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
                lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label
            } else {

            }

        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        reiniciarJTable(jtable_reclusos);
        consulta_general();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txt_besquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_besquedaKeyPressed
     
    }//GEN-LAST:event_txt_besquedaKeyPressed

    private void txt_besquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_besquedaKeyTyped
       reiniciarJTable(jtable_reclusos);

        if(txt_besqueda.getText().length() ==0 )
        {

        }
        else
        {
            nombrex = txt_besqueda.getText();
            //    System.out.println("asdasdasd " + nombrex);
            mtx.setNombre(nombrex);

            if(mtx.consulta_like())
            {
                
                txt_besqueda.setText("");
                codigox = mtx.getCodigo();
                nombrex = mtx.getNombre();
                apellidosx = mtx.getApellidos();
                edadx = mtx.getEdad();
                alturax = mtx.getAltura();
                pesox = mtx.getPeso();
                tezx = mtx.getTez();
                aliasx = mtx.getAlias();
                estado_reclusox = mtx.getEstado_recluso();
                codigo_barrax = mtx.getCodigo_barra();
                captura_datos();
                model.addRow(new Object[]{codigox, nombrex, estado_reclusox, codigo_barrax,apellidosx,edadx ,alturax, pesox, tezx,aliasx }); // agratar terminos a la tabla
                mta.receptor_data(apellidosx);
                lbl_foto1.setIcon(mta.getNewicon1_ur()); // se monta la img en el label
                lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label

                lbl_foto2.setIcon(mta.getNewicon2_ur()); // se monta la img en el label
                lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label

                lbl_foto3.setIcon(mta.getNewicon3_ur()); // se monta la img en el label
                lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label

            }
            else
            {
                txt_besqueda.getText();
            }

        }
    }//GEN-LAST:event_txt_besquedaKeyTyped

    private void txt_besquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_besquedaMouseClicked
        txt_besqueda.setText("");
    }//GEN-LAST:event_txt_besquedaMouseClicked

    private void cmb_celdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_celdaActionPerformed
        codigo_celda = Integer.parseInt(cmb_celda.getSelectedItem().toString());
        buscador_celdas();
    }//GEN-LAST:event_cmb_celdaActionPerformed

    private void txt_nombre_reclusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_reclusKeyTyped
        nombre_recluso = txt_nombre_reclus.getText();
        Busqueda_recluso();
    }//GEN-LAST:event_txt_nombre_reclusKeyTyped

    private void txt_cierre_celdasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cierre_celdasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cierre_celdasKeyTyped

    private void txt_sector_celdasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sector_celdasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sector_celdasKeyTyped

    private void txt_agrupacion_celdasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_agrupacion_celdasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_agrupacion_celdasKeyTyped

    private void txt_apertura_celdasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apertura_celdasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apertura_celdasKeyTyped

    private void btn_ter_asist1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ter_asist1ActionPerformed
        txt_codigo_barra.setText("");
        txt_nombre_recluso_asis1.setText("");
        txt_sector.setText("");
        txt_apellidos_recluso.setText("");
        txt_alias1.setText("");
        txt_estado_recluso1.setText("");
        DefaultTableModel dtm = (DefaultTableModel)Jtable_asistencia_R.getModel();
        dtm.setRowCount(0);
        txt_codigo_barra.setEnabled(true);
        mts.setCode_barra(0);
        btn_ter_asist.setEnabled(true);
        mts.setSector(Blocke);
        mts.Obtener_cod_sector();
        int cod_sector = mts.getCod_sector();
        mts.setCod_sector(cod_sector);
        mts.modificar_asistencia();
        verificador_de_codigo2 = 0;
        obtener_reclusos_sector();
                        
    }//GEN-LAST:event_btn_ter_asist1ActionPerformed

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

    private void txtNombreCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCitasActionPerformed

    private void txtNombreCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCitasKeyTyped

    private void txtApellidosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosCitasActionPerformed

    private void txtApellidosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosCitasKeyTyped

    private void txtVinculosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVinculosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVinculosCitasActionPerformed

    private void txtVinculosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVinculosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVinculosCitasKeyTyped

    private void txtMotivosCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotivosCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivosCitasActionPerformed

    private void txtMotivosCitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivosCitasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivosCitasKeyTyped

    private void txtNombreRe11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreRe11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRe11ActionPerformed

    private void txtNombreRe11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRe11KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRe11KeyTyped

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

    private void jPanel_AsistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_AsistenciaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel_AsistenciaMouseClicked

    private void jComboBox_SectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SectorActionPerformed
        // TODO add your handling code here:
        mthConsultarRecluso();
    }//GEN-LAST:event_jComboBox_SectorActionPerformed

    private void jTable_ReclusosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ReclusosMouseClicked
        Mth_CargarReclusos();
        mthConsultarConducta();
    }//GEN-LAST:event_jTable_ReclusosMouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        if (jTextArea_Descripcion.getText().equals("") || cod_recluso == 0){
            JOptionPane.showMessageDialog(this, "Campos vacios o registro no seleccionado.");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mtod_conducta obj = new Mtod_conducta();
            //insertando los nuevos valores a los metodos de la clase
            obj.setDescripcion(jTextArea_Descripcion.getText());
            obj.setFecha(fecha_conducta);
            obj.setCodigoRecluso(cod_recluso);
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if(obj.mthGuardar()){
                JOptionPane.showMessageDialog(this, "Datos guardados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos");
            }
            //ejecuta el metodo de limpiar campos
            mthConsultarConducta();
        }
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        if (jTextArea_Descripcion.getText().equals("") || cod_conducta == 0 ){
            JOptionPane.showMessageDialog(this, "Campos vacios o registro no seleccionado.");
        } else {
            //declarando variable de la clase que contiene los metodos
            Mtod_conducta obj = new Mtod_conducta();
            //insertando los nuevos valores ingresados por los usuarios a los metodos de la clase
            obj.setDescripcion(jTextArea_Descripcion.getText());
            obj.setFecha(fecha_conducta);
            //ejecuta el metodo y valida si se realizo satisfactoriamente
            if (obj.mthModificar()){
                JOptionPane.showMessageDialog(this, "Datos modificados");
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar");
            }
            //ejecuta el metodo de limpiar campos
            mthConsultarConducta();
        }
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jTextField_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FiltrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FiltrarActionPerformed

    private void jTextField_FiltrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_FiltrarKeyTyped
        // TODO add your handling code here:

        char C= evt.getKeyChar();
        //Dennis Alberto Benavides Chavarria
        if (jTextField_Filtrar.getText().length() >= 30) {
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
        else
        {
            Mtod_conducta obj = new Mtod_conducta();
            ResultSet res;

            ConsultarSector();
            obj.setCodigoSector(cod_sector);
            obj.setNombreRecluso(jTextField_Filtrar.getText());
            DefaultTableModel dtm = (DefaultTableModel) jTable_Reclusos.getModel();
            dtm.setRowCount(0);
            res = obj.FiltrarRecluso();
            //ENCABEZADOS DE COLUMNAS
            dtm.setColumnIdentifiers(new Object[]{"Codigo de Recluso", "Nombre", "Apellido", "Edad"});
            try {
                while (res.next()) {
                    //DATOS QUE SE CARGARAN A COLUMNAS
                    Vector v = new Vector();
                    v.add(res.getInt(1));
                    v.add(res.getString(2));
                    v.add(res.getString(3));
                    v.add(res.getString(4));
                    dtm.addRow(v);
                    jTable_Reclusos.setModel(dtm);

                    jTable_Reclusos.getColumnModel().getColumn(0).setMaxWidth(0);
                    jTable_Reclusos.getColumnModel().getColumn(0).setMinWidth(0);
                    jTable_Reclusos.getColumnModel().getColumn(0).setPreferredWidth(0);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            }
        }

    }//GEN-LAST:event_jTextField_FiltrarKeyTyped

    private void jTable_ConductaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ConductaMouseClicked
        // TODO add your handling code here:
        Mth_CargarConducta();
    }//GEN-LAST:event_jTable_ConductaMouseClicked

    private void jButton_ReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ReporteMouseClicked
        // TODO add your handling code here:\

    }//GEN-LAST:event_jButton_ReporteMouseClicked

    private void jButton_ReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReporteActionPerformed
//         TODO add your handling code here:
        String path = ""; //Ubicacion del reporte
        try
        {
            String url = "src/Reportes/Reporte_conducta.jasper";
            Map parametro = new HashMap();
            parametro.put("cod_recluso", 1);
            Connection cn = new Mantenimiento.Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametro, cn);
            JasperViewer ventana = new JasperViewer(reporte,false);
            ventana.setTitle("Reporte de Conducta");
            ventana.setVisible(true);

            }
        catch(Exception ex)
        {
                System.out.println(ex.getMessage());
            }
    }//GEN-LAST:event_jButton_ReporteActionPerformed

    private void jtable_reclusosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_reclusosMouseClicked
        
        
        
    }//GEN-LAST:event_jtable_reclusosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
      
        Calendar gc = new GregorianCalendar();
        Date fecha_actual = gc.getTime();
        gc.setTime(fecha_actual);
        int dia = gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH);
        int anio = gc.get(gc.YEAR);
        String fecha_ahora = dia + "-" + (mes + 1)   + "-" + anio;
        

                        

        //fecha salida
        dia = dia - 7;
        String fecha_atras = dia + "-" + (mes + 1) + "-" + anio;
        Calendar gc2 = new GregorianCalendar();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha_salida = null;
        try {
            fecha_salida = ft.parse(fecha_atras);
        } catch (ParseException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        gc2.setTime(fecha_salida);
        int dia1 = gc2.get(gc.DAY_OF_MONTH);
        int mes1 = gc2.get(gc.MONTH);
        int anio1 = gc2.get(gc.YEAR);
        String fecha_atras2 = dia1 + "-" + (mes1 + 1)+ "-" + anio1;
                            
       

        try
        {
            //String dato=dpDesde.toString();
            String url = "src/Reportes/Reclusos.jasper";
            Map parametro = new HashMap();
            parametro.put("Fecha1", fecha_atras2);
            parametro.put("Fecha2", fecha_ahora);
            JOptionPane.showMessageDialog(this, "fecha1 " + fecha_atras2);
            JOptionPane.showMessageDialog(this, "fecha2 " + fecha_ahora);
            Connection cn = new Mantenimiento.Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametro, cn);
            JasperViewer ventana = new JasperViewer(reporte,false);
            ventana.setTitle("Reportes de reclusos por salir");
            ventana.setVisible(true);        
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
                        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
            java.util.logging.Logger.getLogger(Sector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sector("NULL").setVisible(true);
            }
        });
    }

    // eventos para llamar el  menu de los iconos---------------------------------------------------inicia programacion 
    public void llenarMenu() {
        List<Avatar> avatars = new ArrayList<Avatar>();
        avatars.add(new Avatar("Video", loadImage("/PNG/camara.png")));
        avatars.add(new Avatar("Seguridad", loadImage("/PNG/Safety-Box-02.png")));
      //  avatars.add(new Avatar("Lectura de huellas", loadImage("/PNG/Finger Print.png")));
        avatars.add(new Avatar("Informacion del usuario", loadImage("/PNG/User-Profile.png")));
        avatars.add(new Avatar("Configuración", loadImage("/PNG/User-Interaction.png")));
        avatars.add(new Avatar("Consulta", loadImage("/PNG/Find-Replace.png")));
        avatars.add(new Avatar("Asistencia", loadImage("/PNG/Document-Check.png")));
        //avatars.add(new Avatar("Visitas", loadImage("/PNG/User-Headphone.png"))); 
        avatars.add(new Avatar("Citas", loadImage("/PNG/Group-Delete.png")));
        avatars.add(new Avatar("Conducta", loadImage("/PNG/Reclusos1.png")));
        avatars.add(new Avatar("ayuda", loadImage("/PNG/Help.png")));
        avatars.add(new Avatar("Horarios", loadImage("/PNG/Horarios_Celdas.png")));
        avatars.add(new Avatar("Cerrar sesion", loadImage("/PNG/Logout.png")));
        
        menu.setAvatars(avatars);
    }  // evento donde se cargan los logos de la seccion  1)

    public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(Sector.class.getResource(fileName));
        } catch (Exception e) {
            return null;
            
        }
    } // evento donde se  cargan las imagenes 2) 

    public void carga() {
        ImageIcon icono = new ImageIcon(loadImage("/PNG/camara.png"));
        Image img = icono.getImage();
        Image newing = img.getScaledInstance(155, 180, java.awt.Image.SCALE_SMOOTH);
        ImageIcon Newicon = new ImageIcon(newing);
        lbl_foto1.setIcon(Newicon);
        lbl_foto1.setSize(300, 300);
        
    }
    
    public void llamarMenu() {
        if (menu.getSelectedtitulo().equals("Video")) {
            
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(true);
           panel_horarios.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
        panel_conducta.setVisible(false);
        panelNice1.setVisible(true);
        mts.setVerificdor(0);
            
        } else if (menu.getSelectedtitulo().equals("Crear Reporte")) {
              mts.setVerificdor(1);
        } else if (menu.getSelectedtitulo().equals("Seguridad")) {
        Jpanel_control_celdas.setVisible(true);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
         panel_asistencia_citas.setVisible(false);
               panelNice1.setVisible(true);
                  panel_horarios.setVisible(false);
               panel_conducta.setVisible(false);
            
              mts.setVerificdor(1);
        } else if (menu.getSelectedtitulo().equals("Lectura de huellas")) {
            
        } else if (menu.getSelectedtitulo().equals("Cerrar sesion")) {
            Login_MT2_v2 hole = new Login_MT2_v2();
            hole.setVisible(true);
            this.setVisible(false);
            
        } else if (menu.getSelectedtitulo().equals("Consulta")) {

        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
           panel_horarios.setVisible(false);
        Buscador_reclusos.setVisible(true);
        panel_asistencia_citas.setVisible(false);
                panelNice1.setVisible(true);
                panel_conducta.setVisible(false);
            mts.setVerificdor(0);
        } else if (menu.getSelectedtitulo().equals("Asistencia")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(true);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
               panelNice1.setVisible(true);
               panel_conducta.setVisible(false);
            mts.setVerificdor(0);
        } else if (menu.getSelectedtitulo().equals("Visitas")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(true);
           panel_horarios.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
              panelNice1.setVisible(true);
            
            mts.setVerificdor(1);
        }else if (menu.getSelectedtitulo().equals("Informacion del usuario")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(true);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
           panel_horarios.setVisible(false);
        panelNice1.setVisible(true);
        panel_conducta.setVisible(false);
        mts.setVerificdor(1);
        
        }
        else if (menu.getSelectedtitulo().equals("Conducta")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
        panelNice1.setVisible(true);
           panel_horarios.setVisible(false);
        panel_conducta.setVisible(true);
         mts.setVerificdor(1);
         
        }else if (menu.getSelectedtitulo().equals("Citas")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(true);
        panel_horarios.setVisible(false);
        mts.setVerificdor(0);
        panelNice1.setVisible(false);
        panel_conducta.setVisible(false);    
        }else if (menu.getSelectedtitulo().equals("Horarios")) {
        Jpanel_control_celdas.setVisible(false);
        Jpanel_Control_Camaras.setVisible(false);
        jPanel_Asistencia.setVisible(false);
        Jpanel_visitas.setVisible(false);
        Panel_control_datos.setVisible(false);
        Buscador_reclusos.setVisible(false);
        panel_asistencia_citas.setVisible(false);
        mts.setVerificdor(0);
        panelNice1.setVisible(false);
        panel_conducta.setVisible(false);   
           panel_horarios.setVisible(true);
        }
        
     
        
    } /// evento  donde se cargan los jpanels 3)

    // programacion del panel del control de  celdas ---------------------------------------------

    public void buscador_celdas(){
        txt_sector_celdas.setText("");
        txt_agrupacion_celdas.setText("");
        txt_apertura_celdas.setText("");
        txt_cierre_celdas.setText("");
        
        ResultSet res;
        DefaultTableModel dtm = (DefaultTableModel) Jtable_control_celdas.getModel();
        dtm.setRowCount(0);
        mtx.setCodigo_celda(codigo_celda);
        res = mtx.ObtenerReclusos();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Recluso", "Nombre", "Apellido", "Codigo de Barra", "Sector", "Agrupacion", "Hora apertura", "Hora cierre"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getInt(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));             
                dtm.addRow(v);
                Jtable_control_celdas.setModel(dtm);
            }
            
            Jtable_control_celdas.getColumnModel().getColumn(0).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(0).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(4).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(4).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(5).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(5).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(5).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(6).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(6).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(6).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(7).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(7).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(7).setPreferredWidth(0);
            
            txt_sector_celdas.setText(Jtable_control_celdas.getValueAt(0,4).toString());
            txt_agrupacion_celdas.setText(Jtable_control_celdas.getValueAt(0,5).toString());
            txt_apertura_celdas.setText(Jtable_control_celdas.getValueAt(0,6).toString());
            txt_cierre_celdas.setText(Jtable_control_celdas.getValueAt(0,7).toString());
            
            
            
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "1" + ex);
        }
    }
    
    public void Busqueda_recluso(){
        ResultSet res;
        DefaultTableModel dtm = (DefaultTableModel) Jtable_control_celdas.getModel();
        dtm.setRowCount(0);
        mtx.setCodigo_celda(codigo_celda);
        mtx.setNombre_recluso(nombre_recluso);
        res = mtx.ObtenerReclusos2();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Recluso", "Nombre", "Apellido", "Codigo de Barra", "Sector", "Agrupacion", "Hora apertura", "Hora cierre"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getInt(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));             
                dtm.addRow(v);
                Jtable_control_celdas.setModel(dtm);
            }
            
            Jtable_control_celdas.getColumnModel().getColumn(0).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(0).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(4).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(4).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(5).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(5).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(5).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(6).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(6).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(6).setPreferredWidth(0);
            
            Jtable_control_celdas.getColumnModel().getColumn(7).setMaxWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(7).setMinWidth(0);
            Jtable_control_celdas.getColumnModel().getColumn(7).setPreferredWidth(0);
            
        } catch (Exception ex) {

        }
    }
    /*public void buscador(String nombre) {
        Mtod_control_celdas cell = new Mtod_control_celdas();
        cell.setBlockex(Blocke);
        cell.setNombre_recluso(txt_nombre_recluso_cel.getText());
     
       
       
        
        if (cell.buscador_recluso()) {
            nombre_recluso = cell.getNombre_recluso();
            Apellidos_recluso = cell.getApellidos_recluso();
            codigo_barra = cell.getCodigo_barra();
            sector = cell.getSector();
            Agrupacion = cell.getAgrupacion();
            codigo_celda = cell.getCodigo_celda();
            horario_salida = cell.getHorario_salida();
            horario_entrada = cell.getHorario_entrada();
            
       
            try{
             DefaultTableModel temp = (DefaultTableModel) Jtable_buscador.getModel();
             int a =temp.getRowCount()-1;
            for(int i=0; i<a; i++)
                temp.removeRow(i);
             }catch(Exception e){
            System.out.println(e);
            }
            
            txt_nombre_recluso_cel.setText(cell.getNombre_recluso());
            txt_codigo_barra_cell.setText("" + cell.getCodigo_barra());
            txt_sector_cell.setText(cell.getSector());
            txt_agrupacion.setText(cell.getAgrupacion());
            txt_codigo_celda_cel.setText("" + cell.getCodigo_celda());
            txt_apellidos_r_cell.setText(cell.getApellidos_recluso());
            txt_horario_salida_cell.setText(cell.getHorario_salida());
            txt_horario_endrada_cell.setText(cell.getHorario_entrada());
            
            mta.receptor_data(Apellidos_recluso);
            lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
            lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label 
            
            lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
            lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label 
            
            lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
            lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
            
            modelo_control_celdas_bus.addRow(new Object[]{nombre_recluso, codigo_barra, codigo_celda, sector, Agrupacion, Apellidos_recluso, horario_salida, horario_entrada});
            txt_nombre_recluso_cel.setText("");
        }
        
        
    }*/
    
    /*public void consulta_control_de_celdas() // consultade las tablas del control de celdas 
    {
        try {
            
            String sql = "select nombre_recluso,apellido_recluso, codigo_barra, nombre_sector, Agrupacion.nombre_agrupacion,cod_celda,hora_apertura, hora_cierre  from Reclusos, Sector, Agrupacion,Celdas, Horario where Sector.cod_sector = Reclusos.cod_sector AND Agrupacion.cod_agrupacion = Sector.cod_agrupacion AND Sector.cod_sector = Celdas.cod_celda AND Horario.cod_horario = Sector.cod_horario and nombre_sector =?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, Blocke);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                modelo_control_celdas.addRow(new Object[]{rs.getString("nombre_recluso"), rs.getInt("codigo_barra"), rs.getInt("cod_celda"), rs.getString("nombre_sector"), rs.getString("nombre_agrupacion"), rs.getString("apellido_recluso"), rs.getString("hora_apertura"), rs.getString("hora_cierre")});
                
            }
            cmd.close();
            // cn.close();
            Jtable_control_celdas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (Jtable_control_celdas.getSelectedRow() >= 1) /// selecccion de jtextfields  del jtable
                    {                        
                        //txt_nombre_recluso_cel.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 0) + "");
                        txt_codigo_barra_cell.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 1) + "");
                        txt_sector_cell.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 2) + "");
                        txt_agrupacion.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 3) + "");
                        txt_nombre_reclus.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 4) + "");
                        txt_apellidos_r_cell.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 5) + "");
                        txt_horario_salida_cell.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 6) + "");
                        txt_horario_endrada_cell.setText(Jtable_control_celdas.getValueAt(Jtable_control_celdas.getSelectedRow(), 7) + "");
                        
                        mta.receptor_data(txt_apellidos_r_cell.getText());
                        lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
                        lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label 
                        
                        lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
                        lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label 
                        
                        lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
                        lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                        
                    } else {
                        
                    }
                }                
            });
            
        } catch (Exception e) {
            System.out.println("error " + e);
        }        
    }*/
    //PROGRAMACION PRIMODIAL NO MODIFICAR---------------------------------------------------------------
    
    //PROGRMACION DE LA OBTENCION DE RECLUSOS POR SECTOR-----------------------------------------------------------------------
    
    public void obtener_reclusos_sector()
    {
      
      mts.setBloke_sector(Blocke); // se manda a setear el bloque del sector asignado del jefe de dicho sector 
      mts.cpnsulta_reclusos();   // consulta  la cantidad de  reclusos del sector 
      total_reclusos_sector = mts.getCant_reclusos();  // se obtienen el total de reclusos 
      code_reclusos =  new int[total_reclusos_sector]; // se asigna una rreglo con la cantidad de  reclusos que  obtuvo 
      total_r_sector = total_reclusos_sector;
    }
    
     // TERMINA PROGRMACION DE LA OBTENCION DE RECLUSOS POR SECTOR-----------------------------------------------------------------------
    
    
    
    //PROGRAMACION SOLO DE EL PANEL DE ASISTENCIA  XASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
         public void consult_asist_xas()
       {
            try{
                
                DefaultTableModel dtm = (DefaultTableModel)jAsis_Reclusos_complete.getModel();
                dtm.setRowCount(0);
                Statement declara = cn.createStatement();
                ResultSet res = declara.executeQuery("select cod_recluso, nombre_recluso, apellido_recluso ,estado, codigo_barra , nombre_sector,hora_apertura, hora_cierre FROM Reclusos, Tez, Estado, Sector, Horario  Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND Sector.cod_sector = Reclusos.cod_sector  AND  Horario.cod_horario = Sector.cod_horario AND Estado.cod_estado in (7,8) AND nombre_sector = '"+Blocke+"'");
                //res = obj.Consulta();
                dtm.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Apellido", "Estado", "Codigo de barra", "Sector", "Hora apertura", "Hora cierre"});
                try{
                    while(res.next()){
                        Vector v = new Vector();
                        v.add(res.getInt(1));
                        v.add(res.getString(2));
                        v.add(res.getString(3));
                        v.add(res.getString(4));
                        v.add(res.getInt(5));
                        v.add(res.getString(6));
                        v.add(res.getString(7));
                        v.add(res.getString(8));
                        
                        dtm.addRow(v);
                        jAsis_Reclusos_complete.setModel(dtm);
                        jAsis_Reclusos_complete.getColumnModel().getColumn(0).setMaxWidth(0);
                        jAsis_Reclusos_complete.getColumnModel().getColumn(0).setMinWidth(0);
                        jAsis_Reclusos_complete.getColumnModel().getColumn(0).setPreferredWidth(0);
                        contador_a ++;
                        // se recibe el total de reclusos del sector;
                        // contador para agregar datos en code_reclusos
                    }
                    code_reclusos[0] = contador_a;
                    //mthLimpiarCampos();
                }catch(Exception ex){
                    
                }
                
                /*try {
                
                String sql = "select cod_recluso, nombre_recluso,alias ,estado, codigo_barra , nombre_sector,hora_apertura, hora_cierre FROM Reclusos, Tez, Estado, Sector, Horario  Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND Sector.cod_sector = Reclusos.cod_sector  AND  Horario.cod_horario = Sector.cod_horario AND Estado.cod_estado in (7,8) AND nombre_sector = '"+Blocke+"'";
                PreparedStatement cmd = cn.prepareStatement(sql);
                ResultSet rs = cmd.executeQuery();
                while (rs.next()) {
                modelx.addRow(new Object[]{rs.getInt("cod_recluso"), rs.getString("nombre_recluso"), rs.getString("estado"), rs.getInt("codigo_barra"), rs.getString("nombre_sector"),rs.getString("hora_apertura"),rs.getString("hora_cierre")});
                code_reclusos[contador_a] = rs.getInt(1); // se recibe el total de reclusos del sector;
                contador_a ++;    // contador para agregar datos en code_reclusos
                }
                for (int i = 0; i <= contador_a-1 ; i++)
                {
                System.out.println(" " + code_reclusos[i] );
                }
                cmd.close();
                // cn.close();
                jAsis_Reclusos_complete.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                if(Jtable_asistencia_R.getSelectedRow() >=1) /// selecccion de jtextfields  del jtable
                {
                
                }
                else
                {
                
                }
                }
                });
                
                } catch (Exception e) {
                System.out.println("error" + e);
                }*/
            }catch(SQLException ex){
            Logger.getLogger(Sector.class.getName()).log(Level.SEVERE, null, ex);

            }
           
           
            /*try {
            
            String sql = "select cod_recluso, nombre_recluso,alias ,estado, codigo_barra , nombre_sector,hora_apertura, hora_cierre FROM Reclusos, Tez, Estado, Sector, Horario  Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND Sector.cod_sector = Reclusos.cod_sector  AND  Horario.cod_horario = Sector.cod_horario AND Estado.cod_estado in (7,8) AND nombre_sector = '"+Blocke+"'";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                modelx.addRow(new Object[]{rs.getInt("cod_recluso"), rs.getString("nombre_recluso"), rs.getString("estado"), rs.getInt("codigo_barra"), rs.getString("nombre_sector"),rs.getString("hora_apertura"),rs.getString("hora_cierre")});
                code_reclusos[contador_a] = rs.getInt(1); // se recibe el total de reclusos del sector; 
                contador_a ++;    // contador para agregar datos en code_reclusos 
            }
            for (int i = 0; i <= contador_a-1 ; i++)
           {
               System.out.println(" " + code_reclusos[i] );
           } 
            cmd.close();
           // cn.close();
               jAsis_Reclusos_complete.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(Jtable_asistencia_R.getSelectedRow() >=1) /// selecccion de jtextfields  del jtable
                    {  
                       
                    }
                    else
                    {
                    
                    }
                }  
            });
      
        } catch (Exception e) {
            System.out.println("error" + e);
        }*/ 
       }
      
      
    public void comp_asistencia(int code)
    {
    codigo_barraq = code; /// masfgbj siodejAasiuzruvgb modificar
    mts.setCode_barra(codigo_barraq); /// efaosduxhy szszodftgyszwguays modificar 
    mts.setBloke_sector(Blocke);
   
    if(total_r_sector !=0 && xas == false) // variable booleana
    {
     //  JOptionPane.showMessageDialog(null, "Hola")
        if(mts.asistencia_final())
        {
            if(codigo_barraq == verificador_de_codigo2)
            {
                JOptionPane.showMessageDialog(this, "Este usuario ya fue verificado");    
            }
            else
            {
                Code_recluso = mts.getCode_recluso();
                contador ++;
                // validador(Code_recluso);
                nombrex =mts.getNombre();
                sector = mts.getSector();
                apellidosx = mts.getApellidos();
                aliasx = mts.getAlias();
                estado_reclusox = mts.getEstado_recluso();
                integrar_asistencia_txt();
                verificador_de_codigo2 = codigo_barraq;
                mts.setCode_barra(codigo_barraq);
                mts.setCod_asistencia(2);
                mts.modificar_asistencia();
                mts.consult_asist();
                asistencia=  mts.getAsistencia();
                modely.addRow(new Object[]{""+Code_recluso, nombrex, apellidosx, codigo_barraq, asistencia});
                total_r_sector --; 
      
                mta.receptor_data(apellidosx);
                lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
                lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label 

                lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
                lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label 

                lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
                lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                
                txt_codigo_barra.setText("");
            }
        }
    }
    else
    {
        JOptionPane.showMessageDialog(null,  "Asistencia Completada");
        txt_codigo_barra.setEnabled(false);
        mts.setCant_reclusos(total_reclusos_sector);
        mts.setCode_barra(0);
        mts.setSector(Blocke);
        mts.Obtener_cod_sector();
        int cod_sector = mts.getCod_sector();
        mts.setCod_sector(cod_sector);
        mts.scanning_asist();
        
        
        /*String path = ""; //Ubicacion del reporte
        try {
            //Llamamos la ubicación
            path = getClass().getResource("/Reportes/Asistencia.jasper").getPath();
            //Decodificamos -esto es por si acaso un caracter especial nos falla
            path = URLDecoder.decode(path, "UTF-8");
            //Creamos la conexion
            Connection cn = new Conexion().conectar();
            //Creamos los parametros
            //Aunq no los necesitamos para este reporte
            Map parametros = new HashMap();
            parametros.put("sector", Blocke);
            //Creamos el Objeto Reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Creamos el objeto de impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora solo falta crear el Visor-formulario donde se muestra el reporte-
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de Asistencia"); //titulo a la ventana
            visor.setVisible(true); // mostramos el visor con el reporte

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
        
        
        try {
            String url = "src/Reportes/Asistencia.jasper";
            Map parametros = new HashMap(); 
            parametros.put("sector", Blocke);
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Reporte de Asistencia");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        btn_ter_asist.setEnabled(false);
        
        mts.modificar_asistencia();
    }
    }
      
      
      public void validador(int code_reclusos )
      {
      if(contador == 1)
      {
      codex = code_reclusos;
      }
      else if(contador >=2)
      {
       if( codex == code_reclusos)
       {
    Notification.show("Informacion " , "Este prisionero ya fue registrado", Notification.NICON_DARK_THEME, Notification.INFO_ICON);
     xas = true;
       }
       else
       {
      xas = false;
       }
       }
      
      }
      
      
        public void integrar_asistencia_txt()
      {
     
      txt_nombre_recluso_asis1.setText("" + nombrex);
      txt_sector.setText("" + sector);
      txt_apellidos_recluso.setText("" + apellidosx);
      txt_alias1.setText("" + aliasx);
      txt_estado_recluso1.setText("" + estado_reclusox);
      }
        
    
        
        
     //TERMINA PROGRAMACION SOLO DEL PANEL DE ASISTENCIA   
    //------------------------------------------------------------------------------------------------------------------------------------------
        
    // PROGRAMACION SOLO DEL PANEL DE VISITAS     
        
       String a; 
       public void consulta_visistas_dia() {
        try {
            String sql = "Select codigo_barra, nombre_visita,apellido_visita,nombre_recluso,apellido_recluso,hora_cita,fecha_cita,asistencia From Reclusos, Asistencia, Visitas, Citas,Estado Where Asistencia.cod_asistencia = Reclusos.cod_asistencia AND Reclusos.cod_recluso = Citas.cod_recluso AND Visitas.cod_visita = Citas.cod_visita AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado=1 AND fecha_cita = ? ";

            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1,Fecha_año);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
               
                modelo_visitas.addRow(new Object[]{rs.getString("nombre_visita"), rs.getString("nombre_recluso"),rs.getInt("codigo_barra"), rs.getString("asistencia"),rs.getTime("hora_cita"),rs.getString("apellido_visita"),rs.getString("apellido_recluso")});
            }
            cmd.close();
           // cn.close();

               Jtable_visitas_totales_recluso.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(Jtable_visitas_totales_recluso.getSelectedRow() >=1)
                    {
                   
                    
                   // txt_codigo_barra_visitas.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),2)+"");
                    txt_nombre_visitante_visit.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),0)+"");
                    txt_apellido_visitante.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),5)+"");
                    txt_nombre_reclu_visit.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),1)+"");
                    txt_apellido_reclu_visit.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),6)+"");
                    txt_crimen_reclu_visit.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),3)+"");
                    txt_hora_reclu_visit.setText(Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(),4)+"");
                    a = Jtable_visitas_totales_recluso.getValueAt(Jtable_visitas_totales_recluso.getSelectedRow(), 6) + "";
                   
                                
                    }
                   
                                   
       mta.receptor_data(a);
       lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
       lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label 
      
       lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
       lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label 
       
       lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
       lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                
                }
                
            });
            
        
            
                    

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    } // en este evento se consultan todos los reclusos 
       
      //PROGRMACION DE LA OBTENCION DE  visitas POR SECTOR-----------------------------------------------------------------------

      public void obtener_citas_reclusos()
     {
      
      mtv.setBloke_sector(Blocke); // se manda a setear el bloque del sector asignado del jefe de dicho sector 
      mtv.setFecha_act(Fecha_año);
      mtv.consulta_de_visitas();   // consulta  la cantidad de  reclusos del sector 
      total_de_visitas_sector = mtv.getCant_reclusos();  // se obtienen el total de reclusos 
      code_recluso_visitas =  new int[total_de_visitas_sector]; // se asigna una rreglo con la cantidad de  reclusos que  obtuvo 
      total_v_sector = total_de_visitas_sector;
      //System.out.println("" +total_de_visitas_sector);
      // System.out.println("" +total_v_sector);
     }
    
   //---------------------------------------------------------------------------------------------------------------
    
    
       public void comp_asistencia_visitas(int code)
      {
         
      codigo_barraq2 = code; /// masfgbj siodejAasiuzruvgb modificar
      mtv.setCode_barra(codigo_barraq2); /// efaosduxhy szszodftgyszwguays modificar 
      mtv.setBloke_sector(Blocke);
      mtv.setFecha_act(Fecha_año);
   
    if(total_v_sector !=0 && xas2 == false) // variable booleana
    {
     
      if(mtv.obtener_visita())
      {
          if(codigo_barraq2 == verificador_de_codigo1)
          {
              JOptionPane.showMessageDialog(this, "Este usuario ya fue verificado");
          }
          else
          {
              Code_recluso = mtv.getCode_recluso();
              contador ++;
              // validador(Code_recluso);
              nombre_visitante = mtv.getNombre_visitante();
              Apellidos_visitante = mtv.getApellido_visitante();
              
              nombrex = mtv.getNombre();
              apellidosx = mtv.getApellidos();
              hora = mtv.getHora_r();
              
              integrar_asistencia_txt_visitas();
              //pasos de validacion y de asistencia en este caso la asistencia se hara en el metodo asistencia
              verificador_de_codigo1 = codigo_barraq;
              mtv.setCode_barra(codigo_barraq2);
              mtv.setCod_asistencia(2);
              mtv.modificar_asistencia();
              mtv.consult_asist();
              asistencia=  mts.getAsistencia();
              modelo_visitasx.addRow(new Object[]{""+nombre_visitante, nombrex, codigo_barra, asistencia, hora,Apellidos_visitante,Apellidos_recluso });
              total_v_sector --;
              
              mta.receptor_data(apellidosx);
              lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
              lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label
              
              lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
              lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label
              
              lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
              lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label
          } 
      } 
      
      
    }
    else
    {
    JOptionPane.showMessageDialog(null,  "Asistencia Completada");
      txt_codigo_barra.setEnabled(false);
      mtv.setCant_reclusos(total_reclusos_sector);
      mtv.setCode_barra(0);
      mtv.scanning_asist_v();
      mtv.modificar_asistencia();
      xas2 = true;
    }
      
      
      }
      
      
        public void integrar_asistencia_txt_visitas() /// hay que modificar
      {
     
      txt_nombre_recluso_asis1.setText("" + nombrex);
      txt_nombre_reclu_visit.setText("" + nombre_visitante);
      txt_apellido_visitante.setText("" + Apellidos_visitante);
      txt_nombre_reclu_visit.setText("" +  nombrex);
      txt_apellido_reclu_visit.setText("" + apellidosx);
      txt_hora_reclu_visit.setText(""+hora);
              }
      
      
    // TERMINA PROGRAMACION SOLO DEL PANEL DE VISITAS    
        
   //Inicia progrmacion del hisyoria; del sistema
        
        
        
           public void consulta_historial_sistema()
       {
         try {
            
            String sql = "select nombre_usuario, fecha, descripcion from Registros_sistemas, Empleado where Empleado.cod_empleado = Registros_sistemas.cod_empleado";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                modelo_control_historial.addRow(new Object[]{rs.getString("nombre_usuario"), rs.getString("fecha"), rs.getString("descripcion")});
                
            }
            cmd.close();
           // cn.close();
               Jtable_his_sistema.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(Jtable_his_sistema.getSelectedRow() >=1) /// selecccion de jtextfields  del jtable
                    {  
                       txt_nombre_empleado_his.setText(Jtable_his_sistema.getValueAt(Jtable_his_sistema.getSelectedRow(),0)+ "");
                       txt_fecha_reporte_his.setText(Jtable_his_sistema.getValueAt(Jtable_his_sistema.getSelectedRow(),1)+ "");
                       txt_descripcion_his.setText(Jtable_his_sistema.getValueAt(Jtable_his_sistema.getSelectedRow(),2)+ "");
                       mta.receptor_data_empleado(txt_nombre_empleado_his.getText());
                       
                     lbl_foto3.setIcon(mta.getNewicon2()); // se monta la img en el label
                     lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                       
                    }
                    else
                    {
                    
                    }
                }  
            });
      
        } catch (Exception e) {
            System.out.println("error " + e);
        } 
       }
       
        public void buscador_histoty_sistema()
        {
        
          
             
         
          hqsq.setFecha(fechax);
          if(hqsq.buscador_historial2())
                 {
           
                              
                     txt_nombre_empleado_his.setText(hqsq.getNombre_user());
                     txt_fecha_reporte_his.setText(hqsq.getFecha());
                     txt_descripcion_his.setText(hqsq.getDescripcion());
                     mta.receptor_data_empleado(txt_nombre_empleado_his.getText());
                       
                     lbl_foto3.setIcon(mta.getNewicon2()); // se monta la img en el label
                     lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                     modelo_control_historial_x.addRow(new Object[]{txt_nombre_empleado_his.getText(), txt_fecha_reporte_his.getText(), txt_descripcion_his.getText()});
                    
                 }
     
        
        }

        
                 
        public void obtener_años()
        {
            
      
        if( mtx.consulta_de_años())
         {
       fecha_anterior = mtx.getFecha_historial();
       fecha_fin =mtx.getFecha_historial_fin();
       String sSubCadena = fecha_anterior.substring(0, 4);
       String sSubCadena2 = fecha_fin.substring(0, 4);
       int xas = Integer.parseInt(sSubCadena);
       int xas2 = Integer.parseInt(sSubCadena2);
       JOptionPane.showMessageDialog(null, ""+xas);
       JOptionPane.showMessageDialog(null, ""+xas2);
       Jyear_inicio.setYear(xas);
       Jyear_fin.setYear(xas2);
         }
          }
        
       public void receptorx2()
       {
           String xas= txt_nombre_empleado_his.getText();
           hqsq.setNombre_user(xas);
           
           if(hqsq.buscador_historial())
           {
           txt_nombre_empleado_his.setText("Busca aquí");
           txt_fecha_reporte_his.setText(hqsq.getFecha());
           txt_descripcion_his.setText(hqsq.getDescripcion());
           
           
           }
       
       }
       
    //termina progrmacion del  historial del sistema    
       
    // programacion del  buscador de reclusos
       
    public void consulta_general() {
       
        try {
            String sql = "select cod_recluso, nombre_recluso,apellido_recluso,edad_recluso, altura, peso , tez, alias ,estado,codigo_barra, nombre_sector FROM Reclusos, Tez, Estado, Sector Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND Sector.cod_sector = Reclusos.cod_sector AND Reclusos.cod_estado != 10 ";
         
            System.out.println("asdfasdg " + Blocke);
            PreparedStatement cmd = cn.prepareStatement(sql);
            //cmd.setString(1,  Blocke);
            System.out.println("dsfsdf == " + Blocke);
           // cmd.setString(1, Blocke);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("cod_recluso"), rs.getString("nombre_recluso"), rs.getString("estado"), rs.getInt("codigo_barra"), rs.getString("apellido_recluso"),rs.getInt("edad_recluso"),rs.getInt("altura"),rs.getInt("peso"),rs.getString("tez"),rs.getString("alias")});
            }
            cmd.close();
           // cn.close();

               jtable_reclusos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(jtable_reclusos.getSelectedRow() >=1)
                    {
                    txt_cod_recluso.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),0)+ "");
                    txt_nombre_recluso.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),1)+ "");
                    txt_estado_recluso.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),2)+ "");
                    txt_apellidos.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),4)+ "");
                    txt_edad.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),5)+ "");
                    txt_altura.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),6)+ " m");
                    txt_peso.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),7)+ " Kg");
                    txt_tez.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),8)+ "");
                    txt_alias.setText(jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(),9)+ "");
                    
                    a = jtable_reclusos.getValueAt(jtable_reclusos.getSelectedRow(), 4) + "";
                                
                    }
                   
       mta.receptor_data(a);
       lbl_foto1.setIcon(mta.getNewicon1()); // se monta la img en el label
       lbl_foto1.setSize(154, 151); // y el tamaño que se le asigna al label 
      
       lbl_foto2.setIcon(mta.getNewicon2()); // se monta la img en el label
       lbl_foto2.setSize(154, 151); // y el tamaño que se le asigna al label 
       
       lbl_foto3.setIcon(mta.getNewicon3()); // se monta la img en el label
       lbl_foto3.setSize(154, 151); // y el tamaño que se le asigna al label 
                 
                  
                }
                
            });
            
        
            
                    

        } catch (Exception e) {
            System.out.println("error" + e);
        }
    } // en este evento se consultan todos los reclusos 
  
    public static void reiniciarJTable(javax.swing.JTable Tabla) // evento para limpiar el jtable
    {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);

       // TableColumnModel modCol = Tabla.getColumnModel();
      //  while(modCol.getColumnCount()>0)modCol.removeColumn(modCol.getColumn(0));
    }

    public void captura_datos() {
        txt_cod_recluso.setText("" + codigox);
        txt_nombre_recluso.setText(nombrex);
        txt_apellidos.setText(apellidosx);
        txt_altura.setText("" + alturax + " m");
        txt_alias.setText(aliasx);
        txt_peso.setText("" + pesox + " Kg");
        txt_tez.setText(tezx);
        txt_estado_recluso.setText(estado_reclusox);
        txt_edad.setText("" + edadx);

    }
        
        
        
        
        
    // termina programacion del  buscador de reclusos 
       
       
    //programacion del codigo de barra en todos los paneles  y la fecha actual
     
    public void code(String xode)
    {
             Barcode barcode = null;
         try {
            barcode = BarcodeFactory.createCode39(xode, true);
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
         lbl_barra.setIcon(icon);
         lbl_code_barra.setIcon(icon);
      
    } 
    
    //variables para la fecha
   int dia;
   int mes;
   int año;

   String dia_C;
   String mes_C;
   String año_C;

   String Fecha_año;
  
        
    public void fecha_actual()
    {

       dia = otroTime.get(GregorianCalendar.DAY_OF_MONTH);
        System.out.println(dia);
       mes = otroTime.get(GregorianCalendar.MONTH);
       año = otroTime.get(GregorianCalendar.YEAR);
       dia_C = String.valueOf(dia);
       mes_C = String.valueOf(mes+1);
       año_C = String.valueOf(año);

       if(dia>=0 && dia<=9 && mes>=0  &&  mes<=9)
       {

       Fecha_año = año_C +"-0"+ mes_C +"-0"+ dia_C;
           System.out.println(Fecha_año);
       }
       else
       {
       Fecha_año = año_C +"-0"+ mes_C +"-"+ dia_C;
          System.out.println(Fecha_año);
          System.out.println("Dash");

       }
           
           
    }
         
    //termina programacion de codigo  de barras y de la fecha actual
        
    ResultSet rs = null;
    Statement st = null;
    int cod_sector = 0;
    int cod_recluso = 0;
    int cod_conducta = 0;
    Date fecha_actual2 = new Date();
    DateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy");
    String fecha_conducta = formato2.format(fecha_actual2);
    
    public void ConsultarSector()
    {
        //Se obtiene el codigo de sector
        try {
            st = cn.createStatement();

            rs = st.executeQuery("SELECT cod_sector FROM Sector WHERE nombre_sector ='" + jComboBox_Sector.getSelectedItem().toString().trim() + "'");
            if (rs.next()) {
                cod_sector = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Mth_CargarReclusos() {
        //SE CARGAN LOS VALORES DE TABLA A LOS TEXTBOX
        //Obteniendo los valores de las filas
        int row = jTable_Reclusos.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable_Reclusos.getValueAt(row, 0).toString();
        cod_recluso = Integer.parseInt(codigo);
        
    }
    
    public void mthConsultarConducta() {
        ResultSet res;
        Mtod_conducta obj = new Mtod_conducta();
        obj.setCodigoRecluso(cod_recluso);
        DefaultTableModel dtm = (DefaultTableModel) jTable_Conducta.getModel();
        dtm.setRowCount(0);
        res = obj.ConsultaConducta();
        //ENCABEZADOS DE COLUMMNAS
        dtm.setColumnIdentifiers(new Object[]{"Codigo de Registro", "Nombre de Recluso", "Nombre de Empleado", "Fecha", "Descripcion"});
        try {
            while (res.next()) {
                //DATOS QUE SE CARGARAN A COLUMNAS 
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                dtm.addRow(v);
                jTable_Conducta.setModel(dtm);

                //Metodo para ocultar columnas
                jTable_Conducta.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable_Conducta.getColumnModel().getColumn(0).setMinWidth(0);
               jTable_Conducta.getColumnModel().getColumn(0).setPreferredWidth(0);
               
               jTextArea_Descripcion.setText(null);
            }
        } catch (Exception ex) {

        }
    }
    
    public void Mth_CargarConducta() {
        //SE CARGAN LOS VALORES DE TABLA A LOS TEXTBOX
        //Obteniendo los valores de las filas
        int row = jTable_Conducta.getSelectedRow();
        //guardando los valores de la tabla
        String codigo = jTable_Conducta.getValueAt(row, 0).toString();
        String descripcion = jTable_Conducta.getValueAt(row, 4).toString();

        cod_conducta = Integer.parseInt(codigo);
        jTextArea_Descripcion.setText(descripcion);
    }
    
    public void mthConsultarRecluso() {
        ResultSet res;
        Mtod_conducta obj = new Mtod_conducta();
        ConsultarSector();
        obj.setCodigoSector(cod_sector);
        DefaultTableModel dtm = (DefaultTableModel) jTable_Reclusos.getModel();
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
                jTable_Reclusos.setModel(dtm);

                //Metodo para ocultar columnas
                mthOcultarColumRe();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: "+ex);
        }
    }
    
    public void mthOcultarColumRe()
    {
        
                //SE OCULTAN LAS COLUMNAS INNESESARIAS
                jTable_Reclusos.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(0).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(0).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(4).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(4).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(4).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(5).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(5).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(5).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(6).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(6).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(6).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(7).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(7).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(7).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(8).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(8).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(8).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(9).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(9).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(9).setPreferredWidth(0);

                jTable_Reclusos.getColumnModel().getColumn(10).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(10).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(10).setPreferredWidth(0);
                
                jTable_Reclusos.getColumnModel().getColumn(11).setMaxWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(11).setMinWidth(0);
                jTable_Reclusos.getColumnModel().getColumn(11).setPreferredWidth(0);
    }
        
    public void LlenarComboSector(){
        //Conectando la clase Conexion con la clase actual
        try{
            //escribiendo la consulta 
            String sql = "SELECT * FROM Sector";
            //enviando la consulta al cmd
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            //mostrando los valores en el combo box
            while(rs.next()){
                jComboBox_Sector.addItem(rs.getString("nombre_sector"));
            }
        } catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    } 
        
        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buscador_reclusos;
    private javax.swing.JPanel Jpanel_Control_Camaras;
    private javax.swing.JPanel Jpanel_control_celdas;
    private javax.swing.JPanel Jpanel_visitas;
    private javax.swing.JTable Jtable_asistencia_R;
    private javax.swing.JTable Jtable_control_celdas;
    private javax.swing.JTable Jtable_fecha_buscator;
    private javax.swing.JTable Jtable_his_sistema;
    private javax.swing.JTable Jtable_visitas;
    private javax.swing.JTable Jtable_visitas_totales_recluso;
    private com.toedter.calendar.JYearChooser Jyear_fin;
    private com.toedter.calendar.JYearChooser Jyear_inicio;
    private javax.swing.JPanel Panel_control_datos;
    private javax.swing.JLabel btn_busqueda_año;
    private javax.swing.JButton btn_ter_asist;
    private javax.swing.JButton btn_ter_asist1;
    private org.edisoncor.gui.button.ButtonIpod buttonIpod2;
    private javax.swing.JComboBox<String> cmb_celda;
    private javax.swing.JTable jAsis_Reclusos_complete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton_Reporte;
    private javax.swing.JComboBox jComboBox_Sector;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JLabel jLabel58;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Asistencia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable_Conducta;
    private javax.swing.JTable jTable_Reclusos;
    private javax.swing.JTextArea jTextArea_Descripcion;
    private javax.swing.JTextField jTextField_Filtrar;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTable jtable_reclusos;
    private org.edisoncor.gui.label.LabelMetric labelMetric12;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private javax.swing.JLabel lbl_barra;
    private javax.swing.JLabel lbl_busqueda;
    private javax.swing.JLabel lbl_code_barra;
    private javax.swing.JLabel lbl_foto1;
    private javax.swing.JLabel lbl_foto2;
    private javax.swing.JLabel lbl_foto3;
    private javax.swing.JLabel lbl_usuario10;
    private javax.swing.JLabel lbl_usuario11;
    private javax.swing.JLabel lbl_usuario12;
    private javax.swing.JLabel lbl_usuario13;
    private javax.swing.JLabel lbl_usuario6;
    private javax.swing.JLabel lbl_usuario7;
    private javax.swing.JLabel lbl_usuario8;
    private javax.swing.JLabel lbl_usuario9;
    private org.edisoncor.gui.panel.PanelAvatarChooser menu;
    private org.edisoncor.gui.panel.PanelCurves panelCurves4;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private javax.swing.JPanel panel_asistencia_citas;
    private javax.swing.JPanel panel_conducta;
    private javax.swing.JPanel panel_horarios;
    private javax.swing.JTable tabCitasNo;
    private javax.swing.JTable tabCitasSi;
    private javax.swing.JTextField txtApellidosCitas;
    private javax.swing.JTextField txtApellidosReclusoCitas;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtMotivosCitas;
    private javax.swing.JTextField txtNombreCitas;
    private javax.swing.JTextField txtNombreRe11;
    private javax.swing.JTextField txtNombreReclusoCitas;
    private javax.swing.JTextField txtVinculosCitas;
    private org.edisoncor.gui.textField.TextFieldRound txt_agrupacion_celdas;
    private org.edisoncor.gui.textField.TextFieldRound txt_alias;
    private org.edisoncor.gui.textField.TextFieldRound txt_alias1;
    private org.edisoncor.gui.textField.TextFieldRound txt_altura;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellido_reclu_visit;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellido_visitante;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellidos;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellidos_recluso;
    private javax.swing.JTextField txt_apertura;
    private javax.swing.JTextField txt_apertura2;
    private javax.swing.JTextField txt_apertura3;
    private javax.swing.JTextField txt_apertura4;
    private org.edisoncor.gui.textField.TextFieldRound txt_apertura_celdas;
    private org.edisoncor.gui.textField.TextFieldRound txt_besqueda;
    private javax.swing.JTextField txt_cierre;
    private javax.swing.JTextField txt_cierre2;
    private javax.swing.JTextField txt_cierre3;
    private javax.swing.JTextField txt_cierre4;
    private org.edisoncor.gui.textField.TextFieldRound txt_cierre_celdas;
    private org.edisoncor.gui.textField.TextFieldRound txt_cod_recluso;
    private org.edisoncor.gui.textField.TextFieldRound txt_codigo_barra;
    private org.edisoncor.gui.textField.TextFieldRound txt_codigo_barra_visitas;
    private org.edisoncor.gui.textField.TextFieldRound txt_crimen_reclu_visit;
    private org.edisoncor.gui.textField.TextFieldRound txt_descripcion_his;
    private org.edisoncor.gui.textField.TextFieldRound txt_edad;
    private org.edisoncor.gui.textField.TextFieldRound txt_estado_recluso;
    private org.edisoncor.gui.textField.TextFieldRound txt_estado_recluso1;
    private org.edisoncor.gui.textField.TextFieldRound txt_fecha_reporte_his;
    private org.edisoncor.gui.textField.TextFieldRound txt_hora_reclu_visit;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_empleado_his;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_reclu_visit;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_reclus;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_recluso;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_recluso_asis1;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre_visitante_visit;
    private org.edisoncor.gui.textField.TextFieldRound txt_peso;
    private org.edisoncor.gui.textField.TextFieldRound txt_sector;
    private org.edisoncor.gui.textField.TextFieldRound txt_sector_celdas;
    private org.edisoncor.gui.textField.TextFieldRound txt_tez;
    private javax.swing.JTextField txt_timer;
    // End of variables declaration//GEN-END:variables
}
