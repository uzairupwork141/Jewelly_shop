/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Gouhar Ali
 */
public class usermodel {

    public usermodel() {
        ConnectDB c = new ConnectDB();
        con=c.Connect();
        
    }
    
    
    
    ResultSet rs;
    PreparedStatement str;
    Connection con;
    
    private static String ID;
    private static String name;
    private static String username;
    private static String password;
    private static String code;
    private static String email;
    private static String App_pass;
    

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        usermodel.ID = ID;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        usermodel.name = name;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        usermodel.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        usermodel.password = password;
    }

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        usermodel.code = code;
    }
        
    
     public static String getEmail() {
        return email;
    }

    public static void getEmail(String email) {
        usermodel.email = email;
    }
    
    public static String getAppPass() {
        return App_pass;
    }

    public static void getAppPass(String app_pass) {
        usermodel.App_pass = app_pass;
    }
    
    
    
    public void setuser(String id){
        try{
            str=con.prepareStatement("Select* from logs where ID="+id);
            rs=str.executeQuery();
            if(rs.next()){
                ID=rs.getString("ID");
                name=rs.getString("name");
                username=rs.getString("username");
                password=rs.getString("password");
                code=rs.getString("code");
                email=rs.getString("EMAIL");
                App_pass=rs.getString("APP_PASSWORD");
                
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    
    
    public boolean update(String field,String data,String id){
        try{      
            str=con.prepareStatement ("UPDATE `logs` SET `"+field+"`='"+data+"' WHERE ID="+id);
            str.execute();
            setuser(id);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
}
