package mum.edu.swe.trailerrentalserver.exceptions;

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
