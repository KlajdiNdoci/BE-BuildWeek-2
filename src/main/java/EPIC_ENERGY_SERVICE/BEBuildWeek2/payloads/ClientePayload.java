package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoCliente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientePayload(
        @NotEmpty(message = "Il campo nome cliente non può essere vuoto")
        String nomeCliente,
        @NotEmpty(message = "Il campo ragione sociale non può essere vuoto")
        String ragioneSociale,
        @NotEmpty(message = "Il campo partita iva non può essere vuoto")
        String partitaIva,
        @NotEmpty(message = "Il campo email non può essere vuoto")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotNull(message = "Il campo fatturato annuale non può essere vuoto")
        double fatturatoAnnuale,
        @NotEmpty(message = "Il campo pec non può essere vuoto")
        String pec,
        @NotEmpty(message = "Il campo telefono non può essere vuoto")
        String telefono,
        @NotEmpty(message = "Il campo email di contatto non può essere vuoto")
        String emailContatto,
        @NotEmpty(message = "Il campo nome contatto non può essere vuoto")
        String nomeContatto,
        @NotEmpty(message = "Il campo cognome contatto non può essere vuoto")
        String cognomeContatto,
        @NotEmpty(message = "Il campo telefono contatto non può essere vuoto")
        String telefonoContatto,
        @NotNull(message = "Il campo tipo cliente non può essere vuoto")
        TipoCliente tipoCliente,
        String sedeLegale,
        String sedeOperativa,

        @NotNull(message = "Il campo data ultimo contatto non può essere vuoto")
        LocalDate dataUltimoContatto
) {
}
