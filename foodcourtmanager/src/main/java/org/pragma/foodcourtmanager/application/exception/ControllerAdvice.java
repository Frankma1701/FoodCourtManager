package org.pragma.foodcourtmanager.application.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorInfo> runtimeExceptionHandler (RuntimeException ex){
        ErrorInfo error = ErrorInfo.builder().code("500").message(ex.getMessage()).build();
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> handleMethodArgumentNotValid(ConstraintViolationException exception) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", "500");

        // Get all errors
        List<String> exceptionalErrors = exception.getConstraintViolations()
                .stream()
                .map(x -> x.getMessageTemplate())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);
        ErrorInfo error = ErrorInfo.builder().code("500").message(String.join(", ",exceptionalErrors)).build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
