package acc.spring.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<?> handleNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(value = { UnexpectedTypeException.class })
    public ResponseEntity<?> handleTypeException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor invalido en su solicitud: " + ex.getMessage());
    }

    @ExceptionHandler(value = { InvalidParameter.class })
    public ResponseEntity<?> handleDtoParametersException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = { InsufficientFundsException.class })
    public ResponseEntity<?> handleInsuficientFundsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = { DateException.class })
    public ResponseEntity<?> handleDatesException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<?> handleModelConstrainsException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> listOfValidations = ex.getConstraintViolations();

        Map<String, String> errorMessages = new HashMap<String, String>();

        listOfValidations.stream().forEach((validation) -> {
            errorMessages.put(validation.getPropertyPath().toString(), validation.getMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

}
