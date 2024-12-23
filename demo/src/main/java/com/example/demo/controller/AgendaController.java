package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String agenda(@RequestParam(name = "date", required = false) String dateStr, Model model) {
        // Parse the date provided or use LocalDate.now() as a default value
        LocalDate date = (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr) : LocalDate.now();
        // Get the first day of the week (Monday)
        LocalDate weekStart = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Get the last day of the week (Sunday)
        LocalDate weekEnd = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Map<String, List<Appointment>> appointmentsGroupedByDay = agendaService.getAppointmentsGroupedByDay(weekStart, weekEnd);

        model.addAttribute("weekDays", weekDays);
        model.addAttribute("appointmentsGroupedByDay",appointmentsGroupedByDay);
        model.addAttribute("currentDate", date);
        return "agenda";
    }
}
