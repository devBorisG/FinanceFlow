package finance.corp.financeflowapplication.service;

public interface UseCaseFacade<T> {
    void execute(T dto);
}
