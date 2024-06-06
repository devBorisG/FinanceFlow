package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.meta.EditarMetaFacade;
import finance.corp.financeflowapplication.validator.meta.EditarMetaValidator;
import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.meta.EditarMetaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditarMetaFacadeImpl implements EditarMetaFacade {
    private final EditarMetaUseCase editarMetaUseCase;
    private final EditarMetaValidator editarMetaValidator;
    MapperDTOToDomain<MetaDTO, MetaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();

    public EditarMetaFacadeImpl(EditarMetaUseCase editarMetaUseCase, EditarMetaValidator editarMetaValidator) {
        this.editarMetaUseCase = editarMetaUseCase;
        this.editarMetaValidator = editarMetaValidator;
    }

    @Override
    public void execute(MetaDTO dto) {
        MetaDomain domain = mapperDTOToDomain.mapToDomain(dto, MetaDomain.class);
        domain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(), UsuarioDomain.class));
        try {
            System.out.println("domain = " + domain);
            editarMetaUseCase.execute(domain);
        } catch (AplicationCustomException e) {
            throw e;
        } catch (TransactionException e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error en la transaccion.");
        } catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado ejecutando la transaccion para modificar los datos del usuario.");
        }

    }
}
