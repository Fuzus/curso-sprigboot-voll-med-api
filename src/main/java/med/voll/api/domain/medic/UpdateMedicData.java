package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdateMedicData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressData endereco) {
}
