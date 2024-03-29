package finance.corp.financeflowutils.helper;

import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.constant.Constants.ZERO;
import static finance.corp.financeflowutils.constant.Constants.ZERO_DOUBLE;

/**
 * Utility class for handling integers.
 * This class provides static methods to handle null integers.
 */
public class NumberHelper {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private NumberHelper() {
        super();
    }

    /**
     * Returns the integer if it is not null, otherwise returns the default value.
     *
     * @param integer the integer to check
     * @return the integer if it is not null, otherwise the default value
     */
    public static int getDefaultInteger(Integer integer){
        return getDefaultIfNull(integer, ZERO);
    }

    /**
     * Returns the double if it is not null, otherwise returns the default value.
     *
     * @param doubleValue the integer to check
     * @return the double if it is not null, otherwise the default value
     */
    public static double getDefaultDouble(Double doubleValue){
        return getDefaultIfNull(doubleValue, ZERO_DOUBLE);
    }
}