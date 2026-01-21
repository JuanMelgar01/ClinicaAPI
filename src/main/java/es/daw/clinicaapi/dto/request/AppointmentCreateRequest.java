package es.daw.clinicaapi.dto.request;


import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record AppointmentCreateRequest(
        Long patientId,
        Long doctorId,
        LocalDateTime startAt,
        int minutes,
        String reason
) {}

