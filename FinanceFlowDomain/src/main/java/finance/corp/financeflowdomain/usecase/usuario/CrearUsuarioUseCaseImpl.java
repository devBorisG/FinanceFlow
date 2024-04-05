package finance.corp.financeflowdomain.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowdomain.port.input.usuario.CrearUsuarioUseCase;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class CrearUsuarioUseCaseImpl implements CrearUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntity = new MapperDomainToEntity<>();

    public CrearUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(UsuarioDomain domain) {
        try{
            domain.setContrasena(passwordEncoder.encode(domain.getContrasena()));
            UsuarioEntity entity = mapperDomainToEntity.mapToEntity(domain, UsuarioEntity.class);
            usuarioRepository.save(entity);
        }catch (IllegalArgumentException e) {
            throw DomainCustomException.createUserException("La contraseña es demasiado larga. La longitud máxima es de 72 caracteres.");
        } catch (DataIntegrityViolationException e){
            throw DomainCustomException.createTechnicalException(e,"Se ha violado la integridad de los datos al intentar guardar el usuario.");
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
