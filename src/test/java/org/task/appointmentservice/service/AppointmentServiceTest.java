package org.task.appointmentservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment_Success() {
        Appointment appointment = mock(Appointment.class);
        when(appointment.checkNull()).thenReturn(false);
        when(appointment.getAvailabilityId()).thenReturn(1);
        when(restTemplate.getForObject("http://AVAILABILITY-SERVICE/api/v1/availability/book/1", Boolean.class))
                .thenReturn(true);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment result = appointmentService.createAppointment(appointment);
        assertNotNull(result);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    public void testCreateAppointment_Failure() {
        Appointment appointment = mock(Appointment.class);
        when(appointment.checkNull()).thenReturn(false);
        when(appointment.getAvailabilityId()).thenReturn(1);
        when(restTemplate.getForObject("http://AVAILABILITY-SERVICE/api/v1/availability/book/1", Boolean.class))
                .thenReturn(false);

        assertThrows(RuntimeException.class, () -> appointmentService.createAppointment(appointment));
        verify(appointmentRepository, never()).save(any());
    }

    @Test
    public void testDeleteAppointment_Success() {
        Appointment appointment = mock(Appointment.class);
        when(appointment.getAvailabilityId()).thenReturn(1);
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));

        appointmentService.deleteAppointment(1);

        verify(restTemplate).getForObject("http://AVAILABILITY-SERVICE/api/v1/availability/reset/1", Boolean.class);
        verify(appointmentRepository).deleteById(1);
    }

    @Test
    public void testDeleteAppointment_NotFound() {
        when(appointmentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> appointmentService.deleteAppointment(1));
        verify(appointmentRepository, never()).deleteById(anyInt());
    }

    @Test
    public void testGetAppointmentsForUser() {
        int patientId = 1;
        List<Appointment> appointments = List.of(mock(Appointment.class), mock(Appointment.class));
        when(appointmentRepository.findAllByPatientId(patientId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAppointmentsForUser(patientId);

        assertEquals(2, result.size());
        verify(appointmentRepository).findAllByPatientId(patientId);
    }

    @Test
    public void testGetAppointmentsForDoctor() {
        int doctorId = 1;
        List<Appointment> appointments = List.of(mock(Appointment.class), mock(Appointment.class));
        when(appointmentRepository.findAllByDoctorId(doctorId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAppointmentsForDoctor(doctorId);

        assertEquals(2, result.size());
        verify(appointmentRepository).findAllByDoctorId(doctorId);
    }
}
