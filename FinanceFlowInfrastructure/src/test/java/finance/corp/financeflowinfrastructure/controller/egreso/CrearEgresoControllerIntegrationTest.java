package finance.corp.financeflowinfrastructure.controller.egreso;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.egreso.CrearEgresoFacade;
import finance.corp.financeflowapplication.service.meta.CrearMetaFacade;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import finance.corp.financeflowutils.exception.enumeration.LayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class CrearEgresoControllerIntegrationTest {
    static final UUID id = UUID.randomUUID();
    @MockBean
    private CrearEgresoFacade facade;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void CrearEgresoTest_Success() throws Exception {
        UUID id = UUID.randomUUID();
        String egresoJson = "{ \"nombre\": \"Nombre del egreso\", \"descripcion\": \"Descripción del egreso\", \"fechaInicio\": \"2024-06-07T12:00:00\", \"fechaFin\": \"2024-06-30T12:00:00\", \"monto\": 1000.0, \"periodisida\": 10, \"usuario\": { \"id\": \"" + id.toString() + "\", \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" }, \"categoria\": { \"id\": \"" + id.toString() + "\", \"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"usuario\": { \"id\": \"" + id.toString() + "\", \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" } } }";

        mockMvc.perform(post("/finance-flow/v1/egreso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(egresoJson))
                .andExpect(status().isOk());
    }
    @Test
    void CrearEgresoTest_BadRequest() throws Exception {
        UUID id = UUID.randomUUID();
        String egresoJson = "{ \"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"fechaInicio\": \"2024-06-07T12:00:00\", \"fechaFin\": \"2024-06-30T12:00:00\", \"monto\": 1000.0,\"periodisida\": 10, \"usuario\": { \"id\": id, \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" },\"categoria\":{ \"id\": id,\"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"usuario\": { \"id\": id, \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" } }}";
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setUsuarioDTO(usuario);
        categoriaDTO.setId(id);
        categoriaDTO.setDescripcion("nueva descripcion de categoria");
        categoriaDTO.setNombre("nueva nombre de categoria");

        // Crea un objeto MetaDTO con la ID válida del usuario
        EgresoDTO egresoDTO = new EgresoDTO();
        egresoDTO.setId(id);
        egresoDTO.setNombre("prueba");
        egresoDTO.setDescripcion("prueba");
        egresoDTO.setMonto(12312312);
        egresoDTO.setUsuario(usuario);
        egresoDTO.setPeriodicidad(12);
        egresoDTO.setCategoria(categoriaDTO);


        doThrow(new FinanceFlowCustomException(null, "Technical message", "Nombre de categoria ya existe", LayerException.CONTROLLER))
                .when(facade).execute(egresoDTO);

        mockMvc.perform(post("/finance-flow/v1/egreso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(egresoJson))
                .andExpect(status().isBadRequest());
    }
}
