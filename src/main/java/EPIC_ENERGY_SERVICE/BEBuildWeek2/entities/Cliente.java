package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoCliente;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name= "Clienti")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Cliente {
    @Id
    @GeneratedValue
    private int idCliente;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private String telefeno;
    @JoinColumn(name = "email_utente")
    private String emailContatto;
    @JoinColumn(name = "nome_utente")
    private String nomeContatto;
    @JoinColumn(name = "cognome_utente")
    private String cognomeContatto;
    @JoinColumn(name = "telefono_utente")
    private String telefenoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @OneToMany(mappedBy = "idCliente")
    @JsonIgnore
    private List<Fattura> fatture;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "indirizzo_id")
    private Indirizzo indirizzo;
}


