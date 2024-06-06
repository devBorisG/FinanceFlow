package finance.corp.financeflowapplication.service.usecase.ingreso;

import ch.qos.logback.core.net.SyslogOutputStream;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.ingreso.CrearIngresoUseCase;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class CrearIngresoUseCaseImpl implements CrearIngresoUseCase {

    MapperDomainToEntity<IngresoDomain, IngresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntityCategoria = new MapperDomainToEntity<>();

    private final IngresoRepository ingresoRepository;

    public CrearIngresoUseCaseImpl(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    @Override
    public void execute(IngresoDomain domain) {
        try{
            IngresoEntity entity = mapperDomainToEntity.mapToEntity(domain,IngresoEntity.class);
            entity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(),UsuarioEntity.class));
            CategoriaEntity categoriaEntity = mapperDomainToEntityCategoria.mapToEntity(domain.getCategoria(),CategoriaEntity.class);
            categoriaEntity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            entity.setCategoria(categoriaEntity);
            ingresoRepository.save(entity);
        } catch(DataIntegrityViolationException exception){
            throw AplicationCustomException.createTechnicalException(exception,"No cumple con la integridad de los datos");
        } catch(TransactionSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Hubo un problema con la transacción en la base de datos");
        } catch(TransactionRequiredException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se requiere una transacción para esta operación");
        } catch(JpaSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se genero un error en la capa de persistencia JPA.");
        } catch(Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado");
        }
    }
}
