package com.ignaciosuay.fruitshopapi.rest.controller;

import com.ignaciosuay.fruitshopapi.rest.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest servletRequest, Exception e) {
        return new ResponseEntity<>(
            errorResponse(servletRequest.getServletPath(), e.getClass().getCanonicalName(), BAD_REQUEST, e.getMessage()),
            BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest servletRequest, Exception e) {
        return new ResponseEntity<>(
            errorResponse(servletRequest.getServletPath(), e.getClass().getCanonicalName(), INTERNAL_SERVER_ERROR, e.getMessage()),
            INTERNAL_SERVER_ERROR
        );
    }

    private ErrorResponse errorResponse(String path, String exception, HttpStatus status, String message) {
        return ErrorResponse.builder()
            .timestamp(LocalDateTime.now().toEpochSecond(UTC))
            .status(status.value())
            .error(status.getReasonPhrase())
            .exception(exception)
            .message(message)
            .path(path)
            .build();
    }
}
