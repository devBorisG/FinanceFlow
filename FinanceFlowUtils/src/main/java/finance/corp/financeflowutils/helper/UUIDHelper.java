package finance.corp.financeflowutils.helper;

import java.util.UUID;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;

public class UUIDHelper {

    private UUIDHelper() {
        super();
    }

    public static UUID getDefaultUUID(final UUID uuid) {
        return ObjectHelper.getDefaultIfNull(uuid, DEFAULT_UUID);
    }

    public static UUID getNewUUID() {
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (isDefaultUUID(uuid));
        return uuid;
    }

    public static boolean isDefaultUUID(final UUID uuid) {
        return DEFAULT_UUID.equals(uuid);
    }
}
