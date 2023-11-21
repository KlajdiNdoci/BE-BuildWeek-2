package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate data;
    private int numeroFattura;
    private StatoFattura statoFattura;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente idCliente;

    public Fattura(int importo, LocalDate data, int numeroFattura, StatoFattura statoFattura, Cliente idCliente) {
    }
}
