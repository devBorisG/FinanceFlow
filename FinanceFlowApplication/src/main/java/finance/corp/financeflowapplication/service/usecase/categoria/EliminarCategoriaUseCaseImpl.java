package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.port.input.categoria.EliminarCategoriaUseCase;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.stereotype.Service;

@Service
public class EliminarCategoriaUseCaseImpl implements EliminarCategoriaUseCase{
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final CategoriaRepository categoriaRepository;

    public EliminarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void execute(CategoriaDomain domain) {
        try{
            categoriaRepository.delete(
                    mapperDomainToEntity.mapToEntity(
                            domain,
                            CategoriaEntity.class
                    )
            );
        }catch (Exception e){
            throw e;
        }
    }
}
