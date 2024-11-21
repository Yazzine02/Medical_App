package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    public PaymentService(PaymentRepository paymentRepository, PatientRepository patientRepository, ConsultationRepository consultationRepository) {
        this.paymentRepository = paymentRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
    }

    public void addPayment(int patientID, int consultationId, double amount) {
        if(patientRepository.findById(patientID).isPresent() && consultationRepository.findById(consultationId).isPresent()) {
            Payment payment = new Payment();
            payment.setPatient(patientRepository.findById(patientID).get());
            payment.setPaymentDate(LocalDate.now());
            payment.setAmount(amount);
            payment.setConsultation(consultationRepository.findById(consultationId).get());
            paymentRepository.save(payment);
        }
        else{
            throw new IllegalArgumentException("Patient with id " + patientID + " does not exist OR Consultation with id " + consultationId + " does not exist.");
        }
    }
}
