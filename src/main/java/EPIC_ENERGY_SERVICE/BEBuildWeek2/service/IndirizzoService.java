package EPIC_ENERGY_SERVICE.BEBuildWeek2.service;


import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Indirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoIndirizzoDTO;
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

    public Indirizzo save(NuovoIndirizzoDTO body) {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setCap(body.cap());
        indirizzo.setComune(body.comune());
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
