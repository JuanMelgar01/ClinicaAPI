package es.daw.clinicaapi.dto.response;

public record PatientResponse(Long id, String dni, String fullName, String email, boolean active) {}
