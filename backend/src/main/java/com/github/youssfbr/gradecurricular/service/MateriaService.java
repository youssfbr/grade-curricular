package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.controller.MateriaController;
import com.github.youssfbr.gradecurricular.dto.MateriaDto;
import com.github.youssfbr.gradecurricular.entity.MateriaEntity;
import com.github.youssfbr.gradecurricular.exception.MateriaException;
import com.github.youssfbr.gradecurricular.repository.IMateriaRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig( cacheNames = "materia" )
public class MateriaService implements IMateriaService {

    private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte";
    private static final String MATERIA_NAO_ENCONTRADA = "Matéria não encontrada";
    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    public MateriaService(IMateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    @CachePut( unless = "#result.size() < 3" )
    public List<MateriaDto> listarMateriais() {
        try {
            List<MateriaDto> materiaDto = mapper.map(materiaRepository.findAll(), new TypeToken<List<MateriaDto>>() {
            }.getType());

            materiaDto.forEach(materia -> {
                materia.add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(materia.getId()))
                        .withSelfRel());
            });

            return materiaDto;
        }
        catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean atualizarMateria(MateriaDto materiaDto) {
        try {
            consultarMateria(materiaDto.getId());

            MateriaEntity materiaEntityAtualizada = mapper.map(materiaDto, MateriaEntity.class);

            materiaRepository.save(materiaEntityAtualizada);

            return Boolean.TRUE;
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    @CachePut( key = "#id" )
    public MateriaDto consultarMateria(Long id) {
        try {
            return materiaRepository
                    .findById(id)
                    .map(materia -> mapper.map(materia, MateriaDto.class))
                    .orElseThrow(() ->  new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND));
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrarMateria(MateriaDto materiaDto) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materiaDto, MateriaEntity.class);

            materiaRepository.save(materiaEntity);

            return Boolean.TRUE;
        }
        catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean excluirMateria( Long id ) {
        try {
            this.consultarMateria(id);
            materiaRepository.deleteById(id);
            return Boolean.TRUE;
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
