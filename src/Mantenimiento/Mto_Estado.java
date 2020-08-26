/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Mto_Estado {
    Connection cn;
    int codigo;
    int cod_ap;
    String estado;
    
    public Mto_Estado(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getAplicacion() {
        return cod_ap;
    }

    public void setAplicacion(int cod_ap) {
        this.cod_ap = cod_ap;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    //LLENANDO COMBOBOX-----------------------------------------------------------------------------------------------------------------------------------
    
    public ResultSet LlenarAplicacion(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Aplicacion order by (cod_aplicacion) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    //MANIPULACION---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DE----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//LA-------------------------------------------------------------------------------------------------------------------------------------------------------------------- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
//BASE---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DE---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DATOS----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public boolean MthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Estado(estado, cod_aplicacion) VALUES (?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setString(1, estado);
            cmd.setInt(2, cod_ap);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if(!cmd.execute()){
                resp = true;
            }
            //Se cierra la Conexion y el cmd
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public ResultSet Consulta(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_estado, estado, aplicado_a FROM Estado, Aplicacion WHERE Estado.cod_aplicacion = Aplicacion.cod_aplicacion");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean mthEliminar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "DELETE FROM Estado WHERE cod_estado = ? ";
            //Declarando variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna un codigo a la consulta
            cmd.setInt(1, codigo);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (!cmd.execute()){
                resp = true;
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mthModificar(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Estado SET estado = ?, cod_aplicacion = ? WHERE cod_estado = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, estado);
            cmd.setInt(2,cod_ap);
            cmd.setInt(3,codigo);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (!cmd.execute()){
                resp = true;
            } 
            //cerrando la Conexion y el cmd
            cmd.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
}
