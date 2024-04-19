package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.EliminarCategoriaFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.port.input.categoria.EliminarCategoriaUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EliminarCategoriaFacadeImpl implements EliminarCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final EliminarCategoriaUseCase useCase;

    public EliminarCategoriaFacadeImpl(EliminarCategoriaUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(CategoriaDTO dto) {
        try{
            useCase.execute(
                    mapperDTOToDomain.mapToDomain(
                            dto,
                            CategoriaDomain.class
                    )
            );
        }catch (Exception e){
            throw e;
        }
    }
}
