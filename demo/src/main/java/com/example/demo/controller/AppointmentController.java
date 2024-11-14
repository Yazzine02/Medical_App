package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment-list")
    public String appointmentList(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        model.addAttribute("appointments", appointments);
        return "appointment-list";
    }

    @GetMapping("/add-appointment")
    public String showAddAppointmentForm() {
        return "add-appointment";
    }
    @PostMapping("/add-appointment")
    public String addAppointment(@ModelAttribute("appointment") Appointment appointment, @RequestParam("patientId") int patientId) {
        appointmentService.addAppointment(appointment, patientId);
        return "redirect:/appointment-list";
    }

}