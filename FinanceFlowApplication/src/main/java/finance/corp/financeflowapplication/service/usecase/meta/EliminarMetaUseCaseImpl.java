package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.port.input.meta.EliminarMetaUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;


@Service
public class EliminarMetaUseCaseImpl implements EliminarMetaUseCase {
    MapperDomainToEntity<MetaDomain, MetaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final MetaRepository metaRepository;

    public EliminarMetaUseCaseImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public void execute(MetaDomain domain) {
        try{
            metaRepository.delete(mapperDomainToEntity.mapToEntity(domain,MetaEntity.class));
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar eliminar la meta.");
        }catch (TransactionSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Hubo un problema con la transacción de la base de datos mientras se intentaba eliminar la entidad.");
        }catch (TransactionRequiredException e) {
            throw AplicationCustomException.createTechnicalException(e, "Se requiere una transacción para esta operación.");
        }catch (JpaSystemException e) {
            throw AplicationCustomException.createTechnicalException(e, "Se genero un error en la capa de persistencia JPA.");
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado.");
        }
    }
}

