package EPIC_ENERGY_SERVICE.BEBuildWeek2.entities;


import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoUtente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utente implements UserDetails {
    @Id
    @GeneratedValue
    private int id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruoloUtente.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;

    public Utente( String username, String nome, String cognome,  String emailUtente, String password) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.emailUtente = emailUtente;
        this.password = password;
    }
}


