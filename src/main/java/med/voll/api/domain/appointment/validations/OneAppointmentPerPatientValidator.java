package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneAppointmentPerPatientValidator implements ValidatorScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var firstPossibleSchedule = data.date().withHour(7);
        var lastPossibleSchedule = data.date().withHour(18);
        var patientHasAnotherAppointmentToday = repository.existsByPatientIdAndDateBetween(data.idPaciente(), firstPossibleSchedule, lastPossibleSchedule);
        if (patientHasAnotherAppointmentToday){
            throw new ValidacaoException("Paciente ja possui uma consulta agendada neste dia");
        }
    }
}
