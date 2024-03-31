package finance.corp.financeflowapplication.service.adapter.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.port.usuario.CrearUsuarioFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;

@Service
@Transactional
public class CrearUsuarioFacadeImpl implements CrearUsuarioFacade {
    @Override
    public void execute(UsuarioDTO dto) {
        try{
            //TODO: Crear usuario
            MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
        } catch (Exception e) {
            //TODO: Log error
            throw e;
        }
    }
}
