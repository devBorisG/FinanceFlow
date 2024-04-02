package finance.corp.financeflowdomain.usecase;

public interface FindUseCase<D,E> {
    E execute(D domain);
}
