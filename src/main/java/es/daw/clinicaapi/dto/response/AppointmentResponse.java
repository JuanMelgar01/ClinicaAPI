package es.daw.clinicaapi.dto.response;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int minutes,
        String status,
        PatientMini patient,
        DoctorMini doctor
) {}
