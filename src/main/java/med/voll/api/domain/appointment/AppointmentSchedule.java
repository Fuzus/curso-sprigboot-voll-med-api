package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void doSchedule(ScheduleAppointmentData data){
        if (!patientRepository.existsById(data.idPaciente())){
            throw new ValidacaoException("id do paciente informado não existe");
        }

        if (data.idMedico() != null && !medicRepository.existsById(data.idMedico())){
            throw new ValidacaoException("id do medico informado não existe");
        }



        var medic = chooseMedic(data);
        var patient = patientRepository.getReferenceById(data.idPaciente());
        var appointment = new Appointment(null, medic, patient, data.date());

        appointmentRepository.save(appointment);
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
