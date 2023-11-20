package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Fattura {
    @Id
    @GeneratedValue
    private int idFattura;
    private Data data;
    private int numeroFattura;
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente idCliente;
}
