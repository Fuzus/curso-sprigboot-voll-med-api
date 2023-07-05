package med.voll.api.repository;

import med.voll.api.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatitentRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByAtivoTrue(Pageable pagination);
}
