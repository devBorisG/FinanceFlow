package finance.corp.financeflowapplication.service.port.meta;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.port.FindUseCaseFacade;
import finance.corp.financeflowdomain.domain.MetaDomain;

public interface ConsultarMetaFacade extends FindUseCaseFacade<MetaDTO, MetaDomain> {
}
