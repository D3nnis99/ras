/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Works
 */
public class Hora_Fecha {
Connection cn;

  
String Sector;



  //variables 1
int hora_Apertura;
int minutos_Apertura;
int hora_Cierre;
int minutos_Cierre;

String Receptor_horaA;
String Receptor_horaC;




//variables2
String Sector2;
int hora_Apertura2;
int minutos_Apertura2;
int hora_Cierre2;
int minutos_Cierre2;

String Receptor_horaD;
String Receptor_horaE;



//variables3
String Sector3;
int hora_Apertura3;
int minutos_Apertura3;
int hora_Cierre3;
int minutos_Cierre3;

String Receptor_horaF;
String Receptor_horaG;

//variables4
String Sector4;
int hora_Apertura4;
int minutos_Apertura4;
int hora_Cierre4;
int minutos_Cierre4;

String Receptor_horaH;
String Receptor_horaI;

//variables horario1
    public String getReceptor_horaA() {
        return Receptor_horaA;
    }

    public void setReceptor_horaA(String Receptor_horaA) {
        this.Receptor_horaA = Receptor_horaA;
    }

    public String getReceptor_horaC() {
        return Receptor_horaC;
    }

    public void setReceptor_horaC(String Receptor_horaC) {
        this.Receptor_horaC = Receptor_horaC;
    }

  public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }
  
      public int getHora_Apertura() {
        return hora_Apertura;
    }

    public void setHora_Apertura(int hora_Apertura) {
        this.hora_Apertura = hora_Apertura;
    }

    public int getMinutos_Apertura() {
        return minutos_Apertura;
    }

    public void setMinutos_Apertura(int minutos_Apertura) {
        this.minutos_Apertura = minutos_Apertura;
    }

    public int getHora_Cierre() {
        return hora_Cierre;
    }

    public void setHora_Cierre(int hora_Cierre) {
        this.hora_Cierre = hora_Cierre;
    }

    public int getMinutos_Cierre() {
        return minutos_Cierre;
    }

    public void setMinutos_Cierre(int minutos_Cierre) {
        this.minutos_Cierre = minutos_Cierre;
    }
    
    
    
    
    
    //variables horario2
    public String getReceptor_horaD() {
        return Receptor_horaD;
    }

    public void setReceptor_horaD(String Receptor_horaD) {
        this.Receptor_horaD = Receptor_horaD;
    }

    public String getReceptor_horaE() {
        return Receptor_horaE;
    }

    public void setReceptor_horaE(String Receptor_horaE) {
        this.Receptor_horaE = Receptor_horaE;
    }

  public String getSector2() {
        return Sector2;
    }

    public void setSector2(String Sector2) {
        this.Sector2 = Sector2;
    }
  
      public int getHora_Apertura2() {
        return hora_Apertura2;
    }

    public void setHora_Apertura2(int hora_Apertura2) {
        this.hora_Apertura2 = hora_Apertura2;
    }

    public int getMinutos_Apertura2() {
        return minutos_Apertura2;
    }

    public void setMinutos_Apertura2(int minutos_Apertura2) {
        this.minutos_Apertura2 = minutos_Apertura2;
    }

    public int getHora_Cierre2() {
        return hora_Cierre2;
    }

    public void setHora_Cierre2(int hora_Cierre2) {
        this.hora_Cierre2 = hora_Cierre2;
    }

    public int getMinutos_Cierre2() {
        return minutos_Cierre2;
    }

    public void setMinutos_Cierre2(int minutos_Cierre2) {
        this.minutos_Cierre2 = minutos_Cierre2;
    }
    
    
    
    //variables horario3
    public String getReceptor_horaF(){
        return Receptor_horaF;
    }

    public void setReceptor_horaF(String Receptor_horaF) {
        this.Receptor_horaF = Receptor_horaF;
    }

    public String getReceptor_horaG() {
        return Receptor_horaG;
    }

    public void setReceptor_horaG(String Receptor_horaG) {
        this.Receptor_horaG = Receptor_horaG;
    }

  public String getSector3() {
        return Sector3;
    }

    public void setSector3(String Sector3) {
        this.Sector3 = Sector3;
    }
  
      public int getHora_Apertura3() {
        return hora_Apertura3;
    }

    public void setHora_Apertura3(int hora_Apertura3) {
        this.hora_Apertura3 = hora_Apertura3;
    }

    public int getMinutos_Apertura3() {
        return minutos_Apertura3;
    }

    public void setMinutos_Apertura3(int minutos_Apertura3) {
        this.minutos_Apertura3 = minutos_Apertura3;
    }

    public int getHora_Cierre3() {
        return hora_Cierre3;
    }

    public void setHora_Cierre3(int hora_Cierre3) {
        this.hora_Cierre3 = hora_Cierre3;
    }

    public int getMinutos_Cierre3() {
        return minutos_Cierre3;
    }

    public void setMinutos_Cierre3(int minutos_Cierre3) {
        this.minutos_Cierre3 = minutos_Cierre3;
    }
    
    
    
    
    //variables horario4
    public String getReceptor_horaH() {
        return Receptor_horaH;
    }

    public void setReceptor_horaH(String Receptor_horaH) {
        this.Receptor_horaH = Receptor_horaH;
    }

    public String getReceptor_horaI() {
        return Receptor_horaI;
    }

    public void setReceptor_horaI(String Receptor_horaI) {
        this.Receptor_horaI = Receptor_horaI;
    }

  public String getSector4() {
        return Sector4;
    }

    public void setSector4(String Sector4) {
        this.Sector4 = Sector4;
    }
  
      public int getHora_Apertura4() {
        return hora_Apertura4;
    }

    public void setHora_Apertura4(int hora_Apertura4) {
        this.hora_Apertura4 = hora_Apertura4;
    }

    public int getMinutos_Apertura4() {
        return minutos_Apertura4;
    }

    public void setMinutos_Apertura4(int minutos_Apertura4) {
        this.minutos_Apertura4 = minutos_Apertura4;
    }

    public int getHora_Cierre4() {
        return hora_Cierre4;
    }

    public void setHora_Cierre4(int hora_Cierre4) {
        this.hora_Cierre4 = hora_Cierre4;
    }

    public int getMinutos_Cierre4() {
        return minutos_Cierre4;
    }

    public void setMinutos_Cierre4(int minutos_Cierre4) {
        this.minutos_Cierre4 = minutos_Cierre4;
    }

public Hora_Fecha()
{
Conexion conect = new  Conexion();
cn= conect.conectar();
}
 
    //CONSULTA SECTOR1
 public boolean consult_fecha()
 {
 boolean rest= false ; 
    
     try {
     String sql ="select hora_apertura, hora_cierre from Reclusos, Horario, Sector where horario.cod_horario = Sector.cod_sector AND Sector.cod_sector =Reclusos.cod_sector AND nombre_sector = ?" ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Sector);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
        Receptor_horaA= rs.getString(1);
        Receptor_horaC =rs.getString(2);
       
           conversor();
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","1", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 public void conversor()
 {
       
       String desencadenador =   Receptor_horaA.substring(0, 2);
       String desencadenador1  = Receptor_horaC.substring(0, 2);
       
       String desencadenador2 = Receptor_horaA.substring(3, 5);
       String desencadenador3  = Receptor_horaC.substring(3, 5);
       
       hora_Apertura = Integer.parseInt(desencadenador);
       hora_Cierre = Integer.parseInt(desencadenador1);
       
       minutos_Apertura= Integer.parseInt(desencadenador2);
       minutos_Cierre = Integer.parseInt(desencadenador3);
       
     double   doublex = Double.parseDouble(desencadenador3);
 
       
 }
 
 
 
 
 //CONSULTA SECTOR2
 
  public boolean consult_fecha2()
 {
 boolean rest= false ; 
    
     try {
     String sql ="select hora_apertura, hora_cierre from Reclusos, Horario, Sector where horario.cod_horario = Sector.cod_sector AND Sector.cod_sector =Reclusos.cod_sector AND nombre_sector = ?" ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Sector2);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
        Receptor_horaD = rs.getString(1);
        Receptor_horaE =rs.getString(2);
       
           conversor();
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","2", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 public void conversor2()
 {
       
       String desencadenador =   Receptor_horaD.substring(0, 2);
       String desencadenador1  = Receptor_horaE.substring(0, 2);
       
       String desencadenador2 = Receptor_horaD.substring(3, 5);
       String desencadenador3  = Receptor_horaE.substring(3, 5);
       
       hora_Apertura2 = Integer.parseInt(desencadenador);
       hora_Cierre2 = Integer.parseInt(desencadenador1);
       
       minutos_Apertura2= Integer.parseInt(desencadenador2);
       minutos_Cierre2 = Integer.parseInt(desencadenador3);
       
     double   doublex = Double.parseDouble(desencadenador3);
 
       
 }
 
 
 
 
 //CONSULTA SECTOR3
  public boolean consult_fecha3()
 {
 boolean rest= false ; 
    
     try {
     String sql ="select hora_apertura, hora_cierre from Reclusos, Horario, Sector where horario.cod_horario = Sector.cod_sector AND Sector.cod_sector =Reclusos.cod_sector AND nombre_sector = ?" ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Sector3);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
        Receptor_horaF= rs.getString(1);
        Receptor_horaG =rs.getString(2);
       
           conversor();
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","3", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 public void conversor3()
 {
       
       String desencadenador =   Receptor_horaF.substring(0, 2);
       String desencadenador1  = Receptor_horaG.substring(0, 2);
       
       String desencadenador2 = Receptor_horaF.substring(3, 5);
       String desencadenador3  = Receptor_horaG.substring(3, 5);
       
       hora_Apertura3 = Integer.parseInt(desencadenador);
       hora_Cierre3 = Integer.parseInt(desencadenador1);
       
       minutos_Apertura3= Integer.parseInt(desencadenador2);
       minutos_Cierre3 = Integer.parseInt(desencadenador3);
       
     double   doublex = Double.parseDouble(desencadenador3);
 
       
 }
 
 
 //CONSULTA SECTOR4
  public boolean consult_fecha4()
 {
 boolean rest= false ; 
    
     try {
     String sql ="select hora_apertura, hora_cierre from Reclusos, Horario, Sector where horario.cod_horario = Sector.cod_sector AND Sector.cod_sector =Reclusos.cod_sector AND nombre_sector = ?" ;
     PreparedStatement cms = cn.prepareStatement(sql);
     cms.setString(1, Sector4);
         ResultSet rs = cms.executeQuery();
         if(rs.next())
         {
         rest= true; 
        Receptor_horaH= rs.getString(1);
        Receptor_horaI =rs.getString(2);
       
           conversor();
         }
         else
         {
         
             JOptionPane.showMessageDialog(null ,"Error","4", JOptionPane.ERROR_MESSAGE);
         }
         cms.close();
        // cn.close();
     } catch (Exception ex)
     {
         System.out.println("Error" + ex);   
     }
  return rest;
 }
 
 public void conversor4()
 {
       
       String desencadenador =   Receptor_horaH.substring(0, 2);
       String desencadenador1  = Receptor_horaI.substring(0, 2);
       
       String desencadenador2 = Receptor_horaH.substring(3, 5);
       String desencadenador3  = Receptor_horaI.substring(3, 5);
       
       hora_Apertura4 = Integer.parseInt(desencadenador);
       hora_Cierre4 = Integer.parseInt(desencadenador1);
       
       minutos_Apertura4= Integer.parseInt(desencadenador2);
       minutos_Cierre4 = Integer.parseInt(desencadenador3);
       
     double   doublex = Double.parseDouble(desencadenador3);
 
       
 }
       
}
