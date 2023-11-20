package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Provincia;

public record NuovoComuneDTO(int progressivoDelComune, String nome, Provincia provincia) {
}
