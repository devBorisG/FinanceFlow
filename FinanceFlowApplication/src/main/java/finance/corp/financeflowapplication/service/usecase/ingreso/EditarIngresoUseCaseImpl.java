package finance.corp.financeflowapplication.service.usecase.ingreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.ingreso.EditarIngresoUseCase;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EditarIngresoUseCaseImpl implements EditarIngresoUseCase {

    MapperDomainToEntity<IngresoDomain, IngresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntityCategoria = new MapperDomainToEntity<>();
    private final IngresoRepository ingresoRepository;

    public EditarIngresoUseCaseImpl(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    @Override
    public void execute(IngresoDomain domain) {
        try {
            IngresoEntity ingresoEntity = mapperDomainToEntity.mapToEntity(domain, IngresoEntity.class);
            ingresoEntity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            ingresoEntity.setCategoria(mapperDomainToEntityCategoria.mapToEntity(domain.getCategoria(),CategoriaEntity.class));
            ingresoRepository.save(ingresoEntity);
        } catch(DataIntegrityViolationException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se ha violado la integridad de los datos");
        } catch(TransactionSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"hubo un problema con la transacción en la base de datos");
        }catch(JpaSystemException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se requiere una transacción");
        }catch (Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado");
        }
    }
}
