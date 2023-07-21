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

    public void doSchedule(ScheduleAppointmentData obj){
        if (!patientRepository.existsById(obj.idPaciente())){
            throw new ValidacaoException("id do paciente informado não existe");
        }

        if (obj.idMedico() != null && !medicRepository.existsById(obj.idMedico())){
            throw new ValidacaoException("id do medico informado não existe");
        }



        var medic = chooseMedic(obj);
        var patient = patientRepository.findById(obj.idPaciente()).get();
        var appointment = new Appointment(null, medic, patient, obj.data());

        appointmentRepository.save(appointment);
    }

    private Medic chooseMedic(ScheduleAppointmentData obj) {
        return null;
    }

}
