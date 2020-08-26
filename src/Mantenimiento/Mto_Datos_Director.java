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
public class Mto_Datos_Director {
    
    Connection cn;
    int codigo;
    String nombre;
    String apellido;
    int edad;
    String cargo;
    String estado;
    String foto;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Mto_Datos_Director(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public boolean Consulta(){
        boolean resp = false;
        try{
            String sql = ("SELECT Empleado.cod_empleado, Empleado.nombre_empleado, Empleado.apellido_empleado, Empleado.edad_empleado, Cargo.cargo, Estado.estado, Foto_empleado.url " + 
                          "FROM (((Empleado " + 
                          "inner join Cargo ON Cargo.cod_cargo_empleado = Empleado.cod_cargo_empleado)" + 
                          "inner join Estado ON Estado.cod_estado = Empleado.cod_estado)" +
                          "inner join Foto_empleado ON Foto_empleado.cod_foto = Empleado.cod_foto)" +
                          "WHERE cod_empleado = ? ");
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next()){
                resp = true;
                codigo = rs.getInt(1);
                nombre = rs.getString(2);
                apellido = rs.getString(3);
                edad = rs.getInt(4);
                cargo = rs.getString(5);
                estado = rs.getString(6);
                foto = rs.getString(7);
            }
            cmd.close();
            cn.close();
            //return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            //return null;
        }
        return resp;
    }
    
}
