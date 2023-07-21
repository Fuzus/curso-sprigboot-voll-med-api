package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.patient.PatientRepository;

public class ActivePatientValidator {

    private PatientRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var isPatientActive = repository.findAtivoById(data.idPaciente());
        if (!isPatientActive){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
