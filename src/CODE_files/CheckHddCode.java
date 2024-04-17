/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Gouhar Ali
 */
public class CheckHddCode {
    
    
    public static String getNumeroDisq() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String[] commands = {"wmic", "diskdrive", "get", "SerialNumber"};
        Process process = runtime.exec(commands);
        String chain = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String serialNumber= null;
            while ((serialNumber = bufferedReader.readLine()) != null) {
                if (serialNumber.trim().length() > 0) {
                    chain = serialNumber;
                }
            }
            return chain.trim();
        }
    }
    
    
    
    public static void main(String[] args) throws IOException {
        System.out.println(getNumeroDisq());
    }
    
}
