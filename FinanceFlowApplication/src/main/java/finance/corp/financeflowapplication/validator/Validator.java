package finance.corp.financeflowapplication.validator;

public interface Validator<T> {
    void isValid(T dto);

}
