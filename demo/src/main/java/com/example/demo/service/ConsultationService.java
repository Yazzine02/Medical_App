package com.example.demo.service;

import com.example.demo.dto.ConsultationUpdateDTO;
import com.example.demo.model.Consultation;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.enums.ConsultationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void deleteConsultation(int id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if(consultation.isPresent()) {
            consultationRepository.delete(consultation.get());
        }
        else{
            throw new IllegalArgumentException("Consultation with id " + id + " does not exist");
        }
    }

    public void modifyConsultation(int consultationId, ConsultationUpdateDTO consultationUpdateDTO) {
        Optional<Consultation> consultation = consultationRepository.findById(consultationId);
        // Update fields only if they are not null or empty
        if(consultation.isPresent()) {
            if (consultationUpdateDTO.getPatientId() != null) {
                consultation.get().setPatient(patientRepository.findById(consultationUpdateDTO.getPatientId()).get());
            }
            if (consultationUpdateDTO.getConsultationDate() != null) {
                consultation.get().setConsultationDate(consultationUpdateDTO.getConsultationDate());
            }
            if (consultationUpdateDTO.getLeftToPay() != null) {
                consultation.get().setLeftToPay(consultationUpdateDTO.getLeftToPay());
            }
            if (consultationUpdateDTO.getPrice() != null) {
                consultation.get().setPrice(consultationUpdateDTO.getPrice());
            }
            if (consultationUpdateDTO.getTotalPaid() != null) {
                consultation.get().setTotalPaid(consultationUpdateDTO.getTotalPaid());
            }
            consultationRepository.save(consultation.get());
        }
        else{
            throw new IllegalArgumentException("Consultation with id " + consultationId + " does not exist");
        }
    }
}
