package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.ConsultarUsuarioUseCase;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarUsuarioUseCaseImpl implements ConsultarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public ConsultarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    MapperDomainToEntity<UsuarioDomain,UsuarioEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperEntityToDomain<UsuarioEntity,UsuarioDomain> mapperEntityToDomain = new MapperEntityToDomain<>();

    @Override
    public List<UsuarioDomain> execute(Optional<UsuarioDomain> domain) {
        List<UsuarioEntity> result;
        List<UsuarioDomain> convertResult = new ArrayList<>();
        if(domain.isPresent()){
            UsuarioEntity usuarioEntity = mapperDomainToEntity.mapToEntity(domain.get(),UsuarioEntity.class);
            try{
                result = usuarioRepository.findCustom(usuarioEntity);
            } catch (Exception exception){
                throw DomainCustomException.createTechnicalException(exception,"Ocurrio un error consultando el usuario");
            }
        } else{
            try{
                result = usuarioRepository.findAll();
            } catch (Exception exception){
                throw DomainCustomException.createTechnicalException(exception,"Ocurrio un error consultando todos los usuarios");
            }
        }

        result.forEach(value ->{
            UsuarioDomain usuarioDomain = mapperEntityToDomain.mapToDomain(value,UsuarioDomain.class);
            convertResult.add(usuarioDomain);
        });

        return convertResult;
    }
}
