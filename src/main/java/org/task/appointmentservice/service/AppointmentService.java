package org.task.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.repository.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public String createAppointment(Appointment appointment) {
        //TODO: check if timeframe is available and if it is set it to taken, return true.
        if (!appointment.checkNull()){
            appointmentRepository.save(appointment);
            return "Appointment created";
        }
        return "Bad values";
    }

    public String deleteAppointment(int id) {
        if(appointmentRepository.existsById(id)){
            appointmentRepository.deleteById(id);
            return "Appointment deleted";
        }
        return "Appointment not found";
    }

    public List<Appointment> getAppointmentsForUser(int patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }
}
