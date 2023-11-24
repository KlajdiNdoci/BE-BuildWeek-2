package EPIC.ENERGY.SERVICE.BEBuildWeek2;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers.AuthController;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Fattura;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.FatturaPayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.FatturaService;
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
import java.time.LocalDate;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {AuthController.class})


public class TestFatture {

    FatturaPayload newFattura;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FatturaService fatturaService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        newFattura = new  FatturaPayload (100, LocalDate.of(2010,10,10), 21, "statoFattura", 1);

    }
    @Test
    public void getById() throws Exception {
        given(fatturaService.getById(ArgumentMatchers.anyInt())).willReturn(new Fattura());
        ResultActions resp = mockMvc.perform((get("/fattura/1").contentType(MediaType.APPLICATION_JSON)).content(objectMapper.writeValueAsString(new Fattura())));
        resp.andExpect(MockMvcResultMatchers.status().isOk());


    } @Test
    public void deleteFattura() throws Exception {
        ResultActions resp = mockMvc.perform(delete("/fattura/1").contentType(MediaType.APPLICATION_JSON).content(""));
        resp.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
