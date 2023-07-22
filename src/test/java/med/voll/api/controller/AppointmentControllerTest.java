package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.DetailAppointment;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.medic.Specialty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentData> scheduleAppointmentJson;

    @Autowired
    private JacksonTester<DetailAppointment> detailAppointmentJson;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400 quando informações estão invalidas")
    @WithMockUser
    void scheduleAppointment_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 quando informações estão validas")
    @WithMockUser
    void scheduleAppointment_cenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var detailAppointmentData = new DetailAppointment(null, 2L, 3L, date);

        when(appointmentSchedule.doSchedule(any())).thenReturn(detailAppointmentData);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(scheduleAppointmentJson.write(
                                        new ScheduleAppointmentData(2L,3L, date, specialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = detailAppointmentJson.write(detailAppointmentData).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}