package finance.corp.financeflowinfrastructure.controller.ingreso;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.ingreso.implementation.EditarIngresoFacadeImpl;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso.EditarIngresoController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
class EditarIngresoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EditarIngresoFacadeImpl facade;
    @Autowired
    private ObjectMapper objectMapper;
    private UUID userId;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new EditarIngresoController(facade)).build();
        objectMapper = new ObjectMapper();
        userId = UUID.randomUUID();
    }

    @Test
    void editarIngresoTestSuccess() throws Exception{
        UUID id = UUID.randomUUID();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        usuarioDTO.setNombre("prueba");
        usuarioDTO.setApellido("prueba");
        usuarioDTO.setContrasena("prueba");
        usuarioDTO.setCorreo("prueba@prueba.com");

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(id);
        categoriaDTO.setNombre("prueba");
        categoriaDTO.setDescripcion("prueba");
        categoriaDTO.setUsuarioDTO(usuarioDTO);

        IngresoDTO ingresoDTO = new IngresoDTO();
        ingresoDTO.setId(id);
        ingresoDTO.setNombre("prueba");
        ingresoDTO.setCategoria(categoriaDTO);
        ingresoDTO.setUsuario(usuarioDTO);
        ingresoDTO.setDescripcion("prueba");
        ingresoDTO.setMonto(2);
        ingresoDTO.setPeriodicidad(1);

        System.out.println(objectMapper.writeValueAsString(usuarioDTO));
        Map<String,Object> respuestas = new HashMap<>();
        System.out.println(objectMapper.writeValueAsString(respuestas));
        mockMvc.perform(put("/finance-flow/v1/ingreso").param("id",userId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ingresoDTO)))
                .andExpect(status().isOk());
    }

}