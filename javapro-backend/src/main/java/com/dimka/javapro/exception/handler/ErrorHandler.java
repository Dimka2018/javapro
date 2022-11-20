package com.dimka.javapro.exception.handler;

import com.dimka.javapro.exception.PermissionDeniedException;
import com.dimka.javapro.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFound(UserNotFoundException exception) {
        log.info("User not found", exception);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PermissionDeniedException.class)
    public void handlePermissionDenied(PermissionDeniedException exception) {
        log.info("Permission denied", exception);
    }
}
