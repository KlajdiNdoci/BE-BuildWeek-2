package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Page<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable);
    Page<Cliente> findClientiByDataInserimento(Date dataInserimento, Pageable pageable);
    Page<Cliente> findClientiByDataUltimoContatto(Date dataUltimoContatto, Pageable pageable);
}
