package com.github.youssfbr.gradecurricular.constante;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HyperLinkConstant {

    ATUALIZAR("UPDATE"),
    EXCLUIR("DELETE"),
    LISTAR("GET_ALL"),
    CONSULTAR("GET");

    private final String valor;

}
