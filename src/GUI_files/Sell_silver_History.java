/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.GetShopInfo;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class Sell_silver_History extends javax.swing.JFrame {

    /**
     * Creates new form Sell_silver_History
     */
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    int xMouse;
    int yMouse;
    
    public Sell_silver_History() {
        initComponents();
        ImageIcon img = new ImageIcon("src\\ASSETS_files\\icons8-database-syncing-complete-local-drive-and-connected-with-other-pc-48.png");
        this.setIconImage(img.getImage());
        con = new ConnectDB().Connect();
        getForMainTable();
    }

    
    
    
     public String getSalesManName(String id) throws SQLException{
        
            str=con.prepareStatement("Select* from logs where ID="+id);
            rs=str .executeQuery();
            if(rs.next()){
                return rs.getString("NAME");
            }
            return "null";
       
    }
    
     
     
     
    public void clearMianTbl(){
        searchby_cb.setSelectedIndex(0);
        search_txt.setText("");
        getForMainTable();
    }
    
    
    
    public void getForMainTable(){
        int c;
        try {

            
            str = con.prepareStatement ("SELECT * FROM `sell_silver`");
            

            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)Table1.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                
                String ID = rs.getString("SID");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                String cnic = rs.getString("CNIC");
                String weight = rs.getString("TOTAL_WEIGHT");
                String tol_price = rs.getString("TOTAL_PRICE");
                
                String [] row = {ID,name,phone,cnic,weight,tol_price};
               
                df.addRow(row);
                
                        
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }
    }
    
    
    
    
     public void SearchForMainTable(String Search_by,String search){
        int c;
        try {

            if(Search_by.equals("ID")){
                str = con.prepareStatement ("SELECT * FROM `sell_silver` where SID="+search);
            }else{
                str=con.prepareStatement ("SELECT * FROM `sell_silver` where "+Search_by+" LIKE '%"+search+"%'");
            }
            
            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)Table1.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                
                String ID = rs.getString("SID");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                String cnic = rs.getString("CNIC");
                String weight = rs.getString("TOTAL_WEIGHT");
                String tol_price = rs.getString("TOTAL_PRICE");
                
                String [] row = {ID,name,phone,cnic,weight,tol_price};
               
                df.addRow(row);
                
                        
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }
    }
    
     
     
     
     
     
    
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
            total_weight.setText(rs.getString("TOTAL_WEIGHT"));
            total_pasa.setText(rs.getString("PURE_WEIGHT"));
            price.setText(rs.getString("TOTAL_PRICE"));
            total_mazdori.setText(rs.getString("MZDORI"));
            total_price.setText(rs.getString("TOTAL_PRICE"));
            total_recived.setText(rs.getString("RECIVED"));
            total_remaining.setText(rs.getString("REMANING"));
            salesmantxt.setText(getSalesManName(rs.getString("SALESMANID")));
            getDataFromSubTbl(sid, Table2);
            
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
        DefaultTableModel df = (DefaultTableModel)Table2.getModel();
        PrintArea.setText("");
        PrintArea.setText("|------------------------------------------------------");
        PrintArea.setText(PrintArea.getText()+"\n| SALESMAN\t:- "+salesmantxt.getText());
        PrintArea.setText(PrintArea.getText()+"\n| DATE\t:- "+DATElbl.getText());
        PrintArea.setText(PrintArea.getText()+"\n| ID\t:- "+IDtxt.getText());
        PrintArea.setText(PrintArea.getText()+"\n| NAME\t:- "+customer_name.getText());
        PrintArea.setText(PrintArea.getText()+"\n| PHONE\t:- "+customer_phone.getText());
        PrintArea.setText(PrintArea.getText()+"\n| CNIC\t:- "+customer_cnic.getText());
        PrintArea.setText(PrintArea.getText()+"\n|____________________item details______________________");
        PrintArea.setText(PrintArea.getText()+"\n| ITEM\t| WEIGHT\t| PRICE");
        PrintArea.setText(PrintArea.getText()+"\n|------------------------------------------------------");
        for(int i=0 ;i<df.getRowCount();i++){
            String item = df.getValueAt(i,0).toString();
            String wazan = df.getValueAt(i,1).toString();
            Double rakam = Double.valueOf(df.getValueAt(i,7).toString());
            PrintArea.setText(PrintArea.getText()+"\n| ("+item+")\t| "+wazan+"_G\t| "+format("%.0f", rakam)+"/-");
            PrintArea.setText(PrintArea.getText()+"\n|______________________________________________________");
        }
        PrintArea.setText(PrintArea.getText()+"\n| TATAL WEIGHT     :- "+total_weight.getText()+"_g");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| TOTAL MAZDORI :- "+total_mazdori.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| TOTAL PRICE       :- "+total_price.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n| TOTAL RECIVED   :- "+total_recived.getText()+"/-");
        PrintArea.setText(PrintArea.getText()+"\n|-------------------");
        PrintArea.setText(PrintArea.getText()+"\n------------------------------------------------------");
        PrintArea.setText(PrintArea.getText()+"\n       NO RETURN WITHOUT THIS INVOICE"
                                             +"\n               THAK YOU SO MUCH");
        PrintArea.setText(PrintArea.getText()+"\n               Developed by M.UZAIR"
                                            + "\n               Whatsapp:-03476442712");
        printit();
        
    }    
    
    
    
    
    
    
    
    
    
//    
//    public void print(){
//        
//        DefaultTableModel df = (DefaultTableModel)Table2.getModel();
//                
//        PrintArea.setText("");
//        
//        PrintArea.setText("|------------------------------------------------------");
//        PrintArea.setText(PrintArea.getText()+"\n| DATE\t:- "+DATElbl.getText());
//        PrintArea.setText(PrintArea.getText()+"\n| ID\t:- "+IDtxt.getText());
//        PrintArea.setText(PrintArea.getText()+"\n| NAME\t:- "+customer_name.getText());
//        PrintArea.setText(PrintArea.getText()+"\n| PHONE\t:- "+customer_phone.getText());
//        PrintArea.setText(PrintArea.getText()+"\n| CNIC\t:- "+customer_cnic.getText());
//        PrintArea.setText(PrintArea.getText()+"\n|____________________item details______________________");
//       
//        for(int i =0 ;i<df.getRowCount();i++){
//            String item = df.getValueAt(i,0).toString();
//            String wazan = df.getValueAt(i,1).toString();
//            Double price = Double.valueOf(df.getValueAt(i,7).toString());
//           
//            
//            if(i==df.getRowCount()-1){
//                PrintArea.setText(PrintArea.getText()+"\n| "+(i+1)+")"+item+" -> ("+wazan+")G -> "+format("%.0f", price)+"/-");
//                
//            }else{
//                PrintArea.setText(PrintArea.getText()+"\n| "+(i+1)+")"+item+" -> ("+wazan+")G -> "+format("%.0f", price)+"/-");
//                PrintArea.setText(PrintArea.getText()+"\n|______________________________________________________");
//            }
//           
//        }
//         
//        PrintArea.setText(PrintArea.getText()+"\n|______________________________________________________");
//        
//        
//        
//        PrintArea.setText(PrintArea.getText()+"\n| TATAL WEIGHT     :- "+total_weight.getText());
//        PrintArea.setText(PrintArea.getText()+"\n|--------------------");
//        PrintArea.setText(PrintArea.getText()+"\n| TOTAL MAZDORI :- "+total_mazdori.getText());
//        PrintArea.setText(PrintArea.getText()+"\n|--------------------");
//        PrintArea.setText(PrintArea.getText()+"\n| TOTAL PRICE       :- "+total_price.getText());
//        PrintArea.setText(PrintArea.getText()+"\n|--------------------");
//        PrintArea.setText(PrintArea.getText()+"\n| TOTAL RECIVED   :- "+total_recived.getText());
//        PrintArea.setText(PrintArea.getText()+"\n|--------------------");
//     
//        PrintArea.setText(PrintArea.getText()+"\n|------------------------------------------------------");
//        PrintArea.setText(PrintArea.getText()+"\n               THAK YOU SO MUCH");
//        PrintArea.setText(PrintArea.getText()+"\n               Software by M.UZAIR\n  "
//                                                + "             Whatsapp:-03476442712");
//        
//        printit();
//        
//        
//    }    
//    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        DATElbl = new javax.swing.JLabel();
        IDtxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        customer_name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        customer_phone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        customer_cnic = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        total_weight = new javax.swing.JTextField();
        total_pasa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        total_mazdori = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        total_price = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        total_recived = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        total_remaining = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        salesmantxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        searchby_cb = new javax.swing.JComboBox<>();
        search_txt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PrintArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 30, 30));

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
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 30, 30));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("SELL SILVER HISTORY");
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
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Table1.setAutoCreateRowSorter(true);
        Table1.setBackground(new java.awt.Color(255, 255, 102));
        Table1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PHONE", "CNIC", "WEIGHT", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table1.setRowHeight(40);
        Table1.setShowGrid(true);
        Table1.setShowVerticalLines(false);
        Table1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Table1FocusLost(evt);
            }
        });
        Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 530, 520));

        jPanel2.setBackground(new java.awt.Color(224, 224, 224));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DATElbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DATElbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DATElbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(DATElbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        IDtxt.setEditable(false);
        IDtxt.setBackground(new java.awt.Color(204, 255, 255));
        IDtxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        IDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(IDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NAME");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 50, 20));

        customer_name.setEditable(false);
        customer_name.setBackground(new java.awt.Color(204, 255, 255));
        customer_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customer_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_name.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(customer_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 190, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PHONE");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 20));

        customer_phone.setEditable(false);
        customer_phone.setBackground(new java.awt.Color(204, 255, 255));
        customer_phone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customer_phone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_phone.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(customer_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 120, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CNIC");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 50, 20));

        customer_cnic.setEditable(false);
        customer_cnic.setBackground(new java.awt.Color(204, 255, 255));
        customer_cnic.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customer_cnic.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customer_cnic.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(customer_cnic, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 190, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ID");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, 20));

        Table2.setBackground(new java.awt.Color(204, 255, 255));
        Table2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Table2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "WAZAN", "RATE", "GRAM RATE", "KARAT", "KAAT", "PASA", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table2.setRowHeight(30);
        Table2.setShowGrid(true);
        jScrollPane3.setViewportView(Table2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 420, 170));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("WEIGHT");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 80, 30));

        total_weight.setEditable(false);
        total_weight.setBackground(new java.awt.Color(204, 255, 255));
        total_weight.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        total_weight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_weight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 110, 30));

        total_pasa.setEditable(false);
        total_pasa.setBackground(new java.awt.Color(204, 255, 255));
        total_pasa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        total_pasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_pasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_pasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 110, 30));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PASA");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 80, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("PRICE");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 80, 30));

        price.setEditable(false);
        price.setBackground(new java.awt.Color(204, 255, 255));
        price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        price.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 110, 30));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("MZDORI");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 80, 30));

        total_mazdori.setEditable(false);
        total_mazdori.setBackground(new java.awt.Color(204, 255, 255));
        total_mazdori.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        total_mazdori.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_mazdori.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_mazdori, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 110, 30));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 80, 40));

        total_price.setEditable(false);
        total_price.setBackground(new java.awt.Color(204, 255, 255));
        total_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_price.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 130, 40));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("RECIVED");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 80, 30));

        total_recived.setEditable(false);
        total_recived.setBackground(new java.awt.Color(204, 255, 255));
        total_recived.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        total_recived.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_recived.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_recived, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 130, 30));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("REMAINING");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 80, 30));

        total_remaining.setEditable(false);
        total_remaining.setBackground(new java.awt.Color(204, 255, 255));
        total_remaining.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        total_remaining.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_remaining.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(total_remaining, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 130, 30));

        jLabel15.setBackground(new java.awt.Color(204, 255, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("PRINT");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, 90, 30));

        jLabel16.setBackground(new java.awt.Color(255, 204, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CLEAR");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 90, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SALESMAN NAME");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 240, -1));

        salesmantxt.setEditable(false);
        salesmantxt.setBackground(new java.awt.Color(204, 255, 255));
        salesmantxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salesmantxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salesmantxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(salesmantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 240, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 440, 520));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("PRINT");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 230, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("VIEW FULL DETAILS");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 440, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Search By");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        searchby_cb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchby_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAME", "PHONE", "CNIC", "WEIGHT", "TOTAL_PRICE" }));
        jPanel1.add(searchby_cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 120, 30));

        search_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(search_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 160, 30));

        jLabel18.setBackground(new java.awt.Color(255, 204, 204));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Clear");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel18.setOpaque(true);
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 80, 30));

        jLabel21.setBackground(new java.awt.Color(204, 255, 204));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("GET");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel21.setOpaque(true);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel21MouseExited(evt);
            }
        });
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 70, 30));

        PrintArea.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jScrollPane4.setViewportView(PrintArea);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 50, 230, 520));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1240, 590));

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:

        this.setState(ICONIFIED);

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jLabel2.setBackground(Color.GREEN);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jLabel2.setBackground(new java.awt.Color(204,255,204));

    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Silver_sell_invoice.kye=0;
        
        dispose();

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jLabel1.setBackground(Color.red);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel1.setBackground(new java.awt.Color(255, 204, 204));

    }//GEN-LAST:event_jLabel1MouseExited

    private void Table1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Table1FocusLost
        // TODO add your handling code here:
        DefaultTableModel df=(DefaultTableModel)Table1.getModel();
        Table1.clearSelection();
    }//GEN-LAST:event_Table1FocusLost

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        if(search_txt.getText().equals("")){
            getForMainTable();
            return;
        }
        
        SearchForMainTable(searchby_cb.getSelectedItem().toString(), search_txt.getText());
        
        
        
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseExited
        // TODO add your handling code here:\
        jLabel21.setBackground(new java.awt.Color(204, 255, 204));
    }//GEN-LAST:event_jLabel21MouseExited

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        // TODO add your handling code here:
        jLabel21.setBackground(Color.GREEN);
    }//GEN-LAST:event_jLabel21MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        jLabel18.setBackground(new java.awt.Color(255, 204, 204));

    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        jLabel18.setBackground(Color.red);

    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        clearMianTbl();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel df=(DefaultTableModel)Table1.getModel();
        int srow=Table1.getSelectedRow();
        
        String sid=df.getValueAt(srow,0).toString();
        try {
            getDataFromMainTbl(sid);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error :- "+ex.getMessage());
        }
        
    }//GEN-LAST:event_Table1MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
            String[]shopinfo=new GetShopInfo().getData();

        try{
            
            
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/SellSilver.jrxml";
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
        
//        print();
        
        
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        dispose();
        new Sell_silver_History().setVisible(true);
    }//GEN-LAST:event_jLabel16MouseClicked

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
            java.util.logging.Logger.getLogger(Sell_silver_History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sell_silver_History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sell_silver_History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sell_silver_History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sell_silver_History().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DATElbl;
    private javax.swing.JTextField IDtxt;
    private javax.swing.JTextPane PrintArea;
    private javax.swing.JTable Table1;
    private javax.swing.JTable Table2;
    private javax.swing.JTextField customer_cnic;
    private javax.swing.JTextField customer_name;
    private javax.swing.JTextField customer_phone;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField price;
    private javax.swing.JTextField salesmantxt;
    private javax.swing.JTextField search_txt;
    private javax.swing.JComboBox<String> searchby_cb;
    private javax.swing.JTextField total_mazdori;
    private javax.swing.JTextField total_pasa;
    private javax.swing.JTextField total_price;
    private javax.swing.JTextField total_recived;
    private javax.swing.JTextField total_remaining;
    private javax.swing.JTextField total_weight;
    // End of variables declaration//GEN-END:variables
}
