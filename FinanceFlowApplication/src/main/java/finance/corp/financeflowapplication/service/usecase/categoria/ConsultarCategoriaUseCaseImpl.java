package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.categoria.ConsultarCategoriaUseCase;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
import jakarta.persistence.TransactionRequiredException;
import java.util.Collections;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultarCategoriaUseCaseImpl implements ConsultarCategoriaUseCase {

    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperEntityToDomain<CategoriaEntity, CategoriaDomain> mapperEntityToDomain = new MapperEntityToDomain<>();
    private final CategoriaRepository categoriaRepository;

    public ConsultarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDomain> execute(Optional<CategoriaDomain> domain) {
        try {
            if (domain.isPresent()) {
                CategoriaEntity entity = mapperDomainToEntity.mapToEntity(domain.get(), CategoriaEntity.class);
                UsuarioEntity usuarioEntity = mapperDomainToEntityUsuario.mapToEntity(domain.get().getUsuarioDomain(), UsuarioEntity.class);
                entity.setUsuario(usuarioEntity);
                Optional<List<CategoriaEntity>> entities = categoriaRepository.findByUsuario_Id(entity.getUsuario().getId());
                if(entities.isPresent()){
                    return entities.get().stream()
                            .map(value -> mapperEntityToDomain.mapToDomain(value, CategoriaDomain.class))
                            .toList();
                }
            }
            return Collections.emptyList();
        }catch (DataIntegrityViolationException e){
            throw AplicationCustomException.createTechnicalException(e, "Se ha violado la integridad de los datos al intentar guardar la categoria.");
        }catch (TransactionSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Hubo un problema con la transacción de la base de datos mientras se intentaba guardar la entidad.");
        }catch (TransactionRequiredException e){
            throw AplicationCustomException.createTechnicalException(e, "Se requiere una transacción para esta operación.");
        }catch (JpaSystemException e){
            throw AplicationCustomException.createTechnicalException(e, "Se genero un error en la capa de persistencia JPA.");
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado.");
        }
    }
}
