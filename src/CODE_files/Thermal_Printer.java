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
public class Thermal_Printer {
    
    ResultSet rs;
    PreparedStatement str;
    Connection con;
    
    
    public static int    printer_id;
    public static String printer_name;
    public static String printer_type;

    public Thermal_Printer() {
        ConnectDB c = new ConnectDB();
        con=c.Connect();
    }
    
    
    
    

    public static int getPrinter_id() {
        return printer_id;
    }

    public static void setPrinter_id(int printer_id) {
        Thermal_Printer.printer_id = printer_id;
    }

    public static String getPrinter_name() {
        return printer_name;
    }

    public static void setPrinter_name(String printer_name) {
        Thermal_Printer.printer_name = printer_name;
    }

    public static String getPrinter_type() {
        return printer_type;
    }

    public static void setPrinter_type(String printer_type) {
        Thermal_Printer.printer_type = printer_type;
    }
    
    
    
        
    
    public boolean setPrinterDate(){
        try{
            str=con.prepareStatement("Select* from printer_config where TYPE='thermal printer'");
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
            str=con.prepareStatement ("UPDATE `printer_config` SET `"+field+"`='"+data+"' WHERE TYPE='thermal printer' and ID="+id);
            str.execute();
            setPrinterDate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    
}
