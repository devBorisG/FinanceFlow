package finance.corp.financeflowutils.exception.aplication;

import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.exception.enumeration.LayerException;

import java.io.Serial;

import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;

public class AplicationCustomException extends FinanceFlowCustomException {

    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    private AplicationCustomException(Exception rootException,String technicalMessage,String userMessage){
        super(rootException,technicalMessage,userMessage, LayerException.DOMAIN);
    }

    public static FinanceFlowCustomException create(final String userMessage, final String technicalMessage){
        return new AplicationCustomException(new Exception(),technicalMessage,userMessage);
    }

    public static FinanceFlowCustomException createTechnicalException(final String technicalMessage){
        return new AplicationCustomException(new Exception(),technicalMessage,EMPTY_STRING);
    }

    public static FinanceFlowCustomException createTechnicalException(final Exception rootException, final String technicalMessage){
        return new AplicationCustomException(rootException,technicalMessage,EMPTY_STRING);
    }

    public static FinanceFlowCustomException createUserException(final String userMessage){
        return new AplicationCustomException(null,userMessage,userMessage);
    }

    public static final FinanceFlowCustomException createBusinessException(final String businessMessage, final Exception rootException){
        return new AplicationCustomException(rootException,businessMessage,EMPTY_STRING);
    }

    public static FinanceFlowCustomException wrapException(final String message,final FinanceFlowCustomException exception){
        if(exception.isTechnicalException()){
            return DomainCustomException.createBusinessException(message,exception);
        }
        return exception;
    }
}
