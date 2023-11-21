package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDate;

public record FatturaPayload(
        @NotNull(message = "Il campo importo non può essere vuoto")
        int importo,
        @NotNull(message = "Il campo data non può essere vuoto")
        LocalDate data,
        @NotNull(message = "Il campo numero fattura non può essere vuoto")
        int numeroFattura,
        @NotNull(message = "Il campo stato fattura non può essere vuoto")
        StatoFattura statoFattura
) {


}
