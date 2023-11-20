package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoUtente;

@Entity
@Table(name= "entities.Utenti")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Utente {
    @Id
    @GeneratedValue
    private int idUtente;
    private String nome;
    private String cognome;
    private String username;
    private String emailUtente;
    private String password;
    @Enumerated(EnumType.STRING)
    private TipoUtente ruoloUtente;


    public Utente( String username, String nome, String cognome,  String emailUtente, String password) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.emailUtente = emailUtente;
        this.password = password;
    }
}


