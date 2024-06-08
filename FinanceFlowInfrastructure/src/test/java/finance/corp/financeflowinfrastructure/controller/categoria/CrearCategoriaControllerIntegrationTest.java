package finance.corp.financeflowinfrastructure.controller.categoria;

import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria.CrearCategoriaController;
import org.junit.jupiter.api.BeforeEach;



import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



    @SpringBootTest(classes = CrearCategoriaController.class)

public class CrearCategoriaControllerIntegrationTest {



    private MockMvc mockMvc;

    @MockBean
    private CrearCategoriaFacade facade;


    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


}
