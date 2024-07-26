package com.democontroller.controller.service;

import com.democontroller.controller.entities.PatientRecord;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientRecordService {
    PatientRecord saveUser(PatientRecord patientRecord);
    List<PatientRecord> getPatientRecords();
    List<PatientRecord> getPatientRecordsById(String id);
    List<PatientRecord> getPatientRecordsByName(String name);
    String deletePatientRecord(String id);

    PatientRecord updatePatientRecord(PatientRecord patientRecord);

    List<String> getAllRandomGeneratedIds();

}
