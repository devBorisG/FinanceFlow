package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.ConsultarCategoriaFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.categoria.ConsultarCategoriaUseCase;
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
public class ConsultarCategoriaFacadeImpl implements ConsultarCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDomainToDTO<CategoriaDomain, CategoriaDTO> mapperDomainToDTO = new MapperDomainToDTO<>();
    private final ConsultarCategoriaUseCase consultarCategoriaUseCase;

    public ConsultarCategoriaFacadeImpl(ConsultarCategoriaUseCase consultarCategoriaUseCase) {
        this.consultarCategoriaUseCase = consultarCategoriaUseCase;
    }

    @Override
    public List<CategoriaDTO> execute(Optional<CategoriaDTO> dto) {
        try{
            if (dto.isPresent()){
                CategoriaDomain categoriaDomain = mapperDTOToDomain.mapToDomain(dto.get(), CategoriaDomain.class);
                categoriaDomain.setUsuarioDomain(mapperDTOToDomainUsuario.mapToDomain(dto.get().getUsuarioDTO(), UsuarioDomain.class));
                return consultarCategoriaUseCase.execute(Optional.of(categoriaDomain))
                        .stream()
                        .map(value -> mapperDomainToDTO.mapToDomain(value, CategoriaDTO.class))
                        .toList();
            }
            return null;
        } catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion para consultar las categorias.");
        } catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion para consultar las categorias.");
        }
    }
}
