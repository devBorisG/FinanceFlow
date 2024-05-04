package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.port.input.meta.ConsultarMetaUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultarMetaUseCaseImpl implements ConsultarMetaUseCase {
    @Override
    public List<MetaDomain> execute(Optional<MetaDomain> domain) {
        return List.of();
    }
}
