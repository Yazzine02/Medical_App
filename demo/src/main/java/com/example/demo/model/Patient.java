package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cin", unique = true, nullable = false, length = 7)
    private String cin;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name="credit")
    private double credit;
    @Column(name="waiting_room_date")
    private LocalDate waitingRoomDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "waiting_room_status")
    private WaitingRoomStatus waitingRoomStatus;
    public enum WaitingRoomStatus{
        WAITING,
        COMPLETED,
        CANCELLED,
        NOT_IN_WAITING_ROOM,
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public WaitingRoomStatus getWaitingRoomStatus() {
        return waitingRoomStatus;
    }

    public void setWaitingRoomStatus(WaitingRoomStatus waitingRoomStatus) {
        this.waitingRoomStatus = waitingRoomStatus;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getWaitingRoomDate() {
        return waitingRoomDate;
    }

    public void setWaitingRoomDate(LocalDate waitingRoomDate) {
        this.waitingRoomDate = waitingRoomDate;
    }
}
