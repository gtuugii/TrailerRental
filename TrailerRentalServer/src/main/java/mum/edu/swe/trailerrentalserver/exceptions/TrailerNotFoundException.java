package mum.edu.swe.trailerrentalserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Trailer Not Found")
public class TrailerNotFoundException extends RuntimeException {

    public TrailerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrailerNotFoundException(String message) {
        super(message);
    }

    public TrailerNotFoundException(Throwable cause) {
        super(cause);
    }

}
