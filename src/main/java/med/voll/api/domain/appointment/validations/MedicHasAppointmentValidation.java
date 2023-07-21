package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicHasAppointmentValidation implements ValidatorScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var medicHasAppointment = repository.existsByMedicIdAndDate(data.idMedico(), data.date());
        if (medicHasAppointment) {
            throw new ValidacaoException("Medico ja possui outra consulta marcada neste horario");
        }
    }
}
