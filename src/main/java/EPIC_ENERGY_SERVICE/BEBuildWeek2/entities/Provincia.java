package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "province")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sigla;
    private String nome;
    private String regione;
    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;


}
