package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.constante.MensagensConstant;
import com.github.youssfbr.gradecurricular.dto.CursoDto;
import com.github.youssfbr.gradecurricular.entity.CursoEntity;
import com.github.youssfbr.gradecurricular.entity.MateriaEntity;
import com.github.youssfbr.gradecurricular.exception.CursoException;
import com.github.youssfbr.gradecurricular.exception.ErroInternoException;
import com.github.youssfbr.gradecurricular.model.CursoModel;
import com.github.youssfbr.gradecurricular.repository.ICursoRepository;

import com.github.youssfbr.gradecurricular.repository.IMateriaRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@CacheConfig( cacheNames = "curso" )
public class CursoService implements ICursoService {

    private final ICursoRepository cursoRepository;
    private final IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    public CursoService(ICursoRepository cursoRepository, IMateriaRepository materiaRepository) {
        this.cursoRepository = cursoRepository;
        this.materiaRepository = materiaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @CachePut(unless = "#result.size() < 3")
    public List<CursoDto> listarCursos() {
        try {
            return mapper.map(cursoRepository.findAll(), new TypeToken<List<CursoDto>>() {
            }.getType());
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    @CachePut( key = "#id" )
    public CursoDto consultarCurso(Long id) {
        try {
            return cursoRepository
                    .findById(id)
                    .map(curso -> mapper.map(curso, CursoDto.class))
                    .orElseThrow(() ->  new CursoException(MensagensConstant.ERRO_CURSO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND));
        }
        catch (CursoException c) {
            throw c;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    @CachePut(key = "#codCurso")
    public CursoDto consultarCursoPorCodigo(String codCurso) {
        try {

            CursoEntity curso = cursoRepository.findCursoByCodigo(codCurso);

            if (curso == null) {
                throw new CursoException(MensagensConstant.ERRO_CURSO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
            }

            return mapper.map(curso, new TypeToken<CursoDto>() {
            }.getType());
        }
        catch (CursoException c) {
            throw c;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public Boolean cadastrarCurso(CursoModel cursoModel) {
        try {

            if (cursoModel.getId() != null) {
                throw new CursoException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
            }

            if (cursoRepository.findCursoByCodigo(cursoModel.getCodCurso()) != null) {
                throw new CursoException(MensagensConstant.ERRO_CURSO_CADASTRADO_ANTERIORMENTE.getValor(), HttpStatus.BAD_REQUEST);
            }

            return cadastrarOuAtualizar(cursoModel);
        }
        catch (CursoException c) {
            throw c;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public Boolean atualizarCurso(CursoModel cursoModel) {
        try {

            consultarCursoPorCodigo(cursoModel.getCodCurso());

            return cadastrarOuAtualizar(cursoModel);
        }
        catch (CursoException c) {
            throw c;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public Boolean excluirCurso(Long id) {
        try {
            consultarCurso(id);
            cursoRepository.deleteById(id);
            return Boolean.TRUE;
        }
        catch (CursoException c) {
            throw c;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    private Boolean cadastrarOuAtualizar(CursoModel cursoModel) {

        List<MateriaEntity> listMateriaEntity = new ArrayList<>();

        if (!cursoModel.getMaterias().isEmpty()) {

            cursoModel.getMaterias().forEach(materia -> {
                if (materiaRepository.findById(materia).isPresent())
                    listMateriaEntity.add(materiaRepository.findById(materia).get());
            });
        }

        CursoEntity cursoEntity = new CursoEntity();
        if(cursoModel.getId()!=null) {
            cursoEntity.setId(cursoModel.getId());
        }
        cursoEntity.setCodigo(cursoModel.getCodCurso());
        cursoEntity.setNome(cursoModel.getNome());
        cursoEntity.setMaterias(listMateriaEntity);

        cursoRepository.save(cursoEntity);

        return Boolean.TRUE;
    }
}
