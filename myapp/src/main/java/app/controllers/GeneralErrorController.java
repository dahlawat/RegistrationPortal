package app.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This error controller will return a sample error message if a non-existing endpoint is tried to be accessed
 */
@RestController
public class GeneralErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @Override
    @GetMapping(ERROR_PATH)
    public String getErrorPath() {
        return "Something is wrong in the request !!";
    }

}
