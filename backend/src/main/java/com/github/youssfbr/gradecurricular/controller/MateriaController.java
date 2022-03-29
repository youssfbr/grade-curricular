package com.github.youssfbr.gradecurricular.controller;

import com.github.youssfbr.gradecurricular.dto.MateriaDto;
import com.github.youssfbr.gradecurricular.service.IMateriaService;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<List<MateriaDto>> listarMateriais() {
        return ResponseEntity.ok(materiaService.listarMateriais());
    }

    @GetMapping("{id}")
    public ResponseEntity<MateriaDto> consultarMateria(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.consultarMateria(id));
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
