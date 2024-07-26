package com.democontroller.controller.service.Impl;

import com.democontroller.controller.entities.PatientRecord;
import com.democontroller.controller.helpers.ResourceNotFoundException;
import com.democontroller.controller.repositories.PatientRecordRepository;
import com.democontroller.controller.service.PatientRecordService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientRecordServiceImpl implements PatientRecordService {

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    private static final Logger logger = LoggerFactory.getLogger(PatientRecordServiceImpl.class);

    @Override
    @Transactional
    public PatientRecord saveUser(PatientRecord patientRecord) {
        String patientId = UUID.randomUUID().toString();
        patientRecord.setId(patientId);
        logger.info("Saving patient record with ID: {}", patientId);
        return patientRecordRepository.save(patientRecord);
    }

    @Override
    public List<PatientRecord> getPatientRecords() {
        logger.info("Fetching all patient records");
        return patientRecordRepository.findAll();
    }

    @Override
    public List<PatientRecord> getPatientRecordsById(String id) {
        logger.info("Fetching patient record with ID: {}", id);
        Optional<PatientRecord> patientRecord = patientRecordRepository.findById(id);
        if (patientRecord.isPresent()) {
            return List.of(patientRecord.get());
        } else {
            logger.error("Patient record with ID: {} not found", id);
            throw new ResourceNotFoundException("Patient record with ID: " + id + " not found");
        }
    }

    @Override
    public List<PatientRecord> getPatientRecordsByName(String name) {
        logger.info("Fetching patient records with name: {}", name);
        return patientRecordRepository.findByName(name);
    }

    @Override
    @Transactional
    public String deletePatientRecord(String id) {
        logger.info("Deleting patient record with ID: {}", id);
        Optional<PatientRecord> patientRecord = patientRecordRepository.findById(id);
        if (patientRecord.isPresent()) {
            patientRecordRepository.deleteById(id);
            return "Patient Record with ID: " + id + " has been deleted";
        } else {
            logger.error("Patient record with ID: {} does not exist", id);
            throw new ResourceNotFoundException("Patient Record with ID: " + id + " does not exist");
        }
    }

    @Override
    @Transactional
    public PatientRecord updatePatientRecord(PatientRecord patientRecord) {
        logger.info("Updating patient record with ID: {}", patientRecord.getId());
        Optional<PatientRecord> existedPatientRecord = patientRecordRepository.findById(patientRecord.getId());
        if (existedPatientRecord.isPresent()) {
            return patientRecordRepository.save(patientRecord);
        } else {
            logger.error("Patient record with ID: {} does not exist", patientRecord.getId());
            throw new ResourceNotFoundException("Patient Record with ID: " + patientRecord.getId() + " does not exist");
        }
    }

    @Override
    public List<String> getAllRandomGeneratedIds() {
        logger.info("Fetching all random generated IDs");
        return patientRecordRepository.findAll()
                .stream()
                .map(patientRecord -> patientRecord.getId() + " - " + patientRecord.getName())
                .toList();
    }
}
