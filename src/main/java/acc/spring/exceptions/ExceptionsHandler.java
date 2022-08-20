package acc.spring.exceptions;

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

    @ExceptionHandler(value={UnexpectedTypeException.class})
    public ResponseEntity<?> handleTypeException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor invalido en su solicitud.");
    }

    @ExceptionHandler(value={InvalidParameter.class})
    public ResponseEntity<?> handleDtoParametersException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
