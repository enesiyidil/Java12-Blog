package com.enes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(BlogException ex){
        HttpStatus httpStatus = ex.getErrorType().getHttpStatus();
        return new ResponseEntity<>(createError(ex), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(Exception ex){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(createError(ex, httpStatus.value()), httpStatus);
    }

    private ErrorMessage createError(BlogException ex) {
        return ErrorMessage.builder()
                .code(ex.getErrorType().getCode())
                .message(ex.getMessage())
                .build();
    }

    private ErrorMessage createError(Exception ex, int value){
        return ErrorMessage.builder()
                .code(value)
                .message(ex.getMessage())
                .build();
    }
}
