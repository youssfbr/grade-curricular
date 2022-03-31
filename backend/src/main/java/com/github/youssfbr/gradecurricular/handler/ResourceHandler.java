package com.github.youssfbr.gradecurricular.handler;

import com.github.youssfbr.gradecurricular.exception.CursoException;
import com.github.youssfbr.gradecurricular.exception.ErroInternoException;
import com.github.youssfbr.gradecurricular.exception.MateriaException;

import com.github.youssfbr.gradecurricular.model.Response;
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
    public ResponseEntity<Response<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException m) {

        Map<String, String> erros = new HashMap<>();

        m.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError)erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        Response<Map<String, String>> response = new Response<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException m) {

        Response<String> response = new Response<>();
        response.setStatusCode(m.getHttpStatus().value());
        response.setData(m.getMessage());

        return ResponseEntity.status(m.getHttpStatus()).body(response);
    }

    @ExceptionHandler(CursoException.class)
    public ResponseEntity<Response<String>> handlerCursoException(CursoException c) {

        Response<String> response = new Response<>();
        response.setStatusCode(c.getHttpStatus().value());
        response.setData(c.getMessage());

        return ResponseEntity.status(c.getHttpStatus()).body(response);
    }

    @ExceptionHandler(ErroInternoException.class)
    public ResponseEntity<Response<String>> handlerErroInternoException(ErroInternoException e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Response<String> response = new Response<>();
        response.setStatusCode(status.value());
        response.setData(e.getMessage());

        return ResponseEntity.status(status.value()).body(response);
    }

}
