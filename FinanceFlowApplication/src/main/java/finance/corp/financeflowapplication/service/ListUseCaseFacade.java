package finance.corp.financeflowapplication.service;

import java.util.List;

public interface ListUseCaseFacade<T,D>{
    List<D> execute(T dto);
}
