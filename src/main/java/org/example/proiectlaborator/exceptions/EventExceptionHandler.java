package org.example.proiectlaborator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<String> handleNonExistingException(GeneralException generalException) {
        return new ResponseEntity<>(generalException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleGeneralException(MethodArgumentNotValidException eventException) {
        var errorMessage = eventException
                .getBindingResult()
                .getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handlePaymentException(MissingServletRequestParameterException eventException) {
        var errorMessage = eventException.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
