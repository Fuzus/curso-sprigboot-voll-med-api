package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHourValidator implements ValidatorScheduleAppointment {

    public void validate(ScheduleAppointmentData data){
        var dateAppointment = data.date();

        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = dateAppointment.getHour() < 7;
        var afterClosing = dateAppointment.getHour() > 18;
        if(sunday || beforeOpening || afterClosing) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento da Clínica");
        }
    }
}
