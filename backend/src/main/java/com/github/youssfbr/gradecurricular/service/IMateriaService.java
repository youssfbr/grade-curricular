package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {

    List<MateriaEntity> listarMateriais();
    MateriaEntity consultarMateria(Long id);
    Boolean cadastrarMateria(MateriaEntity materia);
    Boolean atualizarMateria(MateriaEntity materia);
    Boolean excluirMateria(Long id);
}
