package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;

public record NuovoIndirizzoDTO(String via, String civico, String località, int cap, Comune comune) {
}
