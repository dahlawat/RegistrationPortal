package app.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralErrorController implements ErrorController {
    private final static String PATH = "/error";

    @Override
    @GetMapping(PATH)
    public String getErrorPath() {
        return "Something wrong in the request";
    }

}
