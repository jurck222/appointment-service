package org.task.appointmentservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.task.appointmentservice.entity.Appointment;
import org.task.appointmentservice.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    public Appointment createAppointment(Appointment appointment) {
        if (!appointment.checkNull()){
            boolean resp = Boolean.TRUE.equals(
                    restTemplate.getForObject("http://AVAILABILITY-SERVICE/api/v1/availability/book/" + appointment.getAvailabilityId(),
                            Boolean.class));
            if(resp) {
                return appointmentRepository.save(appointment);
            }
        }
        throw new RuntimeException("Cant book this slot");
    }

    public void deleteAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isPresent()){
            restTemplate.getForObject("http://AVAILABILITY-SERVICE/api/v1/availability/reset/" + appointment.get().getAvailabilityId(), Boolean.class);
            appointmentRepository.deleteById(id);

        }
        else {
            throw new RuntimeException("Not found");
        }
    }

    public List<Appointment> getAppointmentsForUser(int patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }
}
