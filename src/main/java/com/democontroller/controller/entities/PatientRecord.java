package com.democontroller.controller.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patientrecords")
public class PatientRecord {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private int age;
    private String gender;

    @Column(nullable = false)
    private String bloodType;
    private String medicalCondition;
    private LocalDate dateOfAdmission;
    private String doctor;
    private String hospital;
    private String insuranceProvider;
    private double billingAmount;
    private int roomNumber;
    private String admissionType;
    private LocalDate dischargeDate;
    private String medication;
    private String testResults;
}
