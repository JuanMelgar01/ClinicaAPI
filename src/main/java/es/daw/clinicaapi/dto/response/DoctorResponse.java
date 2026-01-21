package es.daw.clinicaapi.dto.response;

public record DoctorResponse(Long id, String licenseNumber, String fullName, String email, boolean active) {}
