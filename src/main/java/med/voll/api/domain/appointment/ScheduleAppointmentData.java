package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medic.Specialty;

import java.time.LocalDateTime;

public record ScheduleAppointmentData(
        @JsonAlias({"medico_id", "id_medico"})
        Long idMedico,
        @NotNull
        @JsonAlias({"paciente_id", "id_paciente"})
        Long idPaciente,
        @NotNull
        @Future
        @JsonAlias("data")
        LocalDateTime date,

        Specialty especialidade
) {
}
