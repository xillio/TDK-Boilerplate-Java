package nl.xillio.boilerplate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoilerplateController {

    @GetMapping("/")
    public String index()
    {
        return "Greetings from Spring Boot!";
    }
}
