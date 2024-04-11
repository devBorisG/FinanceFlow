package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.ConsultarUsuarioFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.ConsultarUsuarioUseCase;
import finance.corp.financeflowutils.constant.Constants;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import finance.corp.financeflowutils.helper.StringHelper;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import finance.corp.financeflowutils.mapper.MapperDomainToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ConsultarUsuarioFacadeImpl implements ConsultarUsuarioFacade {

    @Autowired
    private ConsultarUsuarioUseCase consultarUsuarioUseCase;

    MapperDTOToDomain<UsuarioDTO,UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain();
    MapperDomainToDTO<UsuarioDomain,UsuarioDTO> mapperDomainToDTO = new MapperDomainToDTO();

    @Override
    public List<UsuarioDTO> execute(Optional<UsuarioDTO> dto) {
        try{
            List<UsuarioDomain> listDomain;
            if(dto.isPresent() && (!Objects.equals(dto.get().getNombre(), Constants.EMPTY_STRING)||
                    !Objects.equals(dto.get().getApellido(),Constants.EMPTY_STRING)||
                    !Objects.equals(dto.get().getCorreo(),Constants.EMPTY_STRING))){
                UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto.get(),UsuarioDomain.class);
                listDomain = consultarUsuarioUseCase.execute(Optional.of(usuarioDomain));
            } else{
                listDomain = consultarUsuarioUseCase.execute(Optional.empty());
            }
            List<UsuarioDTO> convertResult = new ArrayList<>();
            listDomain.forEach(value->{
                UsuarioDTO usuarioDTO = mapperDomainToDTO.mapToDomain(value,UsuarioDTO.class);
                convertResult.add(usuarioDTO);
            });
            return convertResult;
        } catch (InfraestructureCustomException infraestructureCustomException){
            throw infraestructureCustomException;
        } catch (Exception exception){
            throw InfraestructureCustomException.createTechnicalException("Ocurrio un error intentando implementar el caso de uso para consultar" +
                    "los usuarios");
        }
    }
}
