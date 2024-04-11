package finance.corp.financeflowdomain.port.input;

import java.util.List;
import java.util.Optional;

public interface ListUseCase<D> {
    List<D>execute(Optional<D>domain);
}
