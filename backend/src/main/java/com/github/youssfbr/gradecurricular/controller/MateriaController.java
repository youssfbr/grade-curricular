package com.github.youssfbr.gradecurricular.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @GetMapping
    public ResponseEntity<String> lala() {
        return ResponseEntity.status(HttpStatus.OK).body("lala");
    }
}