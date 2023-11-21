package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewUserDTO;
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

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }


    public Utente findUtenteById(int id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByIdAndUpdate(int id, NewUserDTO u) throws NotFoundException {
        Utente foundUser = this.findUtenteById(id);
        foundUser.setId(id);
        foundUser.setUsername(u.username());
        foundUser.setNome(u.nome());
        foundUser.setCognome(u.cognome());
        foundUser.setEmail(u.email());
        return utenteRepository.save(foundUser);
    }

    public void findByIdAndDelete(int id) throws NotFoundException {
        Utente foundUtente = this.findUtenteById(id);
        utenteRepository.delete(foundUtente);
    }

    public Utente findUtenteByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public void deleteAllUtenti() {
        utenteRepository.deleteAll();
    }
}
