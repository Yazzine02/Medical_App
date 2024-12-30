package com.example.demo.controller;

import com.example.demo.dto.ConsultationUpdateDTO;
import com.example.demo.model.Consultation;
import com.example.demo.model.Patient;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public String consultationList(Model model) {
        List<Consultation> consultations = consultationRepository.findAll();
        model.addAttribute("consultations", consultations);
        return "consultation";
    }
    @GetMapping("add/{id}")
    public String showAddConsultationFormForPatient(@PathVariable("id") int id, Model model) {
        model.addAttribute("patientId", id);
        return "add-consultation-waitingroom";
    }
    @GetMapping("/add")
    public String showAddConsultationFrom(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "add-consultation";
    }
    @PostMapping("/add")
    public String addConsultation(@RequestParam(name = "price") double price, @RequestParam(name="patientId") int patientId) {
        consultationService.addConsultation(patientId, price);
        return "redirect:/consultation";
    }
    @GetMapping("/modify/{id}")
    public String showModifyConsultation(@PathVariable("id") int id, Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if(consultation.isPresent()) {
            model.addAttribute("consultation", consultation.get());
            return "consultation-modify";
        }
        else{
            model.addAttribute("errorMessage", "Consultation with id " + id + " not found");
            return "error-page";
        }
    }
    @PostMapping("/modify")
    public String modifyConsultation(@RequestParam int consultationId, @ModelAttribute ConsultationUpdateDTO consultationUpdateDTO) {
        consultationService.modifyConsultation(consultationId, consultationUpdateDTO);
        return "redirect:/consultation";
    }
    @PostMapping("/delete/{id}")
    public String deleteConsultation(@PathVariable("id") int id) {
        consultationService.deleteConsultation(id);
        return "redirect:/consultation";
    }
}
