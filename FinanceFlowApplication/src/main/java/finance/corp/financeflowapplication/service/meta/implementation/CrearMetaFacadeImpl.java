package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.meta.CrearMetaFacade;
import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.meta.CrearMetaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CrearMetaFacadeImpl implements CrearMetaFacade {

    MapperDTOToDomain<MetaDTO, MetaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final CrearMetaUseCase crearMetaUseCase;

    public CrearMetaFacadeImpl(CrearMetaUseCase crearMetaUseCase) {
        this.crearMetaUseCase = crearMetaUseCase;
    }

    @Override
    public void execute(MetaDTO dto) {
        try{
            MetaDomain metaDomain = mapperDTOToDomain.mapToDomain(dto,MetaDomain.class);
            metaDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
            crearMetaUseCase.execute(metaDomain);
        }catch(AplicationCustomException exception){
            throw exception;
        } catch(DomainCustomException exception){
            throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
        } catch(TransactionException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error creando el ingreso");
        } catch(Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado en la creación del ingreso");
        }
    }
}
