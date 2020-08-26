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
public class Mto_Crimen {
    Connection cn;
    int codigo;
    String crimen;

    public Mto_Crimen(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCrimen() {
        return crimen;
    }

    public void setCrimen(String crimen) {
        this.crimen = crimen;
    }
    
    public boolean MthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Crimen(crimen) VALUES (?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setString(1, crimen);
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
            ResultSet respuesta = declara.executeQuery("SELECT cod_crimen, crimen FROM Crimen");
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
            String sql = "DELETE FROM Crimen WHERE cod_crimen = ? ";
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
            String sql = "UPDATE Crimen SET crimen = ? WHERE cod_crimen = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, crimen);
            cmd.setInt(2,codigo);
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
