package finance.corp.financeflowutils.helper;

import java.time.LocalDateTime;
import java.util.Date;

import static finance.corp.financeflowutils.constant.Constants.DEFAULT_DATE;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {

        private DateHelper() {
            super();
        }

        public static LocalDateTime getDefaultDate(final LocalDateTime date) {
            return getDefaultIfNull(date, DEFAULT_DATE);
        }

        public static LocalDateTime getNewDate() {
            return LocalDateTime.now();
        }

        public static boolean isDefaultDate(final LocalDateTime date) {
            return DEFAULT_DATE.equals(date);
        }
}
