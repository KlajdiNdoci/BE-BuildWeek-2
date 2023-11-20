package repository;
import entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository <Utenti, Integer> {
    Optional<Utenti> findByEmailUtente(String email);
}
