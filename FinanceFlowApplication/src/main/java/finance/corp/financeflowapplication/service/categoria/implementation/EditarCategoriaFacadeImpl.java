package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.EditarCategoriaFacade;
import finance.corp.financeflowapplication.validator.categoria.EditarCategoriaValidator;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.categoria.EditarCategoriaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditarCategoriaFacadeImpl implements EditarCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final EditarCategoriaUseCase useCase;
    private final EditarCategoriaValidator validator;

    public EditarCategoriaFacadeImpl(EditarCategoriaUseCase useCase, EditarCategoriaValidator validator) {
        this.useCase = useCase;
        this.validator = validator;
    }

    @Override
    public void execute(CategoriaDTO dto) {
        validator.isValid(dto);
        CategoriaDomain categoriaDomain = mapperDTOToDomain
                .mapToDomain(
                        dto,
                        CategoriaDomain.class
                );
        categoriaDomain.setUsuarioDomain(
                mapperDTOToDomainUsuario
                        .mapToDomain(
                                dto.getUsuarioDTO(),
                                UsuarioDomain.class
                        )
        );

        try {
            useCase.execute(categoriaDomain);
        }catch (AplicationCustomException e){
            throw e;
        }catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion para editar una categoria.");
        } catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado en la facade para editar la categoria");
        }
    }
}
