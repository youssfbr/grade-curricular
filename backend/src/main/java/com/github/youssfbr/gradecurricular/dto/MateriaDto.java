package com.github.youssfbr.gradecurricular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class MateriaDto {

    private Long id;

    @NotBlank(message = "Informe o nome da materia.")
    private String nome;

    @Min(value = 34, message = "Permitido o mínimo de 34 horas por matéria.")
    @Max(value = 120, message = "Permitido o máximo de 120 horas por matéria.")
    private Integer horas;

    @NotBlank(message = "Informe o código da materia.")
    private String codigo;

    @Min(value = 1, message = "Permitido o mínimo de 1 vezao ano.")
    @Max(value = 2, message = "Permitido o máximo de 2 vezes ao ano.")
    private Integer frequencia;
}
