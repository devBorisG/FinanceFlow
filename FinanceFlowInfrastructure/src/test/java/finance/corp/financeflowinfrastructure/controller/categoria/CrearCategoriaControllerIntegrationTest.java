package finance.corp.financeflowinfrastructure.controller.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import finance.corp.financeflowutils.exception.enumeration.LayerException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class CrearCategoriaControllerIntegrationTest {

    @MockBean
    private CrearCategoriaFacade facade;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void CrearCategoriaTest_Success() throws Exception {
        UUID id = UUID.randomUUID();

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(id);
        categoriaDTO.setUsuarioDTO(usuario);
        categoriaDTO.setNombre("Nueva");
        categoriaDTO.setDescripcion("Descripción de la nueva categoría");

        System.out.println(objectMapper.writeValueAsString(categoriaDTO));
        Map<String, Object> respuests = new HashMap<>();
        System.out.println(objectMapper.writeValueAsString(respuests));
        mockMvc.perform(post("/finance-flow/v1/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(respuests)));
    }

    @Test
    void CrearCategoriaTest_BadRequest() throws Exception {
        UUID id = UUID.randomUUID();
        String categoriaJson = "{ \"nombre\": \"Nueva Categoria\", \"descripcion\": \"Descripción de la categoría\", \"usuario\": { \"id\": id, \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" } }";
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(id);
        categoriaDTO.setUsuarioDTO(usuario);
        categoriaDTO.setNombre("Nueva");
        categoriaDTO.setNombre("Nueva");
        categoriaDTO.setDescripcion("Descripción de la nueva categoría");

        doThrow(new FinanceFlowCustomException(null, "Technical message", "Nombre de categoria ya existe", LayerException.CONTROLLER))
                .when(facade).execute(categoriaDTO);

        mockMvc.perform(post("/finance-flow/v1/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoriaJson))
                .andExpect(status().isBadRequest());
    }



}