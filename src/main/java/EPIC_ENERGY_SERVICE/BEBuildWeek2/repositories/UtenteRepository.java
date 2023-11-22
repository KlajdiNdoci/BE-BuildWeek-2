package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);

}
