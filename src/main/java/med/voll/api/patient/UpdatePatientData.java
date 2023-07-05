package med.voll.api.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record UpdatePatientData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressData endereco) {
}
