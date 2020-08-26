/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Rasq;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Works
 */
public class Conexion {
    public Connection conectar()
            {
             Connection cn = null; 
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    cn =DriverManager.getConnection("jdbc:sqlserver://CHRISTIAN;databaseName=Ras_A;integratedSecurity=true;");
                } catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null,"No se puede conectar",ex.getMessage(), JOptionPane.ERROR_MESSAGE );
                    
                }
                return  cn;
                
            }  
    
}
