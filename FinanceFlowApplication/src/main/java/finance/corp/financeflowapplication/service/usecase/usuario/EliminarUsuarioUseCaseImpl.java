package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.ConsultarUsuarioUseCase;
import finance.corp.financeflowdomain.port.input.usuario.EliminarUsuarioUseCase;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarUsuarioUseCaseImpl implements EliminarUsuarioUseCase {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;

    public EliminarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ConsultarUsuarioUseCase consultarUsuarioUseCase) {
        this.usuarioRepository = usuarioRepository;
        this.consultarUsuarioUseCase = consultarUsuarioUseCase;
    }

    @Override
    public void execute(UsuarioDomain domain) {
        try {
            Optional<UsuarioDomain> consulta = consultarUsuarioUseCase.execute(Optional.of(domain)).stream().findFirst();
            if (consulta.isPresent()){
                UsuarioEntity usuarioEntity = new UsuarioEntity();
                BeanUtils.copyProperties(domain, usuarioEntity);
                usuarioRepository.deleteById(usuarioEntity.getId());
            } else {
            throw AplicationCustomException.createTechnicalException("no se ha logrado encontrar");
            }
        }catch (AplicationCustomException e){
            throw e;
        }catch (JpaSystemException exception){
            throw AplicationCustomException.createTechnicalException("No se ha logrado consultar");
        }catch (Exception exception){
            throw AplicationCustomException.createTechnicalException("errro fatal");
        }
    }
}
