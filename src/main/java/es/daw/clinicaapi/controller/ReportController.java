package es.daw.clinicaapi.controller;

import es.daw.clinicaapi.dto.reports.DoctorTopAppointmentsReport;
import es.daw.clinicaapi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/top-doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DoctorTopAppointmentsReport> topDoctors(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return reportService.topDoctors(from, to);
    }


}

