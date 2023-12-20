package com.enes.exception;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{

    private final ErrorType errorType;

    public BlogException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BlogException(String className, ErrorType errorType){
        super(className + errorType.getMessage());
        this.errorType = errorType;
    }
}
