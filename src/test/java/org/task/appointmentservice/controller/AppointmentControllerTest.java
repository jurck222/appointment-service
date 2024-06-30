package org.task.appointmentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.service.AppointmentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment() throws Exception {
        Appointment appointment = new Appointment();
        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/api/v1/appointment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        doNothing().when(appointmentService).deleteAppointment(1);

        mockMvc.perform(delete("/api/v1/appointment/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAppointmentsByUserId() throws Exception {
        List<Appointment> appointments = List.of(new Appointment(), new Appointment());
        when(appointmentService.getAppointmentsForUser(1)).thenReturn(appointments);

        mockMvc.perform(get("/api/v1/appointment/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAppointmentsByDoctorId() throws Exception {
        List<Appointment> appointments = List.of(new Appointment(), new Appointment());
        when(appointmentService.getAppointmentsForDoctor(1)).thenReturn(appointments);

        mockMvc.perform(get("/api/v1/appointment/doctor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
