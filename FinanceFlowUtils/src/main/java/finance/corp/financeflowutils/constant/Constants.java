package finance.corp.financeflowutils.constant;

import java.util.Date;
import java.util.UUID;

/**
 * This class contains constants that are used throughout the application.
 * These constants include an empty string, zero for int and double types, a default UUID, and a default Date.
 * The class is final and has a private constructor to prevent instantiation.
 */
public final class Constants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Constants() {
    }

    /**
     * Constant for an empty string.
     */
    public static final String EMPTY_STRING = "";

    /**
     * Constant for zero of type int.
     */
    public static final int ZERO = 0;

    /**
     * Constant for zero of type double.
     */
    public static final double ZERO_DOUBLE = 0.0;

    /**
     * Constant for a default UUID. This UUID is all zeros.
     */
    public static final UUID DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Constant for a default Date. This date is the epoch (January 1, 1970, 00:00:00 GMT).
     */
    public static final Date DEFAULT_DATE = new Date(0);

}