package es.daw.clinicaapi.repository;

import es.daw.clinicaapi.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByActive(boolean active, Pageable pageable);
}

