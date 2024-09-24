/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author User1
 */
public class moramathistory extends javax.swing.JFrame {

    /**
     * Creates new form moramathistory
     */
     
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    public moramathistory() {
        initComponents();
        ImageIcon img = new ImageIcon("src\\ASSETS_files\\icons8-database-syncing-complete-local-drive-and-connected-with-other-pc-48.png");
        this.setIconImage(img.getImage());
        con= new ConnectDB().Connect();       
        searchtxt.requestFocus();
//        DBconnect();
    }
    
   
    
    
    
    
//    public void print(){
//        
//        
//              DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
//              int row=jTable1.getSelectedRow();
//              String ID1 = df.getValueAt(row, 0).toString();
//              String DATE = (String) df.getValueAt(row, 6);
//              String NAME = df.getValueAt(row, 1).toString();
//              String PHONE = df.getValueAt(row, 2).toString();
//              String WEIGHT = df.getValueAt(row, 3).toString();
//              String DISCRIPTION = df.getValueAt(row, 4).toString();
//              String RDATE = df.getValueAt(row, 5).toString();
//              
//              
//              DefaultTableModel df1=(DefaultTableModel)jTable3.getModel();
//           
//              
//              //////////////////////////////////////////////////////////
//              //////////////////////////////////////////////////////////
//              prt.setText(null);
//              prt.setText(prt.getText()+"                    NHSK\n");
//              prt.setText(prt.getText()+"___________________________\n");
//              
//              prt.setText(prt.getText()+"Receipet no#   "+ID1+"\n"
//                                       +DATE+"\n");
//              prt.setText(prt.getText()+"NAME      :-"+NAME+"\n"
//                                       +"PHONEno:-"+PHONE+"\n");
//              prt.setText(prt.getText()+"_______MORAMMAT_________\n");
//              for (int i=0;i<df1.getRowCount();i++){
//                  String item=(String)df1.getValueAt(i, 0);
//                  String wazan=(String)df1.getValueAt(i, 1);
//                  String discription=(String)df1.getValueAt(i, 2);
//                 prt.setText(prt.getText()+item+"_("+wazan+"_g) + ");
//                 
//                }
//            prt.setText(prt.getText()+"\n_____________________________\n");
//            prt.setText(prt.getText()+"TOTAL WEIGHT :-"+WEIGHT+"\n");
//            prt.setText(prt.getText()+"_____________________________\n");
//            prt.setText(prt.getText()+"description:-\n");
//            prt.setText(prt.getText()+" "+DISCRIPTION+"\n");
//            prt.setText(prt.getText()+"_____________________________\n");
//            prt.setText(prt.getText()+"RETURN DATE : "+RDATE+"\n");
//            prt.setText(prt.getText()+"_____________________________\n"); 
//        
//    } 
//    
    
    
    
    
    
    public void refresh (){
        
        DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
        df.setRowCount(0);
        DefaultTableModel df1=(DefaultTableModel)jTable3.getModel();
        df1.setRowCount(0);
        
    }
    
    
    
    
    
    
    
    
    
    public void DBconnect(){
        int c;
        try {
            str=con.prepareStatement ("SELECT * FROM `moramat`");
            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while (rs.next()){
                Vector v2=new Vector();
                for (int a=1;a<=c;a++){
                    v2.add(String.valueOf(rs.getInt("ID")));
                    v2.add(rs.getString("NAME"));
                    v2.add(rs.getString("PHONE"));
                    v2.add(rs.getString("TOTAL_WEIGHT"));
                    v2.add(rs.getString("RDATE"));
                    v2.add(rs.getString("DATE"));
                    v2.add(rs.getString("STATUS"));
                }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }}
        
    
    
    
    
    
    
    
    
     public void SearchForMainTable(String Search_by,String search){
        int c;
        try {

            if(Search_by.equals("ID")){
                str = con.prepareStatement ("SELECT * FROM `moramat` where ID="+search);
            }else{
                str=con.prepareStatement ("SELECT * FROM `moramat` where "+Search_by+" LIKE '%"+search+"%'");
            }
            
            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                
                String ID = rs.getString("ID");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                String weight = rs.getString("TOTAL_WEIGHT");
                String rdate = rs.getString("RDATE");
                String date = rs.getString("DATE");
                String STATUS = rs.getString("STATUS");
                
                String [] row = {ID,name,phone,weight,rdate,date,STATUS};
               
                df.addRow(row);
                
                        
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        searchtxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        SearchCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME ", "PHONE", "WEIGHT", "RETURN DATE", "DATE", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(204, 0, 0));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1210, 200));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setText("MORAMMAT DETAIL");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton1.setText("DELETE");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 100, 60));

        searchtxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
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
        jPanel1.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 130, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Search by ID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setText("get");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 60, 30));

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("X");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 50, 30));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setFont(new java.awt.Font("Arabic Typesetting", 0, 24)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setRowHeight(30);
        jScrollPane4.setViewportView(jTable3);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 1210, 170));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("MORAMMAT DETAIL");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jButton6.setBackground(new java.awt.Color(51, 255, 51));
        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton6.setText("DONE");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 570, 120, 60));

        jButton7.setBackground(new java.awt.Color(255, 102, 102));
        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton7.setText("REFRESH");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 130, 60));

        SearchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAME", "PHONE", "TOTAL_WEIGHT", "RDATE", "DATE", "STATUS" }));
        SearchCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCBActionPerformed(evt);
            }
        });
        jPanel1.add(SearchCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
        int row=jTable1.getSelectedRow();
        String id = (jTable1.getModel().getValueAt(row, 0).toString());
        DefaultTableModel df1=(DefaultTableModel)jTable3.getModel();
        try{
            str=con.prepareStatement ("SELECT * FROM `moramat_details` where MID="+id);
            rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            int c=rss.getColumnCount();
            df1.setRowCount(0);
            while (rs.next()){
               Vector v2=new Vector();
               for (int a=1;a<=c;a++){
                   v2.add(rs.getString("item"));
                   v2.add(rs.getString("weight"));
                   v2.add(rs.getString("discription"));
               }
               df1.addRow(v2);
           }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
       
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         try
        {
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            int row=jTable1.getSelectedRow();
            String id = (jTable1.getModel().getValueAt(row, 0).toString());
            String nna = (jTable1.getModel().getValueAt(row, 1).toString()) + " INVOICE ";
            int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + nna + "?","Delete",JOptionPane.YES_NO_OPTION);
            if (p==0){
                
                String Str="DELETE FROM `moramat` WHERE ID= '" + id + "'";
                str=con.prepareStatement(Str);
                str.executeUpdate();
                JOptionPane.showMessageDialog(null,"Sucessfully Deleted!");
                df.removeRow(row);
                refresh();
            }
        }
        catch(Exception e)
        {
                JOptionPane.showMessageDialog(rootPane, e);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        SearchForMainTable(SearchCB.getSelectedItem().toString(), searchtxt.getText());
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        MORAMAT.kye=0;
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_searchtxtKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
         try
        {
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            int row=jTable1.getSelectedRow();
            String id = (jTable1.getModel().getValueAt(row, 0).toString());
            String nna = (jTable1.getModel().getValueAt(row, 1).toString()) + " INVOICE ";
            int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to UPDATE " + nna + " STATUS?","Completed",JOptionPane.YES_NO_OPTION);
            if (p==0){
                
                String Str="update moramat set STATUS='DONE' WHERE ID= '" + id + "'";
                str=con.prepareStatement(Str);
                str.execute();
                JOptionPane.showMessageDialog(null,"Sucessfully UPDATED!");
                refresh();
                
            }
        }
        catch(Exception e)
        {
                JOptionPane.showMessageDialog(rootPane, e);
        }
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void SearchCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCBActionPerformed
        
    }//GEN-LAST:event_SearchCBActionPerformed

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed
        // TODO add your handling code here:
         SearchForMainTable(SearchCB.getSelectedItem().toString(), searchtxt.getText());
         searchtxt.setText("");
         
    }//GEN-LAST:event_searchtxtActionPerformed

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
            java.util.logging.Logger.getLogger(moramathistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(moramathistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(moramathistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(moramathistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new moramathistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> SearchCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField searchtxt;
    // End of variables declaration//GEN-END:variables
}
