package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient-list")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public String patientList(Model model) {

        return "patient-list";
    }
}
