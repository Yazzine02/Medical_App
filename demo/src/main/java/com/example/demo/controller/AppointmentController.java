package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentRepository;

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
}
