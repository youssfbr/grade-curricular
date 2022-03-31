package com.github.youssfbr.gradecurricular.controller;

import com.github.youssfbr.gradecurricular.constante.HyperLinkConstant;
import com.github.youssfbr.gradecurricular.dto.MateriaDto;
import com.github.youssfbr.gradecurricular.model.Response;
import com.github.youssfbr.gradecurricular.service.IMateriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/materia")
@RequiredArgsConstructor
public class MateriaController {

    private final IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMaterias() {

        Response<List<MateriaDto>> response = new Response<>();

        response.setData(materiaService.listarMateriais());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
                .withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<MateriaDto>> consultarMateria(@PathVariable Long id) {

        Response<MateriaDto> response = new Response<>();

        response.setData(materiaService.consultarMateria(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id))
                .withRel(HyperLinkConstant.EXCLUIR.getValor()));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(response.getData())).
                withRel(HyperLinkConstant.ATUALIZAR.getValor()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/horario-minimo/{horaMinima}")
    public ResponseEntity<Response<List<MateriaDto>>> consultarMateriaPorHoraMinima(@PathVariable int horaMinima) {

        Response<List<MateriaDto>> response = new Response<>();

        response.setData(materiaService.listarPorHorarioMinimo(horaMinima));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateriaPorHoraMinima(horaMinima))
                .withSelfRel());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/frequencia/{frequencia}")
    public ResponseEntity<Response<List<MateriaDto>>> consultaMateriaPorFrequencia(@PathVariable int frequencia) {

        Response<List<MateriaDto>> response = new Response<>();

        List<MateriaDto> materia = this.materiaService.listarPorFrequencia(frequencia);
        response.setData(materia);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateriaPorFrequencia(frequencia))
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarMateria(@Valid @RequestBody MateriaDto materiaDto) {

        Response<Boolean> response = new Response<>();

        response.setData(materiaService.cadastrarMateria(materiaDto));
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).cadastrarMateria(materiaDto))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(materiaDto))
                .withRel(HyperLinkConstant.ATUALIZAR.getValor()));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarMateria(@Valid @RequestBody MateriaDto materiaDto) {

        Response<Boolean> response = new Response<>();

        response.setData(materiaService.atualizarMateria(materiaDto));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(materiaDto))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> excluirMateria(@PathVariable Long id) {

        Response<Boolean> response = new Response<>();

        response.setData(materiaService.excluirMateria(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.ok(response);
    }

}
