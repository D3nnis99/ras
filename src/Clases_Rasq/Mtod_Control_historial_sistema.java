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

/**
 *
 * @author Works
 */
public class Mtod_Control_historial_sistema {
    
    String nombre_user;
    String Fecha;
    String Descripcion;

    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    Connection cn;

    public Mtod_Control_historial_sistema()
{
  Conexion conect = new  Conexion();
       cn = conect.conectar();

}

  public boolean buscador_historial()
     {
         
      boolean rest = false;
            try {
           String sql ="  select nombre_usuario, fecha,descripcion  from Registros_sistemas, Empleado where  Empleado.cod_empleado =  Registros_sistemas.cod_empleado and fecha like" +"'"+ nombre_user + "%'";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             nombre_user = rs.getString(1);
             Fecha = rs.getString(2);
             Descripcion = rs.getString(3);
             
             
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
             return  rest;
            
     }
  
  
  
  public boolean buscador_historial2()
     {
         
      boolean rest = false;
            try {
           String sql ="  select nombre_usuario, fecha,descripcion  from Registros_sistemas, Empleado where  Empleado.cod_empleado =  Registros_sistemas.cod_empleado and fecha like" +"'"+ Fecha + "%'";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             nombre_user = rs.getString(1);
             Fecha = rs.getString(2);
             Descripcion = rs.getString(3);
             
             
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
             return  rest;
            
     }
}
