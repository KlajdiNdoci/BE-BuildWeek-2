package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "comuni")
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int progressivoDelComune;
    private String nome;
    @OneToMany(mappedBy = "comune")
    private List<Indirizzo> indirizzi;
    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)
    private Provincia provincia;
}
