package finance.corp.financeflowdomain.port.output.email;

public interface SendEmail {
    void send(String to, String token);
}