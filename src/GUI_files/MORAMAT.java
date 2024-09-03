/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.GetShopInfo;
import CODE_files.OnlyNumbers;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
 * @author User1
 */
public class MORAMAT extends javax.swing.JFrame {

    /**
     * Creates new form MORAMAT
     */
    Connection con;
    PreparedStatement str;
    static int kye=0;
    ResultSet rs;
    public MORAMAT() {
        initComponents();
        ImageIcon img = new ImageIcon("src/ASSETS_files/pngwing.com.png");
        this.setIconImage(img.getImage());
        con = new ConnectDB().Connect();
        DATE.setText(currentdate());
        Get_Set_Id();
        currentdate();
        selectItem();
    }
    int xMouse;
    int yMouse;
    
    
   
    
     
     
    
    
    public String currentdate(){
        
            
                
                Calendar cal=new GregorianCalendar();
                int month=cal.get(Calendar.MONTH);
                int year =cal.get(Calendar.YEAR);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                String date ;
                if(month==12){
                    month=1;
                    date=day+" / "+(month)+" / "+year;
                    return date;
                }else{
                    month +=1;
                    date=day+" / "+(month)+" / "+year;
                    return date;
                }
                                
          
    }
     
    
    public void selectItem(){
        try{
        str=con.prepareStatement("select* from `items`");
        rs=str.executeQuery();
        while(rs.next()){
            select_item.addItem(rs.getString("ITEM"));
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);

        }
        
    }
    
    
    
    
    
    
    public void Get_Set_Id(){
//        
         try{
                    str=con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'moramat' ");
                    rs=str.executeQuery();
                    if (rs.next()){
                        int id=rs.getInt("AUTO_INCREMENT");
                        ID1.setText(""+id);
                    }
                    
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex,"error",2);
        }
        
    }
    
    
    
    
    
    
    
    
    
     

     
     
     
     
   
     
     
     
     
//     public void print(){
//            DefaultTableModel df=(DefaultTableModel)jTable1.getModel(); 
//            String Rdate=((JTextField)RDATE.getDateEditor().getUiComponent()).getText();
//            jTextArea1.setText(null);
//            jTextArea1.setText(jTextArea1.getText()+"       YASIR JEWELERY SHOP\n");
//            jTextArea1.setText(jTextArea1.getText()+"_________________________________\n");
//              
//            jTextArea1.setText(jTextArea1.getText()+"\n Receipet no#   "+ID1.getText()+"\n "
//                                                   +DATE.getText()+"\n");
//            jTextArea1.setText(jTextArea1.getText()+" NAME         :- "+NAME.getText()+""
//                                                 +"\n PHONEno   :- "+PHONE.getText()+"\n");
//            jTextArea1.setText(jTextArea1.getText()+"\n___________MORAMMAT___________\n");
//            jTextArea1.setText(jTextArea1.getText()+" items\t | weight\n");
//            jTextArea1.setText(jTextArea1.getText()+"------------------------------------------------------\n"); 
//            for (int i=0;i<df.getRowCount();i++){
//                String item=df.getValueAt(i, 0).toString();
//                String wazan=df.getValueAt(i, 1).toString();
//                String discription=df.getValueAt(i, 2).toString();
//                jTextArea1.setText(jTextArea1.getText()+" ( "+item+" )\t | "+wazan+"_G \n"
//                                                       +" ( "+discription+" )");
//                jTextArea1.setText(jTextArea1.getText()+"\n------------------------------------------------------\n"); 
//            }
//            jTextArea1.setText(jTextArea1.getText()+"_________________________________\n");
//            
//            jTextArea1.setText(jTextArea1.getText()+"\n TOTAL WEIGHT :- "+TWEIGHT.getText()+"_G");
//            jTextArea1.setText(jTextArea1.getText()+"\n_________________________________\n");
//            jTextArea1.setText(jTextArea1.getText()+"\n RETURN DATE :-  "+Rdate+"");
//            jTextArea1.setText(jTextArea1.getText()+"\n_________________________________\n");  
//            jTextArea1.setText(jTextArea1.getText()+"\nNOTE:- No morammat will "
//                                                   +"\n            be returned"
//                                                   +"\n            without this recipt"
//                                                   +"\n            Thank You");  
//                                                        
//            
//         try{
//           
//             jTextArea1.print();
//              
//         }catch(Exception e){
//             JOptionPane.showMessageDialog(this,e);
//         }
//     }
      
     
     
     
     
     
     
     
    
    
    
    public void submit(){
        try{
            String Rdate=((JTextField)RDATE.getDateEditor().getUiComponent()).getText();
            str=con.prepareStatement("INSERT INTO `moramat`(`ID`,`Name`,`PHONE`,`TOTAL_WEIGHT`,`RDATE`,`DATE`)VALUES(?,?,?,?,?,?)");
            str.setString(1, ID1.getText());
            str.setString(2, NAME.getText());
            str.setString(3, PHONE.getText());
            str.setString(4, TWEIGHT.getText());
            str.setString(5, Rdate);
            str.setString(6, DATE.getText());
            str.executeUpdate();
            submitdetail();
            JOptionPane.showMessageDialog(this,"DATA SAVED","SUCCESS",1);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e,"Submit functin\nDB  ERROR",3);
        }
    }
    
    
    
    
    
    
    
    
    
    
    public void submitdetail(){
        try{
                    DefaultTableModel df=(DefaultTableModel)jTable1.getModel(); 
                    
                    for (int i=0;i<df.getRowCount();i++)
                    {
                        
                        String item=df.getValueAt(i, 0).toString();
                        String weight=df.getValueAt(i, 1).toString();
                        String disc=df.getValueAt(i, 2).toString();
                        
                        str=con.prepareStatement("INSERT INTO `moramat_details`(`MID`,`item`,`weight`,`discription`)VALUES(?,?,?,?)");
                        str.setString(1, ID1.getText());
                        str.setString(2, item);
                        str.setString(3, weight);
                        str.setString(4,disc);
                        str.executeUpdate();
                    }
                        JOptionPane.showMessageDialog(this,"DATA SAVED","SUCCESS",1);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(this,e,"SUBMIT details func\nDATABASE CONNECTION FAIL",2);
                    }
    }
    
    
    
    
    
    
    
    
    
    public void Add(){
        DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
        df.addRow(new Object[]{
            select_item.getSelectedItem(),
            item_weight.getText(),
            discription.getSelectedItem()
        });
        
    } 
    
    
    
    
    
    public void calTweight(){
        try{
           
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            float sum = 0;
            int prow=df.getRowCount();
            
            if(prow==0){
                TWEIGHT.setText(""+String.format("%.3f",sum ));
                return;
            }
            for (int i = 0; i < prow; i++)
            {
                sum = sum + Float.parseFloat(df.getValueAt(i, 1).toString()); 
                TWEIGHT.setText(""+String.format("%.3f",sum ));
            } 
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR IN TOTAL WEIGHT CALCULATION");
            TWEIGHT.setText("");
        }
    }
    
    
    
    
    
    
//    public void makeDiscription(){
//        DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
//        DISCRIPTION.setText("");
//        for (int i = 0; i < df.getRowCount(); i++) {
//
//            DISCRIPTION.setText(" "+DISCRIPTION.getText()+df.getValueAt(i, 0).toString()+" :- "+df.getValueAt(i, 2).toString()+"\n");
//        }
//    }
    
    
    
    
    
    public void removeTableRow(JTable tbl){
        try{
            DefaultTableModel  df = (DefaultTableModel)tbl.getModel();
            int row = tbl.getSelectedRow();
            df.removeRow(row);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "error:- PLEASE SELECT AN ITEM ROW\nException:- "+ex.getMessage());
        }
    }
    
    
    
      public void dataTofields(){
        
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        int srow= jTable1.getSelectedRow();
        
        select_item.setSelectedItem(df.getValueAt(srow, 0));
        item_weight.setText(df.getValueAt(srow, 1).toString());
        discription.setSelectedItem(df.getValueAt(srow, 2).toString());
        
        df.removeRow(srow);
        
       
        
        calTweight();
    }
    
    
    public void mouseClicked(MouseEvent event)
    {
      if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
          dataTofields();
      }
    }
    
    
    
    
    
      
    public void clearItem(){
        select_item.setSelectedIndex(0);
        item_weight.setText("");
        discription.setSelectedIndex(0);
    } 
    
    
    
    
    
     
    public void refresh(){
        dispose();
        new MORAMAT().setVisible(true);
    }
    
    
    
    
    
    
    
    
    
    
    public void getDataFromMainTbl(String sid) throws SQLException
    {
        
        str=con.prepareStatement("select* from moramat where id="+sid);
        rs=str.executeQuery();
        if(rs.next()){
            
            ID1.setText(rs.getString("ID"));
            NAME.setText(rs.getString("NAME"));
            PHONE.setText(rs.getString("PHONE"));
            ((JTextField)RDATE.getDateEditor().getUiComponent()).setText(rs.getString("RDATE"));
            getDataFromSubTbl(sid, jTable1);
            submitBtn.setText("UPDATE");
            calTweight();
            
//            clearItemSection();
        }else{
            JOptionPane.showMessageDialog(this, "No Data avalible");
        }
        
    } 
    
    
    
    
    
    
    public void getDataFromSubTbl(String sid,JTable tbl ) throws SQLException{
        
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.setRowCount(0);
        
        str=con.prepareStatement("select* from moramat_details where mid="+sid);
        rs=str.executeQuery();
        while(rs.next()){
            String item  =rs.getString("item");
            String wazan  =rs.getString("weight");
            String discrip =rs.getString("discription");
            String [] row = {item,wazan,discrip};
            df.addRow(row);
        }
        
        
    } 
    
    
    
    
    
    
    
    
    
    
    public void updateToMainTbl (String id) throws SQLException{
        String Rdate=((JTextField)RDATE.getDateEditor().getUiComponent()).getText();
        str= con.prepareStatement("UPDATE `moramat` SET `NAME`=?,`PHONE`=?,`TOTAL_WEIGHT`=?,`RDATE`=? WHERE ID="+id);
        str.setString(1, NAME.getText());
        str.setString(2, PHONE.getText());
        str.setString(3, TWEIGHT.getText());
        str.setString(4, Rdate);
        str.executeUpdate();
        updateSubTable(id);
        JOptionPane.showMessageDialog(this, "UPDATED","update",1);
        
        
    }
    
    
    
    
    
    
    
    public void updateSubTable(String id) throws SQLException{
        str=con.prepareStatement("DELETE FROM `moramat_details` WHERE MID="+id);
        str.executeUpdate();
        submitdetail();
        
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NAME = new javax.swing.JTextField();
        PHONE = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ID1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        RDATE = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        select_item = new javax.swing.JComboBox<>();
        item_weight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        discription = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TWEIGHT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        DATE = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jLabel17.setFont(new java.awt.Font("Arabic Typesetting", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("PRINT");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(219, 219, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("NAME");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, 40));

        NAME.setBackground(new java.awt.Color(204, 255, 255));
        NAME.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        NAME.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NAME.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(NAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 180, 40));

        PHONE.setBackground(new java.awt.Color(204, 255, 255));
        PHONE.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        PHONE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PHONE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PHONE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PHONEActionPerformed(evt);
            }
        });
        PHONE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PHONEKeyTyped(evt);
            }
        });
        jPanel4.add(PHONE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 180, 40));

        jLabel2.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("PHONE");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 80, 40));

        ID1.setEditable(false);
        ID1.setBackground(new java.awt.Color(255, 204, 204));
        ID1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ID1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ID1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID1ActionPerformed(evt);
            }
        });
        jPanel4.add(ID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 180, 40));

        jLabel3.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ID");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 80, 30));

        jLabel4.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("RETURN DATE");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 260, 30));

        RDATE.setBackground(new java.awt.Color(204, 255, 255));
        RDATE.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        RDATE.setDateFormatString("dd/MM/yyyy");
        RDATE.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel4.add(RDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 260, 40));

        jLabel11.setFont(new java.awt.Font("Arabic Typesetting", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Customer Info");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 50));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 300, 480));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_item.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        select_item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ITEMS" }));
        select_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_itemActionPerformed(evt);
            }
        });
        jPanel6.add(select_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 40));

        item_weight.setBackground(new java.awt.Color(204, 255, 255));
        item_weight.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        item_weight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_weight.setToolTipText("");
        item_weight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        item_weight.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        item_weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                item_weightKeyTyped(evt);
            }
        });
        jPanel6.add(item_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 140, 40));

        jLabel7.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(" WEIGHT");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 90, 40));

        jLabel5.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("G");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 30, 40));

        jButton5.setBackground(new java.awt.Color(204, 255, 204));
        jButton5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton5.setText("ADD TO LIST");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 260, 40));

        discription.setEditable(true);
        discription.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        discription.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "صفائی+ مرمت", "ڈانڈی مرمت+صفائی", "نگ تبديل کرنا", "ڈانڈی کنڈے مرمت ", "ڈانڈی کنڈے تبديل ", "موتی لگانا", "پتری لگانا", "کنڈے تبديل", "کنڈے مرمت", "براے ناپ درست کرنا", "براے چين مرمت", "براے چين تبديل", "براے صفائی " }));
        discription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discriptionActionPerformed(evt);
            }
        });
        jPanel6.add(discription, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 260, 40));

        jButton6.setBackground(new java.awt.Color(255, 204, 204));
        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton6.setText("CLAER");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 200, 40));

        jLabel8.setText("TO DO :");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, -1, 20));

        jLabel14.setText("SELECT ITEM");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 20));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 120));

        jTable1.setBackground(new java.awt.Color(204, 255, 255));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "WEIGHT", "DISCRIPTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 0));
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 760, 290));

        TWEIGHT.setEditable(false);
        TWEIGHT.setBackground(new java.awt.Color(255, 204, 204));
        TWEIGHT.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        TWEIGHT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TWEIGHT.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(TWEIGHT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 260, 40));

        jLabel6.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TOTAL WEIGHT(G)");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 250, 40));

        jButton7.setBackground(new java.awt.Color(255, 204, 204));
        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton7.setText("REMOVE ITEM");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 130, 40));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-print-64.png"))); // NOI18N
        jButton2.setText("PRINT");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, 190, 80));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-database-syncing-complete-local-drive-and-connected-with-other-pc-48.png"))); // NOI18N
        jButton3.setText("HISTORY");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 190, 80));

        submitBtn.setBackground(new java.awt.Color(204, 255, 204));
        submitBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-submit-48.png"))); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.setBorder(null);
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        jPanel5.add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 190, 160));

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-refresh-60.png"))); // NOI18N
        jButton4.setText("REFRESH");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 190, 80));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 1000, 480));

        search.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 160, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ID");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("GET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, 30));

        DATE.setFont(new java.awt.Font("Arabic Typesetting", 0, 18)); // NOI18N
        DATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DATE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        DATE.setOpaque(true);
        jPanel3.add(DATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 210, 30));

        jLabel15.setText("Search By ID");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 110, 20));

        jLabel16.setText("CURRENT DATE");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 100, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1320, 560));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MORAMMAT ENTRY SYSTEM");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1320, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1340, 640));

        jLabel10.setBackground(new java.awt.Color(255, 204, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("X");
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, 30, 30));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setForeground(new java.awt.Color(204, 255, 255));
        jLabel13.setText("Morammat entry System");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setOpaque(true);
        jLabel13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel13MouseDragged(evt);
            }
        });
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (select_item.getSelectedItem().equals("ITEMS") || item_weight.getText().equals("")){
            JOptionPane.showMessageDialog(this, """
                                                MISSING IMPORTANT ENTERY IN ORDER
                                                *ERROR CAUSES -
                                                   1) ITEM NOT SELECTED. OR
                                                   2) MISSING WEIGHT. OR
                                                ""","MISSING ENTERY",2);
        }else{
            Add();
            calTweight();
            clearItem();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        if (NAME.getText().equals("")||PHONE.getText().equals("")||jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "IMCOMPLETE ENTERY MISSING\nCan't Proceed with Null Enteries","NULL ENTERY",2);
            return;
        }
        if(submitBtn.getText().equals("SUBMIT")){
            submit();
            refresh();
            return;
        }
        if(submitBtn.getText().equals("UPDATE")){
            try {
                updateToMainTbl(ID1.getText());
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(MORAMAT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

            
            
        


    }//GEN-LAST:event_submitBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//         if (NAME.getText().equals("")|| TWEIGHT.getText().equals("") || jTable1.getRowCount()==0){
//             JOptionPane.showMessageDialog(this, "IMCOMPLETE ENTERY \nCan't Proceed with Null PRINTING","NULL ENTERY",2);
//        }else {
//         print();   
//        }

        if (NAME.getText().equals("")||PHONE.getText().equals("")||jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "IMCOMPLETE ENTERY MISSING\nCan't Proceed with Null Enteries","NULL ENTERY",2);
            return;
        }
        
        if(submitBtn.getText().equals("UPDATE")){
            try {
                updateToMainTbl(ID1.getText());
            } catch (SQLException ex) {
                Logger.getLogger(MORAMAT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(submitBtn.getText().equals("SUBMIT")){
            submit();
            submitBtn.setText("UPDATE");
        }
        
        String [] arr  = new GetShopInfo().getData();
        try{
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/moramat.jrxml";
            InputStream in = new FileInputStream(filePath);
            JasperDesign jd = JRXmlLoader.load(in);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            para.put("ID", ID1.getText());
            para.put("SHOP_NAME", arr[1]);
            para.put("PHONE", arr[2]);
            JasperPrint j = JasperFillManager.fillReport(jr, para,con);
            JasperViewer.viewReport(j, false);
            
        }catch(Exception ex){
            
        }
           
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PHONEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PHONEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PHONEActionPerformed

    private void ID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID1ActionPerformed

    private void select_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_itemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_itemActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        HOME.kye=0;
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        if(item_weight.getText().equals("") && select_item.getSelectedItem().equals("ITEMS") &&  discription.getSelectedItem().equals("select")){
             mouseClicked(evt);
             calTweight();
        }
       
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        clearItem();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void discriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discriptionActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        removeTableRow(jTable1);
        calTweight();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            getDataFromMainTbl(search.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "ERROR:-"+ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(kye>=1){
            JOptionPane.showMessageDialog(this, "History page already opened");
            return;
        }
        kye++;
        new moramathistory().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void PHONEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PHONEKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_PHONEKeyTyped

    private void item_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_weightKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_item_weightKeyTyped

    private void jLabel13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseDragged
        // TODO add your handling code here:

        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);
    }//GEN-LAST:event_jLabel13MouseDragged

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse= evt.getY();
    }//GEN-LAST:event_jLabel13MousePressed

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
            java.util.logging.Logger.getLogger(MORAMAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MORAMAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MORAMAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MORAMAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
       
                new MORAMAT().setVisible(true);
          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DATE;
    private javax.swing.JTextField ID1;
    private javax.swing.JTextField NAME;
    private javax.swing.JTextField PHONE;
    private com.toedter.calendar.JDateChooser RDATE;
    private javax.swing.JTextField TWEIGHT;
    private javax.swing.JComboBox<String> discription;
    private javax.swing.JTextField item_weight;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField search;
    private javax.swing.JComboBox<String> select_item;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables

    private String String(float wazan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
