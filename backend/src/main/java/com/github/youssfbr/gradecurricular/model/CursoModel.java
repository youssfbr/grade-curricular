package com.github.youssfbr.gradecurricular.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CursoModel {

    private Long id;

    @NotBlank(message = "O Nome deve ser preenchido.")
    @Size(min = 10, max = 30, message = "O nome deve ter no mínimo 10 e máximo 30 caracteres.")
    private String nome;

    @NotBlank(message = "O Código deve ser preenchido.")
    @Size(min = 2, max = 5, message = "O Código do curso deve ter no mínimo 2 e máximo 5 caracteres.")
    private String codCurso;

    private List<Long> materias;

}
