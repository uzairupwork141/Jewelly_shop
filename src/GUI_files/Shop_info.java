/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.OnlyNumbers;
import CODE_files.usermodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gouhar Ali
 */
public class Shop_info extends javax.swing.JFrame {

    /**
     * Creates new form Account_info
     */
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    int xMouse;
    int yMouse;
    
    public Shop_info() {
        initComponents();
        ImageIcon img = new ImageIcon("src/ASSETS_files/pngwing.com.png");
        this.setIconImage(img.getImage());
        con= new ConnectDB(). Connect();
        getData();
    }
    
    
    
    
    
    public void getData(){
       try{
          
          str = con.prepareStatement("Select* from shop_details where SHOP_ID=1");
          rs=str.executeQuery();
          while(rs.next()){
            IDtxt.setText(rs.getString("SHOP_ID"));
            NAMEtxt.setText(rs.getString("SHOP_NAME"));
            PHONEtxt.setText(rs.getString("SHOP_PHONE"));
            ADDRESStxt.setText(rs.getString("SHOP_ADDRESS"));
          
          }
          
      }catch(Exception ex){
          JOptionPane.showMessageDialog(this, ex.getMessage());
      }
    }

    
    
    public boolean update(String field,String data,String id){
        try{      
            str=con.prepareStatement ("UPDATE `shop_details` SET `"+field+"`='"+data+"' WHERE SHOP_ID="+id);
            str.execute();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
     
     
     
    
    
    public void update(String field){
     
        
        String newdata=JOptionPane.showInputDialog(this,"ENTER NEW "+field+"");
        
        if (newdata==null){
                JOptionPane.showMessageDialog(this,"REQUEST CANCELLED");
                return;
        }
        
        if(newdata.equals("")){
            JOptionPane.showMessageDialog(this, "ERROR :- Fields empty\n","ERROR",2);
            return;
        }
        
        boolean check = update(field, newdata,IDtxt.getText());
        if(check==true){
            JOptionPane.showMessageDialog(this, "SAVED\n"+field+" = "+newdata,"SAVED",1);
            getData();
            return;
        }
        
       
        JOptionPane.showMessageDialog(this, "ERROR :- database error","ERROR",2);
        
        
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        IDtxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NAMEtxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PHONEtxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ADDRESStxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IDtxt.setEditable(false);
        IDtxt.setBackground(new java.awt.Color(255, 204, 204));
        IDtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        IDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(IDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 340, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("NAME");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, 40));

        NAMEtxt.setEditable(false);
        NAMEtxt.setBackground(new java.awt.Color(204, 255, 255));
        NAMEtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NAMEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NAMEtxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(NAMEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 340, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("PHONE");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, 40));

        PHONEtxt.setEditable(false);
        PHONEtxt.setBackground(new java.awt.Color(204, 255, 255));
        PHONEtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PHONEtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PHONEtxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(PHONEtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 340, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("ADDRESS");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 170, 90));

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-edit-30.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 40, 40));

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-edit-30.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 40, 40));

        jButton5.setBackground(new java.awt.Color(204, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-edit-30.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 40, 90));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("ID");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 30));

        ADDRESStxt.setEditable(false);
        ADDRESStxt.setBackground(new java.awt.Color(204, 255, 255));
        ADDRESStxt.setColumns(20);
        ADDRESStxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ADDRESStxt.setRows(5);
        ADDRESStxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(ADDRESStxt);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 340, 90));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 600, 250));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-admin-64.png"))); // NOI18N
        jLabel2.setText("USER INFORMATION");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 620, 360));

        jLabel6.setBackground(new java.awt.Color(255, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 30, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("USER INFO");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 30));

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel9.setOpaque(true);
        jLabel9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel9MouseDragged(evt);
            }
        });
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         update("SHOP_ADDRESS");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Setting.Skye=0;
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        update("SHOP_NAME");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        update("SHOP_PHONE");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseDragged
        // TODO add your handling code here:

        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);
    }//GEN-LAST:event_jLabel9MouseDragged

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse= evt.getY();
    }//GEN-LAST:event_jLabel9MousePressed

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
            java.util.logging.Logger.getLogger(Shop_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Shop_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Shop_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Shop_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shop_info().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ADDRESStxt;
    private javax.swing.JTextField IDtxt;
    private javax.swing.JTextField NAMEtxt;
    private javax.swing.JTextField PHONEtxt;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
