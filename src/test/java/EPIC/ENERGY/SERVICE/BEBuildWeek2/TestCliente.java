package EPIC.ENERGY.SERVICE.BEBuildWeek2;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers.ClienteController;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ClienteService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ClienteController.class})
public class TestCliente {

    Page<Cliente> clientes;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void init() {

    }

    //----------------------Test delete------------------
    @Test
    public void deleteCliente() throws Exception {

        ResultActions resp = mockMvc.perform(delete("/clienti/1").contentType(MediaType.APPLICATION_JSON).content(""));

        resp.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    //----------------------Test GetAll--------------------
    @Test
    public void getAllClienti() throws Exception {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        Page<Cliente> clientePage = new PageImpl<>(clientes);

        given(clienteService.getAllClienti(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyString())).willReturn(clientePage);

        ResultActions resp = mockMvc.perform(get("/clienti?size=12").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clientes)));
        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    //---------------------Test getbyId---------------------------
    @Test
    public void getById() throws Exception {
        given(clienteService.getById(ArgumentMatchers.anyInt())).willReturn(new Cliente());
        ResultActions resp = mockMvc.perform((get("/clienti/1").contentType(MediaType.APPLICATION_JSON)).content(objectMapper.writeValueAsString(new Cliente())));
        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }


    //--------------------Test findByFatturaato----------------

    @Test
    public void findByFatturato() throws Exception {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        Page<Cliente> clientePage = new PageImpl<>(clientes);

        given(clienteService.findByFatturatoAnnuale(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(), ArgumentMatchers.anyDouble())).willReturn(clientePage);
        ResultActions resp = mockMvc.perform(get("/clienti/filter?fatturatoAnnuale=10").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clientePage)));
        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }

}