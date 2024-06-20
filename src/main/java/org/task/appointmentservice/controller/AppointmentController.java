package org.task.appointmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/")
    public String createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable int id) {
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping("/user/{patientId}")
    public List<Appointment> getAppointmentsByUserId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsForUser(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        return appointmentService.getAppointmentsForDoctor(doctorId);
    }
}
