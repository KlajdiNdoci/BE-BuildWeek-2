package EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<List<Cliente>> findByDataUltimoContatto(LocalDate data);

    Page<Cliente> findByDataInserimento(Pageable pageable, LocalDate date);

    Page<Cliente> findByDataUltimoContatto(Pageable pageable, LocalDate date);

    Page<Cliente> findByFatturatoAnnuale(Pageable pageable, double fatturato);

    Page<Cliente> findByIndirizzoSedeLegaleComuneProvinciaNome(Pageable p, String nome);

    @Query("select c from Cliente c order by c.indirizzoSedeLegale.comune.provincia.nome ")
    Page<Cliente> findByIndirizzoSedeLegaleComuneProvinciaOrderByNome(Pageable p);
}
