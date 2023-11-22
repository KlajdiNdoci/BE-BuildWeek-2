package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    protected LocalDate dataInserimento;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    @JoinColumn(name = "email_utente")
    private String emailContatto;
    @JoinColumn(name = "nome_utente")
    private String nomeContatto;
    @JoinColumn(name = "cognome_utente")
    private String cognomeContatto;
    @JoinColumn(name = "telefono_utente")
    private String telefonoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "idCliente")
    private List<Fattura> fatture;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "indirizzoLegale_id")
    private Indirizzo indirizzoSedeLegale;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "indirizzo_operativo_id")
    private Indirizzo indirizzoSedeOperativa;
}


