package com.example.demo.dto;

import com.example.demo.model.Patient;

import java.time.LocalDate;

public class PatientDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cin;
    private LocalDate birthDate;
    private double credit;
    private LocalDate waitingRoomDate;
    private Patient.WaitingRoomStatus waitingRoomStatus;
    // Payments and Appointments

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.cin = patient.getCin();
        this.birthDate = patient.getBirthDate();
        this.credit = patient.getCredit();
        this.waitingRoomStatus = patient.getWaitingRoomStatus();
        this.waitingRoomDate = patient.getWaitingRoomDate();
        // Payments and Appointments
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient.WaitingRoomStatus getWaitingRoomStatus() {
        return waitingRoomStatus;
    }

    public void setWaitingRoomStatus(Patient.WaitingRoomStatus waitingRoomStatus) {
        this.waitingRoomStatus = waitingRoomStatus;
    }

    public LocalDate getWaitingRoomDate() {
        return waitingRoomDate;
    }

    public void setWaitingRoomDate(LocalDate waitingRoomDate) {
        this.waitingRoomDate = waitingRoomDate;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
