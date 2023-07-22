package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.medic.*;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterMedicData> registerMedicDataJson;

    @Autowired
    private JacksonTester<MedicDetailedData> medicDetailedDataJson;

    @MockBean
    private MedicRepository repository;

    @Test
    @DisplayName("Deveria restornar HTTP 400 quando informacoes estiverem invalidas")
    @WithMockUser
    void cadastrarMedico_cenario1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria restornar HTTP 200 quando informacoes estiverem validas")
    @WithMockUser
    void cadastrarMedico_cenario2() throws Exception {
        var medicDataRequest = new RegisterMedicData(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGIA,
                dadosEndereco()
        );

        when(repository.save(any())).thenReturn(new Medic(medicDataRequest));

        var response = mvc.perform(
                        post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(registerMedicDataJson.write(medicDataRequest).getJson())
                )
                .andReturn().getResponse();

        var medicDataResponse = new MedicDetailedData(
                null,
                medicDataRequest.nome(),
                medicDataRequest.email(),
                medicDataRequest.crm(),
                medicDataRequest.telefone(),
                medicDataRequest.especialidade(),
                new Address(medicDataRequest.endereco())
        );

        var expectedJson = medicDetailedDataJson.write(medicDataResponse).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);

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