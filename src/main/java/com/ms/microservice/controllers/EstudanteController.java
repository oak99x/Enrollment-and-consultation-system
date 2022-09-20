package com.ms.microservice.controllers;

import com.ms.microservice.dtos.EstudanteDto;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.services.cadastroServices.EstudanteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @PostMapping("/cadastra")
    public ResponseEntity<Estudante> cadastraEstudante(@RequestBody EstudanteDto estudanteDto){
        Estudante estudante = new Estudante();
        BeanUtils.copyProperties(estudanteDto, estudante);
        estudanteService.saveEstudante(estudante);
        return new ResponseEntity<>(estudante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Estudante>> findAll(){
        List<Estudante> estudantes = estudanteService.findAllStudent();
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Estudante> findByMatricula(@PathVariable("matricula") Long matricula){
        Estudante estudante = estudanteService.findStudentByMatricula(matricula);
        return new ResponseEntity<>(estudante, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Estudante>> findByNome(@PathVariable("nome") String nome){
        List<Estudante> estudantes = estudanteService.findStudentByName(nome);
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

}
