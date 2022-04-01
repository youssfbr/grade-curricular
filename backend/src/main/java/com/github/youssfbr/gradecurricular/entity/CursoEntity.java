package com.github.youssfbr.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;


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
    @Column(length =  30, nullable = false)
    private String nome;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "cod", length =  30, nullable = false)
    private String codigo;

    @ManyToMany
    @JoinTable(name = "tb_curso_materias",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private Set<MateriaEntity> materias;
}
