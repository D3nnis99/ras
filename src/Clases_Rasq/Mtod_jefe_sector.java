/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import foto_reporteria_2.*;
import java.sql.Connection;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


/**
 *
 * @author Works
 */

public class Mtod_jefe_sector 
{
Connection  cn;
int codigo; 
String nombre; 
String apellidos; 
int edad; 
int altura;
int peso; 
String tez;
String alias;
String estado_recluso;
String crimen;
int codigo_barra;
int total_Reclusos;
String foto_recluso;
private Image data;

//Variable para celdas
    String nombre_recluso;
    String Apellidos_recluso;
    int codigo_bar;
    String Sector;
    String Agrupacion;    
    int codigo_celda;
    String horario_salida;
    String horario_entrada;

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

    public int getCodigo_bar() {
        return codigo_bar;
    }

    public void setCodigo_bar(int codigo_bar) {
        this.codigo_bar = codigo_bar;
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

    public String getFecha_historial_fin() {
        return Fecha_historial_fin;
    }

    public void setFecha_historial_fin(String Fecha_historial_fin) {
        this.Fecha_historial_fin = Fecha_historial_fin;
    }
String Fecha_historial_fin;


String Fecha_historial; 




    public String getFoto_recluso() {
        return foto_recluso;
    }

    public void setFoto_recluso(String foto_recluso) {
        this.foto_recluso = foto_recluso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTez() {
        return tez;
    }

    public void setTez(String tez) {
        this.tez = tez;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEstado_recluso() {
        return estado_recluso;
    }

    public void setEstado_recluso(String estado_recluso) {
        this.estado_recluso = estado_recluso;
    }

    public String getCrimen() {
        return crimen;
    }

    public void setCrimen(String crimen) {
        this.crimen = crimen;
    }

    public int getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(int codigo_barra) {
        this.codigo_barra = codigo_barra;
    }
    
      public int getTotal_Reclusos() {
        return total_Reclusos;
    }

    public void setTotal_Reclusos(int total_Reclusos) {
        this.total_Reclusos = total_Reclusos;
    }
    
        public String getFecha_historial() {
        return Fecha_historial;
    }

    public void setFecha_historial(String Fecha_historial) {
        this.Fecha_historial = Fecha_historial;
    }

    
    
    
    public Mtod_jefe_sector()
    {
        Conexion conect = new  Conexion();
        cn = conect.conectar();
    
    }
    
    public boolean cosulta_total_reclusos() // consulta el ultimo recluso de la base de datos 
    {
     boolean rest = false;
      
        try {
          
            String sql = "select MAX(cod_recluso) from Reclusos";   
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
            total_Reclusos = rs.getInt(1); 
            }
            cmd.close();
            //cn.close();
            
        } catch (Exception e) {
        
            System.out.println("error" + e);    
        }
           
     return  rest;
    
    
    }
    
    public boolean consult_data() // con el buscador de cod recluso 
    {
    boolean rest = false;
      
        try {
         
       String sql = "select cod_recluso, nombre_recluso,apellido_recluso,edad_recluso, altura, peso , tez, alias ,estado     from Reclusos, Tez, Estado Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND cod_recluso =?";   
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
            rest = true; 
            codigo = rs.getInt(1);
            nombre = rs.getString(2);
            apellidos = rs.getString(3);
            edad = rs.getInt(4);
            altura = rs.getInt(5);
            peso = rs.getInt(6);
            tez = rs.getString(7);
            alias = rs.getString(8);
            estado_recluso = rs.getString(9);
                
            }
            cmd.close();
           // cn.close();
            
        } catch (Exception e) {
        
            System.out.println("error" + e);    
        }
    
     return  rest;
    }
    
    public boolean consulta_like() // con el texbox de nombre recluso 
    {
      boolean rest = false;
      
        try {
         
            String sql = "select cod_recluso, nombre_recluso,apellido_recluso,edad_recluso, altura, peso , tez, alias ,estado  from Reclusos, Tez, Estado Where  Tez.cod_tez= Reclusos.cod_tez AND Estado.cod_estado = Reclusos.cod_estado AND nombre_recluso like '" +nombre + "%'" ;   
            PreparedStatement cmd = cn.prepareStatement(sql);
       
            ResultSet rs = cmd.executeQuery();
           if(rs.next())
            {
            rest = true; 
            codigo = rs.getInt(1);
            nombre = rs.getString(2);
            apellidos = rs.getString(3);
            edad = rs.getInt(4);
            altura = rs.getInt(5);
            peso = rs.getInt(6);
            tez = rs.getString(7);
            alias = rs.getString(8);
            estado_recluso = rs.getString(9);
          
            }
             cmd.close();
           //  cn.close();
            
        } catch (Exception e) {
        
            System.out.println("error  " + e);    
        }
    
     return  rest;
    
    
    }
   
    public boolean Consulta_general() /// consulta general estado inactivo --- no utilizar ------------------
    {
    boolean rest = false;
      try {
         
           String sql = "select cod_recluso,nombre_recluso,estado_recluso,codigo_barra FROM Reclusos, Tez, Estado_recluso Where  Tez.cod_tez= Reclusos.cod_tez AND Estado_recluso.cod_estado = Reclusos.cod_estado";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
            rest = true; 
            codigo = rs.getInt(1);
            nombre = rs.getString(2);
            estado_recluso = rs.getString(3);
            codigo_barra = rs.getInt(4);
            }
            cmd.close();
            //cn.close();
            
        } catch (Exception e) {
            System.out.println("error" + e);    
        }
    
     return  rest;
    }
    
    
       private Image ConvertirImagen(byte[] bytes) throws IOException // eventos de ejemplo, no utilizar-------
 {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }
       
     public  Image recuperarFoto(String q , int a) // eventos de ejemplo, no utilizar 
     {
         try {
           String sql = "select url_bit, cod_tipo_foto  from Tipo_foto, Foto_recluso, Reclusos where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND   cod_tipo_foto = ? AND nombre_recluso = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
          // cmd.setInt(1, contador);
           cmd.setInt(1, a);
           cmd.setString(2,q);
           
           ResultSet rs = cmd.executeQuery();
           int i =0;
             while (rs.next()) 
             {
              byte[] x = rs.getBytes("url_bit");
              data = ConvertirImagen(x);
              i++;
                 
             }
             rs.close();
         } catch (Exception e) {
         }
     
     return  data;
     
     }
     
     public boolean consulta_de_años() /// Consulta de años para bitacora 
    {
    boolean rest = false;
      try {
         
           String sql = "select MIN(fecha) from  Registros_sistemas";   
 
           String sql2 = "select MAX(fecha) from  Registros_sistemas";   
           PreparedStatement cmd = cn.prepareStatement(sql);
           PreparedStatement cmd2 = cn.prepareStatement(sql2);
           ResultSet rs = cmd.executeQuery();
           ResultSet rs2 = cmd2.executeQuery();
            if(rs.next() && rs2.next())
            {
            rest = true; 
            Fecha_historial = rs.getString(1);
            Fecha_historial_fin = rs.getString(2);
            }
            cmd.close();
            //cn.close();
            
        } catch (Exception e) {
            System.out.println("error" + e);    
        }
    
     return  rest;
    }
         
    public boolean consulta_like2() // con el texbox de nombre recluso 
    {
      boolean rest = false;
      
        try {
         
            String sql = "select nombre_recluso,apellido_recluso,cod_recluso, edad_recluso from Reclusos where  nombre_recluso like'" +nombre+ "%'" ;   
            PreparedStatement cmd = cn.prepareStatement(sql);
       
            ResultSet rs = cmd.executeQuery();
           if(rs.next())
            {
            rest = true; 
            codigo = rs.getInt(3);
            edad = rs.getInt(4);
            apellidos = rs.getString(2);
            nombre = rs.getString(1);
            }
             cmd.close();
           //  cn.close();
            
        } catch (Exception e) {
        
            System.out.println("error  " + e);    
        }
    
     return  rest;
    
    
    }
    
    public ResultSet LlenarCelda(){
        Statement declara;
        try{
            declara = cn.createStatement();
                ResultSet resp = declara.executeQuery("Select cod_celda from Celdas");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet ObtenerReclusos(){
        Statement declara;
        try{
            
            String sql = "select cod_recluso, nombre_recluso, apellido_recluso, codigo_barra, nombre_sector, nombre_agrupacion, hora_apertura, hora_cierre  from Reclusos, Sector, Agrupacion, Horario, Estado where Sector.cod_sector = Reclusos.cod_sector and Agrupacion.cod_agrupacion = Sector.cod_agrupacion and Horario.cod_horario = Sector.cod_horario and Estado.cod_estado = Reclusos.cod_estado and Estado.cod_estado != 10 and celda = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo_celda);
            ResultSet respuesta = cmd.executeQuery();
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
    
    public ResultSet ObtenerReclusos2(){
        Statement declara;
        try{
            
            String sql = "select cod_recluso, nombre_recluso, apellido_recluso, codigo_barra, nombre_sector, nombre_agrupacion, hora_apertura, hora_cierre  from Reclusos, Sector, Agrupacion, Horario, Estado where Sector.cod_sector = Reclusos.cod_sector and Agrupacion.cod_agrupacion = Sector.cod_agrupacion and Horario.cod_horario = Sector.cod_horario and Estado.cod_estado = Reclusos.cod_estado and Estado.cod_estado != 10 and celda = ? and Reclusos.nombre_recluso like '" + nombre_recluso + "%'";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo_celda);
            ResultSet respuesta = cmd.executeQuery();
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }    
    }
         
}
