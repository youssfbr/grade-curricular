package com.github.youssfbr.gradecurricular.handler;

import com.github.youssfbr.gradecurricular.exception.MateriaException;
import com.github.youssfbr.gradecurricular.exception.ErrorMapResponse;
import com.github.youssfbr.gradecurricular.exception.ErrorMapResponse.ErrorMapResponseBuilder;
import com.github.youssfbr.gradecurricular.model.ErrorResponse;
import com.github.youssfbr.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMapResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException m) {

        Map<String, String> erros = new HashMap<>();

        m.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError)erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.errors(erros)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {

        ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(m.getHttpStatus().value());
        erro.mensagem(m.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
    }

}
