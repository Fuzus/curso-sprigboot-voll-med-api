package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;

import java.time.Duration;
import java.time.LocalDateTime;

public class AntecedenceTimeValidator {

    public void validate(ScheduleAppointmentData data) {
        var appointmentTime = data.date();
        var now = LocalDateTime.now();

        var differenceInMinutes = Duration.between(now, appointmentTime).toMinutes();

        if (differenceInMinutes < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
