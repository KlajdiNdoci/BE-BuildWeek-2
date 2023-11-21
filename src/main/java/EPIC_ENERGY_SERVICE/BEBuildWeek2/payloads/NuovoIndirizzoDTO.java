package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import jakarta.validation.constraints.NotNull;

public record NuovoIndirizzoDTO(
        @NotNull(message = "Il campo via non può essere vuoto")
        String via,
        @NotNull(message = "Il campo civico non può essere vuoto")
        String civico,
        @NotNull(message = "Il campo localitá non può essere vuoto")
        String località,
        @NotNull(message = "Il campo CAP non può essere vuoto")
        int cap,
        @NotNull(message = "Il campo comune non può essere vuoto")
        Comune comune
) {
}
