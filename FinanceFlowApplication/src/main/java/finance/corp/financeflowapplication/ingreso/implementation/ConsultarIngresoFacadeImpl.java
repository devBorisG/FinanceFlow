package finance.corp.financeflowapplication.ingreso.implementation;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.ingreso.ConsultarIngresoFacade;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.ingreso.ConsultarIngresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import finance.corp.financeflowutils.mapper.MapperDomainToDTO;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ConsultarIngresoFacadeImpl implements ConsultarIngresoFacade {

    MapperDTOToDomain<IngresoDTO, IngresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDomainToDTO<IngresoDomain,IngresoDTO> mapperDomainToDTO = new MapperDomainToDTO<>();
    private final ConsultarIngresoUseCase consultarIngresoUseCase;

    public ConsultarIngresoFacadeImpl(ConsultarIngresoUseCase consultarIngresoUseCase) {
        this.consultarIngresoUseCase = consultarIngresoUseCase;
    }

    @Override
    public List<IngresoDTO> execute(Optional<IngresoDTO> dto) {
        try{
            if(dto.isPresent()){
                IngresoDomain ingresoDomain = mapperDTOToDomain.mapToDomain(dto.get(),IngresoDomain.class);
                ingresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.get().getUsuario(),UsuarioDomain.class));
                return consultarIngresoUseCase.execute(Optional.of(ingresoDomain)).stream().map(value->mapperDomainToDTO.mapToDomain(value,IngresoDTO.class)).toList();
            }
            return null;
        } catch(DomainCustomException exception){
            throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
        } catch (TransactionException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error intentando consultar los ingresos del usuario");
        } catch(Exception exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error inesperado consultando los ingresos del usuario");
        }
    }
}
