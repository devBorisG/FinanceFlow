package finance.corp.financeflowdomain.port.input;

import java.util.List;

public interface ListUseCase<D,E> {
    List<E> execute(D domain);
}
