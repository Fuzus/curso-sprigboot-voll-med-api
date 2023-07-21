package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.DetailAppointment;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid ScheduleAppointmentData data){
        var dto = schedule.doSchedule(data);
        return ResponseEntity.ok(dto);
    }
}
