package org.task.appointmentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int doctorId;
    private int patientId;
    private int availabilityId;
    private String service;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public boolean checkNull() {
        if (doctorId == 0) return true;
        if (patientId == 0) return true;
        if (service == null || service.isEmpty()) return true;
        if (startTime == null) return true;
        return endTime == null;
    }
}
