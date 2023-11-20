package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Provincia;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Provincia save(Provincia body) {
        return provinciaRepository.save(body);
    }

    public Page<Provincia> getProvincie(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return provinciaRepository.findAll(pageable);
    }

    public Provincia findById(int id) {
        return provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Provincia found = provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        provinciaRepository.delete(found);
    }

    public Provincia getByNome(String nome) {
        return provinciaRepository.findByNome(nome);
    }
}
