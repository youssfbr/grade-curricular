package com.github.youssfbr.gradecurricular.controller;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;
import com.github.youssfbr.gradecurricular.service.IMateriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
@RequiredArgsConstructor
public class MateriaController {

    private final IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMateriais() {
        return ResponseEntity.ok(materiaService.listarMateriais());
    }

    @GetMapping("{id}")
    public ResponseEntity<MateriaEntity> consultaMateria(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.consultarMateria(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.cadastrarMateria(materia));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizarMateria(@RequestBody MateriaEntity materia) {
        return ResponseEntity.ok(materiaService.atualizarMateria(materia));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.excluirMateria(id));
    }

}
