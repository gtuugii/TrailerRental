package mum.edu.swe.trailerrentalserver.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ConfAuthorizationException extends AuthenticationException {
    private String message;

    public ConfAuthorizationException(String msg) {
        super(msg);
        this.message=msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
