/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Dennis
 */
public class Mtod_conducta {
    Connection cn;
    int codigo;
    String fecha;
    String descripcion;
    int cod_sector;
    int cod_recluso;
    int cod_empleado;
    String nombre_recluso;

    public Mtod_conducta(){
        Mantenimiento.Conexion con = new Mantenimiento.Conexion();
        cn = con.conectar();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public int getCodigoSector() {
        return cod_sector;
    }

    public void setCodigoSector(int cod_sector) {
        this.cod_sector = cod_sector;
    }
    
    public int getCodigoRecluso() {
        return cod_recluso;
    }

    public void setCodigoRecluso(int cod_recluso) {
        this.cod_recluso = cod_recluso;
    }
    
    public int getCodigoEmpleado() {
        return cod_empleado;
    }

    public void setCodigoEmpleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }
    
    public String getNombreRecluso() {
        return nombre_recluso;
    }

    public void setNombreRecluso(String nombre_recluso) {
        this.nombre_recluso = nombre_recluso;
    }
    
    
    public boolean mthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Reporte_conducta(cod_recluso, cod_empleado, fecha, descripcion) VALUES (?, ?, ?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setInt(1, cod_recluso);
            cmd.setInt(2, cod_empleado);
            cmd.setString(3, fecha);
            cmd.setString(4, descripcion);
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
    
    public ResultSet ConsultaConducta(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_reporte_conducta, nombre_recluso, nombre_empleado, fecha, descripcion FROM Reporte_conducta AS rc, Reclusos AS r, Empleado AS e WHERE rc.cod_recluso = r.cod_recluso AND rc.cod_empleado = e.cod_empleado AND rc.cod_recluso = "+cod_recluso);
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean mthModificar(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Reporte_conducta SET cod_recluso = ?, cod_empleado = ?, fecha = ?, descripcion = ? WHERE cod_reporte_conducta = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setInt(1, cod_recluso);
            cmd.setInt(2, cod_empleado);
            cmd.setString(3, fecha);
            cmd.setString(4, descripcion);
            cmd.setInt(5,codigo);
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
    
    public ResultSet ConsultaRecluso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso, estado, nombre_sector, tez, altura, peso, alias, codigo_barra, celda from ((Reclusos as r inner join Estado as e on r.cod_estado = e.cod_estado) inner join Sector as s on r.cod_sector = s.cod_sector inner join Tez as t on r.cod_tez = t.cod_tez) WHERE r.cod_sector = "+cod_sector);
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
    }
    
    public ResultSet FiltrarRecluso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            String sql = "select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso from Reclusos WHERE nombre_recluso LIKE '"+nombre_recluso+"%' AND cod_sector = "+cod_sector;
            ResultSet respuesta = declara.executeQuery(sql);
            //JOptionPane.showMessageDialog(null, "SQL: " + sql);
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
    }
    
}
