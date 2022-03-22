package com.github.youssfbr.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public MateriaException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
