package com.example.demo.service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    //View patient profile
    public PatientDTO getPatientById(Integer id) {
        Patient patient = patientRepository.findById(id)
                            .orElseThrow(()->new IllegalArgumentException("Patient not found"));
        return new PatientDTO(patient);
    }

    public void updatePatient(Integer id, PatientDTO patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Patient not found"));
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setCin(patientDto.getCin());
        patient.setCredit(patientDto.getCredit());
        patient.setBirthDate(patientDto.getBirthDate());
        patientRepository.save(patient);
    }

    public void deletePatient(Integer id) {
        if(patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Patient not found");
        }
    }

    public void addPatient(PatientDTO patientDto) {
        // Check if a patient with the same CIN already exists
        Optional<Patient> existingPatient = patientRepository.findByCin(patientDto.getCin());

        if (existingPatient.isPresent()) {
            // Handle the case where the patient already exists
            throw new IllegalArgumentException("A patient with CIN " + patientDto.getCin() + " already exists.");
        }

        // If no patient exists, create a new one
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setCin(patientDto.getCin());
        patient.setCredit(0.0);  // Set initial credit to 0
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setWaitingRoomStatus(Patient.WaitingRoomStatus.NOT_IN_WAITING_ROOM);
        // Save the new patient to the database
        patientRepository.save(patient);
    }

}
