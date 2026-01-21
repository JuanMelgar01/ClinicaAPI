package es.daw.clinicaapi.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientCreateRequest(
        @NotBlank String dni,
        @NotBlank @Size(max=120) String fullName,
        @Email @NotBlank String email,
        @Size(max=30) String phone,
        LocalDate dateOfBirth
) {}
