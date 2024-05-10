package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.meta.ConsultarMetaFacade;
import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.meta.ConsultarMetaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import finance.corp.financeflowutils.mapper.MapperDomainToDTO;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ConsultarMetaFacadeImpl implements ConsultarMetaFacade {

    MapperDTOToDomain<MetaDTO, MetaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDomainToDTO<MetaDomain,MetaDTO> mapperDomainToDTO = new MapperDomainToDTO<>();
    private final ConsultarMetaUseCase consultarMetaUseCase;

    public ConsultarMetaFacadeImpl(ConsultarMetaUseCase consultarMetaUseCase) {
        this.consultarMetaUseCase = consultarMetaUseCase;
    }

    @Override
    public List<MetaDTO> execute(Optional<MetaDTO> dto) {
        try{
            if(dto.isPresent()){
                MetaDomain metaDomain = mapperDTOToDomain.mapToDomain(dto.get(),MetaDomain.class);
                metaDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.get().getUsuario(), UsuarioDomain.class));
                return consultarMetaUseCase.execute(Optional.of(metaDomain)).stream().map(value->mapperDomainToDTO.mapToDomain(value,MetaDTO.class)).toList();
            }
            return null;
        } catch(DomainCustomException exception){
            throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
        } catch (TransactionException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error intentando consultar las metas del usuario");
        } catch(Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado consultando las metas del usuario");
        }
    }
}
