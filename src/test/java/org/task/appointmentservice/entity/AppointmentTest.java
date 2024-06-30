package org.task.appointmentservice.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {
    @Test
    public void testCheckNull_AllFieldsValid() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(1);
        appointment.setPatientId(1);
        appointment.setAvailabilityId(1);
        appointment.setService("General checkup");
        appointment.setStartTime(LocalDateTime.now());
        appointment.setEndTime(LocalDateTime.now().plusHours(1));

        assertFalse(appointment.checkNull());
    }

    @Test
    public void testCheckNull_DoctorIdMissing() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(0);
        appointment.setPatientId(1);
        appointment.setAvailabilityId(1);
        appointment.setService("General checkup");
        appointment.setStartTime(LocalDateTime.now());
        appointment.setEndTime(LocalDateTime.now().plusHours(1));

        assertTrue(appointment.checkNull());
    }

    @Test
    public void testCheckNull_PatientIdMissing() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(1);
        appointment.setPatientId(0);
        appointment.setAvailabilityId(1);
        appointment.setService("General checkup");
        appointment.setStartTime(LocalDateTime.now());
        appointment.setEndTime(LocalDateTime.now().plusHours(1));

        assertTrue(appointment.checkNull());
    }

    @Test
    public void testCheckNull_ServiceMissing() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(1);
        appointment.setPatientId(1);
        appointment.setAvailabilityId(1);
        appointment.setService("");
        appointment.setStartTime(LocalDateTime.now());
        appointment.setEndTime(LocalDateTime.now().plusHours(1));

        assertTrue(appointment.checkNull());
    }

    @Test
    public void testCheckNull_StartTimeMissing() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(1);
        appointment.setPatientId(1);
        appointment.setAvailabilityId(1);
        appointment.setService("General checkup");
        appointment.setEndTime(LocalDateTime.now().plusHours(1));

        assertTrue(appointment.checkNull());
    }

    @Test
    public void testCheckNull_EndTimeMissing() {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(1);
        appointment.setPatientId(1);
        appointment.setAvailabilityId(1);
        appointment.setService("General checkup");
        appointment.setStartTime(LocalDateTime.now());

        assertTrue(appointment.checkNull());
    }
}
