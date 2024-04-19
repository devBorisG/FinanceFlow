package finance.corp.financeflowutils.exception;

import finance.corp.financeflowutils.exception.enumeration.LayerException;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;
import java.io.Serial;

public class FinanceFlowCustomException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String userMessage;
    private final LayerException layerException;

    protected FinanceFlowCustomException(final Throwable rootException, final String technicalMessage, final String userMessage,
                                         final LayerException layerException){
        super(technicalMessage, getDefaultIfNull(rootException,new Exception()));
        this.userMessage = userMessage;
        this.layerException = layerException;
    }

    public String getUserMessage(){
        return userMessage;
    }

    public LayerException getLayerException(){
        return layerException;
    }

    public final boolean isTechnicalException(){
        return isEmpty(getUserMessage());
    }

}
