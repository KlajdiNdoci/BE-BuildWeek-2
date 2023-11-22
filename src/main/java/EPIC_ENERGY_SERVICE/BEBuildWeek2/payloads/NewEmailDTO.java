package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewEmailDTO(
        @NotEmpty(message = "Il campo oggetto non puó essere vuoto")
        String oggetto,
        @NotEmpty(message = "Il campo contenuto non puó essere vuoto")
        String contenuto,
        @NotEmpty(message = "Il campo destinatario non puó essere vuoto")
        String destinatario
) {
}
