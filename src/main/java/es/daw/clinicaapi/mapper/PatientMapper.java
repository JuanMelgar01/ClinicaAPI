package es.daw.clinicaapi.mapper;


import es.daw.clinicaapi.dto.request.PatientCreateRequest;
import es.daw.clinicaapi.dto.response.PatientResponse;
import es.daw.clinicaapi.entity.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientCreateRequest r) {
        Patient patient = new Patient();
        patient.setDni(r.dni());
        patient.setFullName(r.fullName());
        patient.setEmail(r.email());
        patient.setPhone(r.phone());
        patient.setDateOfBirth(r.dateOfBirth());
        patient.setActive(true);
        return patient;
    }


    public static PatientResponse toResponse(Patient p) {
        return new PatientResponse(p.getId(), p.getDni(), p.getFullName(), p.getEmail(), p.isActive());
    }
}
