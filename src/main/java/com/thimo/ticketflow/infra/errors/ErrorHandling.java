package com.thimo.ticketflow.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(dataValidationError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record dataValidationError(String field,String error){
        public dataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
