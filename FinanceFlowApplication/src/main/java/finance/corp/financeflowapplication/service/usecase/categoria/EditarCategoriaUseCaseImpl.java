package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.categoria.EditarCategoriaUseCase;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.stereotype.Service;

@Service
public class EditarCategoriaUseCaseImpl implements EditarCategoriaUseCase {
    MapperDomainToEntity<CategoriaDomain, CategoriaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
    private final CategoriaRepository categoriaRepository;

    public EditarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void execute(CategoriaDomain domain) {
        CategoriaEntity categoriaEntity = mapperDomainToEntity
                .mapToEntity(
                        domain,
                        CategoriaEntity.class
                );
        categoriaEntity.setUsuario(
                mapperDomainToEntityUsuario
                        .mapToEntity(
                                domain.getUsuarioDomain(),
                                UsuarioEntity.class
                        )
        );
        try{
            categoriaRepository.save(categoriaEntity);
        }catch (Exception e){
            throw e;
        }
    }
}