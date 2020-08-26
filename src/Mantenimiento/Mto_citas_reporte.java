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
 * @author Christian
 */
public class Mto_citas_reporte {
    
    Connection cn;
    public Mto_citas_reporte(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ResultSet ConsultaReclusos(){
            Statement declara;
            try{
                String sql = ("select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso  from Reclusos where nombre_recluso  like '"+ nombre +"%'");
                PreparedStatement cmd = cn.prepareStatement(sql);
                //cmd.setString(1, nombre_vis);
                ResultSet respuesta = cmd.executeQuery();
            return respuesta;
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "ERROR: " + ex);
                return null;
            }
        }
}
