package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.LoginUsuarioFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginUsuarioFacadeImpl implements LoginUsuarioFacade {
    @Override
    public void execute(UsuarioDTO dto) {
        try{
            //TODO: Login usuario
            MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
        }catch (Exception e) {
            //TODO: Log error
            throw e;
        }
    }
}
