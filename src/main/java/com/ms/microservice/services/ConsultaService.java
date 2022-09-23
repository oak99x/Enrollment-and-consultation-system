package com.ms.microservice.services;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.EstudanteRepository;
import com.ms.microservice.repositories.TurmaRepository;
import com.ms.microservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;
    @Autowired
    private TurmaRepository turmaRepository;


    public List<Disciplina> disciplinasDoEstudante(Long matricula){
        Optional<Estudante> estudante = Optional.ofNullable(estudanteRepository.findByMatricula(matricula));
        estudante.orElseThrow(() -> new ResourceNotFoundException(matricula));
        return estudante.get().getDiciplinas();
    }

    public List<Turma> turmasDoEstudante(Long matricula){
        Optional<Estudante> estudante = Optional.ofNullable(estudanteRepository.findByMatricula(matricula));
        estudante.orElseThrow(() -> new ResourceNotFoundException(matricula));
        return estudante.get().getTurmas();
    }
    public List<Estudante> estudantesDaDisciplina(Long disciplinaCode){
        Optional<Disciplina> disciplina = Optional.ofNullable(disciplinaRepository.findByCode(disciplinaCode));
        disciplina.orElseThrow(() -> new ResourceNotFoundException(disciplinaCode));
        return disciplina.get().getEstudantes();
    }

    public List<Estudante> estudantesDaTurma(Long turmaCode){
        Optional<Turma> turma = Optional.ofNullable(turmaRepository.findByCode(turmaCode));
        turma.orElseThrow(() -> new ResourceNotFoundException(turmaCode));
        return turma.get().getEstudantes();
    }
}
