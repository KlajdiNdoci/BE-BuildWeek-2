package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.config.EmailSender;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.UnauthorizedException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewUserDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.UtenteLoginDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.UtenteRepository;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.security.JWTTools;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    EmailSender emailSender;

    public String authenticateUser(UtenteLoginDTO body) {
        Utente user = utenteService.findUtenteByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);

        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }

    public Utente saveUser(NewUserDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " é giá stata utilizzata!");
        });

        Utente newUser = new Utente();
        newUser.setUsername(body.username());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setRuolo(TipoUtente.USER);
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        Utente savedUser = utenteRepository.save(newUser);
        emailSender.sendRegistrationEmail(body.email());

        return savedUser;
    }
}
