package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "indirizzi")
@Data
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String via;
    private String civico;
    private String località;
    private int cap;
    @ManyToOne
    @JoinColumn(name = "comune_id", nullable = false)
    private Comune comune;
}
