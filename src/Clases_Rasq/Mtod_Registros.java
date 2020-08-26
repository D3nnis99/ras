/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Works
 */
public class Mtod_Registros 
{
 Connection cn;

  
 private int code_usuario;
 private String descripcion;
 Calendar calendario = new GregorianCalendar();
 String  año  = Integer.toString(calendario.get(Calendar.YEAR));
 String  mes  = Integer.toString(calendario.get(Calendar.MONTH)+1);
 String  dia  = Integer.toString(calendario.get(Calendar.DAY_OF_MONTH));
     String  fecha = año+"-"+mes+"-"+0+dia;

   public int getCode_usuario() {
        return code_usuario;
    }

    public void setCode_usuario(int code_usuario) {
        this.code_usuario = code_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
 public Mtod_Registros()
 {
     Conexion conect = new  Conexion();
 
 cn = conect.conectar();
 
 }
    
    
   public boolean guardar_registro()
    {

    boolean resp = false;
        try {
            String sql = "insert Registros_sistemas(cod_empleado,fecha,descripcion) values (?,'"+fecha+"',?)";// insertar datos a la base e datos
            PreparedStatement cmd = cn.prepareStatement(sql);
            //llenar los parametros de la clase
       
 
             cmd.setInt(1, code_usuario);
             cmd.setString(2,descripcion);
        
            
            
            //si da errror devuelve 1, caso contrario 0
            //Tomar en cuenta el "!" de negacion 
            if(!cmd.execute())
            {
            resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex.toString(),"502 Bad gateway" , JOptionPane.ERROR_MESSAGE);
        }
    
    return  resp; // que retorne de nuevo resp
    }
  
    
    
    
}
