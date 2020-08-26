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
public class Mto_Pregunta {
    Connection cn;
    int codigo;
    String pregunta;
    
    public Mto_Pregunta(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public boolean MthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Pregunta_seguridad(pregunta) VALUES (?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setString(1, pregunta);
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
            ResultSet respuesta = declara.executeQuery("SELECT cod_pregunta_seg, pregunta FROM Pregunta_seguridad");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean eliminarUsuario(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "DELETE FROM Pregunta_seguridad WHERE cod_pregunta_seg = ? ";
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
    
    public boolean modificarUsuario(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Pregunta_seguridad SET pregunta = ? WHERE cod_pregunta_seg = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, pregunta);
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
