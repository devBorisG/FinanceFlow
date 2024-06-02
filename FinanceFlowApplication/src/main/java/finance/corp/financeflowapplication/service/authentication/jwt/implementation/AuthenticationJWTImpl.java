package finance.corp.financeflowapplication.service.authentication.jwt.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.authentication.jwt.AuthenticationJWT;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.service.JwtService;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationJWTImpl implements AuthenticationJWT {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    MapperDTOToEntity<UsuarioDTO, UsuarioEntity> mapperDTOToEntity = new MapperDTOToEntity();

    @Override
    public String authenticate(UsuarioDTO usuarioDTO) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getCorreo(),usuarioDTO.getContrasena()));
        } catch(AuthenticationException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Error con el authentication manager" + exception.getMessage());
        } catch (Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado autenticando el usuario" + exception.getMessage());
        }
        UsuarioEntity usuario = mapperDTOToEntity.mapToEntity(usuarioDTO,UsuarioEntity.class);
        UsuarioEntity user = usuarioRepository.findByCorreo(usuario.getCorreo()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
