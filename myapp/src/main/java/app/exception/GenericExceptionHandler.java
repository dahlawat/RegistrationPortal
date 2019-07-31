package app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method to handle the case where student is not found for a certain Id
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ StudentNotFoundException.class })
    protected ResponseEntity<Object> handleStudentNotFoundExceptions(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Method to handle the case where a Constraint is violated or something wrong is in Data Integrity
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ ConstraintViolationException.class,
            DataIntegrityViolationException.class, IOException.class })
    public ResponseEntity<Object> handleBadRequest(
            Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return handleExceptionInternal(ex, ex.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
