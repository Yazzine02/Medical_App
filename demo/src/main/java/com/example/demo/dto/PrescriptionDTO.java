package com.example.demo.dto;

import java.time.LocalDate;

public class PrescriptionDTO {
    private LocalDate date;
    private String prescriptionText;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPrescriptionText() {
        return prescriptionText;
    }

    public void setPrescriptionText(String prescriptionText) {
        this.prescriptionText = prescriptionText;
    }
}
