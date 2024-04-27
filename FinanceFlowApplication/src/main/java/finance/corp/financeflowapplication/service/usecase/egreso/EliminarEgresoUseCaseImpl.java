package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.egreso.ConsultarEgresoUseCase;
import finance.corp.financeflowdomain.port.input.egreso.EliminarEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EliminarEgresoUseCaseImpl implements EliminarEgresoUseCase {
    private final EgresoRepository egresoRepository;
    private final ConsultarEgresoUseCase consultarEgresoUseCase;

    public EliminarEgresoUseCaseImpl(EgresoRepository egresoRepository, ConsultarEgresoUseCase consultarEgresoUseCase) {
        this.egresoRepository = egresoRepository;
        this.consultarEgresoUseCase = consultarEgresoUseCase;
    }

    @Override
    public void execute(EgresoDomain domain) {
        try {
            Optional<EgresoDomain> consulta = consultarEgresoUseCase.execute(Optional.of(domain)).stream().findFirst();
            if (consulta.isPresent()){
                UsuarioEntity usuarioEntity = new UsuarioEntity();
                BeanUtils.copyProperties(domain, usuarioEntity);
                egresoRepository.deleteById(usuarioEntity.getId());
            } else {
                throw AplicationCustomException.createTechnicalException("no se ha logrado encontrar");
            }
        }catch (AplicationCustomException e){
            throw e;
        }catch (JpaSystemException exception){
            throw AplicationCustomException.createTechnicalException("No se ha logrado consultar");
        }catch (Exception exception){
            throw AplicationCustomException.createTechnicalException("errro fatal");
        }
    }
}
