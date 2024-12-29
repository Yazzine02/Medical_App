package com.example.demo.controller;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.WaitingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
// Defines the base URL for all methods in this controller to localhost:8080/waiting-room
@RequestMapping("/waiting-room")
public class WaitingRoomController {
    @Autowired
    private PatientRepository patientRepository;
    // Controller to get patients by status
    @GetMapping("/status")
    public String showWaitingRoom(@RequestParam(value = "status", required = false) String status, Model model) {
        // Default to WAITING if no status is provided
        String selectedStatus = (status != null) ? status : "WAITING";

        // Fetch patients based on the selected status
        // add condition of date of today
        List<Patient> patients = waitingRoomService.getPatientsByStatus(selectedStatus);
        int numberOfPatients = patients.size();
        // Add data to the model
        model.addAttribute("numberOfPatients", numberOfPatients);
        model.addAttribute("patients", patients);
        model.addAttribute("selectedStatus", selectedStatus);

        return "waiting-room"; // Name of the Thymeleaf template
    }
    // Adding a patient to the waiting room functionality
    @Autowired
    private WaitingRoomService waitingRoomService;
    // Show the adding patient to waiting room form
    @GetMapping("/add-new-patient-to-waiting-room")
    public String showAddNewPatientForm() {
        return "add-new-patient-to-waiting-room";
    }
    // Adding patient to waiting room logic
    @PostMapping("/add-new-patient-to-waiting-room")
    public String addPatientToWaitingRoom(@ModelAttribute PatientDTO patientDTO, RedirectAttributes redirectAttributes){
        try{
            waitingRoomService.addPatientToWaitingRoom(patientDTO);
            redirectAttributes.addFlashAttribute("message", "Patient added to waiting room");
            return "redirect:/waiting-room/status";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to add patient to waiting room, " + e.getMessage());
            return "redirect:/add-new-patient-to-waiting-room";
        }
    }

    // Adding an old patient
    @GetMapping("/add-old-patient-to-waiting-room")
    public String showAllOldPatients(Model model){
        patientRepository.findAll();
        model.addAttribute("patients", patientRepository.findAll());
        return "add-old-patient-to-waiting-room";
    }
    @PostMapping("/add-old-patient-to-waiting-room")
    public String addOldPatientToWaitingRoom(@RequestParam("patientId") Integer patientId){
        waitingRoomService.addOldPatientToWaitingRoom(patientId);
        return "redirect:/waiting-room/status?status=WAITING";
    }
    // Cancelling patient in waiting room
    @PostMapping("/cancel/{id}")
    public String cancelPatient(@PathVariable("id") Integer id){
        waitingRoomService.cancelWaitingRoom(id);
        return "redirect:/waiting-room/status?status=WAITING";
    }
}