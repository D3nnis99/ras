/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mantenimiento;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author Christian Batres
 */
public class Mto_Administrador {
    
    Connection cn;
    //variables empleado--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
     int cod_empleado;
    String nombre;
    String apellido;
    String usuario;
    String contraseña;
    int cod_cargo;
    int cod_pregunta;
    String respuesta;
    String cod_barra;
    String edad;
    int cod_estado_u;
    int cod_tipo_telefono;
    String telefono;
    String tipo_tel;
    int ultimo_empleado;
    
    //variables extra para recluso-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    int cod_recluso;
    int cod_estado;
    int cod_sector;
    int cod_tez;
    String altura;
    String alias;
    String fecha_ingreso;
    String estadoIndex;
    String tezIndex;
    Date fecha_salida;
     double peso;
     int codigo_barra;
     //int condena;
     int cod_celda;
     
     //variables extra para el historial de recluso-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     
     //int cod_crimen;
     String descripcion_his;
     String fecha_his;
     
     
     //variables extra extra :v para visitantes-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    int cod_visita;
    String nombre_vis;
    String apellido_vis;
    String direccion;
    int ultimo_visita;
    
    
    //Variables para las fotos de reclusos----------------------------------------------------------------------------------
    
    Image imagen;
    int tipo_foto;
    String url;
    boolean comp;
    
     //variables extra extra :v para citas-------------------------------------------------------
     int cod_citass;
     int cod_vis;
     int cod_rec;
     int cod_asis;  
     int indexestado; 
     String cod_v;
     String cod_r;
     String nombre_recluso;
     String apellido_recluso;
     String fechs;
     String hour;
     String vinculo;
     String motivos;

    public int getindexestado() {
        return indexestado;
    }

    public void setindextestado(int indexestado) {
        this.indexestado = indexestado;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public int getCod_citass() {
        return cod_citass;
    }

    public void setCod_citass(int cod_citass) {
        this.cod_citass = cod_citass;
    }

    public int getCod_vis() {
        return cod_vis;
    }

    public void setCod_vis(int cod_vis) {
        this.cod_vis = cod_vis;
    }

    public int getCod_rec() {
        return cod_rec;
    }

    public void setCod_rec(int cod_rec) {
        this.cod_rec = cod_rec;
    }

    public int getCod_asis() {
        return cod_asis;
    }

    public void setCod_asis(int cod_asis) {
        this.cod_asis = cod_asis;
    }

    public String getCod_v() {
        return cod_v;
    }

    public void setCod_v(String cod_v) {
        this.cod_v = cod_v;
    }

    public String getNombre_recluso() {
        return nombre_recluso;
    }

    public void setNombre_recluso(String nombre_recluso) {
        this.nombre_recluso = nombre_recluso;
    }

    public String getApellido_recluso() {
        return apellido_recluso;
    }

    public void setApellido_recluso(String apellido_recluso) {
        this.apellido_recluso = apellido_recluso;
    }

    public String getFechs() {
        return fechs;
    }

    public void setFechs(String fechs) {
        this.fechs = fechs;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    
   
    
    
    public Mto_Administrador(){
        Conexion con = new Conexion();
        cn = con.conectar();
        
    }
    //VARIABLE CITAS--------------------------------------------------------
     String fechaC;    
     int cod_asistencia;
     String cod_citas;
    
     //Variables para el historial de reclusos ------------------------------------------------------------------------------
    int cod_historial_r;
    int codigo_r;
    int cod_crimen;
    int condena;
    String descripcion;
    String fecha;
    int estado_condena;
    Double fianza;
    int tipo_condena;
    String fecha_2;
    int numero;

    public int getCod_crimen() {
        return cod_crimen;
    }

    public void setCod_crimen(int cod_crimen) {
        this.cod_crimen = cod_crimen;
    }

    public int getCod_historial_r() {
        return cod_historial_r;
    }

    public void setCod_historial_r(int cod_historial_r) {
        this.cod_historial_r = cod_historial_r;
    }

    public int getCodigo_r() {
        return codigo_r;
    }

    public void setCodigo_r(int codigo_r) {
        this.codigo_r = codigo_r;
    }

    public int getCondena() {
        return condena;
    }

    public void setCondena(int condena) {
        this.condena = condena;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado_condena() {
        return estado_condena;
    }

    public void setEstado_condena(int estado_condena) {
        this.estado_condena = estado_condena;
    }

    public Double getFianza() {
        return fianza;
    }

    public void setFianza(Double fianza) {
        this.fianza = fianza;
    }

    public int getTipo_condena() {
        return tipo_condena;
    }

    public void setTipo_condena(int tipo_condena) {
        this.tipo_condena = tipo_condena;
    }

    public String getFecha_2() {
        return fecha_2;
    }

    public void setFecha_2(String fecha_2) {
        this.fecha_2 = fecha_2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
     
    //EMPLEADO-----------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public String getTipo_tel() {
        return tipo_tel;
    }

    public void setTipo_tel(String tipo_tel) {
        this.tipo_tel = tipo_tel;
    }
    
    public int getCodigo() {
        return cod_empleado;
    }
     public void setCodigo(int codigo) {
        this.cod_empleado = codigo;
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
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
     public int getCargo() {
        return cod_cargo;
    }

    public void setCargo(int cargo) {
        this.cod_cargo = cargo;
    }
    
     public int getPregunta() {
        return cod_pregunta;
    }

    public void setPregunta(int pregunta) {
        this.cod_pregunta = pregunta;
    }
    
    public String getRespuesta() {
        return respuesta;
    }
    
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEdad() {
        return edad;
    }
    
    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    public String getEstadoIndex() {
        return estadoIndex;
    }

    public void setEstadoIndex(String estado) {
        this.estadoIndex= estado;
    }
    
    
    
    public String getTezIndex() {
        return tezIndex;
    }

    public void setTezIndex(String tez) {
        this.tezIndex= tez;
    }
    
    
    
    
        public int getEstadoR() {
        return cod_estado_u;
    }

    public void setEstadoR(int estado) {
        this.cod_estado_u= estado;
    }
    
    public int getCodTipoTelefono() {
        return cod_tipo_telefono;
    }

    public void setCodTipoTelefono(int codTipoTelefono) {
        this.cod_tipo_telefono= codTipoTelefono;
    }
    
    
        public String getTipoTelefono() {
        return telefono;
    }

    public void setTipoTelefono(String TipoTelefono) {
        this.telefono= TipoTelefono;
    }
    
        public void setUltimoEmpleado(int codigo_barra) {
        this.ultimo_empleado = codigo_barra;
    }
    public int getUltimoEmpleado() {
        return ultimo_empleado;
    }
    
    //RECLUSO-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public int getCelda() {
        return cod_celda;
    }
     public void setCelda(int cod_celda) {
        this.cod_celda = cod_celda;
    }
    
    public int getSector() {
        return cod_sector;
    }
     public void setSector(int cod_sector) {
        this.cod_sector = cod_sector;
    }
    
    public int getCodigo_R() {
        return cod_recluso;
    }
     public void setCodigo_R(int codigo) {
        this.cod_recluso = codigo;
    }
    
    public int getEstado() {
        return cod_estado;
    }

    public void setEstado(int estado) {
        this.cod_estado = estado;
    }
    
    
    
    public int getTez() {
        return cod_tez;
    }

    public void setTez(int tez) {
        this.cod_tez = tez;
    }
    
    public String getAltura() {
        return altura;
    }
    
    public void setAltura(String altura) {
        this.altura = altura;
    }
    
      public String getAlias() {
        return alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getFechaIngreso() {
        return fecha_ingreso;
    }
    
    public void setFechaIngreso(String fechaingreso) {
        this.fecha_ingreso = fechaingreso;
    }
    
    public Date getFechaSalida() {
        return fecha_salida;
    }
    
    public void setFechaSalida(Date fechasalida) {
        this.fecha_salida = fechasalida;
    }
    
      public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public int getCodigo_Barra() {
        return codigo_barra;
    }

    public void setCodigo_Barra(int codigo_barra) {
        this.codigo_barra = codigo_barra;
    }
    public int getCodigobarra() {
        return codigo_barra;
    }
     public void setCodigobarra(int codigobarra) {
        this.codigo_barra = codigobarra;
    }
     
     public Image getImagen() {
        return imagen;
    }
    
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getTipo_Foto() {
        return tipo_foto;
    }
    
    public void setTipo_Foto(int tipo_foto) {
        this.tipo_foto = tipo_foto;
    }
    
    public String getURL() {
        return url;
    }
    
    public void setURL(String url) {
        this.url = url;
    }
    
    /*public boolean getComp() {
        return comp;
    }
    
    public void setComp(boolean comp) {
        this.comp = comp;
    }*/
    
    public int getcondena() {
        return condena;
    }
     public void setcondena_R(int condena) {
        this.condena = condena;
    }

    public String getDescripcion_His() {
        return descripcion_his;
    }

    public void setDescripcion_His(String descripcion_his) {
        this.descripcion_his = descripcion_his;
    }
    
    public String getFecha_His() {
        return fecha_his;
    }

    public void setFecha_His(String fecha_his) {
        this.fecha_his = fecha_his;
    }
    
    public int getCod_Crimen() {
        return cod_crimen;
    }
     public void setCod_Crimen(int cod_crimen) {
        this.cod_crimen = cod_crimen;
    }

    
    
    
    //VISITANTE-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void setUltimoVisita(int cod_vis) {
        this.ultimo_visita = cod_vis;
    }
    public int getUltimoVisita() {
        return ultimo_visita;
    }
    
    public int getCodigo_Vis() {
        return cod_visita;
    }
     public void setCodigo_Vis(int codigo) {
        this.cod_visita = codigo;
    }
    
    public String getNombre_Vis() {
        return nombre_vis;
    }
    
    public void setNombre_Vis(String nombre) {
        this.nombre_vis = nombre;
    }
    
    public String getApellido_Vis() {
        return apellido_vis;
    }
    
    public void setApellido_Vis(String apellido) {
        this.apellido_vis = apellido;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    
    
    //CITAS------------------------------------------------
    
    
    
    

    
    public void setFechaCita(String fechaC) {
        this.fechaC = fechaC;
    }
    

     public void setCod_Citas(String cod_citas) {
        this.cod_citas = cod_citas;
    }
     
     public void setmotivos(String motivos) {
        this.motivos = motivos;
    }
     public String getMotivos() {
        return motivos;
    }
    
    
    
    
    
    //LLENANDO COMBOBOX-----------------------------------------------------------------------------------------------------------------------------------
    
    public ResultSet LlenarCargo(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Cargo order by (cod_cargo_empleado) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet LlenarTez(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Tez order by (cod_tez) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet LlenarSector(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Sector order by (cod_sector) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet LlenarPregunta(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Pregunta_seguridad order by (cod_pregunta_seg) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet LlenarEstado(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Estado where cod_aplicacion='3'order by (cod_estado) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    public ResultSet LlenarEstadoU(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet resp = declara.executeQuery("Select * from Estado where cod_aplicacion='1'order by (cod_estado) asc");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
     public ResultSet LlenarTelefono(){
        Statement declara;
        try{
            declara = cn.createStatement();
                ResultSet resp = declara.executeQuery("Select * from Tipo_telefono order by (cod_tipo) asc ");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
     
     public ResultSet LlenarCrimen(){
        Statement declara;
        try{
            declara = cn.createStatement();
                ResultSet resp = declara.executeQuery("Select * from crimen order by (cod_crimen) asc ");
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
     
     public ResultSet LlenarCelda(){
        Statement declara;
        try{
            declara = cn.createStatement();
                ResultSet resp = declara.executeQuery("Select cod_celda from Celdas where cod_sector= "+cod_sector);
            return resp;
        } catch (SQLException ex){
           return null; 
        }
    }
    
    //CERRAR CONEXION-----------------------------------------------------------------------------------------------------------
    public void CerrarConexion()
     {
        try {
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
     }
    
//MANIPULACION---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DE----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//LA-------------------------------------------------------------------------------------------------------------------------------------------------------------------- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
//BASE---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DE---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//DATOS----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //EMPLEADO----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean GuardarEmpleado(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Empleado(nombre_empleado, apellido_empleado, nombre_usuario, cotraseña, cod_cargo_empleado, cod_pregunta_seg, resp_seguridad, edad_empleado,cod_estado, url_bit, estado_contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            FileInputStream fis = null;
            cmd.setString(1, nombre);
            cmd.setString(2, apellido);
            cmd.setString(3, usuario);
            cmd.setString(4, contraseña);
            cmd.setInt(5, cod_cargo);
            cmd.setInt(6, cod_pregunta);
            cmd.setString(7, respuesta);
            cmd.setString(8, edad);
            cmd.setInt(9, cod_estado);
            
            if(url.equals(""))
            {
                 cmd.setBinaryStream(10,null);                
            }
            else
            {
                File file = new File(url);
                fis = new FileInputStream(file);
                 cmd.setBinaryStream(10,fis,(int)file.length());
            }
            cmd.setInt(11, 0);
            if(!cmd.execute()){
                resp = true;
            }
//            cmd.close();
//            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
        
        
        
            public boolean UEmpleado(){
    boolean resp=false;
    try{
        String sql="SELECT MAX(cod_empleado) FROM Empleado";
        PreparedStatement cmd = cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        if(rs.next()){
        
        resp =true;
        ultimo_empleado=rs.getInt(1);
        
        }
//        cmd.close();
//        cn.close();
    } catch (Exception ex){
        System.err.println("Error"+ex.getMessage());
    }
    return resp;
    
    }
        public boolean GuardarContactoEmpleado(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Telefono_empleado(telefono, cod_tipo, cod_empleado) VALUES (?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, telefono);
            cmd.setInt(2, cod_tipo_telefono);
            cmd.setInt(3, cod_empleado);
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public ResultSet ConsultaEmpleado(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select e.cod_empleado, nombre_empleado, apellido_empleado, nombre_usuario, cotraseña, cargo, pregunta, resp_seguridad, edad_empleado, huella, estado, telefono from ((Empleado as e inner join Pregunta_seguridad as p on e.cod_pregunta_seg = p.cod_pregunta_seg) inner join Cargo as c on e.cod_cargo_empleado = c.cod_cargo_empleado  inner join Telefono_empleado as t on e.cod_empleado=t.cod_empleado inner join Estado as es on e.cod_estado=es.cod_estado)");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
    }
    
    public boolean eliminarEmpleado(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Empleado WHERE cod_empleado = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_empleado);
            if (!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarEmpleado(){
        boolean resp = false;
        try {
            String sql = "UPDATE Empleado SET nombre_empleado = ? , apellido_empleado = ?, nombre_usuario = ?, cotraseña = ?, cod_cargo_empleado = ?," + 
                    "cod_pregunta_seg=?, resp_seguridad = ?,  edad_empleado = ?, cod_estado = ?, url_bit = ? WHERE cod_empleado = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
             FileInputStream fis = null;
            cmd.setString(1, nombre);
            cmd.setString(2, apellido);
            cmd.setString(3, usuario);
            cmd.setString(4, contraseña);
            cmd.setInt(5, cod_cargo);
            cmd.setInt(6, cod_pregunta);
            cmd.setString(7, respuesta);
            cmd.setString(8, edad);
            cmd.setInt(9, cod_estado);
            if(url.equals(""))
            {
                 cmd.setBinaryStream(10,null);                
            }
            else
            {
                File file = new File(url);
                fis = new FileInputStream(file);
                 cmd.setBinaryStream(10,fis,(int)file.length());
            }
            cmd.setInt(11, cod_empleado);
            
            if (!cmd.execute()){
                resp = true;
            } 
            cmd.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    
    

    
    
    //RECLUSO---------------------------------------------------------------------------------------------------------------------------------
    public boolean GuardarRecluso(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Reclusos(nombre_recluso, apellido_recluso, edad_recluso, cod_estado, cod_sector, cod_tez, altura, peso, alias, codigo_barra,celda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            cmd.setString(2, apellido);
            cmd.setString(3, edad);;
            cmd.setInt(4, cod_estado);
            cmd.setInt(5, cod_sector);
            cmd.setInt(6, cod_tez);
            cmd.setString(7, altura);
            cmd.setDouble(8, peso);
            cmd.setString(9, alias);
            cmd.setInt(10, codigo_barra);
            cmd.setInt(11, cod_celda);
            //cmd.setDate(11, fecha_salida);
            
            if(!cmd.execute()){
                resp = true;
            }
            //cmd.close();
            //cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public ResultSet ConsultaRecluso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso, estado, nombre_sector, tez, altura, peso, alias, codigo_barra, celda from ((Reclusos as r inner join Estado as e on r.cod_estado = e.cod_estado) inner join Sector as s on r.cod_sector = s.cod_sector inner join Tez as t on r.cod_tez = t.cod_tez)");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
    }
    
    
    
    public ResultSet UCRecluso(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT MAX(cod_recluso) FROM Reclusos");
            //cn.close();
            return respuesta;
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean eliminarRecluso(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Reclusos WHERE cod_recluso = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_recluso);
            if (!cmd.execute()){
                resp = true;
            }
            //cmd.close();
            //cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarRecluso(){
        boolean resp = false;
        try {
            String sql = "UPDATE Reclusos SET nombre_recluso = ? , apellido_recluso = ?, edad_recluso = ?, cod_estado = ?, cod_sector = ?," + 
                    "cod_tez=?, altura = ?,  peso = ?,  alias = ?, codigo_barra = ?, celda= ? WHERE cod_recluso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre);
            cmd.setString(2, apellido);
            cmd.setString(3, edad);;
            cmd.setInt(4, cod_estado);
            cmd.setInt(5, cod_sector);
            cmd.setInt(6, cod_tez);
            cmd.setString(7, altura);
            cmd.setDouble(8, peso);
            cmd.setString(9, alias);
            cmd.setInt(10, codigo_barra);
            cmd.setInt(11, cod_celda);
            cmd.setInt(12, cod_recluso);
            
            if (!cmd.execute()){
                resp = true;
            } 
            //cmd.close();
            //cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
        public boolean modificarReclusoCondena(){
        boolean resp = false;
        try {
            String sql = "UPDATE Reclusos SET condena = ? WHERE cod_recluso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, condena);
            cmd.setInt(2, cod_recluso);
            
            if (!cmd.execute()){
                resp = true;
            } 
            //cmd.close();
            //cn.close();
        } catch (Exception ex) {
            System.out.println("Error:  xff " + ex.getMessage());
        }
        return resp;
    }
    //VISITA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                public boolean UVisita(){
    boolean resp=false;
    try{
        String sql="SELECT MAX(cod_visita) FROM Visitas";
        PreparedStatement cmd = cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        if(rs.next()){
        
        resp =true;
        ultimo_visita=rs.getInt(1);
        
        }
//        cmd.close();
//        cn.close();
    } catch (Exception ex){
        System.err.println("Error"+ex.getMessage());
    }
    return resp;
    
    }
    
    
    public boolean GuardarContactoVisita(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Telefono_visita(telefono, cod_tipo, cod_visita) VALUES (?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, telefono);
            cmd.setInt(2, cod_tipo_telefono);
            cmd.setInt(3, cod_visita);
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public boolean GuardarVisita(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Visitas(nombre_visita, apellido_visita, direccion) VALUES (?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre_vis);
            cmd.setString(2, apellido_vis);
            cmd.setString(3, direccion);
            if(!cmd.execute()){
                resp = true;
            }
//            cmd.close();
//            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public ResultSet ConsultaVisita(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_visita, nombre_visita, apellido_visita, direccion FROM Visitas");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean eliminarVisita(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Visitas WHERE cod_visita = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_visita);
            if (!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarVisita(){
        boolean resp = false;
        try {
            String sql = "UPDATE Visitas SET nombre_visita = ? , apellido_visita = ?, direccion = ? WHERE cod_visita = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre_vis);
            cmd.setString(2, apellido_vis);
            cmd.setString(3, direccion);
            cmd.setInt(4, cod_visita);
            
            if (!cmd.execute()){
                resp = true;
            } 
//            cmd.close();
//            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarContactoV(){
        boolean resp = false;
        try {
            String sql = "UPDATE Telefono_visita SET telefono = ?, cod_tipo=? WHERE cod_visita = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, telefono);
            cmd.setInt(2, cod_tipo_telefono);
            cmd.setInt(4, cod_visita);
            
            if (!cmd.execute()){
                resp = true;
            } 
//            cmd.close();
//            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
  
//MISCELANEA--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //COD DE RECLUSO PARA COD DE BARRA----------------------------------------------------------------------------------------------------------------
    public boolean codBarra(){
    boolean resp=false;
    try{
        String sql="select MAX(cod_recluso) from Reclusos";
        PreparedStatement cmd = cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        if(rs.next()){
        
        resp =true;
        codigo_barra=rs.getInt(1);
        
        }
        cmd.close();
        cn.close();
    } catch (Exception ex){
        System.err.println("Error"+ex.getMessage());
    }
    return resp;
    
    }
    
    
 
    
//MANTENIMIENTOS DE IMAGENES----------------------------------------------------------------------------------------------------------------    
    
    
    
    public boolean GuardarImagenR(){
       String insert = "INSERT INTO Foto_recluso(cod_recluso, url_bit, cod_tipo_foto) VALUES (?,?,?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(insert);
            ps.setInt(1, cod_recluso);
            if(url.equals(""))
            {
                ps.setBinaryStream(2,null);                
            }
            else
            {
                File file = new File(url);
                fis = new FileInputStream(file);
                ps.setBinaryStream(2,fis,(int)file.length());
            }
            ps.setInt(3, tipo_foto);
            ps.execute();
            cn.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Error: Imposible guardar imagen - " + ex.getMessage());
        }finally{
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                //System.out.println("Error: "+ ex.getMessage());
            }
        }        
        return false;
    }
      
     
     public boolean ModificarImagenR(){
        boolean resp = false;
        try {
            FileInputStream fis = null;
            String sql = "UPDATE Foto_recluso SET url_bit = ? WHERE cod_recluso = ? AND cod_tipo_foto = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            if (url.equals("")) {
                cmd.setBinaryStream(1, null);
            } else {
                File file = new File(url);
                fis = new FileInputStream(file);
                cmd.setBinaryStream(1, fis, (int) file.length());
            }
            cmd.setInt(2, cod_recluso);
            cmd.setInt(3, tipo_foto);
            if (!cmd.execute()) {
                resp = true;
            }
            //cmd.close();
            //cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
     
     public boolean EliminarImagenR(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Foto_recluso WHERE cod_recluso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_recluso);
            if (!cmd.execute()){
                resp = true;
            }
            //cmd.close();
            //cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
     
     
     
    /*
    public boolean guardarImagen(String ruta,String nombre){
        String insert = "insert into Foto_recluso(cod_recluso, url, cod_tipo_foto) values(?,?,?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            File file = new File(ruta);
            fis = new FileInputStream(file);
            ps = cn.prepareStatement(insert);
            ps.setBinaryStream(1,fis,(int)file.length());
            ps.executeUpdate();
            cn.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }finally{
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }        
        return false;
    }

    Statement declara;
    ArrayList<Imagen> getImagenes() {
        
        ArrayList<Imagen> lista = new ArrayList();
        
        try {
            ResultSet rs = declara.executeQuery("SELECT imagen,nombre FROM Imagenes"); 
            while (rs.next())
            {
                Imagen imagen=new Imagen();
                Blob blob = rs.getBlob("imagen");
                String nombre = rs.getObject("nombre").toString();
                byte[] data = blob.getBytes(1, (int)blob.length());
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                
                setImagen(img);
                setNombre(nombre);
                lista.add(imagen);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
     */
     
//Mantenimiento telefono------------------------------------------------------------------------------------------------------------------------------------
        public boolean GuardarTipoTelefono(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Tipo_telefono(tipo) VALUES (?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1,telefono );
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
        
        public ResultSet ConsultaTipoTelefono(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT cod_tipo, tipo FROM Tipo_telefono");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    public boolean EliminarTipoTelefono(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Tipo_telefono WHERE cod_tipo = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_tipo_telefono);
            if (!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    //Obtener_estadoIndex
    public boolean ModificarTipoTel(){
        boolean resp = false;
        try {
            //Escribiendo la consulta
            String sql = "UPDATE Tipo_telefono SET tipo = ? WHERE cod_tipo = ?";
            //Declarando la variable cmd y enviando la consulta a la Conexion
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Ingresando los nuevos valores
            cmd.setString(1, tipo_tel);
            cmd.setInt(2, cod_tipo_telefono);
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
    
     
    //Mantenimiento del HISTORIAL del Recluso--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public boolean GuardarHistorial(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Historial_recluso(cod_recluso, cod_crimen, descripcion, fecha) VALUES (?, ?, ?, ?)";
            
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_recluso);
            cmd.setInt(2, cod_crimen);
            cmd.setString(3, descripcion_his);
            cmd.setString(4, fecha_his);
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    /*public ResultSet ConsultaHistorial(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("SELECT crimen, descripcion, fecha FROM Historial_recluso AS HR, Crimen AS C WHERE HR.cod_crimen = C.cod_crimen AND cod_recluso = "+cod_recluso);
            
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }*/
    




//CITAS
public ResultSet ConsultaCitas(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select cod_citas, nombre_visita, apellido_visita, vinculo, motivos, nombre_recluso, apellido_recluso from Citas, Asistencia, Reclusos, Visitas where Citas.cod_visita=Visitas.cod_visita and Citas.cod_recluso=Reclusos.cod_recluso and Citas.cod_asistencia=1 and Asistencia.cod_asistencia=1 AND fecha_cita='"+fechaC+"'");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
}

public ResultSet ConsultaCitasActualizadas(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select cod_citas, nombre_visita, apellido_visita, vinculo, motivos, nombre_recluso, apellido_recluso from Citas, Asistencia, Reclusos, Visitas where Citas.cod_visita=Visitas.cod_visita and Citas.cod_recluso=Reclusos.cod_recluso and Citas.cod_asistencia=2 and Asistencia.cod_asistencia=2 AND fecha_cita='"+fechaC+"'");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
}

 public boolean modificarCitas(){
        boolean resp = false;
        try {
            String sql = "UPDATE Citas SET cod_asistencia =2 WHERE cod_citas = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, cod_citas);
            
            if (!cmd.execute()){
                resp = true;
            } 
//            cmd.close();
//            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
 
     //------------------------------------------------------------Historial reclusos-----------------------------------------------------
    
    public boolean GuardarHistorialR(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO Historial_recluso(cod_recluso, cod_crimen, condena, descripcion, fecha, estado_condena, fianza, tipo_condena) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo_r);
            cmd.setInt(2, cod_crimen);
            cmd.setInt(3, condena);
            cmd.setString(4, descripcion);
            cmd.setString(5, fecha);
            cmd.setInt(6, estado_condena);
            cmd.setDouble(7, fianza);
            cmd.setInt(8, tipo_condena);
            
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
    }
    
    public ResultSet ConsultaHistorial(){
        try{
            String sql = ("select cod_historial, cod_recluso, crimen, condena, descripcion, fecha, estado_condena, fianza, tipo_condena from Historial_recluso, Crimen where Crimen.cod_crimen = Historial_recluso.cod_crimen and Historial_recluso.cod_recluso = ? and Historial_recluso.estado_condena = 0");
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo_r);
            ResultSet respuest = cmd.executeQuery();
            return respuest;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
        
    }
    
    
    public boolean modificarHistorial(){
        boolean resp = false;
        try {
            String sql = "UPDATE Historial_recluso SET cod_crimen = ?, condena = ?, descripcion = ?, fecha = ?, estado_condena = ?, fianza = ?, tipo_condena = ? WHERE  cod_historial = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_crimen);
            cmd.setInt(2, condena);
            cmd.setString(3, descripcion);
            cmd.setString(4, fecha);
            cmd.setInt(5, estado_condena);
            cmd.setDouble(6, fianza);
            cmd.setInt(7, tipo_condena);
            cmd.setInt(8, cod_historial_r);
            
            if (!cmd.execute()){
                resp = true;
            } 
            cmd.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarCondena(){
        boolean resp = false;
        try {
            String sql = "UPDATE Reclusos SET condena = ? WHERE  cod_recluso = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, condena);
            cmd.setInt(2, codigo_r);
            
            if (!cmd.execute()){
                resp = true;
            } 
            cmd.close();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    
    //---------------------------------------------Verificacion---------------------------------------------
    
    
    public boolean ObtenerReclusos(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select Count(*) from Reclusos where fecha_salida = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, fecha);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                numero = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public boolean modificarLibertad(){
        boolean resp = false;
        try {
            String sql = "UPDATE Reclusos SET cod_estado = 10 WHERE  fecha_salida = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, fecha);
            if (!cmd.execute()){
                resp = true;
            } 
            cmd.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    //--------------------------------------------------------------
    
    public boolean Obtener_estado(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select cod_estado from Reclusos where cod_recluso = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setInt(1, codigo_r);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                numero = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean Obtener_estadoIndex(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select cod_estado from Estado where estado = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, estadoIndex);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                cod_estado = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
//            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean Obtener_tezIndex(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select cod_tez from Tez where tez = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setString(1, tezIndex);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                cod_tez = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
//            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    
        public boolean modificarFecha(){
            boolean resp = false;
            try {
                String sql = "UPDATE Reclusos SET fecha_ingreso = ? WHERE  cod_recluso = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setString(1, fecha_2);
                cmd.setInt(2, codigo_r);
                if (!cmd.execute()){
                    resp = true;
                } 
                cmd.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return resp;
        }
        
        
         public boolean modificarFechaSalida(){
            boolean resp = false;
            try {
                String sql = "UPDATE Reclusos SET fecha_salida = ? WHERE  cod_recluso = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setString(1, fecha_2);
                cmd.setInt(2, codigo_r);
                if (!cmd.execute()){
                    resp = true;
                } 
                cmd.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return resp;
        }
         
        public boolean Obtener_fecha(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "Select fecha_salida from Reclusos where cod_recluso = ?";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            cmd.setInt(1, codigo_r);
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                fecha = rs.getString(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
        public boolean ObtenerR(){
        boolean resp = false;
        try {
            //Escribiendo consulta
            String sql = "select MAX(cod_recluso) from Reclusos where cod_estado = 10";
            //Declarando variable cmd y enviando consulta
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Asigna el codigo que podra ser mostrado
            ResultSet rs = cmd.executeQuery();
            //validacion de si la consulta se ejecuto satisfactoriamente
            if (rs.next()){
                resp = true;
                //Obteniendo datos del registro consultado
                codigo_r = rs.getInt(1);
            }
            //cerrando la Conexion y el cmd
            cmd.close();
            
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
        
        public boolean modificarEstadoCrimen(){
            boolean resp = false;
            try {
                String sql = "UPDATE Historial_recluso SET estado_condena = 1 WHERE  cod_recluso = ?";
                PreparedStatement cmd = cn.prepareStatement(sql);
                cmd.setInt(1, codigo_r);
                if (!cmd.execute()){
                    resp = true;
                } 
                cmd.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return resp;
        }
        
            //citas consultar
    public ResultSet Consultacitas(){
        Statement declara;
        try{
            declara = cn.createStatement();
            ResultSet respuesta = declara.executeQuery("select a.cod_citas,b.nombre_visita,b.apellido_visita,c.nombre_recluso,c.apellido_recluso,a.fecha_cita, a.hora_cita, a.vinculo, a.motivos from Citas a, Visitas b, Reclusos c, Asistencia d where a.cod_visita=b.cod_visita and a.cod_recluso = c.cod_recluso and d.cod_asistencia= a.cod_asistencia");
            return respuesta;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR: " + ex);
            return null;
        }
    }
    //eliminar citas
    public boolean eliminarcitas(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM Citas WHERE cod_citas = ? ";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_citass);
            if (!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }


//guardar citas------------------------------------------------
public boolean Guardarcitas(){
boolean resp = false;
        try {
            String sql = "INSERT INTO Citas (cod_visita, cod_recluso, cod_asistencia, fecha_cita, hora_cita, vinculo,motivos) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,cod_vis );
            cmd.setInt(2,cod_rec );
            cmd.setInt(3, cod_asis );
            cmd.setString(4,fechs);
            cmd.setString(5,hour);
            cmd.setString(6,vinculo);
            cmd.setString(7,motivos);
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        } 
        return resp;
}
public boolean modificarcitas(){
        boolean resp = false;
        try {
            String sql = "UPDATE Citas set cod_visita=?, cod_recluso=?, cod_asistencia=?, fecha_cita=?,hora_cita=?, vinculo=? where cod_citas= ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, cod_vis);
            cmd.setInt(2, cod_rec);
            cmd.setInt(3, cod_asis);
            cmd.setString(4, fechs);
            cmd.setString(5, hour);
            cmd.setString(6, vinculo);
            cmd.setString(7, motivos);
            cmd.setInt(8,cod_citass);
            
            
            if (!cmd.execute()){
                resp = true;
            } 
            cmd.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    public boolean mostrar_recluso (){
        {
            boolean rest = false;
            try {
                String sql = "select nombre_recluso, apellido_recluso, edad_recluso, cod_recluso from Reclusos where nombre_recluso  like '"+nombre_recluso+"%'";
                PreparedStatement cmd = cn.prepareStatement(sql);
                
                ResultSet rs = cmd.executeQuery();
                if(rs.next ())
                {
                    rest = true;
                    cod_rec= rs.getInt(4);
                    nombre_recluso=rs.getString(1);
                    apellido_recluso= rs.getString(2);
                    edad=rs.getString(3);
                 
                }  
            } catch (Exception e) {
            }
            
        return rest;
        }
}
         
        public boolean mostrar_visita (){
        {
            boolean rest = false;
            try {
                String sql = "select cod_recluso,nombre_recluso, apellido_recluso, edad_recluso from Reclusos where nombre_recluso  like '"+nombre_recluso+"%'";
                PreparedStatement cmd = cn.prepareStatement(sql);
                
                ResultSet rs = cmd.executeQuery();
                if(rs.next ())
                {
                    rest = true;
                    cod_vis = rs.getInt(1);
                    nombre_vis=rs.getString(2);
                    apellido_vis= rs.getString(3);
                    edad=rs.getString(4);
                    System.out.println(cod_vis);
                }  
            } catch (Exception e) {
            }
            
        return rest;
    }
        }
        
        public ResultSet ConsultaVisitas(){
            Statement declara;
            try{
                String sql = ("select cod_recluso, nombre_recluso, apellido_recluso, edad_recluso  from Reclusos where nombre_recluso  like '"+nombre_vis+"%'");
                PreparedStatement cmd = cn.prepareStatement(sql);
                //cmd.setString(1, nombre_vis);
                ResultSet respuesta = cmd.executeQuery();
            return respuesta;
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "ERROR: " + ex);
                return null;
            }
        }
        
         public ResultSet Consultarecluso(){
            Statement declara;
            try{
                String sql = ("select cod_visita, nombre_visita, apellido_visita, direccion  from Visitas where nombre_visita like '"+nombre_recluso+"%'");
                PreparedStatement cmd = cn.prepareStatement(sql);
                //cmd.setString(1, nombre_vis);
                ResultSet respuesta = cmd.executeQuery();
            return respuesta;
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "ERROR: " + ex);
                return null;
            }
         }
        
        public boolean mostrar_visitaape (){
        {
            boolean rest = false;
            try {
                String sql = "select cod_visita, nombre_visita, apellido_visita, direccion  from Visitas where apellido_visita like '"+apellido_vis+"%'";
                PreparedStatement cmd = cn.prepareStatement(sql);
                
                ResultSet rs = cmd.executeQuery();
                if(rs.next ())
                {
                    rest = true;
                    cod_vis= rs.getInt(1);
                    nombre_vis=rs.getString(2);
                    apellido_vis= rs.getString(3);
                    direccion=rs.getString(4);
                    System.out.println(cod_vis);
                }  
            } catch (Exception e) {
            }
            
        return rest;
    }
}

 
}
