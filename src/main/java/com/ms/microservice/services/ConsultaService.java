package com.ms.microservice.services;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.EstudanteRepository;
import com.ms.microservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    //trabalhar as execoes
    //caso não exista a diciplina
    //caso não exista a turma
    //caso não exista a matricula


    public List<Disciplina> disciplinasDoEstudante(Long matricula){
        Estudante estudante = estudanteRepository.findByMatricula(matricula);
        return estudante.getDiciplinas();
    }

    public List<Turma> turmasDoEstudante(Long matricula){
        Estudante estudante = estudanteRepository.findByMatricula(matricula);
        return estudante.getTurmas();
    }
    public List<Estudante> estudantesDaDisciplina(Long disciplinaCode){
        Disciplina disciplina = disciplinaRepository.findByCode(disciplinaCode);
        return disciplina.getEstudantes();
    }

    public List<Estudante> estudantesDaTurma(Long turmaCode){
        Turma turma = turmaRepository.findByCode(turmaCode);
        return turma.getEstudantes();
    }
}
