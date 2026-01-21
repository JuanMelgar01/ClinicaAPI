package es.daw.clinicaapi.service;

import es.daw.clinicaapi.dto.reports.DoctorTopAppointmentsReport;
import es.daw.clinicaapi.dto.response.PatientResponse;
import es.daw.clinicaapi.exception.BadRequestException;
import es.daw.clinicaapi.mapper.PatientMapper;
import es.daw.clinicaapi.repository.AppointmentRepository;
import es.daw.clinicaapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientsService {

    private final PatientRepository patientRepository;

    private static final Set<String> CAMPOS_PERMITIDOS =
            Set.of("fullName", "dni", "email", "active");


    public Page<PatientResponse> listPatients(Pageable pageable) {


        if (pageable.getPageSize() > 50) {
            throw new BadRequestException("El parámetro 'size' no puede ser mayor que 50.");
        }

        for (Sort.Order order : pageable.getSort()) {
            String campo = order.getProperty();
            if (!CAMPOS_PERMITIDOS.contains(campo)) {
                throw new BadRequestException("No se puede ordenar por el campo '" + campo + "'.");
            }
        }


        return patientRepository.findByActive(true, pageable)
                .map(PatientMapper::toResponse);
    }
}

