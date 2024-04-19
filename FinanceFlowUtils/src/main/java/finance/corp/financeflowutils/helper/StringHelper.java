package finance.corp.financeflowutils.helper;

import java.util.Objects;

import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;

/**
 * Utility class for handling strings.
 * This class provides static methods to handle null strings and to perform common string operations.
 */
public class StringHelper {

    public static final String EMPTY = "";


    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private StringHelper() {
        super();
    }

    /**
     * Returns the string if it is not null, otherwise returns the default value.
     *
     * @param string the string to check
     * @param defaultValue the default value to return if the string is null
     * @return the string if it is not null, otherwise the default value
     */
    public static String getDefaultString(final String string,final String defaultValue) {
        return ObjectHelper.isNull(string) ? defaultValue : string;
    }

    /**
     * Returns the string if it is not null, otherwise returns an empty string.
     *
     * @param string the string to check
     * @return the string if it is not null, otherwise an empty string
     */
    public static String getDefaultString(final String string) {

        return getDefaultIfNull(string, EMPTY);

    }

    /**
     * Returns the string with leading and trailing whitespace removed.
     * If the string is null, returns an empty string.
     *
     * @param string the string to trim
     * @return the trimmed string, or an empty string if the original string was null
     */
    public static String applyTrim(final String string){
        return getDefaultString(string).trim();
    }

    /**
     * Checks if the string is equal to an empty string.
     *
     * @param string the string to check
     * @return true if the string is equal to an empty string, false otherwise
     */
    public static boolean isEmpty(final String string) {

        return Objects.equals(string, EMPTY);

    }
}