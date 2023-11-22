package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewUserDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    @GetMapping("")
    public Page<Utente> getUser(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy) {
        return utenteService.findAll(page, size > 20 ? 5 : size, orderBy);
    }


    @GetMapping("/{id}")
    public Utente findById(@PathVariable int id) {
        return utenteService.findUtenteById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        utenteService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findByIdAndUpdate(@PathVariable int id, @RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return utenteService.findByIdAndUpdate(id, body);
        }
    }


    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public UserDetails updateProfile(@AuthenticationPrincipal Utente currentUser, @RequestBody NewUserDTO body) {
        return utenteService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentUser) {
        utenteService.findByIdAndDelete(currentUser.getId());
    }

    @PutMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente upload(@RequestParam("avatar") MultipartFile body, @PathVariable int id) throws IOException {
        try {
            return utenteService.uploadImg(body, id);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/me/upload")
    public UserDetails uploadOnProfile(@AuthenticationPrincipal Utente currentUser,  @RequestParam("avatar") MultipartFile body) throws IOException {
        try {

            return utenteService.uploadImg(body, currentUser.getId());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
