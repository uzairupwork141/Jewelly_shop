/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gouhar Ali
 */
public class AfterExp extends javax.swing.JFrame {

    /**
     * Creates new form NotExp
     */
    Connection con ;
    PreparedStatement str;
    ResultSet rs;
    public AfterExp() {
        initComponents();
        ImageIcon img = new ImageIcon("src/ASSETS_files/pngwing.com.png");
        this.setIconImage(img.getImage());
        con=new ConnectDB().Connect();
        englbl.setText(" this software is on free trial till "+getLastDate());
        urdlbl.setText("یہ سافٹ ویئر " +getLastDate()+" تک مفت ہے۔ ");
    }
    
    
    
    
    
     public String getLastDate(){
        try{
           
            
                str=con.prepareStatement("select* from trial where ID=1");
                rs=str.executeQuery();
                if(rs.next()){
                    int d=rs.getInt("day");
                    int m=rs.getInt("month");
                    int y=rs.getInt("year");
                    
                   String D=d+"/"+m+"/"+y;
                   return D;
                }
            return "00/00/0000";
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,  "ERROR:-\n"+ex.getMessage());
             return "00/00/0000";
        }
        
    }
    
    
    
    
    
    public void activate (){
        if(kyetxt.getText().equals("khankhan1234?")){
            try{
                str=con.prepareStatement("UPDATE `software_status` SET `status`='activated' WHERE id=1");
                str.executeUpdate();
                new LOGIN().setVisible(true);
                dispose();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "WRONG KEY ENTERED");
                System.exit(0);
            }
        }else{
            JOptionPane.showMessageDialog(this, "WRONG KEY ENTERED");
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
        urdlbl = new javax.swing.JLabel();
        englbl = new javax.swing.JLabel();
        kyetxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Free Trial Ended");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(227, 225, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        urdlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        urdlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        urdlbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(urdlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 490, 40));

        englbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        englbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        englbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(englbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 40));

        kyetxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        kyetxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(kyetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 420, 40));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("ACTIVATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 420, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRATION KEY");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 420, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 330));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        activate();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AfterExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AfterExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AfterExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AfterExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AfterExp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel englbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField kyetxt;
    private javax.swing.JLabel urdlbl;
    // End of variables declaration//GEN-END:variables
}
