/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Gouhar Ali
 */
public class FieldSetting {
    public void fieldFoucasGain(JTextField tx ,double equal,String set){
        try {
            double value =Double.parseDouble(tx.getText());
            if(value==equal){
                tx.setText(set);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
    
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    
    public void fieldFoucasLost(JTextField tx,String equal ,String set){
        if(tx.getText().equals(equal)){
            tx.setText(set);
        }
    }    
    
     //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void only_numbers_with_point(KeyEvent ke){
        
        if(!(ke.getKeyChar()>='0' && ke.getKeyChar()<='9' || ke.getKeyChar()=='.' ) ){
            ke.consume();
        }
    }
    
     
      //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
     
    public void only_numbers(KeyEvent ke){
        
        if(!(ke.getKeyChar()>='0' && ke.getKeyChar()<='9' ) ){
            ke.consume();
        }
    }
   
    
    
     //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
    public void limit(KeyEvent ke ,int cchar,int lmt){
        
        if(cchar>lmt){
            ke.consume();
        }
    }
    
    
     //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    
   public void NoSpace(KeyEvent ke){
        
        if(ke.getKeyChar()==' '){
            ke.consume();
        }
    }


}
