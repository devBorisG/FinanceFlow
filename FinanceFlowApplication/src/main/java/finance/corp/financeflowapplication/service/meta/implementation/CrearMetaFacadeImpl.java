package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.meta.CrearMetaFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CrearMetaFacadeImpl implements CrearMetaFacade {
    @Override
    public void execute(MetaDTO dto) {

    }
}
