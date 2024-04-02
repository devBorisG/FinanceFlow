package finance.corp.financeflowdomain.usecase;

public interface UseCase<D> {
    void execute(D domain);
}
