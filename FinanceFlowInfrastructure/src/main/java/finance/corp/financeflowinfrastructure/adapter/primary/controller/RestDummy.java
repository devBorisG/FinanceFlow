package finance.corp.financeflowinfrastructure.adapter.primary.controller;

import finance.corp.financeflowinfrastructure.adapter.secondary.MailgunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class RestDummy {

    private final MailgunService mailgunService;

    public RestDummy(MailgunService mailgunService) {
        this.mailgunService = mailgunService;
    }

    @PostMapping("/recuperar-cuenta")
    public ResponseEntity<String> dummyResponse() {
        mailgunService.sendMail("david.andres.2801@gmail.com");
        return ResponseEntity.ok("Dummy response");
    }
}
