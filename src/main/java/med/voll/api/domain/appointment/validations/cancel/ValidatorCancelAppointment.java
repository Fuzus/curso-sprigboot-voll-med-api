package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.appointment.CancelAppointmentData;

public interface ValidatorCancelAppointment {
    void validate(CancelAppointmentData data);
}
