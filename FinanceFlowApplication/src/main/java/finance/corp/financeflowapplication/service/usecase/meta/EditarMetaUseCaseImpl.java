package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.meta.EditarMetaUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class EditarMetaUseCaseImpl implements EditarMetaUseCase {

    private final MetaRepository metaRepository;
    MapperDomainToEntity<MetaDomain, MetaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();

    public EditarMetaUseCaseImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public void execute(MetaDomain domain) {
        try {
            MetaEntity entity = mapperDomainToEntity.mapToEntity(domain, MetaEntity.class);
            entity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuario(),UsuarioEntity.class));
            metaRepository.save(entity);
        }
        catch(DataIntegrityViolationException exception){
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
