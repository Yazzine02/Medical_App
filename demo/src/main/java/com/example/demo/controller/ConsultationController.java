package com.example.demo.controller;

import com.example.demo.model.Consultation;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    private ConsultationService consultationService;

    @GetMapping("")
    public String consultationList(Model model) {
        List<Consultation> consultations = consultationRepository.findAll();
        model.addAttribute("consultations", consultations);
        return "consultation";
    }
    @GetMapping("/add")
    public String showAddConsultationFrom(Model model) {
        return "add-consultation";
    }
    @PostMapping("/add")
    public String addConsultation(@RequestParam(name = "price") double price, @RequestParam(name="patientId") int patientId) {
        consultationService.addConsultation(patientId, price);
        return "redirect:/consultation";
    }
}
