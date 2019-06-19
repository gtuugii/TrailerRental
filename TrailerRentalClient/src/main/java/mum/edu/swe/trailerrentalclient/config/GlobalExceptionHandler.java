package mum.edu.swe.trailerrentalclient.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class})
    public String forbidden(HttpClientErrorException e) {
        return "access-denied";
    }

    @ExceptionHandler({Exception.class})
    public String doIt(Exception e) {
        e.printStackTrace();
        return "redirect:/auth/login";
    }

}
