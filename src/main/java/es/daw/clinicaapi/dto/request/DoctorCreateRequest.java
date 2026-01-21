package es.daw.clinicaapi.dto.request;

import jakarta.validation.constraints.*;

public record DoctorCreateRequest(
        @NotBlank String licenseNumber,
        @NotBlank @Size(max=120) String fullName,
        @Email @NotBlank String email
) {}

