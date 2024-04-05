package finance.corp.financeflowdomain.port.input;

public interface UseCase<D> {
    void execute(D domain);
}
