package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.saveUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostMapping("/login")
    public UtenteLoginSuccessDTO login(@RequestBody UtenteLoginDTO body) {
        return new UtenteLoginSuccessDTO(authService.authenticateUser(body));
    }
}
