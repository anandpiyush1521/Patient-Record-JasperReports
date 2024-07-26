package com.democontroller.controller.service.Impl;

import com.democontroller.controller.entities.PatientRecord;
import com.democontroller.controller.repositories.PatientRecordRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private PatientRecordRepository repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Datafoundary-demo\\HospitalController";
        List<PatientRecord> patientRecord = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:patient_record.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patientRecord);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Piyush Anand");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\patient_record.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\patient_record.pdf");
        }
        if (reportFormat.equalsIgnoreCase("csv")) {
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(path + "\\patient_record.csv"));

            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setFieldDelimiter(",");
            exporter.setConfiguration(configuration);

            exporter.exportReport();
        }

        return "report generated in path : " + path; 
    }
}
