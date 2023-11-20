package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmailUtente(String email);
}
