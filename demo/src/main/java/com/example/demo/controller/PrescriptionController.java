package com.example.demo.controller;

import com.example.demo.model.Prescription;
import com.example.demo.repository.PrescriptionRepository;
import com.example.demo.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, PrescriptionService prescriptionService) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("")
    public String prescriptionList(Model model) {
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        model.addAttribute("prescriptions", prescriptionList);
        return "prescription";
    }

    @GetMapping("/add")
    public String showAddPrescriptionForm() {
        return "add-prescription";
    }
    @PostMapping("/add")
    public String addPrescription(@RequestParam(name = "consultationId") int consultationId, @RequestParam(name = "patientId") int patientId, @RequestParam(name = "prescriptionText") String prescriptionText) {
        prescriptionService.addPrescription(consultationId, patientId, prescriptionText);
        return "redirect:/prescription";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePrescriptionForm(@PathVariable(name = "id") int id, Model model) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescription";
    }

    @GetMapping("/view/{id}")
    public String showViewPrescriptionForm(@PathVariable(name = "id") int id, Model model) {
        if(prescriptionRepository.findById(id).isPresent()) {
            Prescription prescription = prescriptionRepository.findById(id).get();
            model.addAttribute("prescription", prescription);
            return "prescription-view";
        }
        else{
            model.addAttribute("errorMessage", "Prescription not found");
            return "redirect:/prescription";
        }
    }
}
