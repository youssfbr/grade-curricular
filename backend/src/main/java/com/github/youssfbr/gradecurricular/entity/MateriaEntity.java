package com.github.youssfbr.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
public class MateriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    @Column(length =  50, nullable = false)
    private String nome;

    @Column(name = "hrs")
    private Integer horas;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "cod", length =  6, nullable = false)
    private String codigo;

    @Column(name = "freq")
    private Integer frequencia;
}
