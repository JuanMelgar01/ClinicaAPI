package es.daw.clinicaapi.controller;

import es.daw.clinicaapi.dto.response.PatientResponse;
import es.daw.clinicaapi.entity.Patient;
import es.daw.clinicaapi.mapper.PatientMapper;
import es.daw.clinicaapi.repository.PatientRepository;
import es.daw.clinicaapi.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientsService patientsService;

    @GetMapping
    public ResponseEntity<Page<PatientResponse>> list(
            @PageableDefault(sort = "fullName", direction = Sort.Direction.ASC, size = 10)Pageable pageable) {
        return ResponseEntity.ok(patientsService.listPatients(pageable));
    }


}
