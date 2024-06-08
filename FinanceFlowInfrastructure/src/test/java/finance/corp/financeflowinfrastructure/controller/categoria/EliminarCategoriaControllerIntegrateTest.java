package finance.corp.financeflowinfrastructure.controller.categoria;


import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.categoria.builder.CategoriaDTOBuilder;
import finance.corp.financeflowapplication.service.categoria.EliminarCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria.EliminarCategoriaController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class EliminarCategoriaControllerIntegrateTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private EliminarCategoriaFacade facade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new EliminarCategoriaController(facade)).build();
    }

    @Test
    public void eliminarCategoriaTest_Success() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act & Assert
        mockMvc.perform(delete("/finance-flow/v1/categoria")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
