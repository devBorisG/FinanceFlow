package finance.corp.financeflowapplication.service.port.ingreso;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.service.port.ListUseCaseFacade;
import finance.corp.financeflowdomain.domain.IngresoDomain;

public interface ConsultarIngresosFacade extends ListUseCaseFacade<IngresoDTO, IngresoDomain> {
}
