package finance.corp.financeflowapplication.service;

import java.util.List;
import java.util.Optional;

public interface ListUseCaseFacade<T>{
    List<T> execute(Optional<T> dto);
}
