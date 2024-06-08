package finance.corp.financeflowinfrastructure.controller.categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.ConsultarCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria.ConsultarCategoriaController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class ConsultarCategoriaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultarCategoriaFacade consultarCategoriaFacade;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConsultarCategoriaController(consultarCategoriaFacade)).build();
        objectMapper = new ObjectMapper();

    }
    @Test
    public void testExecute_Success() throws Exception {
        UUID id = UUID.randomUUID();
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        List<CategoriaDTO> categoriaDTOList = Collections.singletonList(categoriaDTO);

        when(consultarCategoriaFacade.execute(Optional.of(categoriaDTO))).thenReturn(categoriaDTOList);

        mockMvc.perform(get("/finance-flow/v1/categoria")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
