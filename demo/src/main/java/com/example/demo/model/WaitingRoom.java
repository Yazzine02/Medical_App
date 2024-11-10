package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="waiting_room")
public class WaitingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@OneToMany(mappedBy = "waitingRoom", cascade = CascadeType.ALL)
    //private List<Patient> patients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
