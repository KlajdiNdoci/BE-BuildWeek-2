package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.TipoUtente;

@Entity
@Table(name= "entities.Utenti")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Utenti {
    @Id
    @GeneratedValue
    private int idUtenti;
    private String nome;
    private String cognome;
    private String username;
    private String emailUtente;
    private String password;
    @Enumerated(EnumType.STRING)
    private TipoUtente ruoloUtente;

}


