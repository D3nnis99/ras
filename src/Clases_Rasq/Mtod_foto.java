/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import com.sun.crypto.provider.RSACipher;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import sun.nio.cs.KOI8_R;

/**
 *
 * @author Works
 */

public class Mtod_foto 
{
    Connection cn;
    String foto_recluso;
    private Image data;
    ImageIcon Newicon;
    ImageIcon Newicon1;
    ImageIcon Newicon2;
    ImageIcon Newicon3;
    private String A_url;
    
  
    ImageIcon Newicon1_ur;
    ImageIcon Newicon2_ur;
    ImageIcon Newicon3_ur;

    public ImageIcon getNewicon1_ur() {
        return Newicon1_ur;
    }

    public void setNewicon1_ur(ImageIcon Newicon1_ur) {
        this.Newicon1_ur = Newicon1_ur;
    }

    public ImageIcon getNewicon2_ur() {
        return Newicon2_ur;
    }

    public void setNewicon2_ur(ImageIcon Newicon2_ur) {
        this.Newicon2_ur = Newicon2_ur;
    }

    public ImageIcon getNewicon3_ur() {
        return Newicon3_ur;
    }

    public void setNewicon3_ur(ImageIcon Newicon3_ur) {
        this.Newicon3_ur = Newicon3_ur;
    }
  
  

    public String getA_url() {
        return A_url;
    }

    public void setA_url(String A_url) {
        this.A_url = A_url;
    }

   

   
    public ImageIcon getNewicon1() {
        return Newicon1;
    }

    public void setNewicon1(ImageIcon Newicon1) {
        this.Newicon1 = Newicon1;
    }

    public ImageIcon getNewicon2() {
        return Newicon2;
    }

    public void setNewicon2(ImageIcon Newicon2) {
        this.Newicon2 = Newicon2;
    }

    public ImageIcon getNewicon3() {
        return Newicon3;
    }

    public void setNewicon3(ImageIcon Newicon3) {
        this.Newicon3 = Newicon3;
    }
    
    
    
 //------------ consultar imagen a nivel de bits formato blob-----------------------------  
    public Mtod_foto()
    {
      Conexion conect = new  Conexion();
      cn = conect.conectar();
    }
    
      public  Image ConvertirImagen(byte[] bytes) throws IOException // evento para converir imagen de escala de bits  
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
       
          
     public  Image recuperarFoto(String q , int a)
     {
         try {
           String sql = "select url_bit, cod_tipo_foto  from Tipo_foto, Foto_recluso, Reclusos where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND   cod_tipo_foto = ? AND apellido_recluso = ?";   
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
             System.out.println("Error" +e);
         }
     
     return  data;
     
     }
     
        public  Image recuperarFoto_empleado(String q )
     {
         try {
           String sql = "select url_bit from Empleado where  nombre_usuario = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
          // cmd.setInt(1, contador);
         
           cmd.setString(1,q);
           
           ResultSet rs = cmd.executeQuery();
           
       
             while (rs.next()) 
             {
              byte[] x = rs.getBytes("url_bit");
              data = ConvertirImagen(x);
            
                 
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
     
     return  data;
     
     }
     
     
     
     public void  receptor_data(String nombre_R)
     {
         for (int i = 1; i <= 3; i++)
         {
         Image capturador = recuperarFoto(nombre_R, i);
         ImageIcon icono = new ImageIcon(capturador);
         Image img = icono.getImage();
         Image newIMG = img.getScaledInstance(155, 180, java.awt.Image.SCALE_SMOOTH);
         Newicon = new ImageIcon(newIMG); 
         
         if(i==1)
         {
         Newicon1 = new ImageIcon(newIMG); 
       //  JOptionPane.showMessageDialog(null , "Hola");
         }
         else if(i ==2)
         {
           Newicon2 = new ImageIcon(newIMG); 
         }
         else if( i ==3)
         {
          Newicon3 = new ImageIcon(newIMG); 
         }
         else
         {
             JOptionPane.showMessageDialog(null, "error");
         
         } 
     
         }  
     }
     
          public void  receptor_data_empleado(String nombre_R)
     {
         
         Image capturador = recuperarFoto_empleado(nombre_R);
         ImageIcon icono = new ImageIcon(capturador);
         Image img = icono.getImage();
         Image newIMG = img.getScaledInstance(155, 180, java.awt.Image.SCALE_SMOOTH);
 
           Newicon = new ImageIcon(newIMG); 
           
           Newicon1 = new ImageIcon(newIMG);
           Newicon2 = new ImageIcon(newIMG); 
           Newicon3 = new ImageIcon(newIMG); 
        
         
      
     
         
     }
     
     
         
 //------------ consultar imagen a nivel de bits formato blob-----------------------------  
    
     
     
     
 //-----------------consultar imagen a nivel de  url ----------------------------------------------------------------
     
     public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(Mtod_foto.class.getResource(fileName));
        } catch (Exception e) {
            return null;
        }
    } // evento donde se  mandan a cargar las img 
     
      public  void  recuperarFoto2 (String q , int a)
     {
         try {
           String sql = "select url , cod_tipo_foto from Tipo_foto, Foto_recluso, Reclusos where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND   cod_tipo_foto = ? AND apellido_recluso = ?";   
           PreparedStatement cmd = cn.prepareStatement(sql);
          // cmd.setInt(1, contador);
           cmd.setInt(1, a);
           cmd.setString(2,q);
           
           ResultSet rs = cmd.executeQuery();
           
             if(rs.next()) 
             {
              A_url = rs.getString(1);
        
             }
             rs.close();
         } catch (Exception e) {
             System.out.println("Error" +e);
         }
     
     }
      

      
      
      
       public void  receptor_data2(String nombre_R)
     {
         for (int i = 1; i <= 3; i++)
         {
         recuperarFoto2(nombre_R, i);
         ImageIcon icono = new ImageIcon(loadImage(A_url));
         Image img =icono.getImage();
         Image newing = img.getScaledInstance(155, 180, java.awt.Image.SCALE_DEFAULT);
         ImageIcon Newicon = new ImageIcon(newing);

         
         if(i==1)
         {
         Newicon1_ur = new ImageIcon(newing);
      
         }
         else if(i ==2)
         {
         Newicon2_ur = new ImageIcon(newing);
         }
         else if( i ==3)
         {
         Newicon3_ur = new ImageIcon(newing);
         }
         else
         {
       
         } 
     
         }  
     }
       
  
     
      
      
      
      
    
}
