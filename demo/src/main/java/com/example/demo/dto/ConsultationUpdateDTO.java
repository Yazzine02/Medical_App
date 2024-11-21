package com.example.demo.dto;

import java.time.LocalDateTime;

public class ConsultationUpdateDTO {
    private Integer patientId; // Nullable
    private LocalDateTime consultationDate; // Nullable
    private Double leftToPay; // Nullable
    private Double price; // Nullable
    private Double totalPaid; // Nullable

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public Double getLeftToPay() {
        return leftToPay;
    }

    public void setLeftToPay(Double leftToPay) {
        this.leftToPay = leftToPay;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }
}
