package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.appointment.validations.ValidatorScheduleAppointment;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorScheduleAppointment> validators;

    public DetailAppointment doSchedule(ScheduleAppointmentData data){
        if (!patientRepository.existsById(data.idPaciente())){
            throw new ValidacaoException("id do paciente informado não existe");
        }

        if (data.idMedico() != null && !medicRepository.existsById(data.idMedico())){
            throw new ValidacaoException("id do medico informado não existe");
        }

        validators.forEach(x -> x.validate(data));

        var medic = chooseMedic(data);
        if (medic == null){
            throw new ValidacaoException("Não existe medico dessa especialidade disponivel nesta data");
        }

        var patient = patientRepository.getReferenceById(data.idPaciente());
        var appointment = new Appointment(null, medic, patient, data.date());

        appointmentRepository.save(appointment);
        return new DetailAppointment(appointment);
    }

    private Medic chooseMedic(ScheduleAppointmentData data) {
        if(data.idMedico() != null){
            return medicRepository.getReferenceById(data.idMedico());
        }

        if (data.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando medico não for selecionado");
        }

        return medicRepository.chooseRandomFreeMedic(data.especialidade(), data.date());
    }

}
