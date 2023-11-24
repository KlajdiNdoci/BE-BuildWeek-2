package EPIC.ENERGY.SERVICE.BEBuildWeek2;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers.ClienteController;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ClienteService;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.utils.TipoCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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

    //----------------Test createCliente-----------------------
    @Test
    public void createCliente() throws Exception {
        ClientePayload clientePayload = new ClientePayload("pippo", "ragione sociale", "qqqqqqqqqqq", "akmks@gmail.com", 11111, "akmkma@gmail.com", "11111111", "aa@gmail.com", "pluto", "aa", "111111", TipoCliente.SRL, "1", "1", LocalDate.of(2023, 11, 12));

        Cliente c = new Cliente();
        given(clienteService.save(ArgumentMatchers.any())).willReturn(new Cliente());

        ResultActions resp = mockMvc.perform(post("/clienti").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clientePayload)));

        resp.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}