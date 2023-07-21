package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicValidator implements ValidatorScheduleAppointment {

    @Autowired
    private MedicRepository repository;

    public void validate(ScheduleAppointmentData data) {
        //Escolha do medico é opcional
        if (data.idMedico() == null) {
            return;
        }

        var isMedicActive = repository.findAtivoById(data.idMedico());
        if (!isMedicActive){
            throw new ValidacaoException("Consulta não pode ser agendada com medico inativo");
        }

    }
}
