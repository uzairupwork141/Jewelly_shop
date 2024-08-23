/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.OnlyNumbers;
import CODE_files.usermodel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.String.format;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
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
public class Silver_sell_invoice extends javax.swing.JFrame {

    /**
     * Creates new form Silver_sell_invoice
     */
    static int kye=0;
    int xMouse;
    int yMouse;
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    ArrayList<String> items = new ArrayList();
    public Silver_sell_invoice() {
        initComponents();
        ImageIcon img = new ImageIcon("src/ASSETS_files/pngwing.com.png");
        this.setIconImage(img.getImage());
        con=new ConnectDB().Connect();
        
        Get_Set_Id();
        getCurrentSalesnam();
        getItem();
        DATElbl.setText(currentdate());
    }
    
    
    
    public void refresh(){
        dispose();
        new Silver_sell_invoice().setVisible(true);
    }
    
    
    
    
    
    
    public void getSalesMansID(String id){
        try{
            str=con.prepareStatement("Select* from logs where ID="+id);
            rs=str .executeQuery();
            if(rs.next())
            {
                SALESMAN_ID.setText(rs.getString("ID"));
                SALESMAN_NAME.setText(rs.getString("NAME"));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    
    
    public void getCurrentSalesnam(){
        try{
            
            SALESMAN_ID.setText(usermodel.getID());
            SALESMAN_NAME.setText(usermodel.getName());
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
     
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
    
    
    
    

    
    public void getItem(){
        try{
            str=con.prepareStatement("select* from items");
            rs=str.executeQuery();
            items.clear();
            while(rs.next()){
                items.add(rs.getString("ITEM"));
            }
            
            for (int i = 0; i<items.size();i++){
               select_item.addItem(items.get(i));
            }
        }catch(Exception ex ){
            JOptionPane.showMessageDialog(this, ex.getMessage(),"error",2);
        }
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void fieldFoucasGain(JTextField tx ,String equal ,String set){
        if(tx.getText().equals(equal)){
            tx.setText(set);
        }
    }    
    
    
    public void fieldFoucasLost(JTextField tx,String equal ,String set){
        if(tx.getText().equals(equal)){
            tx.setText(set);
            
        }
    }    
    
    public void enterPress(KeyEvent evt, JTextField nextField){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            nextField.requestFocus();
        }
    }
    
    public void calRate(JTextField from ,JTextField to){
         if(!from.getText().equals("")){
            double rate=Double.parseDouble(from.getText());
            double pg_rate=rate/12.150;
            to.setText(""+format("%.3f",pg_rate));
        }else{
            to.setText("0.00");
        }
    }
    
    public void calRate2(JTextField from ,JTextField to){
         if(!from.getText().equals("")){
            double pg_rate=Double.parseDouble(from.getText());
            double rate=pg_rate*12.150;
            to.setText(""+format("%.0f",rate));
        }else{
            to.setText("0.00");
        }
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    public void itemCal(){
        try{
            double karat=Double.parseDouble(item_karat.getText());
            double weight=Double.parseDouble(item_wazan.getText());
            double pgram_rate=Double.parseDouble(item_pgram.getText());

            double pure_gold=(karat/24)*weight;
            double kaat=weight-pure_gold;
            double item_price=pure_gold*pgram_rate;
          

            item_kaat.setText(""+format("%.3f", kaat));
            item_pasa.setText(""+format("%.3f", pure_gold));
            item_rakam.setText(""+format("%.0f", item_price));
         
            
            
        }catch(Exception ex){
            item_kaat.setText("0.00");
            item_pasa.setText("0.00");
            item_rakam.setText("0");
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    public void checkKarat(JTextField txt){
        
        
       try{
            float karat=Float.parseFloat(txt.getText());
            if(karat>24){
                JOptionPane.showMessageDialog(this, "Wrong Karat Value");
                txt.setText(removeLastChar(txt.getText()));
                
                return;
            }
            txt.setText(txt.getText());
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
        
    }
    
    
    
    
    public String removeLastChar(String str) 
    {
       
        str = str.substring(0, str.length()-1);
     
        return str;
    }
    
    
    
    
    
    
    public void addRowToTable(String[]row ,JTable tbl){
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        df.addRow(row);
    }
    
    
    
    
    public void clearItemSection(){
        select_item.setSelectedIndex(0);
        item_wazan.setText("0.00");
        item_rate.setText("0");
        item_pgram.setText("0.00");
        item_karat.setText("0.00");
        item_kaat.setText("0.00");
        item_pasa.setText("0.00");
        item_rakam.setText("0");
        
    }
    
   
       
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public void calTableData(){
        
        try{
            double tol_weight = 0.0;
            double tol_pasa = 0.0;
            double price = 0.0;

            DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            
            System.out.println(df.getRowCount());
            
            for (int i=0;i<df.getRowCount();i++){
                tol_weight +=Double.parseDouble(df.getValueAt(i, 1).toString());
                tol_pasa +=Double.parseDouble(df.getValueAt(i, 6).toString());
                price +=Double.parseDouble(df.getValueAt(i, 7).toString());
                
                        
            }
            total_wazan.setText(""+format("%.3f", tol_weight));
            total_pasa.setText(""+format("%.3f", tol_pasa));
            rakam.setText(""+format("%.0f", price));
            
            
        }catch(Exception ex){
            
            total_wazan.setText("0.00");
            total_pasa.setText("0.00");
            rakam.setText("0");
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this,ex );
        }
        
    }
    
    
    
    
    
    
    public void findTolPrice(){
        try{

            double price     = Double.parseDouble(rakam.getText());
            double mazdori   = Double.parseDouble(mzdory.getText());
            double tol_price = price+mazdori;
            total_rakam.setText(""+format("%.0f", tol_price));
        }catch(Exception ex){
            total_rakam.setText("0");
            System.out.println(ex.getMessage());
        }
           
    }
    
    public void findRemaing(){

       try{
            double tol_price   = Double.parseDouble(total_rakam.getText());
            double wasol     = Double.parseDouble(total_wasul.getText());
            double remaning  = tol_price-wasol;
            total_bakaya.setText(""+format("%.0f", remaning));
       }catch(Exception ex){
            total_bakaya.setText("0");
            System.out.println(ex.getMessage());
       }
        
    }
    
    
    
    
    public void removeTableRow(JTable tbl){
        try{
            DefaultTableModel  df = (DefaultTableModel)tbl.getModel();
            int row = tbl.getSelectedRow();
            df.removeRow(row);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "error:- PLEASE SELECT AN ITEM ROW\nException:- "+ex.getMessage());
        }
    }
    
    
    
    public void addItemSideCase(){
        
        
    
    }
    
    
    
    
    public boolean submitSideCase(){
        
        if(customer_name.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Name Required");
            return false;
        }
        
        if(jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Empty table\nItem Data Required");
            return false;
        }
        return true;
    }
    
    
    
    
    
    public void Get_Set_Id(){
        
         try{
                    str=con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'sell_silver' ");
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
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void submitToMainTable() throws SQLException{
            String ID=IDtxt.getText();
            String name=customer_name.getText();
            String phone=customer_phone.getText();
            String cnic=customer_cnic.getText();
            String weight=total_wazan.getText();
            String pureWeight=total_pasa.getText();
            String price=rakam.getText();
            String mazdori=mzdory.getText();
            String tolPrice=total_rakam.getText();
            String recived=total_wasul.getText();
            String remaning=total_bakaya.getText();
            String smanid =SALESMAN_ID.getText();
            String date=currentdate();
            
            str=con.prepareStatement("INSERT INTO `sell_silver` (`SID`, `NAME`, `PHONE`, `CNIC`, `TOTAL_WEIGHT`, `PURE_WEIGHT`, `SUB_PRICE`, `MZDORI`, `TOTAL_PRICE`, `RECIVED`, `REMANING`, `DATE`,SALESMANID ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            str.setString(1, ID);
            str.setString(2, name);
            str.setString(3, phone);
            str.setString(4, cnic);
            str.setString(5, weight);
            str.setString(6, pureWeight);
            str.setString(7, price);
            str.setString(8, mazdori);
            str.setString(9, tolPrice);
            str.setString(10, recived);
            str.setString(11, remaning);
            str.setString(12, date);
            str.setString(13, smanid);
            str.executeUpdate();
            submitToChildTable();        
            JOptionPane.showMessageDialog(this,"DATA SAVED","SUCCESS",1);                    
            
    }
         
    
    
    

    public void submitToChildTable() throws SQLException{
            
            
                DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
                for(int i=0;i<jTable1.getRowCount();i++){
                    String SID=         IDtxt.getText();
                    String item=        df.getValueAt(i, 0).toString();
                    String weight=      df.getValueAt(i, 1).toString();
                    String rate=        df.getValueAt(i, 2).toString();
                    String pgram=       df.getValueAt(i, 3).toString();
                    String karat=       df.getValueAt(i, 4).toString();
                    String kaat=        df.getValueAt(i, 5).toString();
                    String pasa=        df.getValueAt(i, 6).toString();
                    String price=       df.getValueAt(i, 7).toString();
                    
                    str=con.prepareStatement("INSERT INTO `sell_invoice_details`(`SID`, `ITEM`, `WEIGHT`,`RATE`, `PGRAM_RATE`, `KARAT`, `KAAT`, `PASA`, `PRICE`) VALUES (?,?,?,?,?,?,?,?,?)");
                    str.setString(1, SID);
                    str.setString(2, item);
                    str.setString(3, weight);
                    str.setString(4, rate);
                    str.setString(5, pgram);
                    str.setString(6, karat);
                    str.setString(7, kaat);
                    str.setString(8, pasa);
                    str.setString(9, price);
                    str.executeUpdate();
                    
                }
            JOptionPane.showMessageDialog(this,"Item DATA SAVED","SUCCESS",1);
    }
    
    
    
    
    
    
    
    public void submit(){
        if(submitSideCase()==false){
            return;
        }
        
        try{
            submitToMainTable();
            saveBtn.setText("UPDATE");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR\nEXCEPTION :-"+ex.getMessage(),"ERROR",2);
        }
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void dataTofields(){
        
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        int srow= jTable1.getSelectedRow();
        
        select_item.setSelectedItem(df.getValueAt(srow, 0));
        item_wazan.setText(df.getValueAt(srow, 1).toString());
        item_rate.setText(df.getValueAt(srow, 2).toString());
        item_pgram.setText(df.getValueAt(srow, 3).toString());
        item_karat.setText(df.getValueAt(srow, 4).toString());
        item_kaat.setText(df.getValueAt(srow, 5).toString());
        item_pasa.setText(df.getValueAt(srow, 6).toString());
        item_rakam.setText(df.getValueAt(srow,7).toString());
        
        df.removeRow(srow);
        
        mainCalculation();
        
    }
    
    
    
    
    
    
    public void mouseClicked(MouseEvent event)
    {
      if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
          dataTofields();
          itemCal();
      }
    }
    
    
    
    
    
    
    
    public void mainCalculation(){
        calTableData();



        findTolPrice();


        findRemaing();
    }
    
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void getDataFromMainTbl(String sid) throws SQLException
    {
        
        str=con.prepareStatement("select* from sell_silver where sid="+sid);
        rs=str.executeQuery();
        if(rs.next()){
            DATElbl.setText(rs.getString("DATE"));
            IDtxt.setText(rs.getString("SID"));
            customer_name.setText(rs.getString("NAME"));
            customer_phone.setText(rs.getString("PHONE"));
            customer_cnic.setText(rs.getString("CNIC"));
            total_wazan.setText(rs.getString("TOTAL_WEIGHT"));
            total_pasa.setText(rs.getString("PURE_WEIGHT"));
            rakam.setText(rs.getString("SUB_PRICE"));
            mzdory.setText(rs.getString("MZDORI"));
            total_rakam.setText(rs.getString("TOTAL_PRICE"));
            total_wasul.setText(rs.getString("RECIVED"));
            total_bakaya.setText(rs.getString("REMANING"));
            getSalesMansID(rs.getString("SALESMANID"));
            getDataFromSubTbl(sid, jTable1);
            saveBtn.setText("UPDATE");
            clearItemSection();
        }else{
            JOptionPane.showMessageDialog(this, "No Data avalible");
        }
        
    } 
    
    
    
    
    
    
    public void getDataFromSubTbl(String sid,JTable tbl ) throws SQLException{
        
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.setRowCount(0);
        
        str=con.prepareStatement("select* from sell_invoice_details where sid="+sid);
        rs=str.executeQuery();
        while(rs.next()){
            String item  =rs.getString("item");
            String wazan  =rs.getString("weight");
            String rate  =rs.getString("rate");
            String prate =rs.getString("pgram_rate");
            String karat =rs.getString("karat");
            String kaat  =rs.getString("kaat");
            String pasa  =rs.getString("pasa");
            String rakam =rs.getString("price");
            String [] row = {item,wazan,rate,prate,karat,kaat,pasa,rakam};
            df.addRow(row);
        }
        
        
    } 
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////// 
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    public void updateToMainTbl (String id) throws SQLException{
        str= con.prepareStatement("UPDATE `sell_silver` SET `NAME`=?,`PHONE`=?,`CNIC`=?,`TOTAL_WEIGHT`=?,`PURE_WEIGHT`=?,`SUB_PRICE`=?,`MZDORI`=?,`TOTAL_PRICE`=?,`RECIVED`=?,`REMANING`=? , `SALESMANID`=? WHERE SID="+id);
        str.setString(1, customer_name.getText());
        str.setString(2, customer_phone.getText());
        str.setString(3, customer_cnic.getText());
        str.setString(4, total_wazan.getText());
        str.setString(5, total_pasa.getText());
        str.setString(6, rakam.getText());
        str.setString(7, mzdory.getText());
        str.setString(8, total_rakam.getText());
        str.setString(9, total_wasul.getText());
        str.setString(10, total_bakaya.getText());
        str.setString(11, SALESMAN_ID.getText());
        
        str.executeUpdate();
        updateSubTable(id);
        JOptionPane.showMessageDialog(this, "UPDATED","update",1);
    }
    
    
    
    
    
    
    
    public void updateSubTable(String id) throws SQLException{
        str=con.prepareStatement("DELETE FROM `sell_invoice_details` WHERE SID="+id);
        str.executeUpdate();
        
        submitToChildTable();
        
    }
    
    
    
    public void update(){
        if(submitSideCase()==false){
            return;
        }
        
        try{
            updateToMainTbl(IDtxt.getText());
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR\nEXCEPTION :-"+ex.getMessage(),"ERROR",2);
        }
    }
    
    
    
    
    
    public void delete(String id) throws SQLException{
        str = con.prepareStatement("DELETE FROM `sell_silver` WHERE SID="+id);
        str.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record Deleted","Deleted",2);
        refresh();
    }
    
    
    
    
    public boolean checkIfIdExist(String id) throws SQLException{
        str = con.prepareStatement("select* from sell_silver where sid="+id);
        rs=str.executeQuery();
        if(rs.next()){
           return true;
        }
        return false;
    }
    
    
    
    public boolean CheckItemSectionEmpty(){
        double wazan=Double.parseDouble(item_wazan.getText());
        double rate=Double.parseDouble(item_rate.getText());
        double pgram=Double.parseDouble(item_pgram.getText());
        double karat=Double.parseDouble(item_karat.getText());
        double kaat=Double.parseDouble(item_kaat.getText());
        double pasa=Double.parseDouble(item_pasa.getText());
        double rakam=Double.parseDouble(item_rakam.getText());
        if(wazan>0 || rate>0 ||pgram>0 || karat >0 || kaat>0 || pasa>0 || rakam>0){
            JOptionPane.showMessageDialog(this, "Clear Item section\n Click clear button");
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
     public void printit(){
        try {
            
            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f,72f, 72f, MediaPrintableArea.MM)); 
            
//            logo.print(null, null, false, service, attr, false);
           
            PrintArea.print(null, null, true, null, attr, false);
            
           
        } catch (PrinterException ex) {
            System.out.println(""+ex);
        }
    }
    
    
    
    
    public void print(){
        
        if(submitSideCase()==false)
        {
            return;
        }
        
        
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
                
        PrintArea.setText("");
        
        PrintArea.setText("|______________________________________________________");
        PrintArea.setText(PrintArea.getText()+"\n| SALESMAN\t:- "+SALESMAN_NAME.getText());
        PrintArea.setText(PrintArea.getText()+"\n| DATE\t:- "+DATElbl.getText());
        PrintArea.setText(PrintArea.getText()+"\n| ID\t:- "+IDtxt.getText());
        PrintArea.setText(PrintArea.getText()+"\n| NAME\t:- "+customer_name.getText());
        PrintArea.setText(PrintArea.getText()+"\n| PHONE\t:- "+customer_phone.getText());
        PrintArea.setText(PrintArea.getText()+"\n| CNIC\t:- "+customer_cnic.getText());
        PrintArea.setText(PrintArea.getText()+"\n|____________________item details______________________");
        PrintArea.setText(PrintArea.getText()+"\n| ITEM\t| WEIGHT\t| PRICE");
        PrintArea.setText(PrintArea.getText()+"\n|------------------------------------------------------");
        for(int i =0 ;i<df.getRowCount();i++){
            String item = df.getValueAt(i,0).toString();
            String wazan = df.getValueAt(i,1).toString();
            Double price = Double.valueOf(df.getValueAt(i,7).toString());
           
     
            PrintArea.setText(PrintArea.getText()+"\n| ("+item+")\t| "+wazan+"_G\t| "+format("%.0f", price)+"/-");
            PrintArea.setText(PrintArea.getText()+"\n|------------------------------------------------------");
            
           
        }
         PrintArea.setText(PrintArea.getText()+"\n|______________________________________________________");
        
        
        
        
        PrintArea.setText(PrintArea.getText()+"\n| NET WEIGHT\t:- "+total_wazan.getText()+"_g");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| NET MAZDORI\t:- "+mzdory.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| NET PRICE\t:- "+total_rakam.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| NET RECIVED\t:- "+total_wasul.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
     
        PrintArea.setText(PrintArea.getText()+"\n------------------------------------------------------");
       PrintArea.setText(PrintArea.getText()+"\n       NO RETURN WITHOUT THIS INVOICE"
                                             +"\n               THAK YOU SO MUCH");
        PrintArea.setText(PrintArea.getText()+"\n               Developed by M.UZAIR"
                                            + "\n               Whatsapp:-03476442712");
        
        printit();
        
        
    }    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        IDtxt = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        search_id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        DATElbl = new javax.swing.JLabel();
        SALESMAN_ID = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        SALESMAN_NAME = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        item_wazan = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        item_rate = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        item_pgram = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        item_karat = new javax.swing.JTextField();
        item_kaat = new javax.swing.JTextField();
        item_pasa = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        item_rakam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        select_item = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        customer_name = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        customer_phone = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        customer_cnic = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        total_wazan = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        rakam = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        mzdory = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        total_rakam = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        total_wasul = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        total_bakaya = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        total_pasa = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PrintArea = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 30, 30));

        jLabel2.setBackground(new java.awt.Color(204, 255, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 30, 30));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("SELL SILVER");
        jLabel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel29.setOpaque(true);
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
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel2.setBackground(new java.awt.Color(221, 221, 221));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(204, 204, 204));
        jLabel31.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("حوالہ نمبر");
        jLabel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel31.setOpaque(true);
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 80, 30));

        IDtxt.setEditable(false);
        IDtxt.setBackground(new java.awt.Color(255, 204, 204));
        IDtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        IDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.add(IDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 90, 30));

        jLabel32.setBackground(new java.awt.Color(204, 204, 204));
        jLabel32.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("حوالہ نمبر");
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel32.setOpaque(true);
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 90, 30));

        search_id.setBackground(new java.awt.Color(204, 255, 255));
        search_id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        search_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search_id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.add(search_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 170, 30));

        jLabel4.setBackground(new java.awt.Color(204, 255, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("get");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 30));

        DATElbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DATElbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DATElbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.add(DATElbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 110, 30));

        SALESMAN_ID.setEditable(false);
        SALESMAN_ID.setBackground(new java.awt.Color(255, 204, 204));
        SALESMAN_ID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SALESMAN_ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SALESMAN_ID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        SALESMAN_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SALESMAN_IDKeyReleased(evt);
            }
        });
        jPanel2.add(SALESMAN_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 160, -1));

        jLabel39.setBackground(new java.awt.Color(204, 204, 204));
        jLabel39.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("SALESMAN ID");
        jLabel39.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel39.setOpaque(true);
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 160, 20));

        jLabel40.setBackground(new java.awt.Color(204, 204, 204));
        jLabel40.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("SALESMAN");
        jLabel40.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel40.setOpaque(true);
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 160, 20));

        SALESMAN_NAME.setEditable(false);
        SALESMAN_NAME.setBackground(new java.awt.Color(255, 204, 204));
        SALESMAN_NAME.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SALESMAN_NAME.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SALESMAN_NAME.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        SALESMAN_NAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SALESMAN_NAMEKeyReleased(evt);
            }
        });
        jPanel2.add(SALESMAN_NAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 160, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 960, 60));

        jPanel3.setBackground(new java.awt.Color(220, 217, 217));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(204, 204, 204));
        jLabel34.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("وزن");
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel34.setOpaque(true);
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 80, 30));

        item_wazan.setBackground(new java.awt.Color(204, 255, 255));
        item_wazan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_wazan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_wazan.setText("0.00");
        item_wazan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_wazan.setNextFocusableComponent(item_rate);
        item_wazan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                item_wazanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                item_wazanFocusLost(evt);
            }
        });
        item_wazan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_wazanActionPerformed(evt);
            }
        });
        item_wazan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_wazanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                item_wazanKeyTyped(evt);
            }
        });
        jPanel3.add(item_wazan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 190, 30));

        jLabel35.setBackground(new java.awt.Color(204, 204, 204));
        jLabel35.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("ریٹ");
        jLabel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel35.setOpaque(true);
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 120, 30));

        item_rate.setBackground(new java.awt.Color(204, 255, 255));
        item_rate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_rate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_rate.setText("0");
        item_rate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_rate.setNextFocusableComponent(item_pgram);
        item_rate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                item_rateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                item_rateFocusLost(evt);
            }
        });
        item_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_rateActionPerformed(evt);
            }
        });
        item_rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_rateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                item_rateKeyTyped(evt);
            }
        });
        jPanel3.add(item_rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 30));

        jLabel42.setBackground(new java.awt.Color(204, 204, 204));
        jLabel42.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("گرام ریٹ");
        jLabel42.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel42.setOpaque(true);
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 80, 30));

        item_pgram.setBackground(new java.awt.Color(204, 255, 255));
        item_pgram.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_pgram.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_pgram.setText("0.00");
        item_pgram.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_pgram.setNextFocusableComponent(item_karat);
        item_pgram.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                item_pgramFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                item_pgramFocusLost(evt);
            }
        });
        item_pgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_pgramActionPerformed(evt);
            }
        });
        item_pgram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_pgramKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                item_pgramKeyTyped(evt);
            }
        });
        jPanel3.add(item_pgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 190, 30));

        jLabel43.setBackground(new java.awt.Color(204, 204, 204));
        jLabel43.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("کیرات");
        jLabel43.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel43.setOpaque(true);
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 80, 30));

        item_karat.setBackground(new java.awt.Color(204, 255, 255));
        item_karat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_karat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_karat.setText("0.00");
        item_karat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_karat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                item_karatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                item_karatFocusLost(evt);
            }
        });
        item_karat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_karatActionPerformed(evt);
            }
        });
        item_karat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                item_karatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_karatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                item_karatKeyTyped(evt);
            }
        });
        jPanel3.add(item_karat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 190, 30));

        item_kaat.setEditable(false);
        item_kaat.setBackground(new java.awt.Color(255, 204, 204));
        item_kaat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_kaat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_kaat.setText("0.00");
        item_kaat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_kaat.setFocusable(false);
        jPanel3.add(item_kaat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 190, 30));

        item_pasa.setEditable(false);
        item_pasa.setBackground(new java.awt.Color(255, 204, 204));
        item_pasa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_pasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_pasa.setText("0.00");
        item_pasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_pasa.setFocusable(false);
        jPanel3.add(item_pasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 190, 30));

        jLabel49.setBackground(new java.awt.Color(204, 204, 204));
        jLabel49.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("رقم");
        jLabel49.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel49.setOpaque(true);
        jPanel3.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 80, 30));

        item_rakam.setEditable(false);
        item_rakam.setBackground(new java.awt.Color(255, 204, 204));
        item_rakam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        item_rakam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_rakam.setText("0");
        item_rakam.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        item_rakam.setFocusable(false);
        item_rakam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_rakamActionPerformed(evt);
            }
        });
        jPanel3.add(item_rakam, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 190, 30));

        jLabel5.setBackground(new java.awt.Color(204, 255, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ADD TO LIST");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 190, 30));

        select_item.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        select_item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        select_item.setToolTipText("");
        select_item.setBorder(null);
        select_item.setNextFocusableComponent(item_wazan);
        select_item.setOpaque(true);
        jPanel3.add(select_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 270, 30));

        jLabel48.setBackground(new java.awt.Color(204, 204, 204));
        jLabel48.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("پاسہ");
        jLabel48.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel48.setOpaque(true);
        jPanel3.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 80, 30));

        jLabel13.setBackground(new java.awt.Color(255, 204, 204));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("CLEAR");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, 30));

        jLabel45.setBackground(new java.awt.Color(204, 204, 204));
        jLabel45.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("کاٹ");
        jLabel45.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel45.setOpaque(true);
        jPanel3.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 960, 130));

        jPanel4.setBackground(new java.awt.Color(220, 217, 217));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(204, 204, 204));
        jLabel36.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("نام");
        jLabel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel36.setOpaque(true);
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 50, 30));

        customer_name.setBackground(new java.awt.Color(204, 255, 255));
        customer_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        customer_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        customer_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customer_nameKeyReleased(evt);
            }
        });
        jPanel4.add(customer_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 230, 30));

        jLabel37.setBackground(new java.awt.Color(204, 204, 204));
        jLabel37.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("ٖفون");
        jLabel37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel37.setOpaque(true);
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 90, 30));

        customer_phone.setBackground(new java.awt.Color(204, 255, 255));
        customer_phone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        customer_phone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_phone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        customer_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_phoneActionPerformed(evt);
            }
        });
        customer_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customer_phoneKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customer_phoneKeyTyped(evt);
            }
        });
        jPanel4.add(customer_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 230, 30));

        jLabel38.setBackground(new java.awt.Color(204, 204, 204));
        jLabel38.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("شناختی کارڈ");
        jLabel38.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel38.setOpaque(true);
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 90, 30));

        customer_cnic.setBackground(new java.awt.Color(204, 255, 255));
        customer_cnic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        customer_cnic.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_cnic.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        customer_cnic.setName(""); // NOI18N
        customer_cnic.setNextFocusableComponent(select_item);
        customer_cnic.setOpaque(true);
        customer_cnic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customer_cnicKeyTyped(evt);
            }
        });
        jPanel4.add(customer_cnic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 30));
        customer_cnic.getAccessibleContext().setAccessibleName("");
        customer_cnic.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 960, 50));

        jPanel5.setBackground(new java.awt.Color(222, 221, 221));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(204, 255, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "WEIGHT", "RATE", "GRAM RATE", "KARAT", "KAAT", "PASA", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(35);
        jTable1.setRowSorter(null);
        jTable1.setShowGrid(true);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, 210));

        jLabel8.setBackground(new java.awt.Color(255, 204, 204));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Table Clear");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 120, 30));

        jLabel12.setBackground(new java.awt.Color(255, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Remove item");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 110, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, 530, 260));

        jPanel6.setBackground(new java.awt.Color(217, 216, 216));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total_wazan.setEditable(false);
        total_wazan.setBackground(new java.awt.Color(255, 204, 204));
        total_wazan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_wazan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_wazan.setText("0.00");
        total_wazan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        total_wazan.setFocusable(false);
        jPanel6.add(total_wazan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 130, 40));

        jLabel53.setBackground(new java.awt.Color(204, 204, 204));
        jLabel53.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("وزن");
        jLabel53.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel53.setOpaque(true);
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 80, 40));

        rakam.setEditable(false);
        rakam.setBackground(new java.awt.Color(255, 204, 204));
        rakam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rakam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rakam.setText("0");
        rakam.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        rakam.setFocusable(false);
        rakam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rakamActionPerformed(evt);
            }
        });
        jPanel6.add(rakam, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 180, 40));

        jLabel54.setBackground(new java.awt.Color(204, 204, 204));
        jLabel54.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("رقم");
        jLabel54.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel54.setOpaque(true);
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 80, 40));

        mzdory.setBackground(new java.awt.Color(204, 255, 255));
        mzdory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mzdory.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mzdory.setText("0.0");
        mzdory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mzdory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mzdoryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mzdoryFocusLost(evt);
            }
        });
        mzdory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mzdoryMouseClicked(evt);
            }
        });
        mzdory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mzdoryActionPerformed(evt);
            }
        });
        mzdory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mzdoryKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mzdoryKeyTyped(evt);
            }
        });
        jPanel6.add(mzdory, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 180, 40));

        jLabel55.setBackground(new java.awt.Color(204, 204, 204));
        jLabel55.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("مزدوری");
        jLabel55.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel55.setOpaque(true);
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 80, 40));

        total_rakam.setEditable(false);
        total_rakam.setBackground(new java.awt.Color(255, 204, 204));
        total_rakam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total_rakam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_rakam.setText("0");
        total_rakam.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(total_rakam, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 180, 40));

        jLabel56.setBackground(new java.awt.Color(204, 204, 204));
        jLabel56.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("کل رقم");
        jLabel56.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel56.setOpaque(true);
        jPanel6.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 80, 40));

        total_wasul.setBackground(new java.awt.Color(204, 255, 255));
        total_wasul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total_wasul.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_wasul.setText("0.0");
        total_wasul.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        total_wasul.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                total_wasulFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                total_wasulFocusLost(evt);
            }
        });
        total_wasul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                total_wasulMouseClicked(evt);
            }
        });
        total_wasul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_wasulActionPerformed(evt);
            }
        });
        total_wasul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                total_wasulKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_wasulKeyTyped(evt);
            }
        });
        jPanel6.add(total_wasul, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 180, -1));

        jLabel57.setBackground(new java.awt.Color(204, 204, 204));
        jLabel57.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("وصول");
        jLabel57.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel57.setOpaque(true);
        jPanel6.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 80, 30));

        total_bakaya.setEditable(false);
        total_bakaya.setBackground(new java.awt.Color(255, 204, 204));
        total_bakaya.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total_bakaya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_bakaya.setText("0");
        total_bakaya.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        total_bakaya.setFocusable(false);
        total_bakaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_bakayaActionPerformed(evt);
            }
        });
        jPanel6.add(total_bakaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 180, 40));

        jLabel58.setBackground(new java.awt.Color(204, 204, 204));
        jLabel58.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("بقایہ");
        jLabel58.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel58.setOpaque(true);
        jPanel6.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 80, 40));

        total_pasa.setEditable(false);
        total_pasa.setBackground(new java.awt.Color(255, 204, 204));
        total_pasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_pasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_pasa.setText("0.00");
        total_pasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        total_pasa.setFocusable(false);
        jPanel6.add(total_pasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        jLabel59.setBackground(new java.awt.Color(204, 204, 204));
        jLabel59.setFont(new java.awt.Font("Arabic Typesetting", 1, 17)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("خالص وزن");
        jLabel59.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel59.setOpaque(true);
        jPanel6.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 80, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 430, 260));

        PrintArea.setEditable(false);
        PrintArea.setBackground(new java.awt.Color(204, 255, 255));
        PrintArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        PrintArea.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jScrollPane3.setViewportView(PrintArea);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 500));

        jLabel11.setBackground(new java.awt.Color(102, 255, 153));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CALCULATE");
        jLabel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel11.setOpaque(true);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 160, 40));

        jLabel9.setBackground(new java.awt.Color(102, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PRINT");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 230, 40));

        saveBtn.setBackground(new java.awt.Color(255, 255, 102));
        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveBtn.setText("SAVE");
        saveBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveBtn.setOpaque(true);
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveBtnMouseEntered(evt);
            }
        });
        jPanel1.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, 170, 40));

        jLabel16.setBackground(new java.awt.Color(255, 204, 102));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("HISTORY");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 550, 180, 40));

        jLabel14.setBackground(new java.awt.Color(255, 204, 204));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("NEW");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 550, 120, 40));

        jLabel7.setBackground(new java.awt.Color(255, 102, 102));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DELETE");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 550, 120, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 600));

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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        if(kye>0){
            JOptionPane.showMessageDialog(this, "A window is already Open");
            return;
        }
        
        
        
        HOME.kye=0;
        dispose();
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        
        this.setState(ICONIFIED);
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jLabel1.setBackground(Color.red);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jLabel2.setBackground(Color.GREEN);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jLabel2.setBackground(new java.awt.Color(204,255,204));
        
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        jLabel5.setBackground(Color.GREEN);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
         jLabel5.setBackground(new java.awt.Color(204,255,204));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:
         jLabel4.setBackground(Color.GREEN);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        jLabel4.setBackground(new java.awt.Color(204,255,204));
    }//GEN-LAST:event_jLabel4MouseExited

    private void item_wazanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_wazanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_wazanActionPerformed

    private void item_rakamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_rakamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_rakamActionPerformed

    private void rakamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rakamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rakamActionPerformed

    private void item_wazanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_wazanFocusGained
        // TODO add your handling code here:
       
        fieldFoucasGain(item_wazan,"0.00","");
        
    }//GEN-LAST:event_item_wazanFocusGained

    private void item_wazanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_wazanFocusLost
        // TODO add your handling code here:
        fieldFoucasLost(item_wazan,"","0.00");
        
    }//GEN-LAST:event_item_wazanFocusLost

    private void item_pgramFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_pgramFocusGained
        // TODO add your handling code here:
        fieldFoucasGain(item_pgram,"0.00","");
    }//GEN-LAST:event_item_pgramFocusGained

    private void item_pgramFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_pgramFocusLost
        // TODO add your handling code here:
         fieldFoucasLost(item_pgram,"","0.00");
    }//GEN-LAST:event_item_pgramFocusLost

    private void item_pgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_pgramActionPerformed
        // TODO add your handling code here:
        
        fieldFoucasLost(item_pgram,"","0.00");
        
    }//GEN-LAST:event_item_pgramActionPerformed

    private void item_wazanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_wazanKeyReleased
        // TODO add your handling code here:
        enterPress(evt,  item_rate);
        itemCal();
    }//GEN-LAST:event_item_wazanKeyReleased

    private void item_rateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_rateKeyReleased
        // TODO add your handling code here:
        enterPress(evt,  item_pgram);
        
        calRate(item_rate, item_pgram);
        
        itemCal();
    }//GEN-LAST:event_item_rateKeyReleased

    private void item_pgramKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_pgramKeyReleased
        // TODO add your handling code here:
        enterPress(evt,  item_karat);
        
        calRate2(item_pgram, item_rate);
        
        itemCal();
    }//GEN-LAST:event_item_pgramKeyReleased

    private void item_rateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_rateFocusGained
        // TODO add your handling code here:
        fieldFoucasGain(item_rate,"0","");
    }//GEN-LAST:event_item_rateFocusGained

    private void item_rateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_rateFocusLost
        // TODO add your handling code here:
        fieldFoucasLost(item_rate,"","0");
    }//GEN-LAST:event_item_rateFocusLost

    private void item_karatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_karatFocusGained
        // TODO add your handling code here:
        fieldFoucasGain(item_karat,"0.00","");
    }//GEN-LAST:event_item_karatFocusGained

    private void item_karatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_item_karatFocusLost
        // TODO add your handling code here:
        fieldFoucasLost(item_karat,"","0.00");
        itemCal();
        
    }//GEN-LAST:event_item_karatFocusLost

    private void customer_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customer_nameKeyReleased
        // TODO add your handling code here:
        enterPress(evt, customer_phone);
    }//GEN-LAST:event_customer_nameKeyReleased

    private void customer_phoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customer_phoneKeyReleased
        // TODO add your handling code here:
        enterPress(evt, customer_cnic);
    }//GEN-LAST:event_customer_phoneKeyReleased

    private void item_karatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_karatActionPerformed
        // TODO add your handling code here:
            
        
        jLabel5.requestFocus(true);
        itemCal();
    }//GEN-LAST:event_item_karatActionPerformed

    private void total_wasulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_wasulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_wasulActionPerformed

    private void mzdoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mzdoryFocusGained
        // TODO add your handling code here:
        fieldFoucasGain(mzdory,"0.0","");
    }//GEN-LAST:event_mzdoryFocusGained

    private void mzdoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mzdoryFocusLost
        // TODO add your handling code here:
       fieldFoucasLost(mzdory,"","0.0");
       
       mainCalculation();
    }//GEN-LAST:event_mzdoryFocusLost

    private void mzdoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mzdoryKeyReleased
        // TODO add your handling code here:
        
        
        
        
        
        
        enterPress(evt, total_wasul);
        
        
        findTolPrice();
        
        findRemaing();
        
    }//GEN-LAST:event_mzdoryKeyReleased

    private void total_wasulFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_wasulFocusGained
        // TODO add your handling code here:
        fieldFoucasGain(total_wasul,"0.0","");
    }//GEN-LAST:event_total_wasulFocusGained

    private void total_wasulFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_wasulFocusLost
        // TODO add your handling code here:
        fieldFoucasLost(total_wasul,"","0.0");
        
        mainCalculation();
    }//GEN-LAST:event_total_wasulFocusLost

    private void item_karatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_karatKeyReleased
        // TODO add your handling code here:
        checkKarat(item_karat);
        
        itemCal();
        
    }//GEN-LAST:event_item_karatKeyReleased

    private void item_karatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_karatKeyPressed
        // TODO add your handling code here:
        checkKarat(item_karat);
        itemCal();
    }//GEN-LAST:event_item_karatKeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        
        try{
            String item  = select_item.getSelectedItem().toString();
            String wazan = item_wazan.getText();
            String rate  = item_rate.getText();
            String grate = item_pgram.getText();
            String karat = item_karat.getText();
            String kaat  = item_kaat.getText();
            String pasa  = item_pasa.getText();
            String rakam = item_rakam.getText();


            if(item.equals("Select")){
                JOptionPane.showMessageDialog(this, "ITEM NOT SELECTED\nPLEASE SELECT AN ITEM","ERROR",2);
                return;
            }

            if(Double.parseDouble(wazan)<=0){
                JOptionPane.showMessageDialog(this, "PLEASE ENTER WEIGHT(wazan = 0)","ERROR",2);
                return;
            }

            if(Double.parseDouble(grate)<=0){
                JOptionPane.showMessageDialog(this, "PLEASE ENTER P/GRAM RATE(RATE = 0)","ERROR",2);
                return;
            }

            if(Double.parseDouble(karat)<=0){
                JOptionPane.showMessageDialog(this, "PLEASE ENTER KARAT(KARAT = 0)","ERROR",2);
                return;
            }

            itemCal();



            String [] row={item,wazan,rate,grate,karat,kaat,pasa,rakam};
            addRowToTable(row, jTable1);

            clearItemSection();

            mainCalculation();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "error in data entry","ERROR",2);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        
        clearItemSection();
        
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
        
        jLabel13.setBackground(Color.red);
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
        jLabel13.setBackground(new java.awt.Color(255, 204, 204));

    }//GEN-LAST:event_jLabel13MouseExited

    private void total_wasulKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_wasulKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fieldFoucasLost(total_wasul, "", "0.0");
        }
        
        findTolPrice();
        
        findRemaing();
    }//GEN-LAST:event_total_wasulKeyReleased

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        jLabel11.requestFocus();
        mainCalculation();
        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void mzdoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mzdoryKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_mzdoryKeyTyped

    private void mzdoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mzdoryActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mzdoryActionPerformed

    private void mzdoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mzdoryMouseClicked
        // TODO add your handling code here:
       fieldFoucasGain(mzdory,"0.0","");
    }//GEN-LAST:event_mzdoryMouseClicked

    private void total_wasulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_total_wasulMouseClicked
        // TODO add your handling code here:
        fieldFoucasGain(total_wasul,"0.0","");
    }//GEN-LAST:event_total_wasulMouseClicked

    private void item_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_rateActionPerformed

    private void total_bakayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_bakayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_bakayaActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        removeTableRow(jTable1);
       mainCalculation();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void saveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseClicked
        // TODO add your handling code here:
        mainCalculation();
        if(saveBtn.getText().equals("SAVE")){
            submit();
            return;
        }
        
        if(saveBtn.getText().equals("UPDATE")){
            
            update();
            return;
        }
        
        
    }//GEN-LAST:event_saveBtnMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(CheckItemSectionEmpty()==false){
            return;
        }
        mouseClicked(evt);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        df.setRowCount(0);
        mainCalculation();
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        String ID=search_id.getText();
        try {
            
            getDataFromMainTbl(ID);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"ERROR: can not get data\n","Error",2);
        }
        
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void saveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBtnMouseEntered

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
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
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        try{
            
            mainCalculation();
            if(saveBtn.getText().equals("SAVE")){
                submit();

            }

            if(saveBtn.getText().equals("UPDATE")){

                update();

            }
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/SellSilver.jrxml";
            InputStream in = new FileInputStream(filePath);
            JasperDesign jd = JRXmlLoader.load(in); 
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            para.put("ID", IDtxt.getText());
            para.put("SHOP_NAME", "YASIR GOLD");
            
            JasperPrint j = JasperFillManager.fillReport(jr, para,con);
           
                JasperViewer.viewReport(j, false);
            
//            print();
            
            
        }catch(Exception ex){
            System.out.println("error "+ex);
        }
        
            
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        if(kye>0){
            JOptionPane.showMessageDialog(this, "A window is already Open\nClose it");
            return;
        }
        kye++;
        new Sell_silver_History().setVisible(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void SALESMAN_IDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SALESMAN_IDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SALESMAN_IDKeyReleased

    private void SALESMAN_NAMEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SALESMAN_NAMEKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SALESMAN_NAMEKeyReleased

    private void customer_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customer_phoneActionPerformed

    private void customer_phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customer_phoneKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_customer_phoneKeyTyped

    private void customer_cnicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customer_cnicKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_customer_cnicKeyTyped

    private void item_wazanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_wazanKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_item_wazanKeyTyped

    private void item_rateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_rateKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_item_rateKeyTyped

    private void item_pgramKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_pgramKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_item_pgramKeyTyped

    private void item_karatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_karatKeyTyped
        // TODO add your handling code here:
        new OnlyNumbers().only_numbers(evt);
        
    }//GEN-LAST:event_item_karatKeyTyped

    private void total_wasulKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_wasulKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_total_wasulKeyTyped

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
            java.util.logging.Logger.getLogger(Silver_sell_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Silver_sell_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Silver_sell_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Silver_sell_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Silver_sell_invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DATElbl;
    private javax.swing.JTextField IDtxt;
    private javax.swing.JTextPane PrintArea;
    private javax.swing.JTextField SALESMAN_ID;
    private javax.swing.JTextField SALESMAN_NAME;
    private javax.swing.JTextField customer_cnic;
    private javax.swing.JTextField customer_name;
    private javax.swing.JTextField customer_phone;
    private javax.swing.JTextField item_kaat;
    private javax.swing.JTextField item_karat;
    private javax.swing.JTextField item_pasa;
    private javax.swing.JTextField item_pgram;
    private javax.swing.JTextField item_rakam;
    private javax.swing.JTextField item_rate;
    private javax.swing.JTextField item_wazan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mzdory;
    private javax.swing.JTextField rakam;
    private javax.swing.JLabel saveBtn;
    private javax.swing.JTextField search_id;
    private javax.swing.JComboBox<String> select_item;
    private javax.swing.JTextField total_bakaya;
    private javax.swing.JTextField total_pasa;
    private javax.swing.JTextField total_rakam;
    private javax.swing.JTextField total_wasul;
    private javax.swing.JTextField total_wazan;
    // End of variables declaration//GEN-END:variables
}
