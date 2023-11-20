package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.StatoFattura;

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
