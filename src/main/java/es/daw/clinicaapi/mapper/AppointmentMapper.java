package es.daw.clinicaapi.mapper;


import es.daw.clinicaapi.dto.response.AppointmentResponse;
import es.daw.clinicaapi.dto.response.DoctorMini;
import es.daw.clinicaapi.dto.response.PatientMini;
import es.daw.clinicaapi.entity.Appointment;

public class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getStartAt(),
                a.getEndAt(),
                a.getMinutes(),
                a.getStatus().name(),
                new PatientMini(a.getPatient().getId(), a.getPatient().getFullName()),
                new DoctorMini(a.getDoctor().getId(), a.getDoctor().getFullName())
        );
    }
}
