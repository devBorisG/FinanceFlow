package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.egreso.EditarEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;


@Service
public class EditarEgresoUseCaseImpl implements EditarEgresoUseCase {
    private final EgresoRepository egresoRepository;
    MapperDomainToEntity<EgresoDomain, EgresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntityCategoria = new MapperDomainToEntity<>();
    public EditarEgresoUseCaseImpl(EgresoRepository egresoRepository) {
        this.egresoRepository = egresoRepository;
    }

    @Override
    public void execute(EgresoDomain domain) {
        try {
            EgresoEntity entity = mapperDomainToEntity.mapToEntity(domain,EgresoEntity.class);
            entity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(),UsuarioEntity.class));
            CategoriaEntity categoriaEntity = mapperDomainToEntityCategoria.mapToEntity(domain.getCategoria(), CategoriaEntity.class);
            categoriaEntity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(), UsuarioEntity.class));
            entity.setCategoria(categoriaEntity);

            System.out.print("useeeeeeeeeeeeeeee");
            egresoRepository.save(entity);
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
