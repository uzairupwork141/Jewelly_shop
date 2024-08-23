/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE_files;

import java.io.File;
import java.sql.Connection;
import java.util.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Gouhar Ali
 */
public class ReportGenarator {
    public static String OUT_PUT = "your_output_file_path/myreport.docx";
     public static String REPORT = "your_report_path/myreport.jrxml";

public void genarateReport(String reportPath,
        Map<String, Object> map, Connection con) {
    try {

        JasperReport jr = JasperCompileManager.compileReport(ClassLoader.getSystemResourceAsStream(reportPath));
        JasperPrint jp = JasperFillManager.fillReport(jr, map, con);
        JRDocxExporter export = new JRDocxExporter();
        export.setExporterInput(new SimpleExporterInput(jp));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(OUT_PUT)));
        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
        export.setConfiguration(config);
        export.exportReport();
    } catch (JRException ex) {
        ex.printStackTrace();   
    }
    }
}
