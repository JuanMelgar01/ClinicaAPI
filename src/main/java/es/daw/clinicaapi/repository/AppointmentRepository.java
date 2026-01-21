package es.daw.clinicaapi.repository;

import es.daw.clinicaapi.dto.reports.DoctorTopAppointmentsReport;
import es.daw.clinicaapi.dto.reports.NoShowRateRow;
import es.daw.clinicaapi.entity.Appointment;
import es.daw.clinicaapi.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Solape: existe una cita del mismo doctor que intersecta [startAt, endAt)
    @Query("""
           select (count(a) > 0)
           from Appointment a
           where a.doctor.id = :doctorId
             and a.startAt < :endAt
             and a.endAt > :startAt
             and a.status <> es.daw.clinicaapi.enums.AppointmentStatus.CANCELLED
           """)
    boolean existsOverlapping(Long doctorId, LocalDateTime startAt, LocalDateTime endAt);

    @Query("""
       select new es.daw.clinicaapi.dto.reports.DoctorTopAppointmentsReport(
          d.id, d.fullName, count(a)
       )
       from Appointment a join a.doctor d
       where a.startAt >= :from and a.startAt < :to and a.status <> es.daw.clinicaapi.enums.AppointmentStatus.CANCELLED
       group by d.id, d.fullName
       order by count(a) desc
       """)

    List<DoctorTopAppointmentsReport> topDoctorsByAppointments(LocalDateTime from, LocalDateTime to);


}

