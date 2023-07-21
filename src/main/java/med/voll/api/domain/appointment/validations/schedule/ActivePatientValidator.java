package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements ValidatorScheduleAppointment {

    @Autowired
    private PatientRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var isPatientActive = repository.findAtivoById(data.idPaciente());
        if (!isPatientActive){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
