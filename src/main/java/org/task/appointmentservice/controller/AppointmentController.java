package org.task.appointmentservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/appointment")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
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
