package finance.corp.financeflowdomain.usecase;

import java.util.List;

public interface ListUseCase<D,E> {
    List<E> execute(D domain);
}
