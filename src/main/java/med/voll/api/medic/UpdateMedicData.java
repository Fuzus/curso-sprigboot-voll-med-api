package med.voll.api.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record UpdateMedicData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressData endereco) {
}
