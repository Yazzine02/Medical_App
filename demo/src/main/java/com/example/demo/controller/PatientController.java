package com.example.demo.controller;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/waiting-room")
// Defines the base URL for all methods in this controller to localhost:8080/waiting-room
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public String home(Model model) {
        LocalDate today = LocalDate.now();
        // Do I really need to find it all the patients or should I just feed it the default waiting patients then let the controller do its work ?
        // find patients with status = waiting, completed and canceled of today
        List<Patient> waitingPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.WAITING, today);
        List<Patient> completedPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.COMPLETED, today);
        List<Patient> canceledPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.CANCELLED, today);
        // Feed the objects to the model
        model.addAttribute("waitingPatients", waitingPatients);
        model.addAttribute("completedPatients", completedPatients);
        model.addAttribute("canceledPatients", canceledPatients);

        return "waiting-room";
    }
    // Controller to get patients by status
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> getPatientsByStatus(@RequestParam("status") Patient.WaitingRoomStatus status) {
        LocalDate today = LocalDate.now();
        return patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(status, today);
    }

    // Adding a patient to the waiting room functionality
    @Autowired
    private PatientService patientService;
    // Show the adding patient to waiting room form
    @GetMapping("/add-patient-to-waiting-room")
    public String showAddPatientForm() {
        return "add-patient-to-waiting-room";
    }
    // Adding patient to waiting room logic
    @PostMapping("/add-patient-to-waiting-room")
    public ResponseEntity<?> addPatientToWaitingRoom(@ModelAttribute PatientDTO patientDTO){
        try{
            patientService.addPatientToWaitingRoom(patientDTO);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}