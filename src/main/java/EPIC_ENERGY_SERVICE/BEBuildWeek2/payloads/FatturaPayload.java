package EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class FatturaPayload {

     int importo;
     LocalDate data;
     int numeroFattura;
     StatoFattura statoFattura;
     Cliente idCliente;
}
