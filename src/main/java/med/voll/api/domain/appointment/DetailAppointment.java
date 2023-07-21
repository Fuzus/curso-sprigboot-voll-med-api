package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailAppointment(Long id, Long idMedico, Long idPatiente, LocalDateTime data) {
}
