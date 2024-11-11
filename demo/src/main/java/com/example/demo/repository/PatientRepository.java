package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus waitingRoomStatus, LocalDate waitingRoomDate);

    Optional<Patient> findByCin(String cin);

    Optional<Patient> findById(Integer id);
}
