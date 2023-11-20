package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoComuneDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.service.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comuni")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;

    @GetMapping("")
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return comuneService.getComuni(page, size, orderBy);
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

    @ResponseStatus(HttpStatus.CREATED)
    public Comune save(@RequestBody NuovoComuneDTO body) {
        return comuneService.save(body);
    }
}
