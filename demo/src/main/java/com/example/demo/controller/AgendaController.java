package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

@Controller
public class AgendaController {
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    @Autowired
    AgendaService agendaService;

    @GetMapping("/agenda")
    public String agenda(Model model) {
        LocalDate today = LocalDate.now();
        // Get the first day of the week (Monday)
        LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Get the last day of the week (Sunday)
        LocalDate weekEnd = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Map<String, List<Appointment>> appointmentsGroupedByDay = agendaService.getAppointmentsGroupedByDay(weekStart, weekEnd);

        model.addAttribute("weekDays", weekDays);
        model.addAttribute("appointmentsGroupedByDay",appointmentsGroupedByDay);
        return "agenda";
    }
}
