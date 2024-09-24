/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.FieldSetting;
import CODE_files.GetShopInfo;
import CODE_files.usermodel;
import EmailSend.EmailSend;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Gouhar Ali
 */
public class OrderSystem extends javax.swing.JFrame {

    /**
     * Creates new form OrderSystem
     */
    int xMouse;
    int yMouse;
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    public OrderSystem() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        con=new ConnectDB().Connect();
        Get_Set_Id();
        getItem();
        getBOOKS();
        getDESCRIPTION();
        DATE.setText(currentdate());
        
    }

    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
     public void getDataFromMainTbl(String oid) throws SQLException
    {
        
        str=con.prepareStatement("select* from order_table where OID="+oid);
        rs=str.executeQuery();
        if(rs.next()){
            IDtxt.setText(rs.getString("OID"));
            NAMEtxt.setText(rs.getString("NAME"));
            PHONEtxt.setText(rs.getString("PHONE"));
            EMAILtxt.setText(rs.getString("EMAIL"));
            RATEtxt.setText(rs.getString("RATE"));
            RDATEtxt.setText(rs.getString("RETURN_DATE"));
            TOTAL_REQUIRED_WEIGHTtxt.setText(rs.getString("T_WEIGHT"));
            TOTAL_ADVANCE_PAYEDtxt.setText(rs.getString("T_ADVANCE"));
            TOTAL_ADVANCE_GOLD_WEIGHTtxt.setText(rs.getString("T_ADVANCE_GOLD"));
            TOTAL_ADVANCE_GOLD_PASAtxt.setText(rs.getString("T_ADVANCE_PASA"));
            DATE.setText(rs.getString("DATE"));
            getDataFromSubTbl(oid,detailTable);
            getDataFromAdvance(oid, advanceTable);
            getDataFromAdvanceGold(oid);
            savebtn.setText("UPDATE");
            clearItemSection();
        }else{
            JOptionPane.showMessageDialog(this, "No Data avalible");
        }
        
    } 
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
    public void getDataFromSubTbl(String oid , JTable tbl ) throws SQLException{
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.setRowCount(0);
        str=con.prepareStatement("select* from order_details where OID="+oid);
        rs=str.executeQuery();
        while(rs.next()){
            String item  =rs.getString("item");
            String qty  =rs.getString("QTY");
            String wazan  =rs.getString("weight");
            String size  =rs.getString("size");
            String book  =rs.getString("book");
            String volume  =rs.getString("volume");
            String page =rs.getString("page");
            String item_no =rs.getString("item_no");
            String description =rs.getString("description");
            String [] row = {item,qty,wazan,size,book,volume,page,item_no,description};
            df.addRow(row);
        }
    } 
    
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
    
    
     public void getDataFromAdvance(String oid , JTable tbl ) throws SQLException{
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.setRowCount(0);
        str=con.prepareStatement("select* from order_advance_money where OID="+oid);
        rs=str.executeQuery();
        while(rs.next()){
            String item  =rs.getString("ADVANCE");
            String qty  =rs.getString("PAYMENT_DATE");
            String [] row = {item,qty};
            df.addRow(row);
        }
    } 
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
     
     public void getDataFromAdvanceGold(String oid) throws SQLException
    {
        
        str=con.prepareStatement("select* from order_advance_gold where OID="+oid);
        rs=str.executeQuery();
        if(rs.next()){
            RECIVED_GOLD_WEIGHTtxt.setText(rs.getString("GOLD_WEIGHT"));
            RECIVED_GOLD_KARATtxt.setText(rs.getString("KARAT"));
            RECIVED_GOLD_KAATtxt.setText(rs.getString("KAAT"));
            RECIVED_GOLD_PASAtxt.setText(rs.getString("PASA"));
            clearItemSection();
        }else{
            JOptionPane.showMessageDialog(this, "No Data avalible");
        }
        
    } 
    
     //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public void calAdvance(){
        try{
            int sum=0;
            int i=0;
            DefaultTableModel df = (DefaultTableModel)advanceTable.getModel();
            while (i<df.getRowCount()){
                if(advanceTable.getValueAt(i, 0).toString().equals("")){
                    sum+=0;
                }else{
                    sum+=Integer.parseInt((String) advanceTable.getValueAt(i, 0));

                }
                i++;

            }
            TOTAL_ADVANCE_PAYEDtxt.setText(""+sum);
            advanceTable.clearSelection();
        }catch(Exception e){
            TOTAL_ADVANCE_PAYEDtxt.setText(""+0);
            JOptionPane.showMessageDialog(this, "ERROR: INVALID ENTRY");
            System.out.println("error");
        }
    } 
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void Get_Set_Id(){
//        
         try{
                    str=con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'order_table' ");
                    rs=str.executeQuery();
                    if (rs.next()){
                        int id=rs.getInt("AUTO_INCREMENT");
                        IDtxt.setText(""+id);
                    }
                    
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex,"error",2);
        }
        
    }
    
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
      
      
    public void getItem(){
        try{
            str=con.prepareStatement("select* from items where TYPE='GOLD'");
            rs=str.executeQuery();
            ArrayList<String> items = new ArrayList();
            items.clear();
            while(rs.next()){
                items.add(rs.getString("ITEM"));
            }
            select_item.addItem("NONE");
            for (int i = 0; i<items.size();i++){
               select_item.addItem(items.get(i));
            }
        }catch(Exception ex ){
            JOptionPane.showMessageDialog(this, ex.getMessage(),"error",2);
        }
    }
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
    
    public void getBOOKS(){
        try{
            str=con.prepareStatement("select* from `book`");
            rs=str.executeQuery();
            ArrayList<String> items = new ArrayList();
            items.clear();
            
            while(rs.next()){
                items.add(rs.getString("name"));
            }
            select_books.addItem("NONE");
            for (int i = 0; i<items.size();i++){
               select_books.addItem(items.get(i));
            }
        }catch(Exception ex ){
            JOptionPane.showMessageDialog(this, ex.getMessage(),"error",2);
        }
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
      public void getDESCRIPTION(){
        try{
            str=con.prepareStatement("select* from DESCRIPTIONS");
            rs=str.executeQuery();
            ArrayList<String> items = new ArrayList();
            items.clear();
            select_description.removeAllItems();
            while(rs.next()){
                items.add(rs.getString("DESCRIPTION"));
            }
            select_description.addItem("NONE");
            for (int i = 0; i<items.size();i++){
               select_description.addItem(items.get(i));
            }
        }catch(Exception ex ){
            JOptionPane.showMessageDialog(this, ex.getMessage(),"error",2);
        }
    }
      
      
      //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
      
    public String currentdate(){
        Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year =cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        if(month==12){
            month=1;
        }else{
            month++;
        }
        return String.valueOf(day+"/"+month+"/"+year);
    }
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    public void calAdvanceGold(){
        double weight=Double.parseDouble(RECIVED_GOLD_WEIGHTtxt.getText());
        double karat=Double.parseDouble(RECIVED_GOLD_KARATtxt.getText());
        double pasa=(karat/24)*weight;
        double kaat= weight-pasa;
        
        RECIVED_GOLD_KAATtxt.setText(String.format("%.3f", kaat));
        RECIVED_GOLD_PASAtxt.setText(String.format("%.3f", pasa));
        
        
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setText(String.format("%.3f", weight));
        TOTAL_ADVANCE_GOLD_PASAtxt.setText(String.format("%.3f", pasa));
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
     
    public void dataTofields(MouseEvent event){
        
        if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
            DefaultTableModel df = (DefaultTableModel)detailTable.getModel();
            int srow= detailTable.getSelectedRow();

            select_item.setSelectedItem(df.getValueAt(srow, 0));
            QTYtxt.setText(df.getValueAt(srow, 1).toString() );
            ITEM_WEIGHTtxt.setText(df.getValueAt(srow, 2).toString());
            ITEM_SIZEtxt.setText(df.getValueAt(srow, 3).toString());
            select_books.setSelectedItem(df.getValueAt(srow, 4));
            BOOK_VOLUMEtxt.setText(df.getValueAt(srow, 5).toString());
            BOOK_PAGEtxt.setText(df.getValueAt(srow,6).toString());
            BOOK_ITEM_NOtxt.setText(df.getValueAt(srow,7).toString());
            select_description.setSelectedItem(df.getValueAt(srow,8).toString());
            df.removeRow(srow);

            calTableData();
        }
        
        
        
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public void addRowToTable(String[]row ,JTable tbl){
        
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.addRow(row);
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void clearItemSection(){
        select_item.setSelectedIndex(0);
        QTYtxt.setText("0");
        ITEM_WEIGHTtxt.setText("0");
        ITEM_SIZEtxt.setText("");
        select_books.setSelectedIndex(0);
        BOOK_VOLUMEtxt.setText("0");
        BOOK_PAGEtxt.setText("0");
        BOOK_ITEM_NOtxt.setText("0");
        select_description.setSelectedIndex(0);
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
    public void calTableData(){
        
        try{
            double tol_weight = 0.0;
            

            DefaultTableModel df = (DefaultTableModel)detailTable.getModel();
            
            System.out.println(df.getRowCount());
            
            for (int i=0;i<df.getRowCount();i++){
                tol_weight  +=Double.parseDouble(df.getValueAt(i, 2).toString());
                
            }
            TOTAL_REQUIRED_WEIGHTtxt.setText(""+format("%.3f", tol_weight));
            
            
            
        }catch(Exception ex){
            
           TOTAL_REQUIRED_WEIGHTtxt.setText("0");
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this,ex );
        }
        
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void mainCalculation(){
       try{
        calTableData();
        calAdvance();
        calAdvanceGold();
       }catch(Exception e){
           System.out.println("Error"+e);
       }
    } 
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public boolean submitSideCase(){
        
        if(NAMEtxt.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Name Required");
            return false;
        }
        
        if(TOTAL_REQUIRED_WEIGHTtxt.getText().equals("") || Double.parseDouble(TOTAL_REQUIRED_WEIGHTtxt.getText())<=0){
            JOptionPane.showMessageDialog(this, "Required Weight is zero or invalid");
            return false;
        }
        
        
        if(detailTable.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Empty table\nItem Data Required");
            return false;
        }
        
        return true;
    }
    
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public boolean submitToMainTable() throws SQLException{
        
            boolean flage = false;
            String ID=          IDtxt.getText();
            String name=        NAMEtxt.getText();
            String phone=       PHONEtxt.getText();
            String cnic=        EMAILtxt.getText();
            String rate=        RATEtxt.getText();
            String tweight=     TOTAL_REQUIRED_WEIGHTtxt.getText();
            String tadvance=    TOTAL_ADVANCE_PAYEDtxt.getText();
            String tadvanceGold=TOTAL_ADVANCE_GOLD_WEIGHTtxt.getText();
            String tadvancePasa=TOTAL_ADVANCE_GOLD_PASAtxt.getText();
            String date=        currentdate();
            String returnDate=  RDATEtxt.getText();
            str=con.prepareStatement("INSERT INTO `order_table` (`OID`, `NAME`, `PHONE`, `EMAIL`,`RATE`,`T_WEIGHT`,`T_ADVANCE`,`T_ADVANCE_GOLD`, `T_ADVANCE_PASA`, `DATE`, `RETURN_DATE`) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            str.setString(1,  ID);
            str.setString(2,  name);
            str.setString(3,  phone);
            str.setString(4,  cnic);
            str.setString(5,  rate);
            str.setString(6,  tweight);
            str.setString(7,  tadvance);
            str.setString(8,  tadvanceGold);
            str.setString(9,  tadvancePasa);
            str.setString(10, date);
            str.setString(11, returnDate);
            str.executeUpdate();
            flage=true;
                           
            return flage;
            
            
    }
         
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public boolean submitToChildTable() throws SQLException{
            
            boolean flage=false;
            DefaultTableModel df = (DefaultTableModel)detailTable.getModel();
                for(int i=0;i<df.getRowCount();i++){
                    flage =false;
                    String OID=           IDtxt.getText();
                    String item=         df.getValueAt(i, 0).toString();
                    String qty=         df.getValueAt(i, 1).toString();
                    String weight=       df.getValueAt(i, 2).toString();
                    String size=          df.getValueAt(i, 3).toString();
                    String book=         df.getValueAt(i, 4).toString();
                    String vol=         df.getValueAt(i, 5).toString();
                    String page=        df.getValueAt(i, 6).toString();
                    String itemno=        df.getValueAt(i, 7).toString();
                    String des=        df.getValueAt(i, 8).toString();
                        
                    
                    str=con.prepareStatement("INSERT INTO `order_details`(`OID`,`QTY`,`ITEM`,`WEIGHT`,`SIZE`,`BOOK`,`VOLUME`,`PAGE`,`ITEM_NO`,`DESCRIPTION`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    str.setString(1, OID);
                    str.setString(2, qty);
                    str.setString(3, item);
                    str.setString(4, weight);
                    str.setString(5, size);
                    str.setString(6, book);
                    str.setString(7, vol);
                    str.setString(8, page);
                    str.setString(9, itemno);
                    str.setString(10, des);
                    str.executeUpdate();
                    flage=true ;
                }
            
           
            return flage;
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public boolean submitAdvancePayment() throws SQLException{
            
            boolean flage=false;
            DefaultTableModel df = (DefaultTableModel)advanceTable.getModel();
                for(int i=0;i<df.getRowCount();i++){
                    flage =false;
                    String OID       =   IDtxt.getText();
                    String advance   =   df.getValueAt(i, 0).toString();
                    String date      =   df.getValueAt(i, 1).toString();
                    
                    str=con.prepareStatement("INSERT INTO `order_advance_money`(`OID`,`ADVANCE`,`PAYMENT_DATE`) VALUES (?,?,?)");
                    str.setString(1, OID);
                    str.setString(2, advance);
                    str.setString(3, date);
                  
                    str.executeUpdate();
                    flage=true ;
                }
            
            
            return flage;
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public boolean submitAdvanceGold() throws SQLException{
        
            boolean flage = false;
            String OID=         IDtxt.getText();
            String weight=      RECIVED_GOLD_WEIGHTtxt.getText();
            String karat=       RECIVED_GOLD_KARATtxt.getText();
            String kaat=        RECIVED_GOLD_KAATtxt.getText();
            String pasa=        RECIVED_GOLD_PASAtxt.getText();
            
            str=con.prepareStatement("INSERT INTO `order_advance_gold` (`OID`, `GOLD_WEIGHT`, `KARAT`, `KAAT`,`PASA`) VALUES(?,?,?,?,?)");
            str.setString(1,  OID);
            str.setString(2,  weight);
            str.setString(3,  karat);
            str.setString(4,  kaat);
            str.setString(5,  pasa);
            str.executeUpdate();
            flage=true;
                               
            return flage;
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public boolean submit() throws SQLException{
        if(submitSideCase()==false){
            return false;
        }
        if(checkIfIdExist(IDtxt.getText())){
            JOptionPane.showMessageDialog(this,"ERROR :- Duplicate Id");
            return false;
        }
        if(submitToMainTable()==true){
            submitToChildTable();
            submitAdvancePayment();
            submitAdvanceGold();
            JOptionPane.showMessageDialog(this,"ORDER SAVED");
            return true;
        }
        JOptionPane.showMessageDialog(this,"ERROR");
        return false;
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void refresh(){
        dispose();
        new OrderSystem().setVisible(true);
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public boolean Insertdes(String data){
        try{      
            str=con.prepareStatement ("INSERT INTO `descriptions`(`DESCRIPTION`) VALUES ('"+data+"')");
            str.execute();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
     //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void AddNewDes(String field){
     
        
        String newdata=JOptionPane.showInputDialog(this,"ENTER NEW "+field+"");
        
        if (newdata==null){
                JOptionPane.showMessageDialog(this,"REQUEST CANCELLED");
                return;
        }
        
        if(newdata.equals("")){
            JOptionPane.showMessageDialog(this, "ERROR :- Fields empty\n","ERROR",2);
            return;
        }
        
        boolean check = Insertdes(newdata);
        if(check==true){
            JOptionPane.showMessageDialog(this, "SAVED\n"+field+" ( "+newdata+" )","SAVED",1);
            getDESCRIPTION();
            return;
        }
        
       
        JOptionPane.showMessageDialog(this, "ERROR :- database error","ERROR",2);
        
        
    }
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public void updateToMainTbl (String id) throws SQLException{
            
            String name=NAMEtxt.getText();
            String phone=PHONEtxt.getText();
            String cnic=EMAILtxt.getText();
            String rate=RATEtxt.getText();
            String tweight=TOTAL_REQUIRED_WEIGHTtxt.getText();
            String advance=TOTAL_ADVANCE_PAYEDtxt.getText();
            String tolweight=TOTAL_ADVANCE_GOLD_WEIGHTtxt.getText();
            String tolpasa=TOTAL_ADVANCE_GOLD_PASAtxt.getText();
            String rdate=RDATEtxt.getText();
            String cdate=currentdate();
            
            
            str= con.prepareStatement("UPDATE `order_table` SET `NAME`=?,`PHONE`=?,`EMAIL`=?,`RATE`=?,`T_WEIGHT`=?,`T_ADVANCE`=?,`T_ADVANCE_GOLD`=?,`T_ADVANCE_PASA`=?,`RETURN_DATE`=?  WHERE OID="+id);
        
            str.setString(1,  name);                
            str.setString(2,  phone);
            str.setString(3,  cnic);
            str.setString(4,  rate);
            str.setString(5,  tweight);
            str.setString(6,  advance);
            str.setString(7,  tolweight);
            str.setString(8,  tolpasa);
            str.setString(9, rdate);
            
            
            
            str.executeUpdate();    
            
       
        updateSubTable(id);
        JOptionPane.showMessageDialog(this, "UPDATED","update",1);
    }
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    
    public void updateSubTable(String id) throws SQLException{
        str=con.prepareStatement("DELETE FROM `order_details` WHERE OID="+id);
        str.executeUpdate();
        str=con.prepareStatement("DELETE FROM `order_advance_money` WHERE OID="+id);
        str.executeUpdate();
        
        submitToChildTable();
        submitAdvancePayment();
        updateAdvanceGold(id);
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void updateAdvanceGold(String id)throws SQLException{
            String weight=RECIVED_GOLD_WEIGHTtxt.getText();
            String karat=RECIVED_GOLD_KARATtxt.getText();
            String kaat=RECIVED_GOLD_KAATtxt.getText();
            String pasa=RECIVED_GOLD_PASAtxt.getText();
            
            str= con.prepareStatement("UPDATE `order_advance_gold` SET `GOLD_WEIGHT`=?,`KARAT`=?,`KAAT`=?,`PASA`=? WHERE OID="+id);
            str.setString(1,  weight);                
            str.setString(2,  karat);
            str.setString(3,  kaat);
            str.setString(4,  pasa);
            str.executeUpdate();    
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public boolean update(){
        if(submitSideCase()==false){
            return false;
        }
        try{
            updateToMainTbl(IDtxt.getText());
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR\nEXCEPTION :-"+ex.getMessage(),"ERROR",2);
            return false;
        }
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void delete(String id) throws SQLException{
        str = con.prepareStatement("DELETE FROM `order_table` WHERE OID="+id);
        str.executeUpdate();
        str = con.prepareStatement("DELETE FROM `order_details` WHERE OID="+id);
        str.executeUpdate();
        str = con.prepareStatement("DELETE FROM `order_advance_gold` WHERE OID="+id);
        str.executeUpdate();
        str = con.prepareStatement("DELETE FROM `order_advance_money` WHERE OID="+id);
        str.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record Deleted","Deleted",2);
        refresh();
    }
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
      
    public boolean checkIfIdExist(String id) throws SQLException{
        str = con.prepareStatement("select* from order_table where oid="+id);
        rs=str.executeQuery();
        if(rs.next()){
           return true;
        }
        return false;
    }
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public String CreateEmailText(){
        String text="Order ID : "+IDtxt.getText()+"\n"
                   +"Name     : "+NAMEtxt.getText()+"\n"
                   +"Email      : "+EMAILtxt.getText()+"\n"
                   +"Date        : "+currentdate()+"\n"
                   +"_________________________________________\n"
                   +"item\t       qty\t\tweight\n"
                   +"_________________________________________\n";
                DefaultTableModel df = (DefaultTableModel)detailTable.getModel();
                for(int i=0;i<df.getRowCount();i++){
                    String item       =df.getValueAt(i, 0).toString(); ;
                    String qty   =   df.getValueAt(i, 1).toString();
                    String weight      =   df.getValueAt(i, 2).toString();
                    
                    text=text
                        +"("+item+")\t       "+weight+"\t\t"+qty+"\n"
                        +"------------------------------------------------------------------\n";
                    
                }
                if(Integer.parseInt(RATEtxt.getText())==0 || RATEtxt.getText().equals("") ){
                    text=text
                        +"\t\tRate Done\t\t: Not Done\n";
                }else if(Integer.parseInt(RATEtxt.getText())>0){
                    text=text
                        +"\t\tRate Done\t\t: "+RATEtxt.getText()+"\n";
                }
                
                text=text
                    +"\t\tRequired Weight    : "+TOTAL_REQUIRED_WEIGHTtxt.getText()+"\n"
                    +"\t\tAdvance money     : "+TOTAL_ADVANCE_PAYEDtxt.getText()+"\n"
                    +"\t\tAdvance Gold         : "+TOTAL_ADVANCE_GOLD_PASAtxt.getText()+"\n"
                    +"_________________________________________\n"
                    +"\t\tThank You For Your Order";
        JOptionPane.showMessageDialog(this, text);
        return text;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public void addtolist(){
         try{
            String item  = select_item.getSelectedItem().toString();
            String qty = QTYtxt.getText();
            String wazan = ITEM_WEIGHTtxt.getText();
            String size   = ITEM_SIZEtxt.getText();
            String book  = select_books.getSelectedItem().toString();
            String vol  = BOOK_VOLUMEtxt.getText();
            String page = BOOK_PAGEtxt.getText();
            String item_no= BOOK_ITEM_NOtxt.getText();
            String des = select_description.getSelectedItem().toString();
            

            if(item.equals("NONE") || item.equals("")){
                JOptionPane.showMessageDialog(this,"ITEM="+item+"\nITEM NOT SELECTED\nPLEASE SELECT AN ITEM","ERROR",2);
                return;
            }

            if(Double.parseDouble(wazan)<=0 || wazan == null){
                JOptionPane.showMessageDialog(this, "PLEASE ENTER WEIGHT(wazan = 0)","ERROR",2);
                return;
            }
            
            
            if(Integer.parseInt(qty)<=0 || qty.equals("")){
                JOptionPane.showMessageDialog(this, "PLEASE ENTER Quantity (QTY = 0)","ERROR",2);
                return;
            }
                        
            if((select_books.getSelectedItem().equals("NONE") ||select_books.getSelectedItem()== null) && (select_description.getSelectedItem().equals("NONE") ||select_description.getSelectedItem()== null) ){
                JOptionPane.showMessageDialog(this, "BOOK = NONE\nDESCRIPTION = NONE","ERROR",2);
                return;
            }
            
            if(select_books.getSelectedItem()!="NONE" && select_description.getSelectedItem().equals("NONE")){
                System.out.println(select_books.getSelectedItem().toString()+".");
                if(BOOK_ITEM_NOtxt.getText().equals("0") || BOOK_PAGEtxt.getText().equals("0") || BOOK_VOLUMEtxt.getText().equals("0")){
                    JOptionPane.showMessageDialog(this, "Book detail required","ERROR",2);
                    return;
                }
            }
            
            

            String [] row={item,qty,wazan,size,book,vol,page,item_no,des};
            addRowToTable(row, detailTable);

            clearItemSection();
            calTableData();
                        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "error in data entry","ERROR",2);
            TOTAL_REQUIRED_WEIGHTtxt.setText("0");
        }
    }
    
    
    
    
    
    
    
    
    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void sendEmail(){
        usermodel user = new usermodel();
        
        if(EMAILtxt.getText().equals("")){
            return;
        }
        
        int varify = JOptionPane.showConfirmDialog(this, "Do you want to send EMAIL to ( "+EMAILtxt.getText()+" )","confirmation", JOptionPane.YES_NO_OPTION);
        if(varify==0){
            new EmailSend().SendEmail("ORDER INVOICE", CreateEmailText(), EMAILtxt.getText(),user.getEmail(),this);
        }
        
        
    }
    
    
    
    public void nextFoucas(KeyEvent evt,JTextField next){
          // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
               next .requestFocus();
         }  
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableCellEditor1 = new ALL.TableCellEditor();
        MainPanel = new panal.bgpanal();
        pane11 = new panal.pane1();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        RDATEtxt = new ALL_UI_1.TextField();
        IDtxt = new ALL_UI_1.TextField();
        NAMEtxt = new ALL_UI_1.TextField();
        PHONEtxt = new ALL_UI_1.TextField();
        EMAILtxt = new ALL_UI_1.TextField();
        RATEtxt = new ALL_UI_1.TextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        SEARCHtxt = new ALL_UI_1.TextField();
        button10 = new ALL_UI_1.Button();
        DATE = new ALL_UI_1.TextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pane21 = new panal.pane2();
        jLabel29 = new javax.swing.JLabel();
        button4 = new ALL_UI_1.Button();
        pane12 = new panal.pane1();
        select_books = new ALL.Combobox();
        ITEM_WEIGHTtxt = new ALL_UI_1.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        select_description = new ALL.Combobox();
        BOOK_VOLUMEtxt = new ALL_UI_1.TextField();
        jLabel11 = new javax.swing.JLabel();
        BOOK_PAGEtxt = new ALL_UI_1.TextField();
        jLabel12 = new javax.swing.JLabel();
        BOOK_ITEM_NOtxt = new ALL_UI_1.TextField();
        jLabel13 = new javax.swing.JLabel();
        select_item = new ALL.Combobox();
        jLabel14 = new javax.swing.JLabel();
        button1 = new ALL_UI_1.Button();
        button2 = new ALL_UI_1.Button();
        button5 = new ALL_UI_1.Button();
        ITEM_SIZEtxt = new ALL_UI_1.TextField();
        jLabel27 = new javax.swing.JLabel();
        QTYtxt = new ALL_UI_1.TextField();
        jLabel31 = new javax.swing.JLabel();
        pane13 = new panal.pane1();
        jScrollPane1 = new javax.swing.JScrollPane();
        advanceTable = new panal.Table2();
        button3 = new ALL_UI_1.Button();
        button11 = new ALL_UI_1.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailTable = new panal.Table2();
        pane14 = new panal.pane1();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        RECIVED_GOLD_WEIGHTtxt = new ALL_UI_1.TextField();
        jLabel19 = new javax.swing.JLabel();
        RECIVED_GOLD_KARATtxt = new ALL_UI_1.TextField();
        jLabel20 = new javax.swing.JLabel();
        RECIVED_GOLD_KAATtxt = new ALL_UI_1.TextField();
        jLabel21 = new javax.swing.JLabel();
        RECIVED_GOLD_PASAtxt = new ALL_UI_1.TextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        TOTAL_REQUIRED_WEIGHTtxt = new ALL_UI_1.TextField();
        jLabel23 = new javax.swing.JLabel();
        TOTAL_ADVANCE_GOLD_WEIGHTtxt = new ALL_UI_1.TextField();
        jLabel24 = new javax.swing.JLabel();
        TOTAL_ADVANCE_PAYEDtxt = new ALL_UI_1.TextField();
        jLabel25 = new javax.swing.JLabel();
        TOTAL_ADVANCE_GOLD_PASAtxt = new ALL_UI_1.TextField();
        savebtn = new ALL_UI_1.Button();
        button7 = new ALL_UI_1.Button();
        button8 = new ALL_UI_1.Button();
        button9 = new ALL_UI_1.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        button12 = new ALL_UI_1.Button();
        jSeparator5 = new javax.swing.JSeparator();
        button6 = new ALL_UI_1.Button();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ORDER INVOICE");
        jLabel2.setToolTipText("");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pane11.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 400, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ID#");
        pane11.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 110, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NAME");
        pane11.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 280, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PHONE");
        pane11.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 170, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EMAIL");
        pane11.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 160, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("RATE");
        pane11.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 130, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("RETURN DATE");
        pane11.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 30));

        RDATEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RDATEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RDATEtxt.setRound(40);
        RDATEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        RDATEtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        RDATEtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDATEtxtActionPerformed(evt);
            }
        });
        RDATEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RDATEtxtKeyReleased(evt);
            }
        });
        pane11.add(RDATEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, 50));

        IDtxt.setEditable(false);
        IDtxt.setBackground(new java.awt.Color(255, 204, 204));
        IDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        IDtxt.setRound(40);
        IDtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        IDtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDtxtActionPerformed(evt);
            }
        });
        pane11.add(IDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 110, 50));

        NAMEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NAMEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAMEtxt.setRound(40);
        NAMEtxt.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        NAMEtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        NAMEtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAMEtxtActionPerformed(evt);
            }
        });
        NAMEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NAMEtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NAMEtxtKeyTyped(evt);
            }
        });
        pane11.add(NAMEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 280, 50));

        PHONEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PHONEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PHONEtxt.setRound(40);
        PHONEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        PHONEtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        PHONEtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PHONEtxtActionPerformed(evt);
            }
        });
        PHONEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PHONEtxtKeyReleased(evt);
            }
        });
        pane11.add(PHONEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 170, 50));

        EMAILtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        EMAILtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EMAILtxt.setRound(40);
        EMAILtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        EMAILtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        EMAILtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMAILtxtActionPerformed(evt);
            }
        });
        EMAILtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EMAILtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EMAILtxtKeyTyped(evt);
            }
        });
        pane11.add(EMAILtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 160, 50));

        RATEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RATEtxt.setText("0");
        RATEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RATEtxt.setRound(40);
        RATEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        RATEtxt.setShadowColor(new java.awt.Color(255, 51, 51));
        RATEtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RATEtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                RATEtxtFocusLost(evt);
            }
        });
        RATEtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RATEtxtActionPerformed(evt);
            }
        });
        RATEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RATEtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RATEtxtKeyTyped(evt);
            }
        });
        pane11.add(RATEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 130, 50));
        pane11.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1010, 10));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("current date");
        pane11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 210, -1));

        SEARCHtxt.setBackground(new java.awt.Color(204, 255, 255));
        SEARCHtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SEARCHtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SEARCHtxt.setRound(30);
        SEARCHtxt.setShadowColor(new java.awt.Color(0, 0, 0));
        SEARCHtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCHtxtActionPerformed(evt);
            }
        });
        pane11.add(SEARCHtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, 40));

        button10.setBackground(new java.awt.Color(204, 255, 204));
        button10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-search-20.png"))); // NOI18N
        button10.setText("GET");
        button10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button10.setRippleColor(new java.awt.Color(0, 204, 204));
        button10.setRound(30);
        button10.setShadowColor(new java.awt.Color(0, 0, 0));
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        pane11.add(button10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 90, 40));

        DATE.setEditable(false);
        DATE.setBackground(new java.awt.Color(204, 255, 255));
        DATE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DATE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DATE.setRound(30);
        DATE.setShadowColor(new java.awt.Color(0, 0, 0));
        DATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DATEActionPerformed(evt);
            }
        });
        pane11.add(DATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 210, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("example@gmail.com");
        pane11.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 160, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Search by ID");
        pane11.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        MainPanel.add(pane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1010, 180));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("  ORDER INVOICE");
        jLabel29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel29MouseDragged(evt);
            }
        });
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel29MousePressed(evt);
            }
        });
        pane21.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 40));

        button4.setBackground(new java.awt.Color(204, 255, 204));
        button4.setText("X");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(255, 0, 0));
        button4.setRound(30);
        button4.setShadowColor(new java.awt.Color(0, 0, 0));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        pane21.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 40, 40));

        MainPanel.add(pane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 40));

        select_books.setToolTipText("");
        select_books.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        select_books.setLabeText("SELECT BOOK");
        select_books.setLineColor(new java.awt.Color(255, 0, 0));
        select_books.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_booksActionPerformed(evt);
            }
        });
        select_books.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                select_booksKeyReleased(evt);
            }
        });
        pane12.add(select_books, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 200, -1));

        ITEM_WEIGHTtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ITEM_WEIGHTtxt.setText("0");
        ITEM_WEIGHTtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ITEM_WEIGHTtxt.setRound(40);
        ITEM_WEIGHTtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        ITEM_WEIGHTtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        ITEM_WEIGHTtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ITEM_WEIGHTtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ITEM_WEIGHTtxtFocusLost(evt);
            }
        });
        ITEM_WEIGHTtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ITEM_WEIGHTtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ITEM_WEIGHTtxtKeyTyped(evt);
            }
        });
        pane12.add(ITEM_WEIGHTtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 110, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DISCRIPTION");
        pane12.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 500, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SIZE");
        pane12.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 130, -1));

        select_description.setToolTipText("");
        select_description.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        select_description.setLabeText("");
        select_description.setLineColor(new java.awt.Color(255, 51, 51));
        select_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_descriptionActionPerformed(evt);
            }
        });
        select_description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                select_descriptionKeyReleased(evt);
            }
        });
        pane12.add(select_description, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 500, 60));

        BOOK_VOLUMEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BOOK_VOLUMEtxt.setText("0");
        BOOK_VOLUMEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BOOK_VOLUMEtxt.setRound(40);
        BOOK_VOLUMEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        BOOK_VOLUMEtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        BOOK_VOLUMEtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BOOK_VOLUMEtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BOOK_VOLUMEtxtFocusLost(evt);
            }
        });
        BOOK_VOLUMEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BOOK_VOLUMEtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BOOK_VOLUMEtxtKeyTyped(evt);
            }
        });
        pane12.add(BOOK_VOLUMEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 80, 50));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("VOLUME");
        pane12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 80, -1));

        BOOK_PAGEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BOOK_PAGEtxt.setText("0");
        BOOK_PAGEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BOOK_PAGEtxt.setRound(40);
        BOOK_PAGEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        BOOK_PAGEtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        BOOK_PAGEtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BOOK_PAGEtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BOOK_PAGEtxtFocusLost(evt);
            }
        });
        BOOK_PAGEtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOOK_PAGEtxtActionPerformed(evt);
            }
        });
        BOOK_PAGEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BOOK_PAGEtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BOOK_PAGEtxtKeyTyped(evt);
            }
        });
        pane12.add(BOOK_PAGEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 80, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("PAGE");
        pane12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, -1));

        BOOK_ITEM_NOtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BOOK_ITEM_NOtxt.setText("0");
        BOOK_ITEM_NOtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BOOK_ITEM_NOtxt.setRound(40);
        BOOK_ITEM_NOtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        BOOK_ITEM_NOtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        BOOK_ITEM_NOtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BOOK_ITEM_NOtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BOOK_ITEM_NOtxtFocusLost(evt);
            }
        });
        BOOK_ITEM_NOtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOOK_ITEM_NOtxtActionPerformed(evt);
            }
        });
        BOOK_ITEM_NOtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BOOK_ITEM_NOtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BOOK_ITEM_NOtxtKeyTyped(evt);
            }
        });
        pane12.add(BOOK_ITEM_NOtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 50));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ITEM #");
        pane12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        select_item.setToolTipText("");
        select_item.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        select_item.setLabeText("SELECT ITEM");
        select_item.setLineColor(new java.awt.Color(255, 51, 51));
        select_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                select_itemKeyReleased(evt);
            }
        });
        pane12.add(select_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 200, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("WEIGHT");
        pane12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 110, -1));

        button1.setBackground(new java.awt.Color(255, 204, 204));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-clear-50.png"))); // NOI18N
        button1.setText("CLEAR");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setRippleColor(new java.awt.Color(0, 0, 0));
        button1.setRound(60);
        button1.setShadowColor(new java.awt.Color(255, 51, 51));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        pane12.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 70));

        button2.setBackground(new java.awt.Color(255, 255, 204));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-add-64.png"))); // NOI18N
        button2.setText("ADD TO LIST");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        button2.setRippleColor(new java.awt.Color(0, 0, 0));
        button2.setRound(60);
        button2.setShadowColor(new java.awt.Color(255, 255, 0));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        button2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                button2KeyReleased(evt);
            }
        });
        pane12.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 190, 70));

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-add-64.png"))); // NOI18N
        button5.setRippleColor(new java.awt.Color(0, 0, 0));
        button5.setRound(60);
        button5.setShadowColor(new java.awt.Color(0, 0, 0));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        pane12.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 70, 70));

        ITEM_SIZEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ITEM_SIZEtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ITEM_SIZEtxt.setRound(40);
        ITEM_SIZEtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        ITEM_SIZEtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        ITEM_SIZEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ITEM_SIZEtxtKeyReleased(evt);
            }
        });
        pane12.add(ITEM_SIZEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 130, 50));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("add new description");
        pane12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 120, -1));

        QTYtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        QTYtxt.setText("0");
        QTYtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QTYtxt.setRound(40);
        QTYtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        QTYtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        QTYtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                QTYtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                QTYtxtFocusLost(evt);
            }
        });
        QTYtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QTYtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QTYtxtKeyTyped(evt);
            }
        });
        pane12.add(QTYtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 80, 50));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("QTY");
        pane12.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 80, -1));

        MainPanel.add(pane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 1010, 190));

        advanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RECIVED", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        advanceTable.setRowHeight(20);
        advanceTable.setShowGrid(true);
        advanceTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                advanceTableFocusLost(evt);
            }
        });
        advanceTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                advanceTableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                advanceTableKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(advanceTable);

        pane13.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 240, 200));

        button3.setBackground(new java.awt.Color(255, 204, 204));
        button3.setText("REMOVE PAYMET");
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(255, 0, 0));
        button3.setRound(30);
        button3.setShadowColor(new java.awt.Color(255, 51, 51));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        pane13.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 240, 40));

        button11.setBackground(new java.awt.Color(204, 255, 204));
        button11.setText("ADD NEW PAYMENT");
        button11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button11.setRippleColor(new java.awt.Color(0, 204, 204));
        button11.setRound(30);
        button11.setShadowColor(new java.awt.Color(204, 255, 255));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        pane13.add(button11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 240, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Press ( ENTER )  after every PAYMENT entry");
        pane13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 259, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("RECIVED ADVANCE PAYMETS");
        pane13.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 20));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Note");
        pane13.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 240, 20));
        pane13.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 10));

        MainPanel.add(pane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, 260, 380));

        detailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "QTY", "WEIGHT", "SIZE", "BOOK", "VOLUME", "PAGE", "ITEM", "DESCRIPTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailTable.setRowHeight(20);
        detailTable.setShowGrid(true);
        detailTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                detailTableAncestorRemoved(evt);
            }
        });
        detailTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                detailTableFocusLost(evt);
            }
        });
        detailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailTableMouseClicked(evt);
            }
        });
        detailTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                detailTableInputMethodTextChanged(evt);
            }
        });
        jScrollPane2.setViewportView(detailTable);

        MainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 1010, 230));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("GOLD WEIGHT");
        pane14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("RECIVED ADVANCE GOLD");
        pane14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 20));

        RECIVED_GOLD_WEIGHTtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RECIVED_GOLD_WEIGHTtxt.setText("0");
        RECIVED_GOLD_WEIGHTtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RECIVED_GOLD_WEIGHTtxt.setRound(40);
        RECIVED_GOLD_WEIGHTtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        RECIVED_GOLD_WEIGHTtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        RECIVED_GOLD_WEIGHTtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RECIVED_GOLD_WEIGHTtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                RECIVED_GOLD_WEIGHTtxtFocusLost(evt);
            }
        });
        RECIVED_GOLD_WEIGHTtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RECIVED_GOLD_WEIGHTtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RECIVED_GOLD_WEIGHTtxtKeyTyped(evt);
            }
        });
        pane14.add(RECIVED_GOLD_WEIGHTtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 240, 50));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("KARAT");
        pane14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 240, -1));

        RECIVED_GOLD_KARATtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RECIVED_GOLD_KARATtxt.setText("0");
        RECIVED_GOLD_KARATtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RECIVED_GOLD_KARATtxt.setRound(40);
        RECIVED_GOLD_KARATtxt.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        RECIVED_GOLD_KARATtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        RECIVED_GOLD_KARATtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RECIVED_GOLD_KARATtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                RECIVED_GOLD_KARATtxtFocusLost(evt);
            }
        });
        RECIVED_GOLD_KARATtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RECIVED_GOLD_KARATtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RECIVED_GOLD_KARATtxtKeyTyped(evt);
            }
        });
        pane14.add(RECIVED_GOLD_KARATtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("CUT");
        pane14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 240, 20));

        RECIVED_GOLD_KAATtxt.setEditable(false);
        RECIVED_GOLD_KAATtxt.setBackground(new java.awt.Color(204, 204, 204));
        RECIVED_GOLD_KAATtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RECIVED_GOLD_KAATtxt.setText("0");
        RECIVED_GOLD_KAATtxt.setFocusable(false);
        RECIVED_GOLD_KAATtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RECIVED_GOLD_KAATtxt.setRound(40);
        RECIVED_GOLD_KAATtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        pane14.add(RECIVED_GOLD_KAATtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 240, 50));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("PURE GOLD");
        pane14.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 240, 20));

        RECIVED_GOLD_PASAtxt.setEditable(false);
        RECIVED_GOLD_PASAtxt.setBackground(new java.awt.Color(204, 204, 204));
        RECIVED_GOLD_PASAtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RECIVED_GOLD_PASAtxt.setText("0");
        RECIVED_GOLD_PASAtxt.setFocusable(false);
        RECIVED_GOLD_PASAtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RECIVED_GOLD_PASAtxt.setRound(40);
        RECIVED_GOLD_PASAtxt.setShadowColor(new java.awt.Color(255, 255, 0));
        pane14.add(RECIVED_GOLD_PASAtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 240, 50));
        pane14.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 10));

        MainPanel.add(pane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 440, 260, 300));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("REQUIRED WEIGHT");
        MainPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 160, 20));

        TOTAL_REQUIRED_WEIGHTtxt.setEditable(false);
        TOTAL_REQUIRED_WEIGHTtxt.setBackground(new java.awt.Color(255, 204, 204));
        TOTAL_REQUIRED_WEIGHTtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TOTAL_REQUIRED_WEIGHTtxt.setText("0");
        TOTAL_REQUIRED_WEIGHTtxt.setFocusable(false);
        TOTAL_REQUIRED_WEIGHTtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TOTAL_REQUIRED_WEIGHTtxt.setRound(40);
        TOTAL_REQUIRED_WEIGHTtxt.setShadowColor(new java.awt.Color(0, 0, 0));
        TOTAL_REQUIRED_WEIGHTtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOTAL_REQUIRED_WEIGHTtxtActionPerformed(evt);
            }
        });
        MainPanel.add(TOTAL_REQUIRED_WEIGHTtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, 160, 50));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("ADVANCE GOLD WEIGHT");
        MainPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 750, 160, 20));

        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setEditable(false);
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setBackground(new java.awt.Color(255, 204, 204));
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setText("0");
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setFocusable(false);
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setRound(40);
        TOTAL_ADVANCE_GOLD_WEIGHTtxt.setShadowColor(new java.awt.Color(0, 0, 0));
        MainPanel.add(TOTAL_ADVANCE_GOLD_WEIGHTtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 770, 160, 50));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ADVANCE PAYMENT");
        MainPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 680, 160, 20));

        TOTAL_ADVANCE_PAYEDtxt.setEditable(false);
        TOTAL_ADVANCE_PAYEDtxt.setBackground(new java.awt.Color(255, 204, 204));
        TOTAL_ADVANCE_PAYEDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TOTAL_ADVANCE_PAYEDtxt.setText("0");
        TOTAL_ADVANCE_PAYEDtxt.setFocusable(false);
        TOTAL_ADVANCE_PAYEDtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TOTAL_ADVANCE_PAYEDtxt.setRound(40);
        TOTAL_ADVANCE_PAYEDtxt.setShadowColor(new java.awt.Color(0, 0, 0));
        TOTAL_ADVANCE_PAYEDtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOTAL_ADVANCE_PAYEDtxtActionPerformed(evt);
            }
        });
        MainPanel.add(TOTAL_ADVANCE_PAYEDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 700, 160, 50));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("ADVANCE PURE GOLD");
        MainPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 760, 160, 10));

        TOTAL_ADVANCE_GOLD_PASAtxt.setEditable(false);
        TOTAL_ADVANCE_GOLD_PASAtxt.setBackground(new java.awt.Color(255, 204, 204));
        TOTAL_ADVANCE_GOLD_PASAtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TOTAL_ADVANCE_GOLD_PASAtxt.setText("0");
        TOTAL_ADVANCE_GOLD_PASAtxt.setFocusable(false);
        TOTAL_ADVANCE_GOLD_PASAtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TOTAL_ADVANCE_GOLD_PASAtxt.setRound(40);
        TOTAL_ADVANCE_GOLD_PASAtxt.setShadowColor(new java.awt.Color(0, 0, 0));
        MainPanel.add(TOTAL_ADVANCE_GOLD_PASAtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 770, 160, 50));

        savebtn.setBackground(new java.awt.Color(204, 255, 204));
        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-submit-48.png"))); // NOI18N
        savebtn.setText("SAVE");
        savebtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        savebtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        savebtn.setRippleColor(new java.awt.Color(0, 255, 204));
        savebtn.setRound(40);
        savebtn.setShadowColor(new java.awt.Color(0, 0, 0));
        savebtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        MainPanel.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 700, 130, 120));

        button7.setBackground(new java.awt.Color(204, 255, 204));
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-print-64.png"))); // NOI18N
        button7.setText("PRINT");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button7.setRippleColor(new java.awt.Color(51, 51, 255));
        button7.setRound(40);
        button7.setShadowColor(new java.awt.Color(0, 0, 0));
        button7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        MainPanel.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 700, 130, 120));

        button8.setBackground(new java.awt.Color(204, 255, 204));
        button8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-refresh-60.png"))); // NOI18N
        button8.setText("NEW");
        button8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button8.setRippleColor(new java.awt.Color(255, 51, 51));
        button8.setRound(40);
        button8.setShadowColor(new java.awt.Color(0, 0, 0));
        button8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        MainPanel.add(button8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 700, 130, 120));

        button9.setBackground(new java.awt.Color(204, 255, 204));
        button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-calculate-50.png"))); // NOI18N
        button9.setText("CALCULATE");
        button9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button9.setRippleColor(new java.awt.Color(0, 0, 0));
        button9.setRound(40);
        button9.setShadowColor(new java.awt.Color(0, 0, 0));
        button9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        MainPanel.add(button9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, 130, 120));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MainPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 680, 10, 150));
        MainPanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 687, 650, 10));

        button12.setBackground(new java.awt.Color(255, 204, 204));
        button12.setForeground(new java.awt.Color(0, 0, 0));
        button12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-trash-can-50.png"))); // NOI18N
        button12.setText("DELETE");
        button12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button12.setRippleColor(new java.awt.Color(255, 51, 51));
        button12.setRound(40);
        button12.setShadowColor(new java.awt.Color(0, 0, 0));
        button12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        MainPanel.add(button12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 700, 130, 120));
        MainPanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 820, 920, 10));

        button6.setBackground(new java.awt.Color(236, 233, 233));
        button6.setForeground(new java.awt.Color(0, 0, 0));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-database-syncing-complete-local-drive-and-connected-with-other-pc-48.png"))); // NOI18N
        button6.setText("HISTORY");
        button6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button6.setRippleColor(new java.awt.Color(102, 102, 102));
        button6.setShadowColor(new java.awt.Color(0, 0, 0));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        MainPanel.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 750, 260, 70));
        MainPanel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 750, 340, 10));

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel29MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseDragged
        // TODO add your handling code here:

        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);

    }//GEN-LAST:event_jLabel29MouseDragged

    private void jLabel29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MousePressed
        // TODO add your handling code here:

        xMouse = evt.getX();
        yMouse= evt.getY();

    }//GEN-LAST:event_jLabel29MousePressed

    private void RDATEtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDATEtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RDATEtxtActionPerformed

    private void IDtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDtxtActionPerformed

    private void NAMEtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAMEtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NAMEtxtActionPerformed

    private void PHONEtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PHONEtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PHONEtxtActionPerformed

    private void EMAILtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMAILtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMAILtxtActionPerformed

    private void RATEtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RATEtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RATEtxtActionPerformed

    private void select_booksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_booksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_booksActionPerformed

    private void BOOK_PAGEtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOOK_PAGEtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOOK_PAGEtxtActionPerformed

    private void BOOK_ITEM_NOtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOOK_ITEM_NOtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOOK_ITEM_NOtxtActionPerformed

    private void select_descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_descriptionActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        clearItemSection();
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel  df = (DefaultTableModel)advanceTable.getModel();
            int r=advanceTable.getSelectedRow();
            df.removeRow(r);
            calAdvance();
        }catch(Exception e){
            System.out.println("error"+e);
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        // TODO add your handling code here:
        
        AddNewDes("DESCRIPTION");
    }//GEN-LAST:event_button5ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        // TODO add your handling code here:
        mainCalculation();
    }//GEN-LAST:event_button9ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        HOME.kye=0;
        dispose();
    }//GEN-LAST:event_button4ActionPerformed

    private void SEARCHtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEARCHtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEARCHtxtActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        // TODO add your handling code here:
       
        String ID=SEARCHtxt.getText();
        try {
            
            getDataFromMainTbl(ID);
            
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this,"ERROR: can not get data\n","Error",2);
        }
        
    }//GEN-LAST:event_button10ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel  df = (DefaultTableModel)advanceTable.getModel();
            String []rowData={"0",currentdate()};
            df.addRow(rowData);
            advanceTable.clearSelection();
            calAdvance();
        }catch(Exception e){
            TOTAL_ADVANCE_PAYEDtxt.setText("0");
            System.out.println("error"+e);
        }
    }//GEN-LAST:event_button11ActionPerformed

    private void advanceTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advanceTableKeyTyped
        // TODO add your handling code here:
        
       
        calAdvance();
    }//GEN-LAST:event_advanceTableKeyTyped

    private void advanceTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advanceTableKeyReleased
        // TODO add your handling code here:
        calAdvance();
        
    }//GEN-LAST:event_advanceTableKeyReleased

    private void NAMEtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NAMEtxtKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NAMEtxtKeyTyped

    private void RECIVED_GOLD_WEIGHTtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_WEIGHTtxtKeyReleased
        // TODO add your handling code here:
        try{
            calAdvanceGold();
        }catch(Exception e){
            System.out.println("ERROR "+e);
            RECIVED_GOLD_KAATtxt.setText("0");
            RECIVED_GOLD_PASAtxt.setText("0");


            TOTAL_ADVANCE_GOLD_WEIGHTtxt.setText("0");
            TOTAL_ADVANCE_GOLD_PASAtxt.setText("0");
        }
        
    }//GEN-LAST:event_RECIVED_GOLD_WEIGHTtxtKeyReleased

    private void RECIVED_GOLD_KARATtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_KARATtxtKeyReleased
        // TODO add your handling code here:
          // TODO add your handling code here:
        try{
            calAdvanceGold();
        }catch(Exception e){
            System.out.println("ERROR "+e);
            RECIVED_GOLD_KAATtxt.setText("0");
            RECIVED_GOLD_PASAtxt.setText("0");


            TOTAL_ADVANCE_GOLD_WEIGHTtxt.setText("0");
            TOTAL_ADVANCE_GOLD_PASAtxt.setText("0");
        }
    }//GEN-LAST:event_RECIVED_GOLD_KARATtxtKeyReleased

    private void TOTAL_REQUIRED_WEIGHTtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOTAL_REQUIRED_WEIGHTtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTAL_REQUIRED_WEIGHTtxtActionPerformed

    private void TOTAL_ADVANCE_PAYEDtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOTAL_ADVANCE_PAYEDtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTAL_ADVANCE_PAYEDtxtActionPerformed

    private void RECIVED_GOLD_WEIGHTtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_WEIGHTtxtKeyTyped
        // TODO add your handling code here:
         new FieldSetting().only_numbers_with_point(evt);
    }//GEN-LAST:event_RECIVED_GOLD_WEIGHTtxtKeyTyped

    private void ITEM_WEIGHTtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ITEM_WEIGHTtxtFocusGained
        // TODO add your handling code here:
        
        new FieldSetting().fieldFoucasGain(ITEM_WEIGHTtxt, 0, "");
    }//GEN-LAST:event_ITEM_WEIGHTtxtFocusGained

    private void ITEM_WEIGHTtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ITEM_WEIGHTtxtFocusLost
        // TODO add your handling code here:
        new FieldSetting().fieldFoucasLost(ITEM_WEIGHTtxt, "", "0");

    }//GEN-LAST:event_ITEM_WEIGHTtxtFocusLost

    private void ITEM_WEIGHTtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ITEM_WEIGHTtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().only_numbers_with_point(evt);

    }//GEN-LAST:event_ITEM_WEIGHTtxtKeyTyped

    private void BOOK_VOLUMEtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_VOLUMEtxtFocusGained
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasGain(BOOK_VOLUMEtxt, 0, "");
    }//GEN-LAST:event_BOOK_VOLUMEtxtFocusGained

    private void BOOK_VOLUMEtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_VOLUMEtxtFocusLost
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasLost(BOOK_VOLUMEtxt, "", "0");
    }//GEN-LAST:event_BOOK_VOLUMEtxtFocusLost

    private void BOOK_VOLUMEtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_VOLUMEtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().only_numbers(evt);

    }//GEN-LAST:event_BOOK_VOLUMEtxtKeyTyped

    private void BOOK_PAGEtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_PAGEtxtFocusGained
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasGain(BOOK_PAGEtxt, 0, "");
    }//GEN-LAST:event_BOOK_PAGEtxtFocusGained

    private void BOOK_PAGEtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_PAGEtxtFocusLost
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasLost(BOOK_PAGEtxt, "", "0");
    }//GEN-LAST:event_BOOK_PAGEtxtFocusLost

    private void BOOK_ITEM_NOtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_ITEM_NOtxtFocusGained
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasGain(BOOK_ITEM_NOtxt, 0, "");
      
    }//GEN-LAST:event_BOOK_ITEM_NOtxtFocusGained

    private void BOOK_PAGEtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_PAGEtxtKeyTyped
        // TODO add your handling code here
         new FieldSetting().only_numbers(evt);
    }//GEN-LAST:event_BOOK_PAGEtxtKeyTyped

    private void RATEtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RATEtxtFocusGained
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasGain(RATEtxt, 0, "");
        
    }//GEN-LAST:event_RATEtxtFocusGained

    private void RATEtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RATEtxtFocusLost
        // TODO add your handling code here:
       
         new FieldSetting().fieldFoucasLost(RATEtxt, "", "0");
    }//GEN-LAST:event_RATEtxtFocusLost

    private void RATEtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RATEtxtKeyTyped
        // TODO add your handling code here:
         new FieldSetting().only_numbers(evt);
    }//GEN-LAST:event_RATEtxtKeyTyped

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
       
        addtolist();
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_button2ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_button8ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        // TODO add your handling code here:
        
         try {
            if(checkIfIdExist(IDtxt.getText())==false){
               JOptionPane.showMessageDialog(this, "This ID Dont Exist in database\n","Id not found",2);
               return;
            }

            int varify = JOptionPane.showConfirmDialog(this, "Are You Sure You want to delete","", JOptionPane.YES_NO_OPTION);
            if(varify==0){
                delete(IDtxt.getText());
                return;
            }
            JOptionPane.showMessageDialog(this, "Request Cancelled\n","Action not completed",3);
            
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this, "ERROR while Deleting:-\n"+ex.getMessage(),"ERROR",2);
        }
    }//GEN-LAST:event_button12ActionPerformed

    private void BOOK_ITEM_NOtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BOOK_ITEM_NOtxtFocusLost
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasLost(BOOK_ITEM_NOtxt, "", "0");
    }//GEN-LAST:event_BOOK_ITEM_NOtxtFocusLost

    private void BOOK_ITEM_NOtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_ITEM_NOtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().only_numbers(evt);
    }//GEN-LAST:event_BOOK_ITEM_NOtxtKeyTyped

    private void advanceTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_advanceTableFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_advanceTableFocusLost

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
             mainCalculation();
            if(savebtn.getText().equals("SAVE")){
                if(submit()){
                    MainPanel.disable();
                    sendEmail();
                    MainPanel.enable();
                    refresh();
                }
                return;
            }

            if(savebtn.getText().equals("UPDATE")){
                if(update()){
                    MainPanel.disable();
                    sendEmail();
                    MainPanel.enable();
                    refresh();
                }
                
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this, "ERROR\n"+ex);
            System.out.println("error"+ex);
            Logger.getLogger(OrderSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void detailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailTableMouseClicked
        // TODO add your handling code here:
        
        if(select_item.getSelectedIndex()==0 && Double.parseDouble(ITEM_WEIGHTtxt.getText())==0 && select_books.getSelectedIndex()==0 && select_description.getSelectedIndex()==0){
            dataTofields(evt);
            return;
        }
        JOptionPane.showMessageDialog(this, "PLEASE CLEAR ITEM SECTION");
        
        
    }//GEN-LAST:event_detailTableMouseClicked

    private void DATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DATEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DATEActionPerformed

    private void RECIVED_GOLD_WEIGHTtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_WEIGHTtxtFocusGained
        // TODO add your handling code here:
        new FieldSetting().fieldFoucasGain(RECIVED_GOLD_WEIGHTtxt, 0, "");
    }//GEN-LAST:event_RECIVED_GOLD_WEIGHTtxtFocusGained

    private void RECIVED_GOLD_WEIGHTtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_WEIGHTtxtFocusLost
        // TODO add your handling code here:
        new FieldSetting().fieldFoucasLost(RECIVED_GOLD_WEIGHTtxt, "", "0");
    }//GEN-LAST:event_RECIVED_GOLD_WEIGHTtxtFocusLost

    private void RECIVED_GOLD_KARATtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_KARATtxtFocusGained
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasGain( RECIVED_GOLD_KARATtxt, 0, "");
    }//GEN-LAST:event_RECIVED_GOLD_KARATtxtFocusGained

    private void RECIVED_GOLD_KARATtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_KARATtxtFocusLost
        // TODO add your handling code here:
         new FieldSetting().fieldFoucasLost( RECIVED_GOLD_KARATtxt, "", "0");
    }//GEN-LAST:event_RECIVED_GOLD_KARATtxtFocusLost

    private void RECIVED_GOLD_KARATtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RECIVED_GOLD_KARATtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().only_numbers_with_point(evt);
    }//GEN-LAST:event_RECIVED_GOLD_KARATtxtKeyTyped

    private void EMAILtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EMAILtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().NoSpace(evt);
    }//GEN-LAST:event_EMAILtxtKeyTyped

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
        String[]shopinfo=new GetShopInfo().getData();

        try{
            
            
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/OrderInvoice.jrxml";
            InputStream in = new FileInputStream(filePath);
            JasperDesign jd = JRXmlLoader.load(in);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            para.put("ID", IDtxt.getText());
            para.put("SHOP_NAME", shopinfo[1]);
            para.put("PHONE", shopinfo[2]);
            para.put("ADDRESS", shopinfo[3]);
            
           
            JasperPrint j = JasperFillManager.fillReport(jr, para,con);
           
            JasperViewer.viewReport(j, false);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
        
        
        
        
        
//SELECT order_table.*, order_details.*, order_advance_money.*, order_advance_gold.*
//FROM order_table JOIN order_details JOIN order_advance_money JOIN order_advance_gold
//ON order_table.OID=order_details.OID =order_advance_money.OID =order_advance_gold.OID 
//where order_table.OID=3;  
//        
        
        
    }//GEN-LAST:event_button7ActionPerformed

    private void detailTableInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_detailTableInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_detailTableInputMethodTextChanged

    private void detailTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_detailTableFocusLost
        // TODO add your handling code here:
        

    }//GEN-LAST:event_detailTableFocusLost

    private void detailTableAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_detailTableAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_detailTableAncestorRemoved

    private void QTYtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_QTYtxtFocusGained
        // TODO add your handling code here:
        new FieldSetting().fieldFoucasGain(QTYtxt, 0, "");
    }//GEN-LAST:event_QTYtxtFocusGained

    private void QTYtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_QTYtxtFocusLost
        // TODO add your handling code here:
        new FieldSetting().fieldFoucasLost(QTYtxt, "", "0");
    }//GEN-LAST:event_QTYtxtFocusLost

    private void QTYtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QTYtxtKeyTyped
        // TODO add your handling code here:
        new FieldSetting().only_numbers(evt);
    }//GEN-LAST:event_QTYtxtKeyTyped

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        new Order_history().setVisible(true);
    }//GEN-LAST:event_button6ActionPerformed

    private void NAMEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NAMEtxtKeyReleased
        // TODO add your handling code here:
             nextFoucas(evt, PHONEtxt);
    }//GEN-LAST:event_NAMEtxtKeyReleased

    private void PHONEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PHONEtxtKeyReleased
        // TODO add your handling code here:
       nextFoucas(evt, EMAILtxt);
    }//GEN-LAST:event_PHONEtxtKeyReleased

    private void EMAILtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EMAILtxtKeyReleased
        // TODO add your handling code here:
          nextFoucas(evt, RATEtxt);
    }//GEN-LAST:event_EMAILtxtKeyReleased

    private void RATEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RATEtxtKeyReleased
        // TODO add your handling code here:
       nextFoucas(evt, RDATEtxt);
    }//GEN-LAST:event_RATEtxtKeyReleased

    private void RDATEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RDATEtxtKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
                select_item.requestFocus();
                select_item.showPopup();
                
        }  
    }//GEN-LAST:event_RDATEtxtKeyReleased

    private void select_itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_select_itemKeyReleased
        // TODO add your handling code here:
          // TODO add your handling code here:
         nextFoucas(evt, QTYtxt);
    }//GEN-LAST:event_select_itemKeyReleased

    private void QTYtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QTYtxtKeyReleased
        // TODO add your handling code here:
         nextFoucas(evt, ITEM_WEIGHTtxt);
    }//GEN-LAST:event_QTYtxtKeyReleased

    private void ITEM_WEIGHTtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ITEM_WEIGHTtxtKeyReleased
        // TODO add your handling code here:
         nextFoucas(evt, ITEM_SIZEtxt);
    }//GEN-LAST:event_ITEM_WEIGHTtxtKeyReleased

    private void ITEM_SIZEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ITEM_SIZEtxtKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
                select_books.requestFocus();
                select_books.showPopup();
                
        }  
    }//GEN-LAST:event_ITEM_SIZEtxtKeyReleased

    private void select_booksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_select_booksKeyReleased
        // TODO add your handling code here:
           nextFoucas(evt, BOOK_VOLUMEtxt);

    }//GEN-LAST:event_select_booksKeyReleased

    private void BOOK_VOLUMEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_VOLUMEtxtKeyReleased
        // TODO add your handling code here:
        nextFoucas(evt, BOOK_PAGEtxt);
    }//GEN-LAST:event_BOOK_VOLUMEtxtKeyReleased

    private void BOOK_PAGEtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_PAGEtxtKeyReleased
        // TODO add your handling code here:
        nextFoucas(evt, BOOK_ITEM_NOtxt);
    }//GEN-LAST:event_BOOK_PAGEtxtKeyReleased

    private void BOOK_ITEM_NOtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOOK_ITEM_NOtxtKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
                select_description.requestFocus();
                select_description.showPopup();
                
        }  
    }//GEN-LAST:event_BOOK_ITEM_NOtxtKeyReleased

    private void select_descriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_select_descriptionKeyReleased
        // TODO add your handling code here:
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
               addtolist();
               button2.requestFocus();
        }  
         
    }//GEN-LAST:event_select_descriptionKeyReleased

    private void button2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_button2KeyReleased
        // TODO add your handling code here:
         
       
    }//GEN-LAST:event_button2KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ALL_UI_1.TextField BOOK_ITEM_NOtxt;
    private ALL_UI_1.TextField BOOK_PAGEtxt;
    private ALL_UI_1.TextField BOOK_VOLUMEtxt;
    private ALL_UI_1.TextField DATE;
    private ALL_UI_1.TextField EMAILtxt;
    private ALL_UI_1.TextField IDtxt;
    private ALL_UI_1.TextField ITEM_SIZEtxt;
    private ALL_UI_1.TextField ITEM_WEIGHTtxt;
    private panal.bgpanal MainPanel;
    private ALL_UI_1.TextField NAMEtxt;
    private ALL_UI_1.TextField PHONEtxt;
    private ALL_UI_1.TextField QTYtxt;
    private ALL_UI_1.TextField RATEtxt;
    private ALL_UI_1.TextField RDATEtxt;
    private ALL_UI_1.TextField RECIVED_GOLD_KAATtxt;
    private ALL_UI_1.TextField RECIVED_GOLD_KARATtxt;
    private ALL_UI_1.TextField RECIVED_GOLD_PASAtxt;
    private ALL_UI_1.TextField RECIVED_GOLD_WEIGHTtxt;
    private ALL_UI_1.TextField SEARCHtxt;
    private ALL_UI_1.TextField TOTAL_ADVANCE_GOLD_PASAtxt;
    private ALL_UI_1.TextField TOTAL_ADVANCE_GOLD_WEIGHTtxt;
    private ALL_UI_1.TextField TOTAL_ADVANCE_PAYEDtxt;
    private ALL_UI_1.TextField TOTAL_REQUIRED_WEIGHTtxt;
    private panal.Table2 advanceTable;
    private ALL_UI_1.Button button1;
    private ALL_UI_1.Button button10;
    private ALL_UI_1.Button button11;
    private ALL_UI_1.Button button12;
    private ALL_UI_1.Button button2;
    private ALL_UI_1.Button button3;
    private ALL_UI_1.Button button4;
    private ALL_UI_1.Button button5;
    private ALL_UI_1.Button button6;
    private ALL_UI_1.Button button7;
    private ALL_UI_1.Button button8;
    private ALL_UI_1.Button button9;
    private panal.Table2 detailTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private panal.pane1 pane11;
    private panal.pane1 pane12;
    private panal.pane1 pane13;
    private panal.pane1 pane14;
    private panal.pane2 pane21;
    private ALL_UI_1.Button savebtn;
    private ALL.Combobox select_books;
    private ALL.Combobox select_description;
    private ALL.Combobox select_item;
    private ALL.TableCellEditor tableCellEditor1;
    // End of variables declaration//GEN-END:variables
}
