package finance.corp.financeflowapplication.service.usecase.ingreso;

import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.port.input.ingreso.EliminarIngresoUseCase;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EliminarIngresoUseCaseImpl implements EliminarIngresoUseCase {

    MapperDomainToEntity<IngresoDomain, IngresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final IngresoRepository ingresoRepository;

    public EliminarIngresoUseCaseImpl(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    @Override
    public void execute(IngresoDomain domain) {
        try{
            ingresoRepository.delete(mapperDomainToEntity.mapToEntity(domain,IngresoEntity.class));
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar eliminar el ingreso.");
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
