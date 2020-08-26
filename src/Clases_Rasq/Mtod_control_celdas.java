/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;


import foto_reporteria_2.*;
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
public class Mtod_control_celdas
{
String nombre_empleado;  
String nombre_recluso;
String Apellidos_recluso;
int codigo_barra;
String Sector;
String Agrupacion; 
int codigo_celda;
String horario_salida;
String horario_entrada;
String Blockex;

    public String getBlockex() {
        return Blockex;
    }

    public void setBlockex(String Blockex) {
        this.Blockex = Blockex;
    }


    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getNombre_recluso() {
        return nombre_recluso;
    }

    public void setNombre_recluso(String nombre_recluso) {
        this.nombre_recluso = nombre_recluso;
    }

    public String getApellidos_recluso() {
        return Apellidos_recluso;
    }

    public void setApellidos_recluso(String Apellidos_recluso) {
        this.Apellidos_recluso = Apellidos_recluso;
    }

    public int getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(int codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }

    public String getAgrupacion() {
        return Agrupacion;
    }

    public void setAgrupacion(String Agrupacion) {
        this.Agrupacion = Agrupacion;
    }

    public int getCodigo_celda() {
        return codigo_celda;
    }

    public void setCodigo_celda(int codigo_celda) {
        this.codigo_celda = codigo_celda;
    }

    public String getHorario_salida() {
        return horario_salida;
    }

    public void setHorario_salida(String horario_salida) {
        this.horario_salida = horario_salida;
    }

    public String getHorario_entrada() {
        return horario_entrada;
    }

    public void setHorario_entrada(String horario_entrada) {
        this.horario_entrada = horario_entrada;
    }
Connection cn;


public Mtod_control_celdas()
{
  Conexion conect = new  Conexion();
       cn = conect.conectar();

}

  public boolean buscador_recluso()
     {
         
      boolean rest = false;
            try {
           String sql ="select nombre_recluso,apellido_recluso, codigo_barra, nombre_sector, nombre_agrupacion, cod_celda,hora_apertura,hora_cierre  from Reclusos, Sector, Agrupacion,Celdas, Horario where Sector.cod_sector = Reclusos.cod_sector AND Agrupacion.cod_agrupacion = Sector.cod_agrupacion AND Sector.cod_sector = Celdas.cod_celda AND Horario.cod_horario = Sector.cod_horario and nombre_recluso  like" +"'"+ nombre_recluso + "%' and nombre_sector= ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           cmd.setString(1, Blockex);
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
             rest = true; 
             nombre_recluso = rs.getString(1);
             Apellidos_recluso =rs.getString(2);
             codigo_barra = rs.getInt(3);
             Sector = rs.getString(4);
             Agrupacion = rs.getString(5);
             codigo_celda = rs.getInt(6);
             horario_salida = rs.getString(7);
             horario_entrada = rs.getString(8);
             
             
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
             return  rest;
            
     }



 
}
