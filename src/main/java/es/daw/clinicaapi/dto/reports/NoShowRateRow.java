package es.daw.clinicaapi.dto.reports;

public record NoShowRateRow(Long doctorId, String doctorName, long total, long noShowOrCancelled) {}
