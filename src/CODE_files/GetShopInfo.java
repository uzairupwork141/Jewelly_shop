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
public class GetShopInfo {
    
    Connection con=new ConnectDB().Connect();
    PreparedStatement str;
    ResultSet rs;
    
     public String [] getData(){
       try{
          
          str = con.prepareStatement("Select* from shop_details where SHOP_ID=1");
          rs=str.executeQuery();
          while(rs.next()){
//            IDtxt.setText(rs.getString("SHOP_ID"));
//            NAMEtxt.setText(rs.getString("SHOP_NAME"));
//            PHONEtxt.setText(rs.getString("SHOP_PHONE"));
//            ADDRESStxt.setText(rs.getString("SHOP_ADDRESS"));
              String[]arr={rs.getString("SHOP_ID"),rs.getString("SHOP_NAME"),rs.getString("SHOP_PHONE"),rs.getString("SHOP_ADDRESS")};
              return arr;
          }
          
      }catch(Exception ex){
           System.err.println(ex);
      }
      return null;
    }
    
}
