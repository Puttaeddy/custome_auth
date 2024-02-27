package com.finastra.users.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message, false);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MandatoryFieldException.class)

    public ResponseEntity<Map<String, String>> onDataIntegrityViolationException(MandatoryFieldException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put(ex.fieldName, ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> onDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> resp = new HashMap<>();

        resp.put("email", "User with Email Already Exist");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> userAlreadyExistException(UserAlreadyExistException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("email", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GraphClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorMessage> graphClientException(GraphClientException ex) {
        String message = ex.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message, false);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
