package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.EliminarCategoriaFacade;
import finance.corp.financeflowapplication.validator.categoria.EliminarCategoriaValidator;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.port.input.categoria.EliminarCategoriaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EliminarCategoriaFacadeImpl implements EliminarCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final EliminarCategoriaUseCase useCase;
    private final EliminarCategoriaValidator validator;
    public EliminarCategoriaFacadeImpl(EliminarCategoriaUseCase useCase, EliminarCategoriaValidator validator) {
        this.useCase = useCase;
        this.validator = validator;
    }

    @Override
    public void execute(CategoriaDTO dto) {
        try{
            validator.isValid(dto);
            useCase.execute(
                    mapperDTOToDomain.mapToDomain(
                            dto,
                            CategoriaDomain.class
                    )
            );
        }catch (AplicationCustomException e){
            throw e;
        }catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado.");
        }
    }
}
