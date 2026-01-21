package es.daw.clinicaapi.mapper;


import es.daw.clinicaapi.dto.request.DoctorCreateRequest;
import es.daw.clinicaapi.dto.response.DoctorResponse;
import es.daw.clinicaapi.entity.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorCreateRequest r) {
        Doctor doctor = new Doctor();
        doctor.setLicenseNumber(r.licenseNumber());
        doctor.setFullName(r.fullName());
        doctor.setEmail(r.email());
        doctor.setActive(true);
        return doctor;
    }

    public static DoctorResponse toResponse(Doctor d) {
        return new DoctorResponse(d.getId(), d.getLicenseNumber(), d.getFullName(), d.getEmail(), d.isActive());
    }
}
