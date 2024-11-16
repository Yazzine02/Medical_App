package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // dictionary of <LocalDate ; list of appointments>
    public Map<String, List<Appointment>> getAppointmentsGroupedByDay(LocalDate weekStart, LocalDate weekEnd){
        // Get appointments from the start of the 1st day if the week to the last hour of the last day of the week
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateTimeBetweenOrderByAppointmentDateTimeAsc(weekStart.atStartOfDay(), weekEnd.atTime(LocalTime.MAX));
        // If no appointments
        if(appointments.isEmpty()){
            return new LinkedHashMap<>();
        }
        // Formatter to convert LocalDate to day name
        DateTimeFormatter dayNameFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        return appointments.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getAppointmentDateTime().toLocalDate().format(dayNameFormatter), // Converts LocalDate to day name
                        LinkedHashMap::new, // Preserves order
                        Collectors.toList() // Groups appointments for the same day into a list
                ));
    }
}
