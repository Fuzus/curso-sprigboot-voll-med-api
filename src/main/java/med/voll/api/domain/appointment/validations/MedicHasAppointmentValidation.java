package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentData;

public class MedicHasAppointmentValidation {

    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var medicHasAppointment = repository.existsByMedicIdAndData(data.idMedico(), data.date());
        if (medicHasAppointment) {
            throw new ValidacaoException("Medico ja possui outra consulta marcada neste horario");
        }
    }
}
