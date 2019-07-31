package app.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}