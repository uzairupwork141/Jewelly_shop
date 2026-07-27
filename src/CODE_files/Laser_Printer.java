/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Gouhar Ali
 */
public class Laser_Printer {
    
    ResultSet rs;
    PreparedStatement str;
    Connection con;
    
    
    public static int    printer_id;
    public static String printer_name;
    public static String printer_type;

    public Laser_Printer() {
        ConnectDB c = new ConnectDB();
        con=c.Connect();
    }
    
    
    
    

    public static int getPrinter_id() {
        return printer_id;
    }

    public static void setPrinter_id(int printer_id) {
        Laser_Printer.printer_id = printer_id;
    }

    public static String getPrinter_name() {
        return printer_name;
    }

    public static void setPrinter_name(String printer_name) {
        Laser_Printer.printer_name = printer_name;
    }

    public static String getPrinter_type() {
        return printer_type;
    }

    public static void setPrinter_type(String printer_type) {
        Laser_Printer.printer_type = printer_type;
    }
    
    
    
    
    
    public boolean setPinterDate(){
        try{
            str=con.prepareStatement("Select* from printer_config where TYPE='laser printer'");
            rs=str.executeQuery();
            if(rs.next()){
                this.printer_id=rs.getInt("ID");
                this.printer_name=rs.getString("NAME");
                this.printer_type=rs.getString("TYPE");
                return true;
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        return false;
    }
    
    
    
    
    public boolean update(String field,String data,String id){
        try{      
            str=con.prepareStatement ("UPDATE `printer_config` SET `"+field+"`='"+data+"' WHERE TYPE='laser printer' and ID="+id);
            str.execute();
            setPinterDate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    
}
