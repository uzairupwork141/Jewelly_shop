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
public class Scale_config_model {

    public Scale_config_model() {
        ConnectDB c = new ConnectDB();
        con=c.Connect();
    }
    
    
    
    
    ResultSet rs;
    PreparedStatement str;
    Connection con;
    
    private static int id;
    private static String com;
    private static int baud_rate;
    private static int bits;
    private static int stop_bits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public int getBaud_rate() {
        return baud_rate;
    }

    public void setBaud_rate(int baud_rate) {
        this.baud_rate = baud_rate;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getStop_bits() {
        return stop_bits;
    }

    public void setStop_bits(int stop_bits) {
        this.stop_bits = stop_bits;
    }
   
    


    public boolean setScaleDate(String id){
        try{
            str=con.prepareStatement("Select* from scale_config where ID="+id);
            rs=str.executeQuery();
            if(rs.next()){
                this.id=rs.getInt("ID");
                this.com=rs.getString("COM");
                this.baud_rate=rs.getInt("BAUD_RATE");
                this.bits=rs.getInt("BITS");
                this.stop_bits=rs.getInt("STOP_BITS");
                return true;
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        return false;
    }
    
    
    
    
    public boolean update(String field,String data,String id){
        try{      
            str=con.prepareStatement ("UPDATE `scale_config` SET `"+field+"`='"+data+"' WHERE ID="+id);
            str.execute();
            setScaleDate(id);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
}
