package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String via;
    private String civico;
    private String località;
    private int cap;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comune_id", nullable = false)
    private Comune comune;
}
