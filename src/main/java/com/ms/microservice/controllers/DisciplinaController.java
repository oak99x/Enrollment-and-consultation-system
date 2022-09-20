package com.ms.microservice.controllers;


import com.ms.microservice.dtos.DisciplinaDto;
import com.ms.microservice.dtos.MatriculaDto;
import com.ms.microservice.models.Disciplina;
import com.ms.microservice.services.cadastroServices.DisciplinaService;
import com.ms.microservice.services.MatriculaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private MatriculaService matriculaService;


    @PostMapping("/cadastra")
    public ResponseEntity<Disciplina> cadastraDisciplina(@RequestBody DisciplinaDto disciplinaDto){
        Disciplina disciplina = new Disciplina();
        BeanUtils.copyProperties(disciplinaDto, disciplina);
        disciplinaService.saveDisciplina(disciplina, disciplinaDto.getTurmaCode());
        return new ResponseEntity<>(disciplina, HttpStatus.CREATED);
    }

    @PostMapping("/cadastraTurmaToDisciplina")
    public ResponseEntity<Disciplina> cadastraTurmaToDisciplina(@RequestBody DisciplinaDto disciplinaDto){
        Disciplina disciplina = disciplinaService.cadastraTurmaToDisciplina(disciplinaDto.getCodigo(), disciplinaDto.getTurmaCode());
        return new ResponseEntity<>(disciplina, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        List<Disciplina> disciplinas = disciplinaService.findAllDisciplinas();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Disciplina> findByCodigo(@PathVariable("codigo") Long codigo){
       Disciplina disciplina = disciplinaService.findDisciplinaByCodigo(codigo);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }

    @PostMapping("/matricula")
    public ResponseEntity<Void> matricula(@RequestBody MatriculaDto matriculaDto){
        matriculaService.matricula(matriculaDto.getMatricula(), matriculaDto.getDisciplinaCode(), matriculaDto.getTurmaCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
