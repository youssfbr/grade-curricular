package com.github.youssfbr.gradecurricular.dto;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CursoDto extends RepresentationModel<CursoDto> {

    private Long id;

    @NotBlank(message = "Informe o nome do curso.")
    private String nome;

    @NotBlank(message = "Informe o código do curso.")
    private String codigo;

    private List<MateriaEntity> materias;
}
