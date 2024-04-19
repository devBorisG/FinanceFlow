package finance.corp.financeflowdomain.port.output.email;

import java.util.UUID;

public interface SendEmail {
    void send(String to, UUID token);
}
