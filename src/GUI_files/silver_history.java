/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_files;

import CODE_files.ConnectDB;
import static java.lang.String.format;
import java.sql.*;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class silver_history extends javax.swing.JFrame {

    Connection con;
    PreparedStatement str;
    int xMouse;
    int yMouse;
    public silver_history() {
        initComponents();
        ImageIcon img = new ImageIcon("src\\ASSETS_files\\icons8-database-syncing-complete-local-drive-and-connected-with-other-pc-48.png");
        this.setIconImage(img.getImage());
        con = new ConnectDB().Connect();
        DBconnect();
        
    }
   
    public void DBconnect(){
        int c;
        try {

            
            str=con.prepareStatement ("SELECT * FROM `silver`");
            

            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)table.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                Vector v2=new Vector();
                for (int a=1;a<=c;a++){
                    v2.add(String.valueOf(rs.getInt("ID")));
                    v2.add(rs.getString("NAME"));
                    v2.add(rs.getString("PHONE"));
                    v2.add(rs.getString("CNIC"));
                    v2.add(rs.getString("RATE"));
                    v2.add(rs.getString("PGRAM"));
                    v2.add(rs.getString("WAZAN"));
                    v2.add(rs.getString("NAG"));
                    v2.add(rs.getString("SAFIWAZAN"));
                    v2.add(rs.getString("KARAT"));
                    v2.add(rs.getString("KAAT"));
                    v2.add(rs.getString("PASA"));
                    v2.add(rs.getString("RAKAM"));
                    v2.add(rs.getString("DATE"));
                   
                }
                df.addRow(v2);
                
                        
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }
    }
    
    
    
    
    
    
    
    public void TBLclick(){
        
        int row = table.getSelectedRow();
        String ID = table.getModel().getValueAt(row, 0).toString();
        String name = table.getModel().getValueAt(row, 1).toString();
        String phone = table.getModel().getValueAt(row, 2).toString();
        String cnic = table.getModel().getValueAt(row, 3).toString();
        String date = table.getModel().getValueAt(row, 13).toString();
        float Rate= Float.parseFloat(  table.getModel().getValueAt(row, 4).toString());
        float Prate= Float.parseFloat( table.getModel().getValueAt(row, 5).toString() );
        float Wazan= Float.parseFloat( table.getModel().getValueAt(row, 6).toString() );
        float Nag= Float.parseFloat(   table.getModel().getValueAt(row, 7).toString() );
        float Safi= Float.parseFloat(  table.getModel().getValueAt(row, 8).toString() );
        float Karat= Float.parseFloat( table.getModel().getValueAt(row, 9).toString() );
        float Kaat=Float.parseFloat(   table.getModel().getValueAt(row, 10).toString() );
        float Pasa= Float.parseFloat(  table.getModel().getValueAt(row, 11).toString() );
        float Rkam= Float.parseFloat(  table.getModel().getValueAt(row, 12).toString() );
        
        
        
       
        
        
         
        jTextArea11.setText(null);
     
        jTextArea11.setText(jTextArea11.getText()+"("+Karat+")_________________________\n");
        jTextArea11.setText(jTextArea11.getText()+date+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Reference\t#"+ID+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Name\t= "+name+"\n");        
        jTextArea11.setText(jTextArea11.getText()+"Cnic\t="+cnic+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Phone\t= "+phone+"\n");
        jTextArea11.setText(jTextArea11.getText()+"_______________________________\n");
        jTextArea11.setText(jTextArea11.getText()+"RATE\t= "+format("%.3f",Rate)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"P/G RATE\t= "+format("%.3f",Prate)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"WAZAN\t= "+format("%.3f",Wazan)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"NAG\t= "+format("%.3f",Nag)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"SAFI WAZAN\t= "+format("%.3f",Safi)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"PASA WAZAN\t= "+format("%.3f",Pasa)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"(TOTAL)\t= "+format("%.3f",Rkam)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"_______________________________");
        
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 246, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PERCHASES HISTORY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Castellar", 1, 48))); // NOI18N

        table.setBackground(new java.awt.Color(255, 204, 204));
        table.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PHONE", "CNIC", "RATE", "P/GRAM RATE", "WAZAN", "NAG", "SAFI WAZAN", "KARAT", "KAAT", "PASA", "RAKAM", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setShowGrid(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 990, 470));

        id.setBackground(new java.awt.Color(204, 255, 255));
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 270, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("SEARCH BY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 100, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAME", "PHONE", "CNIC" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 120, 30));

        jTextArea11.setColumns(20);
        jTextArea11.setRows(5);
        jScrollPane2.setViewportView(jTextArea11);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, -1, 450));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1240, 530));

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 20, 20));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setOpaque(true);
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 20));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 180, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel df=(DefaultTableModel)table.getModel();
            int row=table.getSelectedRow();
            
            String id = (table.getModel().getValueAt(row, 0).toString());
            String nna = (table.getModel().getValueAt(row, 1).toString()) + " INVOICE ";
            int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + nna + "?","Delete",JOptionPane.YES_NO_OPTION);
            if (p==0){
                String Str="DELETE FROM `silver` WHERE ID= '" + id + "'";
                str=con.prepareStatement(Str);
                str.execute();

                JOptionPane.showMessageDialog(null,"Sucessfully Deleted!");
                df.removeRow(row);
                jTextArea11.setText("");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"ERROR"+ e.getMessage());
        }
        
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        // TODO add your handling code here:
        
        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse= evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        // TODO add your handling code here:
        int c;
        if (id.getText().equals("")){
            DBconnect();
        }else{
            String field = jComboBox1.getSelectedItem().toString();
        try{
           
            if(field.equals("ID")){
                str=con.prepareStatement ("SELECT * FROM `silver` where ID="+id.getText()+"");
            }else{
                str=con.prepareStatement ("SELECT * FROM `silver` where "+field+" LIKE '%"+id.getText()+"%'");
            }
            
            
            ResultSet rs=str.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)table.getModel();
            df.setRowCount(0);
                    
            while (rs.next()){
                Vector v2=new Vector();
                for (int a=1;a<=c;a++){
                    v2.add(String.valueOf(rs.getInt("ID")));
                    v2.add(rs.getString("NAME"));
                    v2.add(rs.getString("PHONE"));
                    v2.add(rs.getString("CNIC"));
                    v2.add(rs.getString("RATE"));
                    v2.add(rs.getString("PGRAM"));
                    v2.add(rs.getString("WAZAN"));
                    v2.add(rs.getString("NAG"));
                    v2.add(rs.getString("SAFIWAZAN"));
                    v2.add(rs.getString("KARAT"));
                    v2.add(rs.getString("KAAT"));
                    v2.add(rs.getString("PASA"));
                    v2.add(rs.getString("RAKAM"));
                    v2.add(rs.getString("DATE"));
                   
                }
                df.addRow(v2);
                
                        
            }
           
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e);
        } 
        }
           
        
        
    }//GEN-LAST:event_idKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
       TBLclick();
    }//GEN-LAST:event_tableMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Silver_manage.kye=0;
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(silver_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(silver_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(silver_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(silver_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new silver_history().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
