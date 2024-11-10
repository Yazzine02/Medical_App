package com.example.demo.service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void addPatientToWaitingRoom(PatientDTO patientDTO){
        Patient patient;

        // If patient already exists
        if(patientDTO.getPatientId()!=null){
            patient = patientRepository.findById(patientDTO.getPatientId())
                    .orElseThrow(()->new IllegalArgumentException("Patient not Found"));
        }
        // If new patient
        else {
            patient = new Patient();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setLastName(patientDTO.getLastName());
            patient.setId(patientDTO.getPatientId());
            patient.setBirthDate(patientDTO.getBirthDate());
            patient.setCin(patientDTO.getCin());
        }
        patient.setWaitingRoomStatus(Patient.WaitingRoomStatus.WAITING);
        patient.setWaitingRoomDate(LocalDate.now());
        patientRepository.save(patient);
    }
}
