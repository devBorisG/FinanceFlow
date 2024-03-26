package finance.corp.financeflowinfrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class RestDummy {

    @RequestMapping ("/dummyResponse")
    public String dummyResponse() {
        return "This is a dummy response!";
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello World!");
        return "Hello World!";
    }

    @RequestMapping ("/bye")
    public String bye() {
        System.out.println("Bye!");
        return "Goodbye!";
    }

    @RequestMapping ("/")
    public String root() {
        return "Root!";
    }

    @RequestMapping ("/test")
    public String test() {
        return "Test!";
    }
}
