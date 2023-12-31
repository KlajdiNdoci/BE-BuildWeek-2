package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NuovoComuneDTO(
        @NotNull(message = "Il campo progressivo del comune non può essere vuoto")
        int progressivoDelComune,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String nome,
        @NotEmpty(message = "Il campo provincia non può essere vuoto")
        String provincia
) {
}
