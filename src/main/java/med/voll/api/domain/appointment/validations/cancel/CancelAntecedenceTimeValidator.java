package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.CancelAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelAntecedenceTimeValidator implements ValidatorCancelAppointment{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(CancelAppointmentData data) {
        var appointment = repository.getReferenceById(data.id());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();
        if(differenceInHours < 24){
            throw new ValidacaoException("Consulta só pode ser cancelada com antecedencia minima de 24 horas");
        }

    }
}
