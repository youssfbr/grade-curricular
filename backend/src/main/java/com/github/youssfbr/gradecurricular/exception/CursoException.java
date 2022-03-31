package com.github.youssfbr.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CursoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public CursoException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
