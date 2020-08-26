/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;
import foto_reporteria_2.*;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import nicon.notify.core.Notification;

/**
 *
 * @author Works
 */
public class Mtod_asistencia  implements Transferable{
    
     //// comentariados----------------------
       // JOptionPane.showMessageDialog(null, "" + hora);
    
    //-------------------------------------------------
     private Image image;
     Connection cn;
     Calendar c1 = Calendar.getInstance();
     Calendar calendario = new GregorianCalendar();
     String  año  = Integer.toString(calendario.get(Calendar.YEAR));
     String  mes  = Integer.toString(calendario.get(Calendar.MONTH)+1);
     String  dia  = Integer.toString(calendario.get(Calendar.DAY_OF_MONTH));
     String  fecha = año+"-"+mes+"-"+dia;
     String  hora  = Integer.toString(calendario.get(Calendar.HOUR_OF_DAY));
     
    int cant_reclusos;
    int code_barra;
    String bloke_sector;
    int code_recluso;
    String nombre;
    String sector;
    String apellidos;
    String Alias; 
    String Estado_recluso;
    String crimen;
    String asistencia;
    int cod_asistencia;
    String nombre_visitante;
    String apellido_visitante;
    Time  hora_r;
    int verificdor; 
    int verificar_si_es_visitas;
     
    int cod_sector;

    public int getCod_sector() {
        return cod_sector;
    }

    public void setCod_sector(int cod_sector) {
        this.cod_sector = cod_sector;
    }

    public int getVerificar_si_es_visitas() {
        return verificar_si_es_visitas;
    }

    public void setVerificar_si_es_visitas(int verificar_si_es_visitas) {
        this.verificar_si_es_visitas = verificar_si_es_visitas;
    }

    public int getVerificdor() {
        return verificdor;
    }

    public void setVerificdor(int verificdor) {
        this.verificdor = verificdor;
    }
  

    public Time getHora_r() {
        return hora_r;
    }

    public void setHora_r(Time hora_r) {
        this.hora_r = hora_r;
    }


    public String getNombre_visitante() {
        return nombre_visitante;
    }

    public void setNombre_visitante(String nombre_visitante) {
        this.nombre_visitante = nombre_visitante;
    }

    public String getApellido_visitante() {
        return apellido_visitante;
    }

    public void setApellido_visitante(String apellido_visitante) {
        this.apellido_visitante = apellido_visitante;
    }


    public int getCod_asistencia() {
        return cod_asistencia;
    }

    public void setCod_asistencia(int cod_asistencia) {
        this.cod_asistencia = cod_asistencia;
    }
    
       int x = 0;
     
     

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }
     

    public int getCode_barra() {
        return code_barra;
    }

    public void setCode_barra(int code_barra) {
        this.code_barra = code_barra;
    }
   
     

    public int getCode_recluso() {
        return code_recluso;
    }

    public void setCode_recluso(int code_recluso) {
        this.code_recluso = code_recluso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public String getEstado_recluso() {
        return Estado_recluso;
    }

    public void setEstado_recluso(String Estado_recluso) {
        this.Estado_recluso = Estado_recluso;
    }

    public String getCrimen() {
        return crimen;
    }

    public void setCrimen(String crimen) {
        this.crimen = crimen;
    }
    
    public String getBloke_sector() {
        return bloke_sector;
    }

    public void setBloke_sector(String bloke_sector) {
        this.bloke_sector = bloke_sector;
    }

    
    public int getCant_reclusos() {
        return cant_reclusos;
    }

    public void setCant_reclusos(int cant_reclusos) {
        this.cant_reclusos = cant_reclusos;
    }
    
    
     
    //   Object[] columnas = {"Cod recluso","Nombre","Sector","Crimen"};    
     
     
     public  Mtod_asistencia()
     {
       Conexion conect = new  Conexion();
       cn = conect.conectar();
     }
     
     public boolean cpnsulta_reclusos()
     {
         boolean rest = false;
          try {
           String sql ="select count(cod_recluso) from Reclusos , Sector , Horario, Estado Where Sector.cod_sector = Reclusos.cod_sector AND Horario.cod_horario = Sector.cod_horario AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado in (7,8) AND nombre_sector = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setString(1, bloke_sector);
           
            ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             cant_reclusos = rs.getInt(1);
        
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
           return  rest;
     }
     

     
     public boolean asistencia_final() // la asistencia y rrecepcion de datos de los reclusos
     {
      boolean rest = false;
            try {
            if(verificdor == 1)
            {
        
            }
            else
            {
           String sql ="select cod_recluso,codigo_barra,nombre_recluso,nombre_sector,apellido_recluso,alias, estado FROM Reclusos, Tez, Estado, Sector, Horario  Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND Sector.cod_sector = Reclusos.cod_sector  AND  Horario.cod_horario = Sector.cod_horario AND Estado.cod_estado in (7,8) AND nombre_sector = ?  AND codigo_barra  = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setString(1, bloke_sector);
           cmd.setInt(2,code_barra);
           
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             code_recluso = rs.getInt(1);
             nombre = rs.getString(3);
             sector = rs.getString(4);
             apellidos = rs.getString(5);
             Alias = rs.getString(6);
             Estado_recluso =  rs.getString(7);
        
             }
             
             rs.close();
            }
            
         } catch (Exception e) {
             System.out.println("Error en el proceso de asistencias Consulte el evento asistencia_final" +e);
         }
             return  rest;
            
     }
      
     
      public Mtod_asistencia(Image image) {
		this.image = image;
	}
     
      public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}
      
    
     public boolean isDataFlavorSupported(DataFlavor flavor) {
		return DataFlavor.imageFlavor.equals(flavor);
	}
     public Object getTransferData(DataFlavor flavor)throws UnsupportedFlavorException, IOException {
		if (!DataFlavor.imageFlavor.equals(flavor)) {
			throw new UnsupportedFlavorException(flavor);
		}
       return image;
     }
   

    
       public boolean consult_asist()
     {
      boolean rest = false;
            try {
           String sql ="select asistencia from Asistencia, Reclusos, Estado  Where  Asistencia.cod_asistencia = Reclusos.cod_asistencia AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado = 1 AND  codigo_barra = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setInt(1,code_barra);
           
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             asistencia = rs.getString(1);
            
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
             return  rest;
            
     }
            int delta;
            int  y = 0;
            int [] ausentes;
            int [] total_prisionero;
            int counter=0;
            
       public boolean scanning_asist()
       {   
          
           ausentes = new int[cant_reclusos]; 
           total_prisionero = new int[cant_reclusos];
       
           boolean rest = false;
            try {
           String sql ="select apellido_recluso ,cod_recluso,Asistencia from Reclusos , Sector ,  Asistencia, Estado Where Sector.cod_sector = Reclusos.cod_sector AND Asistencia.cod_asistencia = Reclusos.cod_asistencia AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado = 1  AND nombre_sector =?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setString(1,bloke_sector);
           
           ResultSet rs = cmd.executeQuery();
         
             while(rs.next()) 
             {
             rest = true;
             String recluso = rs.getString(1);
             delta = rs.getInt(2);
             String alpha =  rs.getString(3);
            System.out.println("codigo del recluso= " + alpha);
            System.out.println("Asistenacia del recluso= " + delta);
             if(alpha.equals("Presente"))
             {
             total_prisionero[y] = delta;
             
             System.out.println("Total prisionero =" + total_prisionero[y]);
             y++;
             }
             else if (alpha.equals("Ausente"))
             {
             System.out.println("Ausente xas " +delta);
             ausentes[y] = delta;
             total_prisionero[y] = delta;
             Notification.show("El recluso = " + recluso , "A escapado de la  prisión", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
             
             //AQUI SE HARA EL REPORTE DE RECLUSOS ESCAPADOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            
             System.out.println("Delta " + ausentes[y]);
            // System.out.println("Total prisionero =" + total_prisionero[y]);
             y++;
             counter =1;
             }
            
             
             }
            if(counter ==1)
             {
             reporte_de_asistencia();
             }
             
             rs.close();
         } catch (Exception e) {
             System.out.println("Error  en scanning asist " +e);
         }
             return  rest;
       
       }
       
    public void reporte_de_asistencia()///////////////////////////////////////REPORTE DE ASISTENCIA/////////
    {
        
        try {
            String url = "/Reportes/Reporte_asistencia_noexist.jasper";
            Map parametros = new HashMap(); 
            Connection cn = new Conexion().conectar();
            JasperPrint reporte = JasperFillManager.fillReport(url, parametros, cn);
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setTitle("Reporte de Usuarios");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
    }
       
     
       
   public boolean modificar_asistencia()
   {
   boolean resp = false;
       try {
           //Realizar consulta UPDATE
           if(code_barra == 0)
           {
            String sql = "update Reclusos set cod_asistencia = 1 where cod_sector = ?";
            PreparedStatement cmd2 = cn.prepareStatement(sql);
            cmd2.setInt(1, cod_sector);
            if (!cmd2.execute()){
                resp = true;
            }
               /*for (int i = 0; i <= cant_reclusos-1 ; i++)
               {
              cmd2.setInt(1,total_prisionero[i]);
             System.out.println("qqqq = " + total_prisionero[i]);
             if(!cmd2.execute())
              {
              resp= true;
             // JOptionPane.showMessageDialog(null,  "Testing ");
              }
                
               }*/
            cmd2.close(); 
           }
           else
           {
           String sql = "update  Reclusos set cod_asistencia = ? where codigo_barra = ? ";
           PreparedStatement cmd = cn.prepareStatement(sql);
            //llenar los parametros como esta en la clase
           cmd.setInt(1, cod_asistencia);
           cmd.setInt(2, code_barra);
           
         
   
           //Si da error devuelve 1, caso contrario 0 
           //Tomar en cuenta el "!" de negación
          if(!cmd.execute())
          {
          resp= true;
              JOptionPane.showMessageDialog(null,  "Hello");
          }
          cmd.close();
         // cn.close();
           }
       } catch (Exception ex) {
           
         JOptionPane.showMessageDialog(null, ex.toString(), "502 BAD GATEWAY" , JOptionPane.ERROR_MESSAGE);
           
       }
      
   
   
   return resp;
   }
       
    public boolean Obtener_cod_sector() // la asistencia y rrecepcion de datos de los reclusos
    {
     boolean rest = false;
           try {
           if(verificdor == 1)
           {

           }
           else
           {
          String sql ="select cod_sector from sector where nombre_sector  = ?";   
          PreparedStatement cmd = cn.prepareStatement(sql);
          cmd.setString(1, sector);
          ResultSet rs = cmd.executeQuery();

            if(rs.next()) 
            {
            rest = true; 
            cod_sector = rs.getInt(1);
            }

            rs.close();
           }

        } catch (Exception e) {
            System.out.println("Error en el proceso de asistencias Consulte el evento asistencia_final" +e);
        }
            return  rest;

    }
}
