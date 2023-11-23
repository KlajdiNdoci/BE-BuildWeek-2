package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NuovaProvinciaDTO(
        @NotEmpty(message = "Il campo sigla non può essere vuoto")
        String sigla,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String nome,
        @NotEmpty(message = "Il campo regione non può essere vuoto")
        String regione
) {
}
