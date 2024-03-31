package finance.corp.financeflowapplication.service.port.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.port.ListUseCaseFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;

public interface ConsultarCategoriasFacade extends ListUseCaseFacade<CategoriaDTO, CategoriaDomain> {
}
