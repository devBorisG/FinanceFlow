package finance.corp.financeflowapplication.service.port;

public interface UseCaseFacade<T> {
    void execute(T object);
}
