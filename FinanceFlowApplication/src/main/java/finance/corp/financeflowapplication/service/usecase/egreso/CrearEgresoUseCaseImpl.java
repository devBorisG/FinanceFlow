package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.egreso.CrearEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class CrearEgresoUseCaseImpl implements CrearEgresoUseCase {

    private final EgresoRepository egresoRepository;
    MapperDomainToEntity<EgresoDomain, EgresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntityCategoria = new MapperDomainToEntity<>();

    public CrearEgresoUseCaseImpl(EgresoRepository egresoRepository) {
        this.egresoRepository = egresoRepository;
    }
    @Override
    public void execute(EgresoDomain domain) {
        try {
            EgresoEntity entity = mapperDomainToEntity.mapToEntity(domain, EgresoEntity.class);
            entity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            CategoriaEntity categoriaEntity = mapperDomainToEntityCategoria.mapToEntity(domain.getCategoria(), CategoriaEntity.class);
            categoriaEntity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            entity.setCategoria(categoriaEntity);

            System.out.println("entity = " + entity);
            egresoRepository.save(entity);
        } catch (DataIntegrityViolationException e){
            throw DomainCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar guardar el egreso.");
        }catch (TransactionSystemException e){
            throw DomainCustomException.createTechnicalException(e,"Hubo un problema con la transacción de la base de datos mientras se intentaba guardar la entidad.");
        }catch (TransactionRequiredException e){
            throw DomainCustomException.createTechnicalException(e,"Se requiere una transacción para esta operación.");
        }catch (JpaSystemException e){
            throw DomainCustomException.createTechnicalException(e,"Se genero un error en la capa de persistencia JPA.");
        }catch (Exception e){
            throw DomainCustomException.createTechnicalException(e,"Ocurrio un error inesperado.");
        }
    }
}
