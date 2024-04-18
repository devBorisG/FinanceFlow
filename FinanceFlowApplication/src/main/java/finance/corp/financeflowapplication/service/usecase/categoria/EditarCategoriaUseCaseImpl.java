package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.categoria.EditarCategoriaUseCase;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EditarCategoriaUseCaseImpl implements EditarCategoriaUseCase {
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    private final CategoriaRepository categoriaRepository;

    public EditarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void execute(CategoriaDomain domain) {
        CategoriaEntity categoriaEntity = mapperDomainToEntity
                .mapToEntity(
                        domain,
                        CategoriaEntity.class
                );
        categoriaEntity.setUsuario(
                mapperDomainToEntityUsuario
                        .mapToEntity(
                                domain.getUsuarioDomain(),
                                UsuarioEntity.class
                        )
        );
        try{
            categoriaRepository.save(categoriaEntity);
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e, "Se ha violado la integridad de los datos al intentar editar la categoria.");
        }catch (TransactionSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Hubo un problema con la transacción de la base de datos mientras se intentaba editar la entidad.");
        }catch (TransactionRequiredException e){
            throw AplicationCustomException.createTechnicalException(e, "Se requiere una transacción para esta operación de actualización de categoria.");
        }catch (JpaSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Se genero un error en la capa de persistencia JPA al intentar actualizar la categoria.");
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado.");
        }
    }
}
