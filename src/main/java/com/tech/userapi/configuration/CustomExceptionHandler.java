package com.tech.userapi.configuration;

import com.tech.userapi.exception.EmailExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<Object> validEmail(
            EmailExistsException exception) {
        return new ResponseEntity(CustomExceptionMessage.builder()
                .mensaje(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final List<CustomExceptionMessage> fieldMessages = fieldErrors.stream()
                .map(exception -> CustomExceptionMessage.builder()
                        .mensaje(exception.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        // return new ResponseEntity(fieldErrors.toArray(new FieldError[fieldErrors.size()]), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(fieldMessages, HttpStatus.BAD_REQUEST);
    }
}
