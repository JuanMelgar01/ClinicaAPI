package es.daw.clinicaapi.dto.reports;

public record DoctorTopAppointmentsReport(Long doctorId, String doctorName, long totalAppointments) {}