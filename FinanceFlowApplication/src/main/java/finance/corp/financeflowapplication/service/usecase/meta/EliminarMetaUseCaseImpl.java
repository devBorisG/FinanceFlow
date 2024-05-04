package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.port.input.meta.EliminarMetaUseCase;
import finance.corp.financeflowdomain.port.input.usuario.ConsultarUsuarioUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarMetaUseCaseImpl implements EliminarMetaUseCase {
    private final MetaRepository metaRepository;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;

    public EliminarMetaUseCaseImpl(MetaRepository metaRepository, ConsultarUsuarioUseCase consultarUsuarioUseCase) {
        this.metaRepository = metaRepository;
        this.consultarUsuarioUseCase = consultarUsuarioUseCase;
    }

    @Override
    public void execute(MetaDomain domain) {
        try {
            Optional<MetaDomain> metaDomain = consultarUsuarioUseCase.execute(Optional.of(domain)).stream().findFirst();
            if (metaDomain.isPresent()) {
                MetaEntity metaEntity = new MetaEntity();
                BeanUtils.copyProperties(domain, metaEntity);
                metaRepository.deleteById(metaEntity.getId());
            } else {
                throw AplicationCustomException.createTechnicalException("no se ha logrado encontrar");
            }
        } catch (JpaSystemException exception) {
            throw AplicationCustomException.createTechnicalException("No se ha logrado consultar");
        } catch (Exception exception) {
            throw AplicationCustomException.createTechnicalException("errro fatal");
        }
    }
}

