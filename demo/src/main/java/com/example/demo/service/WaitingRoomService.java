package com.example.demo.service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WaitingRoomService {
    @Autowired
    private PatientRepository patientRepository;

    public void addPatientToWaitingRoom(PatientDTO patientDTO){
        Patient patient;

        // If patient already exists
        // Then we only need to change his waiting room status and transform the date to the current date

        // Check if a patient with the provided CIN already exists
        Optional<Patient> existingPatient = patientRepository.findByCin(patientDTO.getCin());

        if (existingPatient.isPresent()) {
            // Patient exists - update waiting room status and date
            patient = existingPatient.get();
            patient.setWaitingRoomStatus(Patient.WaitingRoomStatus.WAITING);
            patient.setWaitingRoomDate(LocalDate.now());
        } else {
            // New patient - create and save a new entry
            patient = new Patient();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setLastName(patientDTO.getLastName());
            patient.setCin(patientDTO.getCin());
            patient.setBirthDate(patientDTO.getBirthDate());
            patient.setWaitingRoomStatus(Patient.WaitingRoomStatus.WAITING);
            patient.setWaitingRoomDate(LocalDate.now());
            patientRepository.save(patient);
        }

        patientRepository.save(patient);
    }

    public void addOldPatientToWaitingRoom(Integer patientID) {
        Patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        patient.setWaitingRoomStatus(Patient.WaitingRoomStatus.WAITING);
        patient.setWaitingRoomDate(LocalDate.now());
        patientRepository.save(patient);
    }
}
