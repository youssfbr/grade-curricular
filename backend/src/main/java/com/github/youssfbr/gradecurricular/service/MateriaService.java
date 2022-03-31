package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.constante.MensagensConstant;
import com.github.youssfbr.gradecurricular.controller.MateriaController;
import com.github.youssfbr.gradecurricular.dto.MateriaDto;
import com.github.youssfbr.gradecurricular.entity.MateriaEntity;
import com.github.youssfbr.gradecurricular.exception.CursoException;
import com.github.youssfbr.gradecurricular.exception.ErroInternoException;
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

            materiaDto.forEach(materia ->
                materia.add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(materia.getId()))
                        .withSelfRel())
            );

            return materiaDto;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public Boolean atualizarMateria(MateriaDto materiaDto) {
        try {
            consultarMateria(materiaDto.getId());

           return cadastrarOuAtualizar(materiaDto);
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    @CachePut( key = "#id" )
    public MateriaDto consultarMateria(Long id) {
        try {
            return materiaRepository
                    .findById(id)
                    .map(materia -> mapper.map(materia, MateriaDto.class))
                    .orElseThrow(() ->  new MateriaException(MensagensConstant.ERRO_MATERIA_NAO_ENCONTRADA.getValor(), HttpStatus.NOT_FOUND));
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public List<MateriaDto> listarPorHorarioMinimo(int horaMinima) {
        return mapper.map(materiaRepository.findByHoraMinima(horaMinima), new TypeToken<List<MateriaDto>>() {
                }.getType());
    }

    @Override
    public List<MateriaDto> listarPorFrequencia(int frequencia) {
        return mapper.map(materiaRepository.findByFrequencia(frequencia), new TypeToken<List<MateriaDto>>() {
        }.getType());
    }

    @Override
    public Boolean cadastrarMateria(MateriaDto materiaDto) {
        try {
            if (materiaDto.getId() != null) {
                throw new MateriaException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
            }

            if (materiaRepository.findByCodigo(materiaDto.getCodigo()) != null) {
                throw new MateriaException(MensagensConstant.ERRO_MATERIA_CADASTRADA_ANTERIORMENTE.getValor(), HttpStatus.BAD_REQUEST);
            }

            return this.cadastrarOuAtualizar(materiaDto);
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    @Override
    public Boolean excluirMateria( Long id ) {
        try {
            consultarMateria(id);
            materiaRepository.deleteById(id);
            return Boolean.TRUE;
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new ErroInternoException();
        }
    }

    private Boolean cadastrarOuAtualizar(MateriaDto materiaDto) {

        MateriaEntity materiaEntity = mapper.map(materiaDto, MateriaEntity.class);

        materiaRepository.save(materiaEntity);

        return Boolean.TRUE;
    }

}
