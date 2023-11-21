package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotNull;

public record NuovaProvinciaDTO(
        @NotNull(message = "Il campo sigla non può essere vuoto")
        String sigla,
        @NotNull(message = "Il campo sigla non può essere vuoto")
        String nome,
        @NotNull(message = "Il campo sigla non può essere vuoto")
        String regione
) {
}
