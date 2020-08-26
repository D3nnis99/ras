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
public class Mto_Director {
    
    Connection cn;
    public Mto_Director(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    //Presupuesto
    int codigo;
    String fecha;
    int anio;
    Double total;
    int empleado;
    String mes;
    
    //DetallePresupuesto y recurso
    Double costo;
    int detalle;
    int recurso; //codigo del recurso
    String recursotxt;  //nombre del recurso
    
    //Obtencion de valores para validacion
    int valor;
    String valor1;

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //reporte
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    String fechas;
    String descripcion;
    //Fechas
    String fecha1;
    String fecha2;

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }
    
    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getRecursotxt() {
        return recursotxt;
    }

    public void setRecursotxt(String recursotxt) {
        this.recursotxt = recursotxt;
    }

    public int getDetalle() {
        return detalle;
    }

    public void setDetalle(int detalle) {
        this.detalle = detalle;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }
    
    //Presupuesto------------------------------------------------------------------------------------------------------------------------------------
    
    public boolean MthGuardar(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Presupuesto(año, cod_empleado, total, mes) VALUES (?, ?, ?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setInt(1, anio);
            cmd.setInt(2, empleado);
            cmd.setDouble(3, 0);
            cmd.setString(4, mes);
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
            ResultSet respuesta = declara.executeQuery("SELECT cod_presupuesto, fecha, año, mes FROM Presupuesto");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    
    public ResultSet Consulta2(){
        Statement declara;
        try{
            
            String sql = "SELECT cod_presupuesto, fecha, año, mes, total FROM Presupuesto where año = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, anio);
            ResultSet respuesta = cmd.executeQuery();
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
            String sql = "DELETE FROM Presupuesto WHERE cod_presupuesto = ? ";
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
            String sql = "UPDATE Presupuesto SET año = ?, mes = ? WHERE cod_presupuesto = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setInt(1, anio);
            cmd.setString(2, mes);
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
    
    public boolean mthModificarTotal(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Presupuesto SET total = ? WHERE cod_presupuesto = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setDouble(1, total);
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
    
    public boolean MthValorPres(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "select mes from Presupuesto where mes = ? and año = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, mes);
            cmd.setInt(2, anio);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                valor1 = rs.getString(1);
            } else {
                valor1 = "";
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    //Detalle presupuesto ---------------------------------------------------------------------------------------------------------------
    
    public boolean MthGuardarD(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Detalle_presupuesto(cod_presupuesto, cod_recurso, gasto) VALUES (?, ?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setInt(1, codigo);
            cmd.setInt(2, recurso);
            cmd.setDouble(3, costo);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if(!cmd.execute()){
                resp = true;
            }
            //Se cierra la Conexion y el cmd
            cmd.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public boolean mthModificarD(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Detalle_presupuesto SET cod_recurso = ?, gasto = ? WHERE cod_detalle = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setInt(1, recurso);
            cmd.setDouble(2, costo);
            cmd.setInt(3, detalle);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (!cmd.execute()){
                resp = true;
            } 
            //cerrando la Conexion y el cmd
            cmd.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mthEliminarD(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "DELETE FROM Detalle_presupuesto WHERE cod_detalle = ? ";
            //Declarando variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna un codigo a la consulta
            cmd.setInt(1, detalle);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (!cmd.execute()){
                resp = true;
            }
            //cerrando la Conexion y el cmd
            cmd.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public ResultSet MostrarDetalle(){
        try{
            String sql = "SELECT cod_detalle, recurso, gasto " +
                         "FROM ((Detalle_presupuesto " +
                         "inner join Recurso ON Detalle_presupuesto.cod_recurso = Recurso.cod_recurso) " +
                         "inner join Presupuesto ON Detalle_presupuesto.cod_presupuesto = Presupuesto.cod_presupuesto) " +
                         "WHERE Presupuesto.cod_presupuesto = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet respuesta = cmd.executeQuery();
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    public boolean MthValorD(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "SELECT cod_detalle from Presupuesto " +
                         "LEFT OUTER JOIN Detalle_presupuesto ON Presupuesto.cod_presupuesto = Detalle_presupuesto.cod_presupuesto " +
                         "WHERE Presupuesto.cod_presupuesto = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                valor = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean MthValorRecurso(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select Recurso.cod_recurso from  " +
                         "((Detalle_presupuesto " +
                         "inner join Recurso on Recurso.cod_recurso = Detalle_presupuesto.cod_recurso) " +
                         "inner join Presupuesto on Presupuesto.cod_presupuesto = Detalle_presupuesto.cod_presupuesto) " +
                         "where recurso = ? and Presupuesto.cod_presupuesto = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, recursotxt);
            cmd.setInt(2, codigo);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                valor = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean MthValorRecursotxt(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select recurso from  " +
                         "((Detalle_presupuesto " +
                         "inner join Recurso on Recurso.cod_recurso = Detalle_presupuesto.cod_recurso) " +
                         "inner join Presupuesto on Presupuesto.cod_presupuesto = Detalle_presupuesto.cod_presupuesto) " +
                         "where recurso = ? and Presupuesto.cod_presupuesto = ? and cod_detalle = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, recursotxt);
            cmd.setInt(2, codigo);
            cmd.setInt(3, detalle);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                valor1 = rs.getString(1);
            } else {
                valor1 = "";
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    //Recursos----------------------------------------------------------------------------------------------------------------------------------
    
    public ResultSet ConsultaRecurso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_recurso, recurso FROM Recurso");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    public ResultSet LlenarRecurso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Recurso order by (cod_recurso) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public boolean MthValorR(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "select recurso from Recurso where recurso = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, recursotxt);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                valor1 = rs.getString(1);
            } else {
                valor1 = "";
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean MthGuardarRecurso(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "INSERT INTO Recurso(recurso) VALUES (?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setString(1, recursotxt);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if(!cmd.execute()){
                resp = true;
            }
            //Se cierra la Conexion y el cmd
            cmd.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    public boolean mthModificarRecurso(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Recurso SET recurso = ? WHERE cod_recurso = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, recursotxt);
            cmd.setInt(2, recurso);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (!cmd.execute()){
                resp = true;
            } 
            //cerrando la Conexion y el cmd
            cmd.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }

    
    
    
    //Graficas --------------------------------------------------------------------------------------------------------------------------------------
    public ResultSet ConsultaGrafica(){
        try{
            String sql = "SELECT TOP 12 mes, total FROM Presupuesto where año = ? ORDER BY cod_presupuesto ASC ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, anio);
            ResultSet respuesta = cmd.executeQuery();
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    public ResultSet ConsultaSeg(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select top 7 fecha, count(fecha) from Reporte_conducta group by (fecha) order by (fecha) desc ");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    //Busquedas
    
    public ResultSet Busqueda1(){
        Statement declara;
        try{
            
            String sql = "SELECT cod_presupuesto, fecha, año, mes, total FROM Presupuesto WHERE fecha BETWEEN ? AND ? ORDER BY fecha ASC";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet respuesta = cmd.executeQuery();
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    //reportecomun----------------------------------------------------------------------------------------------------------------------------------------

public boolean MthGuardarReporte(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "insert into Reporte_comun values (?, ?, ?)";
            //Declarando variable cmd y manda la consuta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingreso de los valores
            cmd.setInt(1, empleado);
            cmd.setString(2, fechas);
            cmd.setString(3, descripcion);
            //validacion de si la consulta se ejecuto satisfactoriamente
            if(!cmd.execute()){
                resp = true;
            }
            //Se cierra la Conexion y el cmd
            cmd.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
}
public ResultSet ConsultaReporte(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_reporte,nombre_empleado,fecha_reporte, descripcion_reporte  FROM Reporte_comun,Empleado WHERE empleado.cod_empleado=Reporte_comun.cod_empleado");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
}

