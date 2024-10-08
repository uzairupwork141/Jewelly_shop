
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package GUI_files;

import CODE_files.ConnectDB;
import CODE_files.OnlyNumbers;
import static GUI_files.Gold_managing.kye;
import java.sql.Connection;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.String.format;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hp
 */
public class Silver_manage extends javax.swing.JFrame {
    public static final Color VERY_LIGHT_RED = new Color(255,204,204);
     public static final Color VERY_LIGHT_blue = new Color(204,204,255);
    Connection con;
    PreparedStatement str;
    ResultSet rs;
    int xMouse;
    int yMouse;
    static int kye=0;
    
    public Silver_manage() {
        con = new ConnectDB().Connect();
        initComponents();
        ImageIcon img = new ImageIcon("src/ASSETS_files/pngwing.com.png");
        this.setIconImage(img.getImage());
        d.setText(currentdate());
        Get_Set_Id();
    }
    
    
    
    
    
    
    
    public void onlynumbers (JTextField tf,KeyEvent ke){
        String value = tf.getText();
        int l = value.length();
        if((ke.getKeyChar()>='A' && ke.getKeyChar()<='Z' || ke.getKeyChar()>='a' && ke.getKeyChar()<='z') ){
            tf.setText("");
            JOptionPane.showMessageDialog(this,"INVALID ENTRY\n INPUT TYPE NOT ALLOWD");
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
    
    
    
    
    
    
    public void clear1()
    {
        serch.setText("");
        name.setText("");
        phone.setText("");
        cnic.setText("");
        RATE.setText("");
        GRATE.setText("0.00");
        wazan.setText("0.00");
        
        nag.setText("0.00");
        twazan.setText("");
        karat.setText("0.00");
        kat.setText("");
        pasa.setText("");
        rakam.setText("");
        jButton2.setText("submit");
        d.setText(currentdate());
        Get_Set_Id();    
    }
    
    
    
    
    
    
    public void calculate(){
        try{
            float pgram=Float.parseFloat(GRATE.getText());
            float krt=Float.parseFloat(karat.getText());
            float wght=Float.parseFloat(twazan.getText());
            float k=krt/24;
            float pG=k*wght;
            float Kaat=wght-pG;
            int Price=(int)(pG*pgram);
            rakam.setText(""+Price);
            pasa.setText(""+pG);
            kat.setText(""+Kaat);
        }catch(Exception ex){
             System.out.println("error"+ex);
        }
        
    }
    
    
    
    
    
 
    
    public void Get_Set_Id(){
//        
         try{
                    str=con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'silver' ");
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
    
    
    ////////////////////////////////////////////////
    
    
    public boolean Check_Id(String id){
//        
         try{
                    
            str=con.prepareStatement("SELECT* FROM 'perchases' where ID="+id+" ");
            rs=str.executeQuery();
            if (rs.next()){
    
                return true;
            }
                    
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex,"error",2);
        }
         
        return false;
    }
    
    
    
    
    
    
    ///////////////////////////////////////////////////////
    public String currentdate()
    {
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
     
     
     
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //---------------------------------------------------SUBMIT BUTTON FUNCTION ------------------------------------------------------------------------//
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void submit(String Id){
         
        String Name=name.getText();
        String Phone=phone.getText();
        String Cnic=cnic.getText();
        String Rate=RATE.getText();
        String Prate=GRATE.getText();
        String Wazan=wazan.getText();
        
        String Nag=nag.getText();
        String Safi=twazan.getText();
        String Karat=karat.getText();
        String Kaat=kat.getText();
        String Pasa=pasa.getText();
        String Rkam=rakam.getText();
        String date=d.getText();
        try{
                    
                    str=con.prepareStatement("INSERT INTO `silver`(`ID`,`NAME`, `PHONE`, `CNIC`, `RATE`, `PGRAM`, `WAZAN`, `NAG`, `SAFIWAZAN`, `KARAT`, `KAAT`, `PASA`, `RAKAM`, `DATE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    str.setString(1, Id);
                    str.setString(2, Name);
                    str.setString(3, Phone);
                    str.setString(4, Cnic);
                    str.setString(5, Rate);
                    str.setString(6, Prate);
                    str.setString(7, Wazan);
                    str.setString(8, Nag);
                    str.setString(9, Safi);
                    str.setString(10,Karat);
                    str.setString(11,Kaat);
                    str.setString(12,Pasa);
                    str.setString(13,Rkam);
                    str.setString(14,date);
                    str.executeUpdate();
                    jButton2.setText("update");
                    JOptionPane.showMessageDialog(jPanel2,"DATA SAVED","SUCCESS",1);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"ERROR :- "+ex.getMessage(),"error",2);
        }
         
    }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     
     
     
     
     
     
     
     
     
     
     
      public void update(String id){
         
        String Name=name.getText();
        String Phone=phone.getText();
        String Cnic=cnic.getText();
        String Rate=RATE.getText();
        String Prate=GRATE.getText();
        String Wazan=wazan.getText();
        String Nag=nag.getText();
        String Safi=twazan.getText();
        String Karat=karat.getText();
        String Kaat=kat.getText();
        String Pasa=pasa.getText();
        String Rkam=rakam.getText();
        String date=d.getText();
        try{
                    str=con.prepareStatement("update `silver` set `NAME`=?, `PHONE`=?, `CNIC`=?, `RATE`=?, `PGRAM`=?, `WAZAN`=?,  `NAG`=?, `SAFIWAZAN`=?, `KARAT`=?, `KAAT`=?, `PASA`=?, `RAKAM`=?  where ID = "+id+"");
                    str.setString(1, Name);
                    str.setString(2, Phone);
                    str.setString(3, Cnic);
                    str.setString(4, Rate);
                    str.setString(5, Prate);
                    str.setString(6, Wazan);
                    str.setString(7, Nag);
                    str.setString(8, Safi);
                    str.setString(9,Karat);
                    str.setString(10,Kaat);
                    str.setString(11,Pasa);
                    str.setString(12,Rkam);
                    str.executeUpdate();
                    JOptionPane.showMessageDialog(this,"DATA UPDATED","SUCCESS",1);
        }catch(SQLException ex)
        {
          
            JOptionPane.showMessageDialog(this,ex.getMessage(),"error",2);
        }
         
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //---------------------------------------------------First_panal_print FUNCTION ---------------------------------------------------------------------//
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void First_panal_print(){
        float Rate= Float.parseFloat(RATE.getText());
        float Prate= Float.parseFloat(GRATE.getText());
        float Wazan= Float.parseFloat(wazan.getText());
        float Nag= Float.parseFloat(nag.getText());
        float Safi= Float.parseFloat(twazan.getText());
        float Karat= Float.parseFloat(karat.getText());
        float Kaat=Float.parseFloat(kat.getText());
        float Pasa= Float.parseFloat(pasa.getText());
        float Rkam= Float.parseFloat(rakam.getText());
        
        
       
        
        
         
        jTextArea11.setText(null);
        if(jCheckBox1.isSelected()){
            jTextArea11.setText(jTextArea11.getText()+"\tYASIR GOLD( "+Karat+" )\n");    
        }
        
        
        jTextArea11.setText(jTextArea11.getText()+"_______________________________\n");
        jTextArea11.setText(jTextArea11.getText()+d.getText()+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Reference# "+IDtxt.getText()+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Name      = "+name.getText()+"\n");        
        jTextArea11.setText(jTextArea11.getText()+"Cnic        = "+cnic.getText()+"\n");
        jTextArea11.setText(jTextArea11.getText()+"Phone     = "+phone.getText()+"\n");
        jTextArea11.setText(jTextArea11.getText()+"_______________________________\n");
        jTextArea11.setText(jTextArea11.getText()+"RATE\t= "+format("%.3f",Rate)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"P/G RATE\t= "+format("%.3f",Prate)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"WAZAN\t= "+format("%.3f",Wazan)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"NAG\t= "+format("%.3f",Nag)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"SAFI WAZAN\t= "+format("%.3f",Safi)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"PASA WAZAN\t= "+format("%.3f",Pasa)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"(TOTAL)\t= "+format("%.3f",Rkam)+"\n");
        jTextArea11.setText(jTextArea11.getText()+"_______________________________");
        printit();
     }
     
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     
     
     
     
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //-------------------------------------------------------GET BUTTON FUNCTION ------------------------------------------------------------------------//
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void get_button(){  
        int c;
        try {
            
            str=con.prepareStatement ("SELECT * FROM `silver` WHERE ID=?");
            str.setString(1, serch.getText());
            rs=str.executeQuery();
                    if (rs.next()){
                    String addID=(rs.getString("ID"));
                    IDtxt.setText(addID);
                    String add1=(rs.getString("NAME"));
                    name.setText(add1);
                    String add2=(rs.getString("PHONE"));
                    phone.setText(add2);
                    String add3=(rs.getString("CNIC"));
                    cnic.setText(add3);
                    String add4=(rs.getString("RATE"));
                    RATE.setText(add4);
                    String add5=(rs.getString("PGRAM"));
                    GRATE.setText(add5);
                    String add6=(rs.getString("WAZAN"));
                    wazan.setText(add6);
                    String add8=(rs.getString("NAG"));
                    nag.setText(add8);
                    String add9=(rs.getString("SAFIWAZAN"));
                    twazan.setText(add9);
                    String add10=(rs.getString("KARAT"));
                    karat.setText(add10);
                    String add11=(rs.getString("KAAT"));
                    kat.setText(add11);
                    String add12=(rs.getString("PASA"));
                    pasa.setText(add12);
                    String add13=(rs.getString("RAKAM"));
                    rakam.setText(add13);
                    String add14=(rs.getString("DATE"));
                    d.setText(add14);
                    jButton2.setText("update");
                    }else{
                        JOptionPane.showMessageDialog(this,"NO DATA AVALIBALE","CONNECTION ",2);

                    }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,ex,"CONNECTION ",3);
        }
     }
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     
     
     
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //--------------------------------------------------- secend_panal_print BUTTON FUNCTION -----------------------------------------------------------//
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//     public void secend_panal_print() {
//        jTextArea11.setText(null);
//        jTextArea11.setText(jTextArea11.getText()+"date = "+currentdate()+"\n");
//        jTextArea11.setText(jTextArea11.getText()+"__________________________\n");        
//        jTextArea11.setText(jTextArea11.getText()+"Name\t= "+jTextField15.getText()+"\n");
//        jTextArea11.setText(jTextArea11.getText()+"__________________________\n");
//        jTextArea11.setText(jTextArea11.getText()+"RTAE\t= "+jTextField16.getText()+"\n");        
//        jTextArea11.setText(jTextArea11.getText()+"p/GRAM\t= "+jTextField17.getText()+"\n");        
//        jTextArea11.setText(jTextArea11.getText()+"WAZAN\t= "+jTextField18.getText()+"\n");        
//        jTextArea11.setText(jTextArea11.getText()+"MAZDORI\t= "+mzdori.getText()+"\n");        
//        jTextArea11.setText(jTextArea11.getText()+"TOTAL\t= "+tpr.getText()+"\n");        
//        jTextArea11.setText(jTextArea11.getText()+"__________________________\n");
//        printit();
//     }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     
   
     
     
    public String removeLastChar(String str) 
    {
       
        str = str.substring(0, str.length()-1);
     
        return str;
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
     
     
     
     
     
     
     ///////////////////////////////////////////
     
     
     
     public void weightCal(){
        try{
        float Wazan=Float.parseFloat(wazan.getText());
        float Nag=Float.parseFloat(nag.getText());
        float Twazan= Wazan-Nag;
        twazan.setText(""+Twazan);
        }catch(Exception e){
            System.out.println("EXCEPTION :- "+e);
        }
     }
     
     
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        serch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pasa = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        cnic = new javax.swing.JTextField();
        RATE = new javax.swing.JTextField();
        GRATE = new javax.swing.JTextField();
        wazan = new javax.swing.JTextField();
        nag = new javax.swing.JTextField();
        twazan = new javax.swing.JTextField();
        karat = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        kat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        rakam = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        IDtxt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextPane();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton13 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SILVER", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(239, 237, 237));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serch.setBackground(new java.awt.Color(204, 255, 255));
        serch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        serch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        serch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serchActionPerformed(evt);
            }
        });
        jPanel3.add(serch, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 190, 30));

        jButton7.setBackground(new java.awt.Color(153, 255, 51));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("get");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 60, 30));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("حوالہ نمبر دے کر ر ریکارڈز چیک کرے");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 280, 30));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("حوالہ نمبر");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 80, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 70));

        pasa.setEditable(false);
        pasa.setBackground(new java.awt.Color(255, 204, 204));
        pasa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(pasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 240, 30));

        phone.setBackground(new java.awt.Color(204, 255, 255));
        phone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        phone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phone.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 240, 30));

        cnic.setBackground(new java.awt.Color(204, 255, 255));
        cnic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cnic.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cnic.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cnic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cnicKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cnicKeyTyped(evt);
            }
        });
        jPanel2.add(cnic, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 240, 30));

        RATE.setBackground(new java.awt.Color(204, 255, 255));
        RATE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RATE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RATE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        RATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RATEActionPerformed(evt);
            }
        });
        RATE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RATEKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RATEKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RATEKeyTyped(evt);
            }
        });
        jPanel2.add(RATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 240, 30));

        GRATE.setEditable(false);
        GRATE.setBackground(new java.awt.Color(255, 204, 204));
        GRATE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GRATE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        GRATE.setText("0.00\n");
        GRATE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(GRATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 240, 30));

        wazan.setBackground(new java.awt.Color(204, 255, 255));
        wazan.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        wazan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        wazan.setText("0.00");
        wazan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        wazan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wazanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                wazanFocusLost(evt);
            }
        });
        wazan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wazanMouseClicked(evt);
            }
        });
        wazan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wazanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                wazanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                wazanKeyTyped(evt);
            }
        });
        jPanel2.add(wazan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 240, 50));

        nag.setBackground(new java.awt.Color(204, 255, 255));
        nag.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nag.setText("0.00");
        nag.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nagFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nagFocusLost(evt);
            }
        });
        nag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nagMouseClicked(evt);
            }
        });
        nag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nagActionPerformed(evt);
            }
        });
        nag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nagKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nagKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nagKeyTyped(evt);
            }
        });
        jPanel2.add(nag, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 240, 30));

        twazan.setEditable(false);
        twazan.setBackground(new java.awt.Color(255, 204, 204));
        twazan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        twazan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        twazan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(twazan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 240, 30));

        karat.setBackground(new java.awt.Color(204, 255, 255));
        karat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        karat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        karat.setText("0.00");
        karat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        karat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                karatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                karatFocusLost(evt);
            }
        });
        karat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                karatActionPerformed(evt);
            }
        });
        karat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                karatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                karatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                karatKeyTyped(evt);
            }
        });
        jPanel2.add(karat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 240, 30));

        name.setBackground(new java.awt.Color(204, 255, 255));
        name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 240, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("کاٹ");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 120, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("موبائل فون");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 120, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("شناختی کارڈ");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 120, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("نگ");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 120, 30));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("صافی وزن");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 120, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("کیرات");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 120, 30));

        kat.setEditable(false);
        kat.setBackground(new java.awt.Color(255, 204, 204));
        kat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(kat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 240, 30));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("نام");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 120, 30));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("ریٹ");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 120, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("فی گرام ریٹ");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 120, 30));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("پاسہ");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 120, 30));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Arabic Typesetting", 1, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("رقم");
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 120, 50));

        rakam.setEditable(false);
        rakam.setBackground(new java.awt.Color(204, 255, 204));
        rakam.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rakam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rakam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(rakam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 240, 50));

        d.setEditable(false);
        d.setBackground(new java.awt.Color(255, 204, 204));
        d.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        d.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 240, 30));

        IDtxt.setEditable(false);
        IDtxt.setBackground(new java.awt.Color(255, 204, 204));
        IDtxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        IDtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(IDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 240, 30));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("تاریخ");
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 120, 30));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 100, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, 30));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 30));

        jButton12.setBackground(new java.awt.Color(204, 255, 255));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setText("CALCULATE");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jButton8.setBackground(new java.awt.Color(255, 255, 0));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("HISTORY");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, 30));

        jButton11.setBackground(new java.awt.Color(255, 255, 0));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS_files/icons8-refresh-60.png"))); // NOI18N
        jButton11.setText("REFRESH");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 210, 70));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 630, 90));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("حوالہ نمبر");
        jLabel37.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 120, 30));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("وزن");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 120, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 650, 650));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextArea11.setEditable(false);
        jTextArea11.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(jTextArea11);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 270, 550));

        jCheckBox1.setBackground(new java.awt.Color(204, 204, 204));
        jCheckBox1.setText("Print Logo");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBox1.setBorderPainted(true);
        jCheckBox1.setBorderPaintedFlat(true);
        jCheckBox1.setOpaque(true);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 270, 30));

        jButton13.setBackground(new java.awt.Color(255, 255, 0));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setText("clear");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 620, 270, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 950, 700));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("X");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 40, 30));

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton9.setText("-");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 40, 30));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
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
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
     clear1();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jButton2.getText().equals("submit")){
            submit(IDtxt.getText());
        }else if(jButton2.getText().equals("update")){
            update(IDtxt.getText());
        }else{
            JOptionPane.showMessageDialog(this, "DATA SAVING ERROR");
        }
        
       
       
       
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            if (jButton2.getText().equals("submit")){
                submit(IDtxt.getText());
            }else if(jButton2.getText().equals("update")){
                update(IDtxt.getText());
            }
//            First_panal_print();
            
            File currentDir = new File(".");
	    String basePath = currentDir.getCanonicalPath();
	    // Define file path
	    String filePath = basePath + "/src/Reports/ReturnSilver.jrxml";
            InputStream in = new FileInputStream(filePath);
            JasperDesign jd = JRXmlLoader.load(in); 
//            String sql="select* from silver where ID="+IDtxt.getText();
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            para.put("ID", IDtxt.getText());
            JasperPrint j = JasperFillManager.fillReport(jr, para,con);
            JasperViewer.viewReport(j,false);
        
        
        }catch(Exception ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "ERROR:-\n"+ex);
        }
            
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(kye>0){
            JOptionPane.showMessageDialog(this, "A window is already Open");
            return;
        }
        
        
        
        HOME.kye=0;        
        dispose();
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        
        
        
        jButton6.setBackground(Color.red);
        
        
        
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        
        
        
        jButton6.setBackground(Color.white);
        
        
        
    }//GEN-LAST:event_jButton6MouseExited

    private void serchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serchActionPerformed

    private void RATEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RATEKeyPressed
        // TODO add your handling code here
             
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) 
          {
          // Enter was pressed. Your code goes here.
           
            if (RATE.getText().equals("")){
                GRATE.setText("0.00");
            }
            wazan.requestFocus(true);
           }  
          
          
     
        
    }//GEN-LAST:event_RATEKeyPressed

    private void wazanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wazanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
           nag.requestFocus();
        }  
      
        
    }//GEN-LAST:event_wazanKeyPressed

    private void RATEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RATEKeyReleased
        // TODO add your handling code here:
        
        if (!RATE.getText().equals("")){
 
                onlynumbers(RATE,evt);
            }
        
        
        try{
         if (RATE.getText().equals("")){
            GRATE.setText("0.00");
        }else{
            float a=Float.parseFloat(RATE.getText());
            float b = (float)(a / 12.150);
            GRATE.setText(""+b);
        }
         
        calculate();
        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        
        
        
    }//GEN-LAST:event_RATEKeyReleased

    private void wazanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wazanKeyReleased
        // TODO add your handling code here:
        
         if (!wazan.getText().equals("")){
 
                onlynumbers(wazan,evt);
            }
        
        
       weightCal();
       calculate();
        
        
    }//GEN-LAST:event_wazanKeyReleased

    private void nagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nagKeyReleased
        // TODO add your handling code here:
        
             if (!nag.getText().equals("")){
 
                onlynumbers(nag,evt);
            }
        
        
       weightCal();
       calculate();
        
    }//GEN-LAST:event_nagKeyReleased

    private void wazanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wazanMouseClicked
        // TODO add your handling code here:
        
        
        
        if (wazan.getText().equals(null)||wazan.getText().equals("0.00")){
            wazan.setText("");
            }
        
        
        
    }//GEN-LAST:event_wazanMouseClicked

    private void nagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nagMouseClicked
        // TODO add your handling code here:
        
        
            if (nag.getText().equals(null)||nag.getText().equals("0.00")){
                nag.setText("");
            }
            
            
            
    }//GEN-LAST:event_nagMouseClicked

    private void karatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_karatKeyReleased
        // TODO add your handling code here:
       
        
        try{
            checkKarat(karat);
            calculate();
           
        }catch(Exception ex){
             System.out.println("Exception :-"+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_karatKeyReleased

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
        
        
        
        jButton8.setBackground(Color.green);
        
        
        
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        // TODO add your handling code here:
        
        
        
        jButton8.setBackground(Color.yellow);
        
        
        
    }//GEN-LAST:event_jButton8MouseExited

    private void wazanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wazanFocusLost
        // TODO add your handling code here:
        
        
         if (wazan.getText().equals("")){
            wazan.setText("0.00");
        }
         
         weightCal();
         
    }//GEN-LAST:event_wazanFocusLost

    private void nagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nagFocusLost
        // TODO add your handling code here:
        
        
         if (nag.getText().equals("")){
            nag.setText("0.00");
        }
         weightCal();
         
    }//GEN-LAST:event_nagFocusLost

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        
        get_button();
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(kye>0){
            JOptionPane.showMessageDialog(this, "A window is already Open");
            return;
        }
        kye++;
        
        silver_history P =new silver_history();
        P.setVisible(true);
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void cnicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnicKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
          // Enter was pressed. Your code goes here.
          RATE.requestFocus();
        }  
     
        
        if (cnic.getText().length()==5 ||cnic.getText().length()==13  ){
            cnic.setText(cnic.getText()+"-");
        }
        if(cnic.getText().length()>14){
            cnic.setForeground(Color.red);
        }else{
            cnic.setForeground(Color.black);
        }
        
        
    }//GEN-LAST:event_cnicKeyPressed

    private void wazanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wazanFocusGained
        // TODO add your handling code here:
        if (wazan.getText().equals("0.00")){
            wazan.setText("");
        }
    }//GEN-LAST:event_wazanFocusGained

    private void nagFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nagFocusGained
        // TODO add your handling code here:
        if (nag.getText().equals("0.00")){
            nag.setText("");
        }
                
    }//GEN-LAST:event_nagFocusGained

    private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
          phone.requestFocus();
        }  
      
    }//GEN-LAST:event_nameKeyPressed

    private void phoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      cnic.requestFocus();
   }  
      
    }//GEN-LAST:event_phoneKeyPressed

    private void nagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nagKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            karat.requestFocus();
        }  
      
    }//GEN-LAST:event_nagKeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        new Silver_manage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void RATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RATEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RATEActionPerformed

    private void karatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_karatFocusGained
        // TODO add your handling code here:
        if(karat.getText().equals("0.00")){
            karat.setText("");
                    
        }
    }//GEN-LAST:event_karatFocusGained

    private void karatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_karatFocusLost
        // TODO add your handling code here:
        if(karat.getText().equals("")){
            karat.setText("0.00");
        }
        
    }//GEN-LAST:event_karatFocusLost

    private void karatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_karatActionPerformed
        // TODO add your handling code here:
         if(karat.getText().equals("")){
            karat.setText("0.00");
        }  
        
         try{
            if (karat.getText().equals(null)){
                rakam.setText("");
                pasa.setText("");

            }else{
                float pgram=Float.parseFloat(GRATE.getText());
                float krt=Float.parseFloat(karat.getText());
                float wght=Float.parseFloat(twazan.getText());
                float k=krt/24;
                float pG=k*wght;
                float Kaat=wght-pG;
                int Price=(int)(pG*pgram);
                rakam.setText(""+Price);
                pasa.setText(""+pG);
                kat.setText(""+Kaat);
            }
        }catch(Exception ex){
             System.out.println("Exception :-"+ex);
        }
        
    }//GEN-LAST:event_karatActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        calculate();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTextArea11.setText(null);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_phoneKeyTyped

    private void cnicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnicKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_cnicKeyTyped

    private void RATEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RATEKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_RATEKeyTyped

    private void wazanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wazanKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_wazanKeyTyped

    private void nagKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nagKeyTyped
        // TODO add your handling code here:
         new OnlyNumbers().only_numbers(evt);
    }//GEN-LAST:event_nagKeyTyped

    private void karatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_karatKeyTyped
        // TODO add your handling code here:
        
        new OnlyNumbers().only_numbers(evt);
        checkKarat(karat);
    }//GEN-LAST:event_karatKeyTyped

    private void nagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nagActionPerformed

    private void karatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_karatKeyPressed
        // TODO add your handling code here:
        checkKarat(karat);
    }//GEN-LAST:event_karatKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Silver_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Silver_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Silver_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Silver_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Silver_manage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField GRATE;
    private javax.swing.JTextField IDtxt;
    private javax.swing.JTextField RATE;
    private javax.swing.JTextField cnic;
    private javax.swing.JTextField d;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextArea11;
    private javax.swing.JTextField karat;
    private javax.swing.JTextField kat;
    private javax.swing.JTextField nag;
    private javax.swing.JTextField name;
    private javax.swing.JTextField pasa;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField rakam;
    private javax.swing.JTextField serch;
    private javax.swing.JTextField twazan;
    private javax.swing.JTextField wazan;
    // End of variables declaration//GEN-END:variables

}
