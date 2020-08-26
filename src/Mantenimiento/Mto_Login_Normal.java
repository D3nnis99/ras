/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson R3F2
 */

public class Mto_Login_Normal 
{
    Connection cn;
   private String Name_user;
   private String Contraseña; 
   private int cod_empleado; 
   private  String foto_url;
   private String Pregunta_s; 
   private String  Pregunta_respuesta;

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
   
   private  String Nombre_usuario;
   private  String apellidos; 
   private  int edad;
   private  String cargo;

    public int getCodigo_emple() {
        return codigo_emple;
    }

    public void setCodigo_emple(int codigo_emple) {
        this.codigo_emple = codigo_emple;
    }
   int codigo_emple;

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

    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
   
   
 public Mto_Login_Normal()
         {
         
    Conexion con = new Conexion();
   cn = con.conectar(); // conectar con la clase para sql server
         }
    
    public boolean consultarlogin()
    {
    boolean resp = false;
        try {
            //realizar la consulta select
           // String sql = "SELECT nombre_usuario, cotraseña, cod_empleado, url  FROM Empleado, Cargo, Foto_empleado where  Cargo.cod_cargo_empleado= Empleado.cod_cargo_empleado AND Foto_empleado.cod_foto =  Empleado.cod_foto AND   nombre_usuario = ?";
            String sql = "SELECT nombre_usuario, cotraseña, cod_empleado, url ,nombre_empleado, apellido_empleado, edad_empleado,cod_cargo_empleado   FROM Empleado, Foto_empleado where Foto_empleado.cod_foto =  Empleado.cod_foto AND nombre_usuario = ?";
          // String sql = "SELECT  nombre_usuario , cotraseña, cod_empleado   From Empleado Where  nombre_usuario like %?% ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            //llenar los parametros 
            cmd.setString(1,Name_user);
            //ejecuta la consulta
            ResultSet rs = cmd.executeQuery();
            // recorrer la lista de registros 
            if(rs.next())
            {
            resp = true;
            //asignandole los atributos a la clase 
            Name_user = rs.getString(1);
            Contraseña = rs.getString(2);
            cod_empleado = rs.getInt(3); 
            foto_url =rs.getString(4);
            Nombre_usuario = rs.getString(5);
            apellidos = rs.getString(6);
            edad = rs.getInt(7);
            codigo_emple  = rs.getInt(8);
            
            //fecha = rs.getDate(5);
            
            
            }
            cmd.close();
            cn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"502 Bad gateway" , JOptionPane.ERROR_MESSAGE);
            System.out.println(""+ ex.toString());
        }
    
    return  resp;
    
    }
    
    public boolean recupera_contra()
    {
    boolean resp = false;
     try {
            //realizar la consulta select
           // String sql = "SELECT nombre_usuario, cotraseña, cod_empleado, url  FROM Empleado, Cargo, Foto_empleado where  Cargo.cod_cargo_empleado= Empleado.cod_cargo_empleado AND Foto_empleado.cod_foto =  Empleado.cod_foto AND   nombre_usuario = ?";
            String sql = "SELECT nombre_usuario, cotraseña, cod_empleado, url, pregunta, resp_seguridad  FROM Empleado, Cargo, Foto_empleado, Pregunta_seguridad where  Cargo.cod_cargo_empleado= Empleado.cod_cargo_empleado AND Foto_empleado.cod_foto =  Empleado.cod_foto AND Pregunta_seguridad.cod_pregunta_seg = Empleado.cod_pregunta_seg   AND  nombre_usuario = ?";
          // String sql = "SELECT  nombre_usuario , cotraseña, cod_empleado   From Empleado Where  nombre_usuario like %?% ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            //llenar los parametros 
            cmd.setString(1,Name_user);
            //ejecuta la consulta
            ResultSet rs = cmd.executeQuery();
            // recorrer la lista de registros 
            if(rs.next())
            {
            resp = true;
            //asignandole los atributos a la clase 
            Name_user = rs.getString(1);
            Contraseña = rs.getString(2);
            cod_empleado = rs.getInt(3); 
            foto_url =rs.getString(4);
            Pregunta_s = rs.getString(5);
            Pregunta_respuesta = rs.getString(6);
          
            
            //fecha = rs.getDate(5);
            
            }
            cmd.close();
            cn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"502 Bad gateway" , JOptionPane.ERROR_MESSAGE);
            System.out.println(""+ ex.toString());
        }
    
    return  resp;
    
    }
 
    
}
