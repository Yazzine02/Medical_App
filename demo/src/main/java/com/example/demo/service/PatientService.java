package com.example.demo.service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    //View patient profile
    public PatientDTO getPatientById(int id) {
        Patient patient = patientRepository.findById(id)
                            .orElseThrow(()->new IllegalArgumentException("Patient not found"));
        return new PatientDTO(patient);
    }
}
