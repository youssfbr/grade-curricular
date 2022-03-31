package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.dto.CursoDto;
import com.github.youssfbr.gradecurricular.model.CursoModel;

import java.util.List;


public interface ICursoService {

    List<CursoDto> listarCursos();
    CursoDto consultarCurso(Long id);
    CursoDto consultarCursoPorCodigo(String codCurso);

    Boolean cadastrarCurso(CursoModel curso);
    Boolean atualizarCurso(CursoModel curso);
    Boolean excluirCurso(Long id);

}
