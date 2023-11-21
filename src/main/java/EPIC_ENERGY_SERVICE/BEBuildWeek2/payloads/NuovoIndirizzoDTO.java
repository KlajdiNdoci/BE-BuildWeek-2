package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NuovoIndirizzoDTO(
        @NotEmpty(message = "Il campo via non può essere vuoto")
        String via,
        @NotEmpty(message = "Il campo civico non può essere vuoto")
        String civico,
        @NotEmpty(message = "Il campo localitá non può essere vuoto")
        String località,
        @NotNull(message = "Il campo CAP non può essere vuoto")
        int cap,
        @NotNull(message = "Il campo comune non può essere vuoto")
        Comune comune
) {
}
