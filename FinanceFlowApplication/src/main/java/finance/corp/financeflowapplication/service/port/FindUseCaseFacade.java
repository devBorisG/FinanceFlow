package finance.corp.financeflowapplication.service.port;

public interface FindUseCaseFacade<T,D> {
    D execute(T dto);
}
