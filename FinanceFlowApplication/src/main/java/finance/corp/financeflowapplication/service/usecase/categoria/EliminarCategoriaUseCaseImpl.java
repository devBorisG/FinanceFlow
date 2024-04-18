package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.port.input.categoria.EliminarCategoriaUseCase;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EliminarCategoriaUseCaseImpl implements EliminarCategoriaUseCase{
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final CategoriaRepository categoriaRepository;

    public EliminarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void execute(CategoriaDomain domain) {
        try{
            categoriaRepository.delete(
                    mapperDomainToEntity.mapToEntity(
                            domain,
                            CategoriaEntity.class
                    )
            );
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar eliminar la categoria.");
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
