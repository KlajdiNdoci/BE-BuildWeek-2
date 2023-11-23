package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.config.EmailSender;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewEmailDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailSender emailSender;

    @PostMapping("/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmail(@RequestBody @Validated NewEmailDTO body,@AuthenticationPrincipal Utente currentUser, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                emailSender.sendEmail(body, currentUser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
