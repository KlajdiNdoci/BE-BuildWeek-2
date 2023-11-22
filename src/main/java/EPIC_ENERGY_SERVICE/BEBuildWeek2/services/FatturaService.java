package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.FatturaPayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.FatturaRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;


@Service
public class FatturaService {
    @Autowired
    private final FatturaRepository fatturaRepository;
    @Autowired
    ClienteService clienteService;

    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    public Fattura getById(int id) {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Fattura con ID : " + id + "non trovata"));
    }


    public Fattura save(FatturaPayload f) {
        Fattura newFattura = new Fattura();
        if (f.statoFattura() == null) {
            throw new BadRequestException("stato fattura obbligatorio");
        } else if (f.statoFattura().toUpperCase().trim().equals("EMESSA") || f.statoFattura().toUpperCase().trim().equals("SCADUTA") || f.statoFattura().toUpperCase().trim().equals("IN_ATTESA") || f.statoFattura().toUpperCase().trim().equals("SALDATA")) {
            newFattura.setImporto(f.importo());
            newFattura.setData(f.data());
            newFattura.setNumeroFattura(f.numeroFattura());
            newFattura.setStatoFattura(StatoFattura.valueOf(f.statoFattura().toUpperCase().trim()));
            return fatturaRepository.save(newFattura);
        } else {
            throw new BadRequestException("stato fattura non corretto inseriscine uno tra EMESSA,SCADUTA,IN_ATTESA,SALDATA");
        }

    }


    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findAll(pageable);
    }


    public Fattura findByIdAndUpdate(FatturaPayload f, int id) {
        Fattura fattura = getById(id);
        if (f.statoFattura().toUpperCase().trim().equals("EMESSA") || f.statoFattura().toUpperCase().trim().equals("SCADUTA") || f.statoFattura().toUpperCase().trim().equals("IN_ATTESA") || f.statoFattura().toUpperCase().trim().equals("SALDATA") || f.statoFattura() == null) {
            fattura.setImporto(f.importo());
            fattura.setData(f.data());
            fattura.setStatoFattura(f.statoFattura() == null ? fattura.getStatoFattura() : StatoFattura.valueOf(f.statoFattura().toUpperCase().trim()));
            fattura.setNumeroFattura(f.numeroFattura());
            return fatturaRepository.save(fattura);
        } else {
            throw new BadRequestException("stato fattura non corretto inseriscine uno tra EMESSA,SCADUTA,IN_ATTESA,SALDATA");
        }
    }


    public void findByIdAndDelete(int id) {
        Fattura f = getById(id);
        fatturaRepository.delete(f);
    }


    public void deleteAllUtenti() {
        fatturaRepository.deleteAll();
    }


    public Page<Fattura> findByData(int page, int size, String order, LocalDate data) {
        Pageable pagina = PageRequest.of(page, size, Sort.by(order));
        return fatturaRepository.findByData(pagina, data);
    }
}
