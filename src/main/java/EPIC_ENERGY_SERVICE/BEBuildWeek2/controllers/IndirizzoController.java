package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Indirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NuovoIndirizzoDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("")
    public Page<Indirizzo> getIndirizzi(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy) {
        return indirizzoService.getIndirizzi(page, size > 20 ? 5 : size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Indirizzo findById(@PathVariable int id) {
        return indirizzoService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        indirizzoService.findByIdAndDelete(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo save(@RequestBody NuovoIndirizzoDTO body) {
        return indirizzoService.save(body);
    }
}
