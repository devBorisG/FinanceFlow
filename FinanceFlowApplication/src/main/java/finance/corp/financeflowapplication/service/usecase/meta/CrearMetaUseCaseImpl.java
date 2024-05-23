package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.meta.CrearMetaUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class CrearMetaUseCaseImpl implements CrearMetaUseCase {

    MapperDomainToEntity<MetaDomain, MetaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    private final MetaRepository metaRepository;

    public CrearMetaUseCaseImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public void execute(MetaDomain domain) {
        try{
            MetaEntity entity = mapperDomainToEntity.mapToEntity(domain,MetaEntity.class);
            entity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            metaRepository.save(entity);
        } catch(DataIntegrityViolationException exception){
            throw AplicationCustomException.createTechnicalException(exception,"No cumple con la integridad de los datos");
        } catch(TransactionSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Hubo problemas con la transacción en la base de datos, intente nuevamente");
        } catch(TransactionRequiredException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se requiere una transacción para esta operación");
        } catch(JpaSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se genero un error en la capa de persistencia JPA");
        } catch(Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado");
        }
    }
}
