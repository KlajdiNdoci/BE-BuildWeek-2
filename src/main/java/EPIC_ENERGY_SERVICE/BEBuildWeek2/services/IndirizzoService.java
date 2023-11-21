package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;


import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Indirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoIndirizzoDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.ComuneRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzo save(NuovoIndirizzoDTO body) {
        Indirizzo indirizzo = new Indirizzo();
        Comune c = comuneRepository.findByNome(body.comune()).orElseThrow(() -> new BadRequestException("comune non trovato"));
        indirizzo.setCap(body.cap());
        indirizzo.setComune(c);
        indirizzo.setVia(body.via());
        indirizzo.setLocalità(body.località());
        indirizzo.setCivico(body.civico());
        return indirizzoRepository.save(indirizzo);
    }

    public Page<Indirizzo> getIndirizzi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(int id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Indirizzo found = indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        indirizzoRepository.delete(found);
    }
}
