/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.GetShopInfo;
import CODE_files.usermodel;
import EmailSend.EmailSend;
import com.jhlabs.vecmath.Color4f;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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
public class Order_history extends javax.swing.JFrame {

    /**
     * Creates new form Order_history
     */
     int xMouse ;
     int yMouse ;
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    public Order_history() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        con = new ConnectDB().Connect();
        try {
            getAllData(tab1);
        } catch (SQLException ex) {
            Logger.getLogger(Order_history.class.getName()).log(Level.SEVERE, null, ex);
        }
//        getContentPane().setBackground(new Color(30, 30, 30));
        tab1.fixTable(jScrollPane1);
        tab1.setColumnAlignment(0, JLabel.LEFT);
        tab1.setCellAlignment(0, JLabel.LEFT);
        tab1.setColumnAlignment(1, JLabel.LEFT);
        tab1.setCellAlignment(1, JLabel.LEFT);
        tab1.setColumnAlignment(2, JLabel.LEFT);
        tab1.setCellAlignment(2, JLabel.LEFT);
        tab1.setColumnAlignment(3, JLabel.LEFT);
        tab1.setCellAlignment(3, JLabel.LEFT);
        tab1.setColumnAlignment(4, JLabel.LEFT);
        tab1.setCellAlignment(4, JLabel.LEFT);
        tab1.setColumnAlignment(5, JLabel.LEFT);
        tab1.setCellAlignment(5, JLabel.LEFT);
        tab1.setColumnAlignment(6, JLabel.RIGHT);
        tab1.setCellAlignment(6, JLabel.RIGHT);
        
        tab1.setColumnWidth(0, 80);
        tab1.setColumnWidth(1, 200);
        tab1.setColumnWidth(3, 200);
        
       
    }

    
    
    
    
    
    public void getAllData(JTable tbl ) throws SQLException{
        DefaultTableModel df = (DefaultTableModel)tbl.getModel();
        df.setRowCount(0);
        str=con.prepareStatement("select* from order_table");
        rs=str.executeQuery();
        while(rs.next()){
            String ID  =rs.getString("OID");
            String name  =rs.getString("NAME");
            String phone =rs.getString("PHONE");
            String email=rs.getString("EMAIL");
            String date=rs.getString("DATE");
            String rdate  =rs.getString("RETURN_DATE");
            String status =rs.getString("STATUS");
           
            String [] row = {ID,name,phone,email,date,rdate,status};
            df.addRow(row);
        }
    } 
    
    
    
    
    public void SearchForMainTable(String Search_by,String search){
        int c;
        try {

            if(Search_by.equals("ID")){
                str = con.prepareStatement ("SELECT * FROM `order_table` where OID='"+search+"'");
            }else{
                str=con.prepareStatement ("SELECT * FROM `order_table` where "+Search_by+" LIKE '%"+search+"%'");
            }
            
            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)tab1.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                
               String ID  =rs.getString("OID");
                String name  =rs.getString("NAME");
                String phone =rs.getString("PHONE");
                String email=rs.getString("EMAIL");
                String date=rs.getString("DATE");
                String rdate  =rs.getString("RETURN_DATE");
                String status =rs.getString("STATUS");
                String [] row = {ID,name,phone,email,date,rdate,status};
                df.addRow(row);
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }
    }
    
    
          
    public boolean checkIfIdExist(String id) throws SQLException{
        str = con.prepareStatement("SELECT * FROM `order_table` WHERE OID='"+id+"'");
        rs=str.executeQuery();
        if(rs.next()){
            System.out.println("idcheck");
           return true;
        }
        return false;
    }
    
    
    
    
    public void delete(String id) throws SQLException{
        str = con.prepareStatement("DELETE FROM `order_table` WHERE OID="+id);
        str.executeUpdate();
        System.out.println("order tbl");
        str = con.prepareStatement("DELETE FROM `order_details` WHERE OID="+id);
        str.executeUpdate();
        System.out.println("order det");
        str = con.prepareStatement("DELETE FROM `order_advance_gold` WHERE OID="+id);
        str.executeUpdate();
        System.out.println("order gold");
        str = con.prepareStatement("DELETE FROM `order_advance_money` WHERE OID="+id);
        str.executeUpdate();
        System.out.println("order money");
        JOptionPane.showMessageDialog(this, "Record Deleted","Deleted",2);
        getAllData(tab1);
    }
    
    
    
    
    
    
    
    
     public boolean updateStatus(String id) throws SQLException{
            int srow = tab1.getSelectedRow();
            String status=tab1.getValueAt(srow,6).toString();
            
            
            if(!status.equals("PENDING")){
                JOptionPane.showMessageDialog(this, "STATUS = "+status+" \nALREADY UPDATED","ALREADY UPDATED",1);
                return false;
            }
            str= con.prepareStatement("UPDATE `order_table` SET `STATUS`=? WHERE OID="+id);
            str.setString(1,  "DONE");                
            str.executeUpdate();    
            getAllData(tab1);
            JOptionPane.showMessageDialog(this, "STATUS UPDATED","update",1);
            return true;
       
    }
    
     
     
     
     
     public String[] getMainTableDataForEmial(String id ) throws SQLException{
        str=con.prepareStatement("select* from order_table where OID="+id);
        rs=str.executeQuery();
        while(rs.next()){
            String ID  =rs.getString("OID");
            String name  =rs.getString("NAME");
            String phone =rs.getString("PHONE");
            String email=rs.getString("EMAIL");
            String date=rs.getString("DATE");
            String rdate  =rs.getString("RETURN_DATE");
            String status =rs.getString("STATUS");
            String tweight =rs.getString("T_WEIGHT");
            String advance=rs.getString("T_ADVANCE");
            String advance_gold =rs.getString("T_ADVANCE_GOLD");
            String rate =rs.getString("RATE");
            
           
            String [] row = {ID,name,phone,email,date,rdate,status,rate,tweight,advance,advance_gold};
            return row;
        }
        return null;
    } 
     
     
    public String getData_From_SubTbl_For_Email(String oid  ) throws SQLException{
        String text ="";
        str=con.prepareStatement("select* from order_details where OID="+oid);
        rs=str.executeQuery();
        while(rs.next()){
                    
                    String item       = rs.getString("ITEM");
                    String qty   =   rs.getString("QTY");
                    String weight      =   rs.getString("WEIGHT");
                    
                    text=text
                        +"("+item+")\t       "+weight+"\t\t"+qty+"\n"
                        +"------------------------------------------------------------------\n";
                    
        }
        return text;
    } 
    
     
     
     
     
    public String CreateFullEmailText(String id){
       try{
            String []mainTblData= getMainTableDataForEmial(id);
           
            
            String text="Order ID : "+mainTblData[0]+"\n"
                   +"Name     : "+mainTblData[1]+"\n"
                   +"Phone     : "+mainTblData[2]+"\n"
                   +"Email      : "+mainTblData[3]+"\n"
                   +"Date        : "+mainTblData[4]+"\n"
                   +"_________________________________________\n"
                   +"item\t       qty\t\tweight\n"
                   +"_________________________________________\n";
            text=text+"\n"
                +getData_From_SubTbl_For_Email(id);
                
                if(Integer.parseInt(mainTblData[7])==0 || mainTblData[7].equals("") ){
                    text=text
                        +"\t\tRate Done\t\t: Not Done\n";
                }else if(Integer.parseInt(mainTblData[7])>0){
                    text=text
                        +"\t\tRate Done\t\t: "+mainTblData[7]+"\n";
                }
                
                text=text
                    +"\t\tRequired Weight    : "+mainTblData[8]+"\n"
                    +"\t\tAdvance money     : "+mainTblData[9]+"\n"
                    +"\t\tAdvance Gold         : "+mainTblData[10]+"\n"
                    +"\t\tReturn DATE           : "+mainTblData[5]+"\n"
                    +"_________________________________________\n"
                    +"\t\tThank You For Your Order";
            JOptionPane.showMessageDialog(this, text);
            return text;
            
       }catch(Exception ex){
           System.out.println(ex);
       }
       return null;
    }
    
    
    
    
     public String CreateDoneEmailText(String id){
       try{
            String []mainTblData= getMainTableDataForEmial(id);
            String []shopData= new GetShopInfo().getData();
            
            
            String text="ORDER #"+mainTblData[0]+"\n"
                    + "DEAR  "+mainTblData[1]+" \n"
                    + "_________________________________________\n"
                    + "We are pleased to inform you that your order is now ready for pickup.\n\n"
                    + "Please visit our location at your earliest convenience to collect your items.\n\n"
                    + "If you have any questions or need further assistance, feel free to contact us.\n\n"
                    + "Thank you for your business.\n"
                    + "_________________________________________\n"
                    + "Best regards,\n"
                    + shopData[1]+"\n"
                    + shopData[2]+"\n"
                    + shopData[3];
            JOptionPane.showMessageDialog(this, text);
            return text;
            
       }catch(Exception ex){
           System.out.println(ex);
       }
       return null;
    }
     
     
//     Dear [Customer’s Name],
//
//We are pleased to inform you that your order is now ready for pickup.
//
//Please visit our location at your earliest convenience to collect your items. If you have any questions or need further assistance, feel free to contact us.
//
//Thank you for your business.
//
//Best regards,
//[Your Name]
//[Your Position]
//[Your Contact Information]
//[Company Name]
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void sendEmail(String email ,String id,String sub,String text,String myemail){
        
        
        if(email.equals("")){
            return;
        }
        
        int varify = JOptionPane.showConfirmDialog(this, "Do you want to send EMAIL to ( "+email+" )","confirmation", JOptionPane.YES_NO_OPTION);
        if(varify==0){
            new EmailSend().SendEmail(sub, text, email,myemail,this);
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

        bgpanal21 = new panal.bgpanal2();
        pane21 = new panal.pane2();
        button1 = new ALL_UI_1.Button();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab1 = new tabledark.TableDark();
        searchtxt = new ALL_UI_1.TextField();
        jLabel3 = new javax.swing.JLabel();
        cbox = new ALL.Combobox();
        button2 = new ALL_UI_1.Button();
        button3 = new ALL_UI_1.Button();
        button4 = new ALL_UI_1.Button();
        button5 = new ALL_UI_1.Button();
        button6 = new ALL_UI_1.Button();
        pane31 = new panal.pane3();
        jLabel1 = new javax.swing.JLabel();
        button7 = new ALL_UI_1.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgpanal21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button1.setBackground(new java.awt.Color(255, 204, 204));
        button1.setText("X");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(255, 51, 51));
        button1.setRound(30);
        button1.setShadowColor(new java.awt.Color(0, 0, 0));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        pane21.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 40, 40));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("  ORDER HISTORY");
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
        pane21.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 40));

        bgpanal21.add(pane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jScrollPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jScrollPane1FocusLost(evt);
            }
        });

        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ORDER #", "NAME", "PHONE", "EMAIL", "DATE", "R DATE", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tab1.setShowGrid(true);
        jScrollPane1.setViewportView(tab1);

        bgpanal21.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 1070, 340));

        searchtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchtxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchtxt.setRound(50);
        searchtxt.setShadowColor(new java.awt.Color(255, 0, 0));
        searchtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxtActionPerformed(evt);
            }
        });
        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
        });
        bgpanal21.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 210, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Search by");
        bgpanal21.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 80, 50));

        cbox.setBackground(new java.awt.Color(248, 248, 248));
        cbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "NAME", "PHONE", "EMAIL", "DATE", "RETURN_DATE", "STATUS" }));
        cbox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbox.setLabeText("");
        cbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxActionPerformed(evt);
            }
        });
        bgpanal21.add(cbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 160, 50));

        button2.setBackground(new java.awt.Color(204, 255, 255));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-search-20.png"))); // NOI18N
        button2.setText("GET");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(51, 102, 255));
        button2.setRound(50);
        button2.setShadowColor(new java.awt.Color(255, 255, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        bgpanal21.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 110, 60));

        button3.setBackground(new java.awt.Color(204, 255, 255));
        button3.setForeground(new java.awt.Color(0, 0, 0));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-order-completed-36.png"))); // NOI18N
        button3.setText("ORDER COMPLETE");
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(51, 102, 255));
        button3.setRound(40);
        button3.setShadowColor(new java.awt.Color(255, 255, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        bgpanal21.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 560, 190, 90));

        button4.setBackground(new java.awt.Color(204, 255, 255));
        button4.setForeground(new java.awt.Color(0, 0, 0));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-print-36.png"))); // NOI18N
        button4.setText("PRINT");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(51, 102, 255));
        button4.setRound(40);
        button4.setShadowColor(new java.awt.Color(255, 255, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        bgpanal21.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 560, 120, 90));

        button5.setBackground(new java.awt.Color(255, 204, 204));
        button5.setForeground(new java.awt.Color(0, 0, 0));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-delete-36.png"))); // NOI18N
        button5.setText("DELETE");
        button5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button5.setRippleColor(new java.awt.Color(51, 102, 255));
        button5.setRound(40);
        button5.setShadowColor(new java.awt.Color(255, 255, 255));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        bgpanal21.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 120, 90));

        button6.setBackground(new java.awt.Color(204, 255, 255));
        button6.setForeground(new java.awt.Color(0, 0, 0));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-refresh-60.png"))); // NOI18N
        button6.setText("CLEAR FILTER");
        button6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button6.setRippleColor(new java.awt.Color(51, 102, 255));
        button6.setRound(50);
        button6.setShadowColor(new java.awt.Color(255, 255, 255));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        bgpanal21.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 170, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-order1-64.png"))); // NOI18N
        jLabel1.setText("ORDER HISTORY");
        pane31.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 970, 80));

        bgpanal21.add(pane31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 1070, 80));

        button7.setBackground(new java.awt.Color(0, 204, 204));
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-email-36.png"))); // NOI18N
        button7.setText("SEND EMAIL");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button7.setRound(40);
        button7.setShadowColor(new java.awt.Color(0, 0, 0));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        bgpanal21.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 160, 90));

        getContentPane().add(bgpanal21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
     // TODO add your handling code here:
        SearchForMainTable(cbox.getSelectedItem().toString(),searchtxt.getText());
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        
        int srow = tab1.getSelectedRow();
        String id=tab1.getValueAt(srow,0).toString();
         try {
             updateStatus(id);
         } catch (SQLException ex) {
             Logger.getLogger(Order_history.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        String[]shopinfo=new GetShopInfo().getData();
        if(tab1.getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(this, "Please Select an order to delete\n","data not selected",2);
            return;
         }
        try{
            
            
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/OrderInvoice.jrxml";
            InputStream in = new FileInputStream(filePath);
            JasperDesign jd = JRXmlLoader.load(in);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            int row = tab1.getSelectedRow();
            String ID=tab1.getValueAt(row,0).toString();
            para.put("ID", ID);
            para.put("SHOP_NAME", shopinfo[1]);
            para.put("PHONE", shopinfo[2]);
            para.put("ADDRESS", shopinfo[3]);
            
           
            JasperPrint j = JasperFillManager.fillReport(jr, para,con);
           
            JasperViewer.viewReport(j, false);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
        
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        
        // TODO add your handling code here:
        
        
        
        
        try {
            
            if(tab1.getSelectedRowCount()==0){
                JOptionPane.showMessageDialog(this, "Please Select an order to delete\n","data not selected",2);
                return;
            }
            int row = tab1.getSelectedRow();
            String ID=tab1.getValueAt(row,0).toString();
            if(checkIfIdExist(ID)==false){
               JOptionPane.showMessageDialog(this, "This ID Dont Exist in database\n","Id not found",2);
               return;
            }

            int varify = JOptionPane.showConfirmDialog(this, "Are You Sure You want to delete this ORDER","", JOptionPane.YES_NO_OPTION);
            if(varify==0){
                delete(ID);
                return;
            }
            JOptionPane.showMessageDialog(this, "Request Cancelled\n","Action not completed",3);
            
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this, "ERROR while Deleting:-\n"+ex.getMessage(),"ERROR",2);
        }
    }//GEN-LAST:event_button5ActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        try {
            if(searchtxt.getText().equals("")){
                getAllData(tab1);    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_history.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchtxtKeyReleased

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        cbox.setSelectedIndex(0);
        searchtxt.setText("");
        try {
            getAllData(tab1);
        } catch (SQLException ex) {
            Logger.getLogger(Order_history.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_button6ActionPerformed

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

    private void jScrollPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jScrollPane1FocusLost
        // TODO add your handling code here:
        DefaultTableModel df=(DefaultTableModel)tab1.getModel();
        tab1.clearSelection();
    }//GEN-LAST:event_jScrollPane1FocusLost

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
        
        
        if(tab1.getSelectedRowCount()==0){
                JOptionPane.showMessageDialog(this, "Please Select an order\n","data not selected",2);
                return;
        }
        
        int srow = tab1.getSelectedRow();
        String Status=tab1.getValueAt(srow,6).toString();
        if(Status.equals("PENDING")){
            JOptionPane.showMessageDialog(this, "ORDER STILL PENDING\n","Action not completed",3);
            return;
        }
        usermodel user = new usermodel();
        
        String id=tab1.getValueAt(srow,0).toString();
        String email=tab1.getValueAt(srow,3).toString();
        
        sendEmail(email, id,"ORDER READY",CreateDoneEmailText(id),user.getEmail());
        
    }//GEN-LAST:event_button7ActionPerformed

    private void cboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxActionPerformed

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
            java.util.logging.Logger.getLogger(Order_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order_history().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panal.bgpanal2 bgpanal21;
    private ALL_UI_1.Button button1;
    private ALL_UI_1.Button button2;
    private ALL_UI_1.Button button3;
    private ALL_UI_1.Button button4;
    private ALL_UI_1.Button button5;
    private ALL_UI_1.Button button6;
    private ALL_UI_1.Button button7;
    private ALL.Combobox cbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private panal.pane2 pane21;
    private panal.pane3 pane31;
    private ALL_UI_1.TextField searchtxt;
    private tabledark.TableDark tab1;
    // End of variables declaration//GEN-END:variables
}
