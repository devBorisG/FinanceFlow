package finance.corp.financeflowapplication.service.port;

import java.util.List;

public interface ListUseCaseFacade<T,D>{
    List<D> execute(T dto);
}
