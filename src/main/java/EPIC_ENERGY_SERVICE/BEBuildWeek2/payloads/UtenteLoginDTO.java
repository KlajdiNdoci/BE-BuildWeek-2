package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UtenteLoginDTO(
        @NotEmpty(message = "You have to enter an email!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
        String email,
        @NotEmpty(message = "You have to enter a password!")
        String password
) {
}
