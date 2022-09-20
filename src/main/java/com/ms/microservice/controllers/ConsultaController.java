package com.ms.microservice.controllers;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/estudante/disciplinas/{matricula}")
    public ResponseEntity<List<Disciplina>> disciplinasDoEstudante(@PathVariable("matricula") Long matricula){
        List<Disciplina> disciplinas = consultaService.disciplinasDoEstudante(matricula);
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @GetMapping("/estudante/turmas/{matricula}")
    public ResponseEntity<List<Turma>> turmasDoEstudante(@PathVariable("matricula") Long matricula){
        List<Turma> turmas = consultaService.turmasDoEstudante(matricula);
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @GetMapping("/diciplina/lista-estudantes/{codigo}")
    public ResponseEntity<List<Estudante>> estudantesDaDisciplina(@PathVariable("codigo") Long disciplinaCode){
        List<Estudante> estudantes = consultaService.estudantesDaDisciplina(disciplinaCode);
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @GetMapping("/turma/lista-estudantes/{codigo}")
    public ResponseEntity<List<Estudante>> estudantesDaTurma(@PathVariable("codigo") Long turmaCode){
        List<Estudante> estudantes = consultaService.estudantesDaTurma(turmaCode);
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

}
