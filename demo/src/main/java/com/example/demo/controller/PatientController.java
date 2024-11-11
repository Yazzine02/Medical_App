package com.example.demo.controller;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    // List of patients section
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient-list")
    public String patientList(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patient-list";
    }

    // CRUD Operations for patients, this concerns the 3 CRUD buttons: select, modify and delete
    @Autowired
    private PatientService patientService;
    // Patient profile
    @GetMapping("/patient-profile/{id}")
    public String viwPatientProfile(@PathVariable Integer id, Model model) {
        PatientDTO patientDto =  patientService.getPatientById(id);
        model.addAttribute("patient", patientDto);
        return "patient-profile";
    }
    // Modify patient profile
    @GetMapping("/modify-patient/{id}")
    public String modifyPatientProfile(@PathVariable Integer id, Model model) {
        PatientDTO patientDto = patientService.getPatientById(id);
        model.addAttribute("patient", patientDto);
        return "modify-patient";
    }
    @PostMapping("/modify-patient/{id}")
    public String modifyPatient(@PathVariable Integer id, @ModelAttribute PatientDTO patientDto) {
        patientService.modifyPatient(id, patientDto);
        return "redirect:/patient-list";
    }
    // Delete patient
    @GetMapping("/delete-patient/{id}")
    public String deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return "redirect:/patient-list";
    }
}
