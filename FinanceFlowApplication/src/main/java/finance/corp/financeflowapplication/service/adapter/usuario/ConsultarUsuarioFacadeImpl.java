package finance.corp.financeflowapplication.service.adapter.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.port.usuario.ConsultarUsuarioFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarUsuarioFacadeImpl implements ConsultarUsuarioFacade {
    @Override
    public UsuarioDomain execute(UsuarioDTO dto) {
        try {
            //TODO: Consultar usuario
            MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
            return null;
        } catch (Exception e) {
            //TODO: Log error
            throw e;
        }
    }
}
