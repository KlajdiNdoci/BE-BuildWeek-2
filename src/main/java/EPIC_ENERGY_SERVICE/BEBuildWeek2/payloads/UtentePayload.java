package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


    public record UtentePayload (
            @NotNull(message = "Il campo username non può essere vuoto")
            String username,
            @NotNull(message = "Il campo email non può essere vuoto")
            String email,
            @NotNull(message = "Il campo password non può essere vuoto")
            String password,
            @NotNull(message = "Il campo nome non può essere vuoto")
            String nome,
            @NotNull(message = "Il campo cognome non può essere vuoto")
            String cognome
    ){

    }

