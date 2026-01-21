package es.daw.clinicaapi.service;

import es.daw.clinicaapi.dto.reports.DoctorTopAppointmentsReport;
import es.daw.clinicaapi.exception.BadRequestException;
import es.daw.clinicaapi.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AppointmentRepository appointmentRepository;

    public List<DoctorTopAppointmentsReport> topDoctors(LocalDateTime from, LocalDateTime to) {

        if (from == null || to == null || !from.isBefore(to)) {
            throw new BadRequestException("Rango invádilo: 'from' debe ser anterior a 'to'");
        }

        return appointmentRepository.topDoctorsByAppointments(from, to);
    }


}

