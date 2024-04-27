package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.egreso.ConsultarEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarEgresoUseCaseImpl implements ConsultarEgresoUseCase {

    @Autowired
    private final EgresoRepository egresoRepository;

    MapperDomainToEntity<EgresoDomain, EgresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperEntityToDomain<EgresoEntity,EgresoDomain> mapperEntityToDomain = new MapperEntityToDomain<>();

    public ConsultarEgresoUseCaseImpl(EgresoRepository egresoRepository) {
        this.egresoRepository = egresoRepository;
    }


    @Override
    public List<EgresoDomain> execute(Optional<EgresoDomain> domain) {
        try{
            if(domain.isPresent()){
                EgresoEntity entity = mapperDomainToEntity.mapToEntity(domain.get(),EgresoEntity.class);
                UsuarioEntity usuarioEntity = mapperDomainToEntityUsuario.mapToEntity(domain.get().getUsuario(),UsuarioEntity.class);
                entity.setUsuario(usuarioEntity);
                Optional<List<EgresoEntity>> entities = egresoRepository.findByUsuario(entity.getUsuario().getId());
                if(entities.isPresent()){
                    return entities.get().stream().map(value -> mapperEntityToDomain.mapToDomain(value, EgresoDomain.class)).toList();
                }
            }
            return Collections.emptyList();
        } catch (DataIntegrityViolationException e){
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
