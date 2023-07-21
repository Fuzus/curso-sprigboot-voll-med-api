package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;

import java.time.DayOfWeek;

public class OpeningHourValidator {

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
