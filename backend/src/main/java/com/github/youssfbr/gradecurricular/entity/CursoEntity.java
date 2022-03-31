package com.github.youssfbr.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_curso")
public class CursoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    private String nome;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "cod")
    private String codigo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private List<MateriaEntity> materias;
}
