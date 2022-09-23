package com.ms.microservice.services;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.EstudanteRepository;
import com.ms.microservice.repositories.TurmaRepository;
import com.ms.microservice.services.exceptions.ResourceAlreadyExistsException;
import com.ms.microservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    public void matricula(Long matricula, Long disciplinaCode, Long turmaCode){

        Optional<Estudante> estudante = Optional.ofNullable(estudanteRepository.findByMatricula(matricula));
        estudante.orElseThrow(() -> new ResourceNotFoundException(matricula));

        Optional<Turma> turma = Optional.ofNullable(turmaRepository.findByCode(turmaCode));
        turma.orElseThrow(() -> new ResourceNotFoundException(turmaCode));

        Optional<Disciplina> disciplina = Optional.ofNullable(disciplinaRepository.findByCode(disciplinaCode));
        disciplina.orElseThrow(() -> new ResourceNotFoundException(disciplinaCode));

        //verifica se a turma esta na lista de turmas da diciplina
        boolean contem = false;
        for(Turma t :  disciplina.get().getTurmas()){
            if(t.getCodigo() == turmaCode){
                contem = true;
                break;
            }
        }

        if(contem == false){
            throw  new ResourceNotFoundException(turmaCode);
        }

        turma.get().getEstudantes().add(estudante.get());
        disciplina.get().getEstudantes().add(estudante.get());

        turmaRepository.save(turma.get());
        disciplinaRepository.save(disciplina.get());
    }
}
