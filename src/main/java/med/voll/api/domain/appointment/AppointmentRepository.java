package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByMedicIdAndData(Long idMedico, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long idPaciente, LocalDateTime firstSchedule, LocalDateTime lastSchedule);
}
