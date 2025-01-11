package com.bookreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookException bookException){
        ErrorResponse errorResponse = new ErrorResponse(
                bookException.getMessage(),
                bookException.getStatus(),
                bookException.getStatus().value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, bookException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserException userException) {
        ErrorResponse errorResponse = new ErrorResponse(
                userException.getMessage(),
                userException.getStatus(),
                userException.getStatus().value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, userException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ReviewException reviewException) {
        ErrorResponse errorResponse = new ErrorResponse(
                reviewException.getMessage(),
                reviewException.getStatus(),
                reviewException.getStatus().value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, reviewException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
