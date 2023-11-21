package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

    Optional<List<Fattura>> findByStatoFattura(StatoFattura statoFattura);

    Page<Fattura> findByData(Pageable p, LocalDate date);
}

