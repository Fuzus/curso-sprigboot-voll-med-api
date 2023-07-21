package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.ScheduleAppointmentData;

public interface ValidatorScheduleAppointment {
    void validate(ScheduleAppointmentData data);
}
