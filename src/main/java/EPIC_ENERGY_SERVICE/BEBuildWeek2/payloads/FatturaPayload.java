package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FatturaPayload(
        @NotNull(message = "Il campo importo non può essere vuoto")
        int importo,
        @NotNull(message = "Il campo data non può essere vuoto")
        LocalDate data,
        @NotNull(message = "Il campo numero fattura non può essere vuoto")
        int numeroFattura,
        @NotEmpty(message = "Il campo stato fattura non può essere vuoto")
        String statoFattura,
        @NotNull(message = "Il cliente non può essere null")
        int id_cliente
) {


}
