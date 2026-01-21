package es.daw.clinicaapi.service;

import es.daw.clinicaapi.dto.request.AppointmentCreateRequest;
import es.daw.clinicaapi.dto.response.AppointmentResponse;
import es.daw.clinicaapi.entity.Appointment;
import es.daw.clinicaapi.entity.Doctor;
import es.daw.clinicaapi.entity.Patient;
import es.daw.clinicaapi.enums.AppointmentStatus;
import es.daw.clinicaapi.exception.BadRequestException;
import es.daw.clinicaapi.exception.BusinessRuleException;
import es.daw.clinicaapi.exception.NotFoundException;
import es.daw.clinicaapi.mapper.AppointmentMapper;
import es.daw.clinicaapi.repository.AppointmentRepository;
import es.daw.clinicaapi.repository.DoctorRepository;
import es.daw.clinicaapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    @Transactional
    public Appointment create(AppointmentCreateRequest req) {

        // COMPLETA ESTE MÉTODO
        if (req.minutes() <= 0) {
            throw new BadRequestException("Los minutos deben ser positivos.");
        }

        //"Patient not found: "
        Patient patient = patientRepository.findById(req.patientId())
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado: " + req.patientId()));
        // "Patient is inactive."
        if (!patient.isActive()) {
            throw new BusinessRuleException("Paciente esta inactivo.");
        }
        //"Doctor not found: "
        Doctor doctor = doctorRepository.findById(req.doctorId())
                .orElseThrow(() -> new NotFoundException("Doctor no encontrado: " + req.doctorId()));
        //"Doctor is inactive."
        if (!doctor.isActive()) {
            throw new BusinessRuleException("Doctor esta inactivo.");
        }

        //"Appointment startAt must be in the future."
        LocalDateTime now = LocalDateTime.now();
        if (req.startAt().isBefore(now)) {
            throw new BusinessRuleException("StartAt no puede ser anterior a la fecha del sistema");
        }
        LocalDateTime endAt = req.startAt().plusMinutes(req.minutes());

        //boolean overlaps = appointmentRepository.existsOverlapping(doctorId, startAt, endAt);
        boolean overlaps = appointmentRepository.existsOverlapping(
                doctor.getId(),
                req.startAt(),
                endAt
        );


        //"Doctor has an overlapping appointment."
        if (overlaps) {
            throw new BusinessRuleException("Ya existe una cita solapada para ese doctor en ese intervalo");
        }
        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setStartAt(req.startAt());
        a.setEndAt(endAt);
        a.setMinutes(req.minutes());
        a.setStatus(AppointmentStatus.SCHEDULED);

        Appointment saved = appointmentRepository.save(a);

        return saved;
    }

}
