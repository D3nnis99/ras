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
import java.sql.Time;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Mto_Horario {
    Connection cn;
    int codigo;
    String horario;
    String hora_a;
    String hora_c;
    
    public Mto_Horario(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHora_a() {
        return hora_a;
    }

    public void setHora_a(String hora_a) {
        this.hora_a = hora_a;
    }

    public String getHora_c() {
        return hora_c;
    }

    public void setHora_c(String hora_c) {
        this.hora_c = hora_c;
    }
    
    public boolean MthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Horario(horario, hora_apertura, hora_cierre) VALUES (?, ?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setString(1, horario);
            cmd.setString(2, hora_a);
            cmd.setString(3, hora_c);
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
            ResultSet respuesta = declara.executeQuery("SELECT cod_horario, horario, hora_apertura, hora_cierre FROM Horario");
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
            String sql = "DELETE FROM Horario WHERE cod_horario = ? ";
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
            String sql = "UPDATE Horario SET horario = ?, hora_apertura = ?, hora_cierre = ? WHERE cod_horario = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, horario);
            cmd.setString(2, hora_a);
            cmd.setString(3, hora_c);
            cmd.setInt(4,codigo);
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
