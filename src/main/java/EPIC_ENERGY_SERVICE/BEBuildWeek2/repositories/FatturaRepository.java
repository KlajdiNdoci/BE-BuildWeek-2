package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {
    Optional<Fattura> findById( Cliente idCliente, Pageable pageable);
    Page<Fattura> findByDate(Pageable pageable, LocalDate data);
}

