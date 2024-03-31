package finance.corp.financeflowapplication.service.port.egreso;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.service.port.ListUseCaseFacade;
import finance.corp.financeflowdomain.domain.EgresoDomain;

public interface ConsultarEgresosFacade extends ListUseCaseFacade<EgresoDTO, EgresoDomain> {
}
