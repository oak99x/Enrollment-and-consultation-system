package com.ms.microservice.services;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.EstudanteRepository;
import com.ms.microservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    //trabalhar as execoes
    //caso não tenha a diciplina
    //caso não tenha a turma
    public void matricula(Long matricula, Long disciplinaCode, Long turmaCode){

        Estudante estudante = estudanteRepository.findByMatricula(matricula);
        Turma turma = turmaRepository.findByCode(turmaCode);
        Disciplina disciplina = disciplinaRepository.findByCode(disciplinaCode);

        turma.getEstudantes().add(estudante);
        disciplina.getEstudantes().add(estudante);
        //disciplina.getTurmas().add(turma);
        turmaRepository.save(turma);
        disciplinaRepository.save(disciplina);
    }
}
