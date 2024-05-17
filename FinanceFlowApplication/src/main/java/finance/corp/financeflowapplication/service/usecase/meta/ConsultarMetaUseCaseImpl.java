package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.meta.ConsultarMetaUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarMetaUseCaseImpl implements ConsultarMetaUseCase {

    MapperDomainToEntity<MetaDomain,MetaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperEntityToDomain<MetaEntity,MetaDomain> mapperEntityToDomain = new MapperEntityToDomain<>();
    private final MetaRepository metaRepository;

    public ConsultarMetaUseCaseImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public List<MetaDomain> execute(Optional<MetaDomain> domain) {
        try{
            if(domain.isPresent()){
                MetaEntity entity = mapperDomainToEntity.mapToEntity(domain.get(),MetaEntity.class);
                UsuarioEntity usuarioEntity = mapperDomainToEntityUsuario.mapToEntity(domain.get().getUsuario(), UsuarioEntity.class);
                entity.setUsuario(usuarioEntity);
                Optional<List<MetaEntity>> entities = metaRepository.findByUsuarioId(entity.getUsuario().getId());
                if(entities.isPresent()){
                    return entities.get().stream().map(value -> mapperEntityToDomain.mapToDomain(value,MetaDomain.class)).toList();
                }
            }
            return Collections.emptyList();
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e, "Se ha violado la integridad de los datos");
        }catch (TransactionSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Hubo un problema con la transacción de la base de datos");
        }catch (TransactionRequiredException e){
            throw AplicationCustomException.createTechnicalException(e, "Se requiere una transacción para esta operación.");
        }catch (JpaSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Se genero un error en la capa de persistencia JPA.");
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado.");
        }
    }
}
