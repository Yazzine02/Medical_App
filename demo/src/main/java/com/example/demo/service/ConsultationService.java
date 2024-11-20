package com.example.demo.service;

import com.example.demo.model.Consultation;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.enums.ConsultationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;

    public ConsultationService(ConsultationRepository consultationRepository, PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
    }

    public void addConsultation(int patientId, double price) {
        if(patientRepository.findById(patientId).isEmpty()) {
            throw new IllegalArgumentException("Patient with id " + patientId + " does not exist");
        }
        Consultation consultation = new Consultation();
        consultation.setPatient(patientRepository.findById(patientId).get());
        consultation.setPrice(price);
        consultation.setConsultationDate(LocalDateTime.now());
        consultation.setLeftToPay(price);
        consultation.setTotalPaid(0.0);
        consultation.setStatus(ConsultationStatus.NOT_PAID);
        consultationRepository.save(consultation);
    }
}
