package finance.corp.financeflowutils.exception.utils;

import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import finance.corp.financeflowutils.exception.enumeration.LayerException;

import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import java.io.Serial;

public class UtilsCustomException extends FinanceFlowCustomException {

    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    private UtilsCustomException(Exception rootException, String technicalMessage, String userMessage){
        super(rootException,technicalMessage,userMessage, LayerException.UTILS);
    }

    public static FinanceFlowCustomException create(final String userMessage, final String technicalMessage){
        return new UtilsCustomException(new Exception(),technicalMessage,userMessage);
    }

    public static FinanceFlowCustomException createTechnicalException(final String technicalMessage){
        return new UtilsCustomException(new Exception(),technicalMessage,EMPTY_STRING);
    }

    public static FinanceFlowCustomException createTechnicalException(final Exception rootException, final String technicalMessage){
        return new UtilsCustomException(rootException,technicalMessage,EMPTY_STRING);
    }
}
