package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.EliminarUsuarioFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.EliminarUsuarioUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EliminarUsuarioFacadeImpl implements EliminarUsuarioFacade {

    private final EliminarUsuarioUseCase eliminarUsuarioUseCase;
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();

    public EliminarUsuarioFacadeImpl(EliminarUsuarioUseCase eliminarUsuarioUseCase) {
        this.eliminarUsuarioUseCase = eliminarUsuarioUseCase;
    }


    @Override
    public void execute(UsuarioDTO dto) {
        try{
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
            eliminarUsuarioUseCase.execute(usuarioDomain);
        }catch (AplicationCustomException aplicationCustomException){
            throw aplicationCustomException;
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "ocurrio un error eliminando el usuario"+ e.getMessage());
        }
    }
}