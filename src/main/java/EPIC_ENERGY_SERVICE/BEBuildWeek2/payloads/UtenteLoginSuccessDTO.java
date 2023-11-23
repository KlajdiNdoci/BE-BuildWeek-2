package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UtenteLoginSuccessDTO(
        @NotEmpty
        String accessToken
) {
}
