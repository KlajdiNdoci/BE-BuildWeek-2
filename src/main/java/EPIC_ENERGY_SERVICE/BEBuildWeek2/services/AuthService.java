package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.UnauthorizedException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.security.JWTTools;
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

    public String authenticateUser(UserLoginDTO body) {
        Utente user = utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);

        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }

    public Utente saveUser(NewUserDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("The email " + user.getEmail() + " has already been used!");
        });

        Utente newUser = new Utente();
        newUser.setUsername(body.username());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setRuolo("USER");
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        return utenteRepository.save(newUser);
    }
}
