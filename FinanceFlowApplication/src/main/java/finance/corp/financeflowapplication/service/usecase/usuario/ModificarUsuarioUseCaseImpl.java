package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.ModificarUsuarioUseCase;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import java.util.Optional;

@Service
public class ModificarUsuarioUseCaseImpl implements ModificarUsuarioUseCase {


    private final UsuarioRepository usuarioRepository;
    public ModificarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntity = new MapperDomainToEntity<>();

    @Override
    public void execute(UsuarioDomain domain) {
        try {
            Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findById(domain.getId());
            if (optionalUsuarioEntity.isPresent()) {
                UsuarioEntity  usuarioEntity = mapperDomainToEntity.mapToEntity(domain, UsuarioEntity.class);
                usuarioRepository.save(usuarioEntity);
            }else {
                throw new RuntimeException("El usuario " + domain.getId() + "No existe");
            }
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
