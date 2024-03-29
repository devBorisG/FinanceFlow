package finance.corp.financeflowutils.helper;

import java.util.Date;

import static finance.corp.financeflowutils.constant.Constants.DEFAULT_DATE;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {

        private DateHelper() {
            super();
        }

        public static Date getDefaultDate(final Date date) {
            return getDefaultIfNull(date, DEFAULT_DATE);
        }

        public static Date getNewDate() {
            return new Date();
        }

        public static boolean isDefaultDate(final Date date) {
            return DEFAULT_DATE.equals(date);
        }
}
