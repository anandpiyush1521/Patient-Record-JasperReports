package com.democontroller.controller.controllers;

import com.democontroller.controller.entities.PatientRecord;
import com.democontroller.controller.service.PatientRecordService;
import com.democontroller.controller.service.Impl.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/patient-record")
@Tag(name = "Patient Record", description = "API for managing patient records")
public class PatientRecordController {

    private static final Logger logger = LoggerFactory.getLogger(PatientRecordController.class);

    @Autowired
    private PatientRecordService patientRecordService;

    @Autowired
    private ReportService service;

    @GetMapping
    @Operation(summary = "Get all patient records")
    public ResponseEntity<List<PatientRecord>> getAllPatientRecords() {
        logger.info("Request received to fetch all patient records");
        List<PatientRecord> records = patientRecordService.getPatientRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient record by ID")
    public ResponseEntity<List<PatientRecord>> getPatientRecordsById(@PathVariable String id) {
        logger.info("Request received to fetch patient record with ID: {}", id);
        List<PatientRecord> records = patientRecordService.getPatientRecordsById(id);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get patient records by name")
    public ResponseEntity<List<PatientRecord>> getPatientRecordsByName(@PathVariable String name) {
        logger.info("Request received to fetch patient records with name: {}", name);
        List<PatientRecord> records = patientRecordService.getPatientRecordsByName(name);
        return ResponseEntity.ok(records);
    }

    @PostMapping
    @Operation(summary = "Save a new patient record")
    public ResponseEntity<PatientRecord> savePatientRecord(@RequestBody PatientRecord patientRecord) {
        logger.info("Request received to save a new patient record");
        PatientRecord savedRecord = patientRecordService.saveUser(patientRecord);
        return ResponseEntity.ok(savedRecord);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient record")
    public ResponseEntity<PatientRecord> updatePatientRecord(@PathVariable String id, @RequestBody PatientRecord patientRecordDetails) {
        logger.info("Request received to update patient record with ID: {}", id);
        patientRecordDetails.setId(id);
        PatientRecord updatedRecord = patientRecordService.updatePatientRecord(patientRecordDetails);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient record by ID")
    public ResponseEntity<String> deletePatientRecord(@PathVariable String id) {
        logger.info("Request received to delete patient record with ID: {}", id);
        String response = patientRecordService.deletePatientRecord(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ids")
    @Operation(summary = "Get all random generated IDs")
    public ResponseEntity<List<String>> getAllIds() {
        logger.info("Request received to fetch all random generated IDs");
        List<String> ids = patientRecordService.getAllRandomGeneratedIds();
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }


}
