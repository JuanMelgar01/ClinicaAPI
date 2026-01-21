package es.daw.clinicaapi.controller;

import es.daw.clinicaapi.dto.request.AppointmentCreateRequest;
import es.daw.clinicaapi.dto.response.AppointmentResponse;
import es.daw.clinicaapi.mapper.AppointmentMapper;
import es.daw.clinicaapi.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public AppointmentResponse create(@RequestBody AppointmentCreateRequest r) {
        return AppointmentMapper.toResponse(appointmentService.create(r));
    }

}
