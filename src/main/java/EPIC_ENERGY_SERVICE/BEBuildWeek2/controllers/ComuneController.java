package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Provincia;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoComuneDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ComuneService;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comuni")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("")
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return comuneService.getComuni(page, size > 20 ? 5 : size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Comune findById(@PathVariable int id) {
        return comuneService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        comuneService.findByIdAndDelete(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Comune save(@RequestBody NuovoComuneDTO body) {
        Provincia p = provinciaService.getByNome(body.provincia());
        Comune c = Comune.builder().progressivoDelComune(body.progressivoDelComune()).nome(body.nome()).provincia(p).build();
        return comuneService.save(c);
    }
}
