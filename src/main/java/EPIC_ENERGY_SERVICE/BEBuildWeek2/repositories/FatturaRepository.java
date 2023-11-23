package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

    Page<Fattura> findByData(Pageable p, LocalDate date);

    Page<Fattura> findByIdClienteId(Pageable p, int id);

    Page<Fattura> findByStatoFattura(Pageable p, StatoFattura s);

    Page<Fattura> findByImportoBetween(Pageable p, double imp1, double imp2);

    @Query("select f from Fattura f where YEAR(f.data)=:y")
    Page<Fattura> getByYear(Pageable p, int y);
}

