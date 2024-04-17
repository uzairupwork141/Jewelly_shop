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
