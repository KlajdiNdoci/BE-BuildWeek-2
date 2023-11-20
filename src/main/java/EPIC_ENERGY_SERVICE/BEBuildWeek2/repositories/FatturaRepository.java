package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;

import java.util.Date;

public interface FatturaRepository extends JpaRepository<Fattura, Integer> {
    Page<Fattura> findByIdCliente(Pageable pageable, Cliente idCliente);

    Page<Fattura> findByState(Pageable pageable, StatoFattura statoFattura);

    Page<Fattura> findByDate(Pageable pageable, Date data);
}
