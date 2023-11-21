package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.ErrorList;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.FatturaPayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("")
    public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy){
        return fatturaService.findAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Fattura findById(@PathVariable int id) {
        return fatturaService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura createFattura(@RequestBody @Validated FatturaPayload body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ErrorList(validation.getAllErrors());
        } else return fatturaService.save(body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        fatturaService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByIdAndUpdate(@PathVariable int id, @RequestBody @Validated FatturaPayload body, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return fatturaService.findByIdAndUpdate(body, id);
        }
    }
}
