package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "prescription_file_path")
    private String prescriptionFilePath;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public String getPrescriptionFilePath() {
        return prescriptionFilePath;
    }

    public void setPrescriptionFilePath(String prescriptionFilePath) {
        this.prescriptionFilePath = prescriptionFilePath;
    }
}
