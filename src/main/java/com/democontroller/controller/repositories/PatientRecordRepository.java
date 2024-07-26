package com.democontroller.controller.repositories;

import com.democontroller.controller.entities.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, String> {
    @Query("SELECT p FROM PatientRecord p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<PatientRecord> findByName(String name);
    List<PatientRecord> findByEmail(String email);
}
