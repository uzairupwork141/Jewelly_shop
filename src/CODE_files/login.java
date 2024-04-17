/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.sql.*;
import javax.swing.*;


/**
 *
 * @author Gouhar Ali
 */


public class login {

    ResultSet rs;
    PreparedStatement str;
    Connection con;
    
    public login() {
        ConnectDB c = new  ConnectDB();
        con=c.Connect();
        
    }
    
    
    
    
    
    
    
    
    public  boolean ADMINLOG(String username,String password){
        try {       
                str=con.prepareStatement("SELECT * FROM `logs` WHERE username=? AND password=?");
                str.setString(1,username);
                str.setString(2,password);
                rs=str.executeQuery();
                if (rs.next()){
                    String g_Pass=rs.getString("password");
                    if (g_Pass.equals(password)){
                        usermodel model = new usermodel();
                        model.setuser(rs.getString("ID"));
                        return true;
                    }
                }else{
                    return false;
                }

        }catch(Exception e)
        {
                
            JOptionPane.showMessageDialog(null, e.getMessage());
                
        }
        return false;
    }
    
    
    
    public void forgetpass(){
        try{      
            String USER=JOptionPane.showInputDialog("ENTER USERNAME");
            String CODE=JOptionPane.showInputDialog("ENTER ADMIN CODE");
            if (USER==null || CODE==null){
                JOptionPane.showMessageDialog(null,"REQUEST CANCELLED");
                return;
            }
            
           
                   
            str=con.prepareStatement ("SELECT * FROM `logs` WHERE USERNAME=? AND CODE=?");
            str.setString(1,USER);
            str.setString(2,CODE);
            rs=str.executeQuery();
            if (rs.next()){
                String newpass=JOptionPane.showInputDialog("ENTER NEW Password");
                if (newpass==null || newpass.equals(""))
                {
                    JOptionPane.showMessageDialog(null ,"EMPTY FIELD\nERROR");
                }else{
                    str=con.prepareStatement ("UPDATE `logs` SET `PASSWORD`='"+newpass+"' WHERE ID="+rs.getInt("ID"));
                    str.execute();
                    JOptionPane.showMessageDialog(null ,"PASSWORD:-> "+newpass+"\nPASSWORD SAVED");
                }
                                    
            }else
            {
                JOptionPane.showMessageDialog(null , "INCORRECT INFO");
            }
            

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    
    
    
    
}




