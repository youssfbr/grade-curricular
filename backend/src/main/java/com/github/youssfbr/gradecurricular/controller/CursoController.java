package com.github.youssfbr.gradecurricular.controller;

import com.github.youssfbr.gradecurricular.dto.CursoDto;

import com.github.youssfbr.gradecurricular.model.CursoModel;
import com.github.youssfbr.gradecurricular.model.Response;
import com.github.youssfbr.gradecurricular.service.ICursoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService cursoService;


    @GetMapping
    public ResponseEntity<Response<List<CursoDto>>> listarCursos() {

        Response<List<CursoDto>> response = new Response<>();
        response.setData(cursoService.listarCursos());
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<CursoDto>> consultarCurso(@PathVariable Long id) {

        Response<CursoDto> response = new Response<>();
        response.setData(cursoService.consultarCurso(id));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/codCurso/{codCurso}")
    public ResponseEntity<Response<CursoDto>> consultarCursoPorMateria(@PathVariable String codCurso) {

        Response<CursoDto> response = new Response<>();
        response.setData(cursoService.consultarCursoPorCodigo(codCurso));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarCurso(@Valid @RequestBody CursoModel curso) {

        Response<Boolean> response = new Response<>();
        response.setData(cursoService.cadastrarCurso(curso));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarCurso(@Valid @RequestBody CursoModel curso) {

        Response<Boolean> response = new Response<>();
        response.setData(cursoService.atualizarCurso(curso));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{cursoId}")
    public ResponseEntity<Response<Boolean>> excluirCurso(@PathVariable Long cursoId) {

        Response<Boolean> response = new Response<>();
        response.setData(cursoService.excluirCurso(cursoId));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

}
