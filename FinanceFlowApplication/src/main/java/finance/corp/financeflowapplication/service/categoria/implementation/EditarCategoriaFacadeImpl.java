package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.categoria.EditarCategoriaFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.categoria.EditarCategoriaUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditarCategoriaFacadeImpl implements EditarCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final EditarCategoriaUseCase useCase;

    public EditarCategoriaFacadeImpl(EditarCategoriaUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(CategoriaDTO dto) {
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
        }catch (Exception e){
            throw e;
        }
    }
}
