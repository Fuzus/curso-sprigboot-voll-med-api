package med.voll.api.domain.medic;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.RegisterPatientData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void chooseRandomFreeMedicCenario1() {
        //Given ou arrange
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medic = cadastrarMedico("medico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);
        var patient = cadastrarPaciente("paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medic, patient, nextMonday10AM);

        //When ou act
        var freeMedic = medicRepository.chooseRandomFreeMedic(Specialty.CARDIOLOGIA, nextMonday10AM);

        //Then ou assert
        assertThat(freeMedic).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando estiver disponivel na data")
    void chooseRandomFreeMedicCenario2() {
        //Given ou arrange
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medic = cadastrarMedico("medico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);

        //When ou act
        var freeMedic = medicRepository.chooseRandomFreeMedic(Specialty.CARDIOLOGIA, nextMonday10AM);

        //Then ou assert
        assertThat(freeMedic).isEqualTo(medic);
    }

    private void cadastrarConsulta(Medic medico, Patient paciente, LocalDateTime data) {
        em.persist(new Appointment(null, medico, paciente, data, null));
    }

    private Medic cadastrarMedico(String nome, String email, String crm, Specialty especialidade) {
        var medico = new Medic(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Patient cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Patient(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private RegisterMedicData dadosMedico(String nome, String email, String crm, Specialty especialidade) {
        return new RegisterMedicData(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private RegisterPatientData dadosPaciente(String nome, String email, String cpf) {
        return new RegisterPatientData(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private AddressData dadosEndereco() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}