/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package zakat.calculator;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static java.lang.String.format;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;

/**
 *
 * @author Yahya
 */
public class calculator extends javax.swing.JFrame {

    /**
     * Creates new form calculator
     */
    public calculator() {
        initComponents();
        cbox();
    }

    
      
     public void cbox(){
         if (cb1.getSelectedItem()=="<NONE>")
         {
             n.setText(null);
             w.setText(null);
             r.setText(null);
             tp.setText(null);
             z.setText(null);
             
             w.setEnabled(false);
             r.setEnabled(false);
             tp.setEnabled(false);
             z.setEnabled(false);
             
             
         }else if (cb1.getSelectedItem()=="GOLD_SILVER"){
             // to set all field null
            n.setText(null);
            w.setText(null);
            r.setText(null);
            tp.setText(null);
            z.setText(null);
            // to make all field editable 
            w.setEditable(true);
            r.setEditable(true);
            tp.setEditable(true);
            z.setEditable(true);
            // to show which field we needed and other  
            w.setEnabled(true);
            r.setEnabled(true);
            tp.setEnabled(true);
            z.setEnabled(true);
            
            tp.setEditable(false);
            z.setEditable(false);
            
            
            
         }else if (cb1.getSelectedItem()=="CASH"){
             // to set all field null
            n.setText(null);
            w.setText(null);
            r.setText(null);
            tp.setText(null);
            z.setText(null);
            // to make all field editable 
            w.setEditable(true);
            r.setEditable(true);
            tp.setEditable(true);
            z.setEditable(true);
             // to show which field we needed and other  
            w.setEnabled(false);
            r.setEnabled(false);
            tp.setEnabled(true);
            z.setEnabled(true);
            
            z.setEditable(false);
            tp.setEditable(true);
           
         }
     }
     
     
     
     public String currentdate(){
            
                            Calendar cal=new GregorianCalendar();
                            int month=cal.get(Calendar.MONTH);
                            int year =cal.get(Calendar.YEAR);
                            int day=cal.get(Calendar.DAY_OF_MONTH);
                            if(month==12){
                                month=1;
                               return(day+" / "+(month)+" / "+year);
                            }else{
                                month +=1;
                                return (day+" / "+(month)+" / "+year);
                            }
                            
     }
     
     
     
     
     public void printit(){
        try {
            String printerName="RONGTA 58mm Series Printer";
            printerName =printerName.toLowerCase() ;

            PrintService service = null;
        
            PrintService[] services = PrinterJob.lookupPrintServices();

        
            for (int index = 0; service == null && index < services.length; index++) {

                if (services[index].getName().toLowerCase().indexOf(printerName) >= 0)
                {
                    service = services[index];         
                }
            }
            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f,72f, 72f, MediaPrintableArea.MM)); 
            
//            logo.print(null, null, false, service, attr, false);
           
            jTextArea11.print(null, null, true, service, attr, false);
            
           
        } catch (PrinterException ex) {
            System.out.println(""+ex);
        }
    }
    
     
     
     
     
     
     
     
     
     public void third_panal_print() throws PrinterException{
         
         float zk = Float.parseFloat(z.getText());
         
        if (cb1.getSelectedItem()=="<NONE>"){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER SOME VALUE\nFor ZAKAT CALCULATION");
        }else if (cb1.getSelectedItem()=="GOLD_SILVER"){
            float pg=(float) (Float.parseFloat(r.getText())/12.150);
            
            jTextArea11.setText("");
            jTextArea11.setText("Date:"+currentdate());
            jTextArea11.setText(jTextArea11.getText()+"\n-------------------------------------\n");  
            jTextArea11.setText(jTextArea11.getText()+"Name\t= "+n.getText()+"\n");
            jTextArea11.setText(jTextArea11.getText()+"         ----ZAKAT----\n");
            jTextArea11.setText(jTextArea11.getText()+"WAZAN\t= "+w.getText()+"\n");
            jTextArea11.setText(jTextArea11.getText()+"RATE\t= "+r.getText()+"\n");
            jTextArea11.setText(jTextArea11.getText()+"P/G RATE\t= "+format("%.3f",pg)+"\n");
            jTextArea11.setText(jTextArea11.getText()+"ZAKAT\t= "+format("%.0f",zk)+"\n");
            jTextArea11.setText(jTextArea11.getText()+"-------------------------------------\n"); 
            
            printit();
       
           
        }else if (cb1.getSelectedItem()=="CASH"){
            jTextArea11.setText("");
            jTextArea11.setText("Date:"+currentdate());
            jTextArea11.setText(jTextArea11.getText()+"\n-------------------------------------\n");  
            jTextArea11.setText(jTextArea11.getText()+"Name       = "+n.getText()+"\n");
            jTextArea11.setText(jTextArea11.getText()+"-------------------------------------\n");

            jTextArea11.setText(jTextArea11.getText()+"\nNET WORTH   = "+tp.getText()+"\n\n");
            jTextArea11.setText(jTextArea11.getText()+"---------------ZAKAT--------------\n");
            jTextArea11.setText(jTextArea11.getText()+"ZAKAT       = "+format("%.0f",zk)+"\n");
            jTextArea11.setText(jTextArea11.getText()+"-------------------------------------\n"); 
           printit();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        n = new javax.swing.JTextField();
        cb1 = new javax.swing.JComboBox<>();
        w = new javax.swing.JTextField();
        r = new javax.swing.JTextField();
        tp = new javax.swing.JTextField();
        z = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(239, 237, 237));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(204, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zakat/calculator/zakat.png"))); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel22.setOpaque(true);
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 320, 140));

        n.setBackground(new java.awt.Color(204, 255, 255));
        n.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        n.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        n.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        n.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nKeyPressed(evt);
            }
        });
        jPanel6.add(n, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 220, 40));

        cb1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<NONE>", "GOLD_SILVER", "CASH" }));
        cb1.setBorder(null);
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });
        jPanel6.add(cb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 320, 30));

        w.setBackground(new java.awt.Color(204, 255, 255));
        w.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        w.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        w.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        w.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wKeyPressed(evt);
            }
        });
        jPanel6.add(w, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 220, 40));

        r.setBackground(new java.awt.Color(204, 255, 255));
        r.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rKeyReleased(evt);
            }
        });
        jPanel6.add(r, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 220, 40));

        tp.setBackground(new java.awt.Color(204, 255, 255));
        tp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpActionPerformed(evt);
            }
        });
        tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tpKeyReleased(evt);
            }
        });
        jPanel6.add(tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 220, 40));

        z.setBackground(new java.awt.Color(204, 255, 204));
        z.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        z.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        z.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(z, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 220, 60));

        jLabel3.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ذکوۃ");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 100, 60));

        jLabel31.setBackground(new java.awt.Color(204, 204, 204));
        jLabel31.setFont(new java.awt.Font("Arabic Typesetting", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("ZAKAT CALCULATOR");
        jLabel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));

        jLabel32.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("وزن");
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 100, 40));

        jLabel33.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("ریٹ");
        jLabel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 100, 40));

        jLabel34.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("رقم");
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 100, 40));

        jLabel35.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("نام");
        jLabel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 100, 40));

        jLabel36.setFont(new java.awt.Font("Arabic Typesetting", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("SELECT");
        jLabel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 320, 30));

        jButton10.setBackground(new java.awt.Color(255, 255, 0));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zakat/calculator/icons8-print-64.png"))); // NOI18N
        jButton10.setText("print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 160, 70));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 570));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextArea11.setEditable(false);
        jTextArea11.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextArea11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(jTextArea11);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 270, 570));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            w.requestFocus();
        }

    }//GEN-LAST:event_nKeyPressed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:

        cbox();

    }//GEN-LAST:event_cb1ActionPerformed

    private void wKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            r.requestFocus();
        }
    }//GEN-LAST:event_wKeyPressed

    private void rKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_rKeyPressed

    private void rKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rKeyReleased
        // TODO add your handling code here:

        float W= Float.parseFloat(w.getText());
        float R= Float.parseFloat(r.getText());
        float pgr=(float) (R/12.150);
        float TP=pgr*W;
        float Z=TP/40;
        tp.setText(""+TP);
        z.setText(""+Z);

    }//GEN-LAST:event_rKeyReleased

    private void tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpActionPerformed

    private void tpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpKeyReleased

        float TP= Float.parseFloat(tp.getText());
        float Z=TP/40;
        z.setText(""+Z);

        // TODO add your handling code here:
    }//GEN-LAST:event_tpKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            // TODO add your handling code here:

            third_panal_print();
        } catch (PrinterException ex) {
            Logger.getLogger(calculator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextArea11;
    private javax.swing.JTextField n;
    private javax.swing.JTextField r;
    private javax.swing.JTextField tp;
    private javax.swing.JTextField w;
    private javax.swing.JTextField z;
    // End of variables declaration//GEN-END:variables
}
