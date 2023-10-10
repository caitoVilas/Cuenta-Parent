package com.caito.customer.api.exceptions.controllers;

import com.caito.customer.api.exceptions.customs.BadRequestException;
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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionController {
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> badRequestExceptionHandler(Exception e, HttpServletRequest request){
        var reponse = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(reponse, HttpStatus.BAD_REQUEST);
    }
}
