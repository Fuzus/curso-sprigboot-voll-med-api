package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.address.AddressData;

public record RegisterPatientData(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String cpf,
        @Valid
        AddressData endereco
) {
}
