package finance.corp.financeflowutils.exception.repository;


import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.enumeration.LayerException;
import finance.corp.financeflowutils.helper.StringHelper;

import java.io.Serial;

public class RepositoryCustomException extends FinanceFlowCustomException {
    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    private RepositoryCustomException(Exception rootException, String technicalMessage, String userMessage) {
        super(rootException, technicalMessage, userMessage, LayerException.REPOSITORY);
    }


    public static FinanceFlowCustomException create(final String userMesssage, final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, userMesssage);
    }


    public static FinanceFlowCustomException create(final Exception rootException, final String userMessage,
                                                 final String technicalMessage) {
        return new RepositoryCustomException(rootException, technicalMessage, userMessage);
    }


    public static FinanceFlowCustomException createTechnicalException(final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, StringHelper.EMPTY);
    }


    public static FinanceFlowCustomException createTechnicalException(final Exception rootException, final String technicalMessage) {
        return new RepositoryCustomException(rootException, technicalMessage, StringHelper.EMPTY );
    }

    public static FinanceFlowCustomException wrapException(final String message, final FinanceFlowCustomException exception) {
        if (exception.isTechnicalException()) {
            return AplicationCustomException.createBusinessException(message, exception);
        }
        return exception;
    }
}

