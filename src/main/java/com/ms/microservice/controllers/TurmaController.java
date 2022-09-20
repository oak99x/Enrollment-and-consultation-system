package com.ms.microservice.controllers;

import com.ms.microservice.dtos.TurmaDto;
import com.ms.microservice.models.Turma;
import com.ms.microservice.services.cadastroServices.TurmaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;


    @PostMapping("/cadastra")
    public ResponseEntity<Turma> cadastraTurma(@RequestBody TurmaDto turmaDto){
        Turma turma = new Turma();
        BeanUtils.copyProperties(turmaDto, turma);
        turmaService.saveTurma(turma);
        return new ResponseEntity<>(turma, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        List<Turma> aaa = turmaService.findAllTurmas();
        return new ResponseEntity<>(aaa, HttpStatus.OK);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Turma> findByCodigo(@PathVariable("codigo") Long codigo){
        Turma turma = turmaService.findTurmaByCodigo(codigo);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }
}
