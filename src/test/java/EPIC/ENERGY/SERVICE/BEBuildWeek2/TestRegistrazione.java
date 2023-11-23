package EPIC.ENERGY.SERVICE.BEBuildWeek2;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers.AuthController;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewUserDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.UtenteLoginDTO;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {AuthController.class})
public class TestRegistrazione {
    NewUserDTO newUser;
    UtenteLoginDTO utenteLoginDTO;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthService authService;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void init() {
        newUser = new NewUserDTO("username", "nome", "cognome", "email@email.com", "ddfmoikmfdDFFd5445)");
        utenteLoginDTO = new UtenteLoginDTO("dssdsdsdds@ds.com", "dsdsffdffddf");
    }


    @Test
    public void createUser() throws Exception {
        given(authService.saveUser(ArgumentMatchers.any())).willReturn(new Utente());

        ResultActions resp = mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newUser)));

        resp.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void loginUser() throws Exception {
        given(authService.authenticateUser(ArgumentMatchers.any())).willReturn("");

        ResultActions resp = mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(utenteLoginDTO)));

        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }


}