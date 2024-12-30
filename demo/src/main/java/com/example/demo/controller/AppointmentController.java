package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientRepository patientRepository;

    // Show appointment lis
    @GetMapping("/appointment-list")
    public String appointmentList(Model model) {
        List<Appointment> appointments = appointmentRepository.findAllByOrderByAppointmentDateTimeDesc();
        model.addAttribute("appointments", appointments);
        return "appointment-list";
    }
    // Add a new appointment
    @GetMapping("/add-appointment")
    public String showAddAppointmentForm(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "add-appointment";
    }
    @PostMapping("/add-appointment")
    public String addAppointment(@ModelAttribute("appointment") Appointment appointment, @RequestParam("patientId") int patientId) {
        appointmentService.addAppointment(appointment, patientId);
        return "redirect:/appointment-list";
    }
    // Modify an appointment
    @GetMapping("/appointment-modify/{id}")
    public String showModifyAppointmentForm(@PathVariable Integer id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointment-modify";
    }
    @PostMapping("/appointment-modify/{id}")
    public String modifyAppointment(@PathVariable Integer id, @ModelAttribute("appointment") Appointment appointment) {
        appointmentService.updateAppointment(id,appointment);
        return "redirect:/appointment-list";
    }
    // Delete an appointment
    @GetMapping("appointment-delete/{id}")
    public String deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment-list";
    }
}
