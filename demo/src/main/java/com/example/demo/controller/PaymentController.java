package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("payment")
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public String showPaymentList(Model model){
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "payment";
    }
    @GetMapping("/add")
    public String showAddPaymentForm(){
        return "add-payment";
    }
    @PostMapping("/add")
    public String addPayment(@RequestParam(name = "patientID") int patientID, @RequestParam(name = "consultationId") int consultationId, @RequestParam(name = "amount") double amount){
        paymentService.addPayment(patientID, consultationId, amount);
        return "redirect:/payment";
    }
}
