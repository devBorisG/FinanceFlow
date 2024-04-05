package finance.corp.financeflowapplication.service;

public interface FindUseCaseFacade<T,D> {
    D execute(T dto);
}
