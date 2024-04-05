package finance.corp.financeflowdomain.port.input;

public interface FindUseCase<D,E> {
    E execute(D domain);
}
