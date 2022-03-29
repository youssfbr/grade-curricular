package com.github.youssfbr.gradecurricular.controller;

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

    private static final String DELETE = "DELETE";
    private static final String UPDATE = "UPDATE";

    private final IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMateriais() {
        Response<List<MateriaDto>> response = new Response<>();
        response.setData(materiaService.listarMateriais());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMateriais()).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<MateriaDto>> consultarMateria(@PathVariable Long id) {
        Response<MateriaDto> response = new Response<>();
        response.setData(materiaService.consultarMateria(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(id)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id)).withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(response.getData())).withRel(UPDATE));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materiaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.cadastrarMateria(materiaDto));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizarMateria(@Valid @RequestBody MateriaDto materiaDto) {
        return ResponseEntity.ok(materiaService.atualizarMateria(materiaDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.excluirMateria(id));
    }

}
