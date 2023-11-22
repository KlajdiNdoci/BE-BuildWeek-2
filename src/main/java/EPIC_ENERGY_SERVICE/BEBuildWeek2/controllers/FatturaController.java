package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.ErrorList;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.FatturaPayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.FatturaService;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.StatoFattura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("")
    public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy) {
        return fatturaService.findAll(page, size > 20 ? 5 : size, orderBy);
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
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return fatturaService.findByIdAndUpdate(body, id);
        }
    }

    @GetMapping("/requests")
    public Page<Fattura> getRequests(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy,
                                     @RequestParam(required = false) String id_cliente,
                                     @RequestParam(required = false) StatoFattura statoFattura,
                                     @RequestParam(required = false) LocalDate data,
                                     @RequestParam(required = false) String year,
                                     @RequestParam(required = false) String imp1,
                                     @RequestParam(required = false) String imp2) {

        if (id_cliente != null) {
            try {
                return fatturaService.findByIdCliente(page, size > 20 ? 5 : size, orderBy, Integer.parseInt(id_cliente));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Inserisci valori validi");
            }
        }

        if (statoFattura != null)
            return fatturaService.findByStatoFattura(page, size > 20 ? 5 : size, orderBy, statoFattura);
        if (data != null)
            return fatturaService.findByData(page, size > 20 ? 5 : size, orderBy, data);
        if (year != null) {
            try {
                return fatturaService.findByYear(page, size > 20 ? 5 : size, orderBy, Integer.parseInt(year));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Inserisci valori validi");
            }

        }

        if (imp1 != null && imp2 != null) {
            try {
                return fatturaService.findByImporto(page, size > 20 ? 5 : size, orderBy, Double.parseDouble(imp1), Double.parseDouble(imp2));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Inserisci valori validi");
            }
        }


        throw new BadRequestException("Inserisci i parametri corretti");
    }
}
