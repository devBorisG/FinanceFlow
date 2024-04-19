package finance.corp.financeflowutils.helper;

/**
 * Utility class for handling objects.
 * This class provides static methods to handle null objects.
 */
public class ObjectHelper {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ObjectHelper() {
        super();
    }

    /**
     * Checks if the given object is null.
     *
     * @param <T> the type of the object
     * @param object the object to check
     * @return true if the object is null, false otherwise
     */
    public static <T> boolean isNull(T object) {
        return object == null;
    }

    /**
     * Returns the object if it is not null, otherwise returns the default value.
     *
     * @param <T> the type of the object
     * @param object the object to check
     * @param defaultValue the default value to return if the object is null
     * @return the object if it is not null, otherwise the default value
     */
    public static <T> T getDefaultIfNull(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }
}