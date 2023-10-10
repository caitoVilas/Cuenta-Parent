package com.caito.customer.api.exceptions.controllers;

import com.caito.customer.api.exceptions.customs.NotFoundExceptions;
import com.caito.customer.api.models.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionController {
    @ExceptionHandler(NotFoundExceptions.class)
    protected ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception e, HttpServletRequest request){
        var reponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(reponse, HttpStatus.NOT_FOUND);
    }
}
