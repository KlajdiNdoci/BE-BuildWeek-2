package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.ErrorList;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayload;
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
        return fatturaService.getFatture(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Fattura findById(@PathVariable long id) {
        return fatturaService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura createFattura(@RequestBody @Validated NewFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ErrorList(validation.getAllErrors());
        } else return fatturaService.save(body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        fatturaService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated NewFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return fatturaService.findByIdAndUpdate(id, body);
        }
    }
}
