/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import foto_reporteria_2.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import javax.swing.JOptionPane;
import nicon.notify.core.Notification;

/**
 *
 * @author Works
 */
public class Mthod_Visitas_x 
{
    Connection cn;
    
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
     String fecha_act;

    public String getFecha_act() {
        return fecha_act;
    }

    public void setFecha_act(String fecha_act) {
        this.fecha_act = fecha_act;
    }

    public int getCant_reclusos() {
        return cant_reclusos;
    }

    public void setCant_reclusos(int cant_reclusos) {
        this.cant_reclusos = cant_reclusos;
    }

    public int getCode_barra() {
        return code_barra;
    }

    public void setCode_barra(int code_barra) {
        this.code_barra = code_barra;
    }

    public String getBloke_sector() {
        return bloke_sector;
    }

    public void setBloke_sector(String bloke_sector) {
        this.bloke_sector = bloke_sector;
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

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public int getCod_asistencia() {
        return cod_asistencia;
    }

    public void setCod_asistencia(int cod_asistencia) {
        this.cod_asistencia = cod_asistencia;
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

    public Time getHora_r() {
        return hora_r;
    }

    public void setHora_r(Time hora_r) {
        this.hora_r = hora_r;
    }

    public int getVerificdor() {
        return verificdor;
    }

    public void setVerificdor(int verificdor) {
        this.verificdor = verificdor;
    }
    
public Mthod_Visitas_x()
{
    Conexion conectars = new Conexion();
    cn = conectars.conectar();
}

  public boolean consulta_de_visitas()
     {
         boolean rest = false;
          try {
           String sql ="select count(Reclusos.cod_recluso) from Reclusos , Citas, Visitas, Estado, Asistencia,Sector Where Reclusos.cod_recluso = Citas.cod_citas AND  Visitas.cod_visita = Citas.cod_citas  AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado = 1  AND Asistencia.cod_asistencia = Citas.cod_citas AND Sector.cod_sector =Reclusos.cod_sector  AND nombre_sector = ? AND fecha_cita =? ";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setString(1, bloke_sector);
           cmd.setString(2, fecha_act);
           
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
  
  
  public boolean obtener_visita()
  {
     boolean rest = false;
      try {
           String sql =" select codigo_barra,nombre_visita,apellido_visita,nombre_recluso,apellido_recluso,hora_cita,asistencia,Reclusos.cod_recluso  from Reclusos , Citas, Visitas, Estado, Asistencia,Sector Where Reclusos.cod_recluso = Citas.cod_citas AND  Visitas.cod_visita = Citas.cod_citas  AND Estado.cod_estado = Reclusos.cod_estado AND Estado.cod_estado = 1  AND Asistencia.cod_asistencia = Citas.cod_citas AND Sector.cod_sector =Reclusos.cod_sector AND  nombre_sector = ? AND codigo_barra  = ? AND fecha_cita = ? ";   
           PreparedStatement cmd2 = cn.prepareStatement(sql);
           cmd2.setString(1, bloke_sector);
           cmd2.setInt(2,code_barra);
           cmd2.setString(3, fecha_act);
           
           ResultSet rs2 = cmd2.executeQuery();
            if(rs2.next()) 
             {
             rest = true; 
             
             code_barra = rs2.getInt(1);
             nombre_visitante = rs2.getString(2);
             apellido_visitante = rs2.getString(3);
             nombre = rs2.getString(4);
             apellidos = rs2.getString(5);
             hora_r = rs2.getTime(6); 
             asistencia = rs2.getString(7);
             code_recluso = rs2.getInt(8);
             
        
             }
             
             rs2.close(); 
      } catch (Exception e) {
      }
   return rest;             


  }
  
            int delta;
            int  y = 0;
            int [] ausentes;
            int [] total_prisionero;
            int counter=0;
            
            
            
              public boolean scanning_asist_v()
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
             Notification.show("El recluso = " + recluso , "No asistio a la cita", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
             
             //AQUI SE HARA EL REPORTE DE RECLUSOS ESCAPADOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            
             System.out.println("Delta " + ausentes[y]);
            // System.out.println("Total prisionero =" + total_prisionero[y]);
             y++;
             counter =1;
             }
            
             
             }
            if(counter ==1)
             {
          
             }
             
           rs.close();
         } catch (Exception e) {
             System.out.println("Error  en scanning asist " +e);
         }
             return  rest;
       
       }
              
   public boolean modificar_asistencia()
   {
   boolean resp = false;
       try {
           //Realizar consulta UPDATE
           if(code_barra == 0)
           {
            String sql = "update  Reclusos set cod_asistencia = 1 where cod_recluso =?";
            PreparedStatement cmd2 = cn.prepareStatement(sql);
         
               for (int i = 0; i <= cant_reclusos-1 ; i++)
               {
              cmd2.setInt(1,total_prisionero[i]);
             System.out.println("qqqq = " + total_prisionero[i]);
             if(!cmd2.execute())
              {
              resp= true;
             // JOptionPane.showMessageDialog(null,  "Testing ");
              }
                
               }
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
            //  JOptionPane.showMessageDialog(null,  "Hello");
          }
          cmd.close();
         // cn.close();
           }
       } catch (Exception ex) {
           
         JOptionPane.showMessageDialog(null, ex.toString(), "502 BAD GATEWAY" , JOptionPane.ERROR_MESSAGE);
           
       }
      
   
   
   return resp;
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
   
}
