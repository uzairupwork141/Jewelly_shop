/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author User1
 */

public class ConnectDB {
    private static Connection con ;
    PreparedStatement insert;
    ResultSet rs;
    public Connection Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con= DriverManager.getConnection("jdbc:mysql://localhost/yasir_db","root","");
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,"DATABASE ERROR\n"+e.getMessage(),"ERROR",2);
        }
        return con;
    }
    
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





