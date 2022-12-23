package com.knf.dev.demo.backend.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessage> 
       internalServerError(Exception ex, WebRequest request) {
        ErrorMessage errors = 
           new ErrorMessage(500, new Date(),
              ex.getMessage(), "🔺 Server Side Error");

        return new ResponseEntity<>
            (errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodNotAllowed.class)
    public ResponseEntity<ErrorMessage> 
    MethodNotAllowed(Exception ex, WebRequest request) {
        ErrorMessage errors =
         new ErrorMessage(405, new Date(), 
           ex.getMessage(), "🔺 Not Allowed!");

        return new ResponseEntity<>
            (errors, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorMessage> 
    userNotFound(Exception ex, WebRequest request) {
        ErrorMessage errors =
         new ErrorMessage(404, new Date(), 
           ex.getMessage(), "🔺 User Not Found !");

        return new ResponseEntity<>
            (errors, HttpStatus.NOT_FOUND);
    }
}