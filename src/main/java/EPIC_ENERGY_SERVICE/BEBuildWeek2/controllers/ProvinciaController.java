package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Provincia;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovaProvinciaDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provincie")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("")
    public Page<Provincia> getProvincie(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy) {
        return provinciaService.getProvincie(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Provincia findById(@PathVariable int id) {
        return provinciaService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        provinciaService.findByIdAndDelete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Provincia save(@RequestBody NuovaProvinciaDTO body) {
        Provincia p = Provincia.builder().nome(body.nome()).regione(body.regione()).sigla(body.sigla()).build();
        return provinciaService.save(p);
    }
}
