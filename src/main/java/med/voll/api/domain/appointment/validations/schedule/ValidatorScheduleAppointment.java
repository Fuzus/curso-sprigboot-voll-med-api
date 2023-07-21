package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.ScheduleAppointmentData;

public interface ValidatorScheduleAppointment {
    void validate(ScheduleAppointmentData data);
}
