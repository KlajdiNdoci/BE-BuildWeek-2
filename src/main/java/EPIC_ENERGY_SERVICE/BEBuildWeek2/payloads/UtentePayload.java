package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class UtentePayload {
        private String username;
        private String email;
        private String password;
        private String nome;
        private String cognome;
    }

