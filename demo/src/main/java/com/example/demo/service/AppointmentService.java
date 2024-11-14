package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void addAppointment(Appointment appointment, int patientId) {
        // Retrieve the patient by ID
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID: " + patientId));

        // Check if an appointment already exists at the same time
        Optional<Appointment> appointmentOptional = appointmentRepository.findByAppointmentDateTime(appointment.getAppointmentDateTime());

        if (appointmentOptional.isPresent()) {
            throw new IllegalArgumentException("An appointment at this time already exists.");
        } else {
            // Associate the patient with the appointment
            appointment.setPatient(patient);
            appointmentRepository.save(appointment);
        }
    }

}
