/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import foto_reporteria_2.*;
import Clases_Rasq.Mtod_Registros;
import Clases_Rasq.Mtod_foto;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Works
 */
public class Mthod_Login_Pro
{
   Mtod_Registros registrar = new Mtod_Registros();
   private int Estado_contra;


   private String Name_user;
   private String Contraseña; 
   private String Contraseña_temporal;
   private int tipo_usuario;

    

   
   private int cod_empleado;
   private  ImageIcon Em_url_bit;
   private String Pregunta_s; 
   private String  Pregunta_respuesta;
   
   private  String Nombre_usuario;
   private  String apellidos; 
   private  int edad;
   private  String cargo; 
   private String estado;
   private Image imagen_bit;
   private String nombre_sector;   
   private  String fecha_retorno;

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    public String getContraseña_temporal() {
        return Contraseña_temporal;
    }

    public void setContraseña_temporal(String Contraseña_temporal) {
        this.Contraseña_temporal = Contraseña_temporal;
    }
    
    public String getFecha_retorno() {
        return fecha_retorno;
    }

    public void setFecha_retorno(String fecha_retorno) {
        this.fecha_retorno = fecha_retorno;
    }
   Mtod_foto foto = new Mtod_foto();
   
       public int getEstado_contra_act() {
        return Estado_contra_act;
    }

    public void setEstado_contra_act(int Estado_contra_act) {
        this.Estado_contra_act = Estado_contra_act;
    }   private int Estado_contra_act;

    
     public int getEstado_contra() {
        return Estado_contra;
    }

    public void setEstado_contra(int Estado_contra) {
        this.Estado_contra = Estado_contra;
    }

    public String getNombre_sector() {
        return nombre_sector;
    }

    public void setNombre_sector(String nombre_sector) {
        this.nombre_sector = nombre_sector;
    }
   

   
   
  public ImageIcon getEm_url_bit() {
        return Em_url_bit;
    }

    public void setEm_url_bit(ImageIcon Em_url_bit) {
        this.Em_url_bit = Em_url_bit;
    }

   

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getNombre_usuario() {
        return Nombre_usuario;
    }

    public void setNombre_usuario(String Nombre_usuario) {
        this.Nombre_usuario = Nombre_usuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
   
 
   
   Connection cn;

    public String getName_user() {
        return Name_user;
    }

    public void setName_user(String Name_user) {
        this.Name_user = Name_user;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    public String getPregunta_s() {
        return Pregunta_s;
    }

    public void setPregunta_s(String Pregunta_s) {
        this.Pregunta_s = Pregunta_s;
    }

    public String getPregunta_respuesta() {
        return Pregunta_respuesta;
    }

    public void setPregunta_respuesta(String Pregunta_respuesta) {
        this.Pregunta_respuesta = Pregunta_respuesta;
    }
public Mthod_Login_Pro()
{

Conexion conect = new  Conexion();
cn= conect.conectar();

}
 public boolean consult_login()
 {
 boolean rest= false ; 
    
     try {
     String sql ="Select nombre_usuario,cotraseña,estado,Empleado.cod_empleado, Empleado.cod_cargo_empleado, url_bit, pregunta, resp_seguridad, estado_contraseña from Empleado, Estado, Cargo, Pregunta_seguridad where  Estado.cod_estado = Empleado.cod_estado AND Cargo.cod_cargo_empleado = Empleado.cod_cargo_empleado AND Pregunta_seguridad.cod_pregunta_seg = Empleado.cod_pregunta_seg  AND   nombre_usuario = ?" ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Name_user);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
         Name_user = rs.getString(1);
         Contraseña = rs.getString(2);
         estado =rs.getString(3);
         //nombre_sector = rs.getString(4);
         cod_empleado = rs.getInt(4);
         tipo_usuario = rs.getInt(5);
         byte[] x = rs.getBytes(6);
         imagen_bit = foto.ConvertirImagen(x);
         Image capturador = imagen_bit;
         ImageIcon iconono = new ImageIcon(capturador);
         Image img = iconono.getImage();
         Image newIMG = img.getScaledInstance(155, 180, java.awt.Image.SCALE_SMOOTH);
         Em_url_bit = new ImageIcon(newIMG);
         Pregunta_s =  rs.getString(7);
         Pregunta_respuesta = rs.getString(8);
         Estado_contra= rs.getInt(9);
         
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","1", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 public boolean Actualizar_contraseña()
   {
   boolean resp = false;
       try {
           //Realizar consulta UPDATE
           String sql = "UPDATE Empleado set estado_contraseña = ? ,cotraseña = ? where  nombre_usuario = ?";
           PreparedStatement cmd = cn.prepareStatement(sql);
           // llenar los parametros como esta en la clase
           cmd.setInt(1, Estado_contra_act);
           cmd.setString(2, Contraseña_temporal);
           cmd.setString(3, Name_user);
           
           //Si da error devuelve 1, caso contrario 0 
          //Tomar en cuenta el "!" de negación
          if(!cmd.execute())
          {
          registrar.setCode_usuario(cod_empleado);
          registrar.setDescripcion(Contraseña_temporal);
          if(registrar.guardar_registro())
          {
              System.out.println("exito");
          }
          else
          {
          
          }
          resp= true;
          }
          cmd.close();
         // cn.close();
          
       } catch (Exception ex) {
           
         JOptionPane.showMessageDialog(null, ex.toString(), "502 BAD GATEWAY" , JOptionPane.ERROR_MESSAGE);
          
       }
      
   
   
   return resp;
   }
  public boolean compro_fecha()
 {
 boolean rest= false ; 
    
     try {
     String sql ="select fecha  from Registros_sistemas, Empleado where Empleado.cod_empleado = Registros_sistemas.cod_empleado and descripcion = ? " ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Contraseña);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
         fecha_retorno = rs.getString(1);
         System.out.println(fecha_retorno);
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","1", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 
public boolean obtener_sector()
{
    boolean rest= false ; 
    
    try {
    String sql ="select nombre_sector from Sector where cod_empleado = ? " ;
    PreparedStatement cms = cn.prepareStatement(sql);
    cms.setInt(1, cod_empleado);
        ResultSet rs = cms.executeQuery();
        if(rs.next())
        {
            rest= true; 
            nombre_sector = rs.getString(1);
        }
        else
        {

            JOptionPane.showMessageDialog(null ,"Error","7", JOptionPane.ERROR_MESSAGE);
        }
        cms.close();
       // cn.close();
    } catch (Exception ex) {
        System.out.println("Error" + ex);   
    }
  return rest;
 }
  
  
}
