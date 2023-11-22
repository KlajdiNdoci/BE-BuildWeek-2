package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.ErrorList;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayloadModificaIndirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order) {
        return clienteService.getAllClienti(page, size > 20 ? 5 : size, order);

    }

    @GetMapping("/get_all_by_provincia")
    public Page<Cliente> getAllByProvincia(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order, @RequestParam String prov) {
        return clienteService.getAllByProvincia(page, size > 20 ? 5 : size, order, prov);

    }

    @GetMapping("/get_all_order_by_provincia")
    public Page<Cliente> getAllOrderByProvincia(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order) {
        return clienteService.findByIndirizzoSedeLegaleComuneProvinciaOrderByNome(page, size > 20 ? 5 : size, order);

    }

    @GetMapping("/{id}")
    public Cliente getSingleCliente(@PathVariable int id) {
        return clienteService.getById(id);
    }

    @GetMapping("/getbydata")
    public Page<Cliente> getByDatainserimento(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order, @RequestParam LocalDate data) {
        return clienteService.getByDataInserimento(page, size > 20 ? 5 : size, order, data);
    }

    @GetMapping("/filter")
    public Page<Cliente> getByFilter(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String order,
                                     @RequestParam(name = "parametro1", required = false) String nome,
                                     @RequestParam(name = "parametro2", required = false) LocalDate dataInserimento,
                                     @RequestParam(name = "parametro3", required = false) Double fatturatoAnnuale,
                                     @RequestParam(name = "parametro4", required = false) LocalDate dataUltimoContatto) {
        if (nome != null) {
            return clienteService.findByNomeContattoStartingWithIgnoreCase(page, size > 20 ? 5 : size, order, nome);
        } else if (dataInserimento != null) {
            return clienteService.findByDataInserimento(page, size > 20 ? 5 : size, order, dataInserimento);
        } else if (fatturatoAnnuale != null) {
            return clienteService.findByFatturatoAnnuale(page, size > 20 ? 5 : size, order, fatturatoAnnuale);
        } else if (dataUltimoContatto != null) {
            return clienteService.findByDataUltimoContatto(page, size > 20 ? 5 : size, order, dataUltimoContatto);
        } else {
            throw new BadRequestException("Tutti i parametri sono null, inseriscine uno");
        }
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente createCliente(@RequestBody @Validated ClientePayload body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ErrorList(validation.getAllErrors());
        } else {
            try {
                return clienteService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/modifica_indirizzi/{id}")
    public Cliente modifyCliente(@RequestBody @Validated ClientePayloadModificaIndirizzo body, BindingResult validation, @PathVariable int id) {
        if (validation.hasErrors()) {
            throw new ErrorList(validation.getAllErrors());
        } else {
            return clienteService.modifyCliente(body, id);
        }
    }

    @PutMapping("/{id}/upload_img")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente uploadImg(@RequestParam("logo") MultipartFile file, @PathVariable int id) throws IOException {
        return clienteService.uploadImg(file, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable int id) {

        clienteService.deleteCliente(id);
    }

}
