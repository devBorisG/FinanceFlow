package finance.corp.financeflowinfrastructure.controller.meta;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
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

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class CrearMetaControllerIntegrationTest {
    static final UUID id = UUID.randomUUID();
    @MockBean
    private CrearMetaFacade facade;

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
    void CrearMetaTest_Success() throws Exception {
        UUID id = UUID.randomUUID();
        String metaJson = "{ \"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"fechaInicio\": \"2024-06-07T12:00:00\", \"fechaFin\": \"2024-06-30T12:00:00\", \"monto\": 1000.0, \"usuario\": { \"id\": \"" + id.toString() + "\", \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" } }";

        mockMvc.perform(post("/finance-flow/v1/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(metaJson))
                .andExpect(status().isOk());
    }
    @Test
    void CrearMetaTest_BadRequest() throws Exception {
        UUID id = UUID.randomUUID();
        String metaJson = "{ \"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"fechaInicio\": \"2024-06-07T12:00:00\", \"fechaFin\": \"2024-06-30T12:00:00\", \"monto\": 1000.0, \"usuario\": { \"id\": id, \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" } }";
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        // Crea un objeto MetaDTO con la ID válida del usuario
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setId(id); // Usa la misma ID para la categoría
        metaDTO.setNombre("Nueva Categoria");
        metaDTO.setDescripcion("Nueva Categoria");
        metaDTO.setUsuario(usuario);

        doThrow(new FinanceFlowCustomException(null, "Technical message", "Nombre de categoria ya existe", LayerException.CONTROLLER))
                .when(facade).execute(metaDTO);

        mockMvc.perform(post("/finance-flow/v1/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(metaJson))
                .andExpect(status().isBadRequest());
    }

}
