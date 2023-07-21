package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.medic.MedicRepository;

public class ActiveMedicValidator {

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
