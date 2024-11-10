package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.model.WaitingRoom;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.WaitingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WaitingRoomController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/waiting_room")
    public String home(Model model) {
        LocalDate today = LocalDate.now();
        // find patients with status = waiting, completed and canceled of today
        List<Patient> waitingPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.WAITING, today);
        List<Patient> completedPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.COMPLETED, today);
        List<Patient> canceledPatients = patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(Patient.WaitingRoomStatus.CANCELLED, today);
        // Feed the objects to the model
        model.addAttribute("waitingPatients", waitingPatients);
        model.addAttribute("completedPatients", completedPatients);
        model.addAttribute("canceledPatients", canceledPatients);

        return "waiting_room";
    }

    @GetMapping("/waiting-room/patients")
    @ResponseBody
    public List<Patient> getPatientsByStatus(@RequestParam("status") Patient.WaitingRoomStatus status) {
        LocalDate today = LocalDate.now();
        return patientRepository.findByWaitingRoomStatusAndWaitingRoomDate(status, today);
    }
}
