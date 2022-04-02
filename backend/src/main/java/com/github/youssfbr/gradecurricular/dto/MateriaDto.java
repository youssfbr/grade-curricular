package com.github.youssfbr.gradecurricular.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MateriaDto extends RepresentationModel<MateriaDto> {

    private Long id;

    @NotBlank(message = "Informe o nome da materia.")
    @Size(min = 10, max = 50, message = "O nome deve ter no mínimo 10 e máximo 50 caracteres.")
    private String nome;

    @Min(value = 34, message = "Permitido o mínimo de 34 horas por matéria.")
    @Max(value = 120, message = "Permitido o máximo de 120 horas por matéria.")
    private Integer horas;

    @NotBlank(message = "Informe o código da materia.")
    @Size(min = 2, max = 6, message = "O código deve ter no mínimo 2 e máximo 6 caracteres.")
    private String codigo;

    @Min(value = 1, message = "Permitido o mínimo de 1 vez ano.")
    @Max(value = 2, message = "Permitido o máximo de 2 vezes ao ano.")
    private Integer frequencia;
}
