package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdatePatientData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressData endereco) {
}
