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

import acc.spring.DTO.ResponseDto;
import acc.spring.exceptions.list.BlockedAccountException;
import acc.spring.exceptions.list.DateExceptionException;
import acc.spring.exceptions.list.InsufficientFundsException;
import acc.spring.exceptions.list.InvalidParameter;
import acc.spring.exceptions.list.NotFoundException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<ResponseDto> handleNotFoundException(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = { UnexpectedTypeException.class })
    public ResponseEntity<ResponseDto> handleTypeException(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = "Valor invalido en su solicitud: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { InvalidParameter.class })
    public ResponseEntity<ResponseDto> handleDtoParametersException(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { InsufficientFundsException.class })
    public ResponseEntity<ResponseDto> handleInsuficientFundsException(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { DateExceptionException.class })
    public ResponseEntity<ResponseDto> handleDatesException(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { BlockedAccountException.class })
    public ResponseEntity<ResponseDto> handleInnactiveAccount(Exception ex) {
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<ResponseDto> handleModelConstrainsException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> listOfValidations = ex.getConstraintViolations();

        Map<String, String> errorMessages = new HashMap<String, String>();

        listOfValidations.stream().forEach((validation) -> {
            errorMessages.put(validation.getPropertyPath().toString(), validation.getMessage());
        });
        
        ResponseDto response = new ResponseDto();
        response.title="Error";
        response.errorsList = errorMessages;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
