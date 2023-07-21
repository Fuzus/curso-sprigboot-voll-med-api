package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancelAppointmentData(
        @NotNull
        Long id,
        @NotNull
        CancelReason motivo
) {
}
