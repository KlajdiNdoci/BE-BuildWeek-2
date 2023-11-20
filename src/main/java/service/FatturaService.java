package service;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.FatturaPayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.FatturaRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.time.LocalDate;
import java.util.NoSuchElementException;


@Service
public class FatturaService {
    @Autowired
    private final FatturaRepository fatturaRepository;
    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }
    @Autowired
    ClienteService clienteService;


    public Fattura getById(int id) {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Fattura con ID : " + id +"non trovata"));
    }


    public Fattura save(FatturaPayload f) {
        Fattura newFattura = new Fattura();
        newFattura.setImporto(f.getImporto());
        newFattura.setData(f.getData());
        return fatturaRepository.save(newFattura);
    }


    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findAll(pageable);
    }


    public Fattura findByIdAndUpdate(int id, int numeroFattura, int anno, LocalDate data, int importo, StatoFattura statoFattura) {
        Fattura fattura = getById(id);
        fattura.setNumeroFattura(numeroFattura);
        fattura.setData(data);
        fattura.setImporto(importo);
        fattura.setStatoFattura(statoFattura);
        return fatturaRepository.save(fattura);
    }


    public void findByIdAndDelete(int id) {
        Fattura f = getById(id);
        fatturaRepository.delete(f);
    }


    public void deleteAllUtenti() {
        fatturaRepository.deleteAll();
    }


    public Page<Fattura> findByData(int page, String order, LocalDate data) {
        Pageable pagina = PageRequest.of(page, 10, Sort.by(order));
        return fatturaRepository.findByDate(pagina, data);
    }
}
