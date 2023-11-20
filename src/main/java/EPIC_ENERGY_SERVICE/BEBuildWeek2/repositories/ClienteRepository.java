package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    Page<Cliente> findByDataInserimento(Pageable pageable, LocalDate dataInserimento);
    Page<Cliente> findByDataUltimoContatto (Pageable pageable,LocalDate dateUltimoContatto);
    Page<Cliente> findByFatturatoAnnuale( Pageable pageable,Double fatturatoAnnuale);
}
