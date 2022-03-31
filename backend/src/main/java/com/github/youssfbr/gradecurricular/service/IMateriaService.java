package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.dto.MateriaDto;

import java.util.List;

public interface IMateriaService {

    List<MateriaDto> listarMateriais();
    MateriaDto consultarMateria(Long id);
    List<MateriaDto> listarPorHorarioMinimo(int horaMinima);
    List<MateriaDto> listarPorFrequencia(int frequencia);

    Boolean cadastrarMateria(MateriaDto materiaDto);
    Boolean atualizarMateria(MateriaDto materiaDto);
    Boolean excluirMateria(Long id);

}
