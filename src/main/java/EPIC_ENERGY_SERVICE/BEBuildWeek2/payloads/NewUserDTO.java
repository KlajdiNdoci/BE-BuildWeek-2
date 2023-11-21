package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Il campo username non può essere vuoto")
        @Size(min = 3, max = 20, message = "The username must have between 3 and 20 characters")
        String username,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        @Size(min = 3, max = 20, message = "The name must have between 3 and 20 characters")
        String name,
        @NotEmpty(message = "Il campo cognome non può essere vuoto")
        @Size(min = 3, max = 20, message = "The surname must have between 3 and 20 characters")
        String surname,

        @NotEmpty(message = "Il campo email non può essere vuoto")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email non é valida")
        String email,

        @NotEmpty(message = "Il campo password non può essere vuoto")
        @Size(min = 8, max = 20, message = "La password deve contenere tra gli 8 e i 20 caratteri")
        @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.\\d).$", message = "La password deve contenere almeno una maiuscola, una minuscola e un carattere speciale")
        String password


) {
}
