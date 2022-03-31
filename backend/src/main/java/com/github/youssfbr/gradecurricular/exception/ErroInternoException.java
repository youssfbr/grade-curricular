package com.github.youssfbr.gradecurricular.exception;

import lombok.Getter;

@Getter
public class ErroInternoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ErroInternoException() {
        super("Erro interno identificado. Contate o suporte.");
    }

}
