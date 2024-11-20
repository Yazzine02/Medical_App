package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class PaymentController {

    @GetMapping("/add")
    public String showAddPaymentForm(){
        return "add-payment";
    }
//    @PostMapping("/add")
//    public String addPayment(){
//
//    }
}
