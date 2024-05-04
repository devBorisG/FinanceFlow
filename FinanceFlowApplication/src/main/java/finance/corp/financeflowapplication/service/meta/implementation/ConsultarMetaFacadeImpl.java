package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.meta.ConsultarMetaFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ConsultarMetaFacadeImpl implements ConsultarMetaFacade {
    @Override
    public List<MetaDTO> execute(Optional<MetaDTO> dto) {
        return List.of();
    }
}
