package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.UtentePayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> findAllUtenti(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findUtenteById(int id) throws NotFoundException { // throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Utente findUtenteByIdAndUpdate(int id, UtentePayload u) throws NotFoundException {
        Utente foundUser = this.findUtenteById(id);
        foundUser.setId(id);
        foundUser.setUsername(u.getUsername());
        foundUser.setNome(u.getNome());
        foundUser.setCognome(u.getCognome());
        foundUser.setEmail(u.getEmail());
        return utenteRepository.save(foundUser);
    }

    public void findUtenteByIdAndDelete(int id) throws NotFoundException {
        Utente foundUtente = this.findUtenteById(id);
        utenteRepository.delete(foundUtente);
    }

    public Utente findUtenteByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public void findByIdAndDelete(int id) {
        Utente u = findUtenteById(id);
        utenteRepository.delete(u);
    }

    public void deleteAllUtenti() {
        utenteRepository.deleteAll();
    }
}
