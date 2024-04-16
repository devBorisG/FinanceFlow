package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
import finance.corp.financeflowapplication.validator.categoria.CrearCategoriaValidator;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.categoria.CrearCategoriaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CrearCategoriaFacadeImpl implements CrearCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final CrearCategoriaUseCase crearCategoriaUseCase;
    private final CrearCategoriaValidator validator;

    public CrearCategoriaFacadeImpl(CrearCategoriaUseCase crearCategoriaUseCase, CrearCategoriaValidator validator) {
        this.crearCategoriaUseCase = crearCategoriaUseCase;
        this.validator = validator;
    }

    @Override
    public void execute(CategoriaDTO dto) {
        validator.isValid(dto);
        try{
            CategoriaDomain categoriaDomain = mapperDTOToDomain.mapToDomain(dto, CategoriaDomain.class);
            categoriaDomain.setUsuarioDomain(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuarioDTO(), UsuarioDomain.class));
            crearCategoriaUseCase.execute(categoriaDomain);
        } catch (AplicationCustomException e){
            throw e;
        }catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion para crear una categoria.");
        } catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion para crear una cat.");
        }
    }
}
