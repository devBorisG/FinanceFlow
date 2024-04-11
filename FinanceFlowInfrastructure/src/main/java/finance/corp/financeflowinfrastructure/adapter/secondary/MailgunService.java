package finance.corp.financeflowinfrastructure.adapter.secondary;

import finance.corp.financeflowdomain.port.output.email.SendEmail;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MailgunService implements SendEmail {

    private final JavaMailSender emailSender;

    public MailgunService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void send(String to, String token) {
        String htmlContent = loadEmailTemplate().replace("{token}",token);
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(htmlContent, true);
            helper.setTo(to);
            helper.setSubject("Recuperar cuenta");
            helper.setFrom(new InternetAddress("zf9gv.finance-flow@inbox.testmail.app","FinanceFlow"));
            emailSender.send(message);
        } catch (MessagingException e) {
            throw InfraestructureCustomException.createTechnicalException(e, "No se pudo enviar el correo");
        } catch (InfraestructureCustomException e) {
            throw e;
        } catch (Exception e) {
            throw InfraestructureCustomException.createTechnicalException(e, "Ocurrio un error inesperado y no se pudo enviar el correo");
        }
    }


    private String loadEmailTemplate() {
        try {
            ClassPathResource resource = new ClassPathResource("./html/email-template.html");
            byte[] data = StreamUtils.copyToByteArray(resource.getInputStream());
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw InfraestructureCustomException.createTechnicalException(e, "Could not load email template");
        }
    }
}
