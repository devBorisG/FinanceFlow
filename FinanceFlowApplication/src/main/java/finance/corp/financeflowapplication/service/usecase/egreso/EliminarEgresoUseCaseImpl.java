package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.port.input.egreso.EliminarEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EliminarEgresoUseCaseImpl implements EliminarEgresoUseCase {

    MapperDomainToEntity<EgresoDomain, EgresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final EgresoRepository repository;

    public EliminarEgresoUseCaseImpl(EgresoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(EgresoDomain domain) {
        try{
            EgresoEntity egresoEntity = mapperDomainToEntity.mapToEntity(domain, EgresoEntity.class);
            System.out.println("egresoEntity = " + egresoEntity);
            repository.delete(egresoEntity);
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar eliminar el egreso.");
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
