package com.github.youssfbr.gradecurricular.handler;

import com.github.youssfbr.gradecurricular.exception.MateriaException;
import com.github.youssfbr.gradecurricular.model.ErrorResponse;
import com.github.youssfbr.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {

        ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(m.getHttpStatus().value());
        erro.mensagem(m.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
    }

}
