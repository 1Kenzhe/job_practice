package com.kenzhe.job.exception;


import com.kenzhe.job.exception.review.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.warn("Resource not found: {}", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp",  LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value()); // 404 code
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", "")); // Get requested path

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        log.warn("Illegal argument: {}", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp",  LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);// Get requested path
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {

        log.warn("Data Integrity Violation: {}", ex.getMessage()); // Log as warning

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value()); // 409 Conflict is often appropriate
        body.put("error", "Conflict");
        // Be cautious about exposing raw DB messages - provide a generic one
        body.put("message", "Database constraint violation occurred. Check for duplicate entries or invalid references.");
        // body.put("detailedMessage", ex.getMostSpecificCause().getMessage()); // Use cautiously - may expose internals
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(
            Exception ex, WebRequest request) {

        log.error("An unexpected error occurred: ", ex); // Log full stack trace as error

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        body.put("error", "Internal Server Error");
        // Provide a generic message to the client - don't leak internal details
        body.put("message", "An unexpected internal server error occurred.");
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
