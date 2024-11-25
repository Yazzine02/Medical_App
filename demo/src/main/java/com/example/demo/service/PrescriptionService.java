package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.model.Prescription;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PatientRepository patientRepository, ConsultationRepository consultationRepository, PrescriptionRepository prescriptionRepository) {
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public void addPrescription(int consultationId, int patientId, String prescriptionText) {
        if(patientRepository.findById(patientId).isPresent() && consultationRepository.findById(consultationId).isPresent()) {
            Prescription prescription = new Prescription();
            prescription.setPrescriptionText(prescriptionText);
            prescription.setDate(LocalDate.now());
            prescription.setConsultation(consultationRepository.findById(consultationId).get());
            prescription.setPatient(patientRepository.findById(patientId).get());

            prescriptionRepository.save(prescription);
        }
        else{
            throw new IllegalArgumentException("Consultation ID "+consultationId+" or Patient ID "+patientId+" not found");
        }
    }

    public void deletePrescription(int id) {
        if(prescriptionRepository.findById(id).isPresent()) {
            prescriptionRepository.delete(prescriptionRepository.findById(id).get());
        }
        else{
            throw new IllegalArgumentException("Prescription ID "+id+" not found");
        }
    }
}
