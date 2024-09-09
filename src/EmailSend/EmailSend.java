/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EmailSend;
import javax.swing.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Gouhar Ali
 */
public class EmailSend {
    
    
    public void SendEmail(String s ,String t ,String ToEmail,String FromEmail,JFrame frame){
        
        GEmailSender gEmailSender = new GEmailSender();
        String to = ToEmail;
        String from = FromEmail;
        String subject = s;
        String text = t;
        boolean b = gEmailSender.sendEmail(to, from, subject, text);
        if (b) {
            JOptionPane.showMessageDialog(frame, "Email sended Successfully","Success",1);
            System.out.println("Email is sent successfully");
        } else {
            JOptionPane.showMessageDialog(frame, "Error While Sending Email","Error",2);
            System.out.println("There is problem in sending email");
        }
    }
    
    public static void main(String[] args) {

        new EmailSend().SendEmail("ORDER INVOICE", "ORDER SAVED", "mu5667733@gmail.com", "hskemails1@gmail.com",null);
            
    }
    
}
