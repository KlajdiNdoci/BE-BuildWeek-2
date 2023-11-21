package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoComuneDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;

    public Comune save(NuovoComuneDTO body) {
        Comune comune = new Comune();
        comune.setNome(body.nome());
        comune.setProgressivoDelComune(body.progressivoDelComune());
        comune.setProvincia(body.provincia());
        return comuneRepository.save(comune);
    }

    public Page<Comune> getComuni(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return comuneRepository.findAll(pageable);
    }

    public Comune findById(int id) {
        return comuneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Comune found = comuneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        comuneRepository.delete(found);
    }


}
