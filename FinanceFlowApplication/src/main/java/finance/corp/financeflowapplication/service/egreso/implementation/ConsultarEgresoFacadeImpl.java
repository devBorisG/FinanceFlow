package finance.corp.financeflowapplication.service.egreso.implementation;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.egreso.ConsultarEgresoFacade;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.egreso.ConsultarEgresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import finance.corp.financeflowutils.mapper.MapperDomainToDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ConsultarEgresoFacadeImpl implements ConsultarEgresoFacade {
    MapperDTOToDomain<EgresoDTO, EgresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDomainToDTO<EgresoDomain,EgresoDTO> mapperDomainToDTO = new MapperDomainToDTO<>();
    private final ConsultarEgresoUseCase consultarEgresoUseCase;

    public ConsultarEgresoFacadeImpl(ConsultarEgresoUseCase consultarEgresoUseCase) {
        this.consultarEgresoUseCase = consultarEgresoUseCase;
    }


    @Override
    public List<EgresoDTO> execute(Optional<EgresoDTO> dto) {
            try{
                if(dto.isPresent()){
                    EgresoDomain egresoDomain = mapperDTOToDomain.mapToDomain(dto.get(),EgresoDomain.class);
                    egresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.get().getUsuario(),UsuarioDomain.class));
                    return consultarEgresoUseCase.execute(Optional.of(egresoDomain)).stream().map(value->mapperDomainToDTO.mapToDomain(value,EgresoDTO.class)).toList();
                }
                return null;
            } catch(DomainCustomException exception){
                throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
            } catch(Exception exception){
                throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado consultando los egresos del usuario");
            }
    }
}
