package com.ms.microservice.services.cadastroServices;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.TurmaRepository;
import com.ms.microservice.services.exceptions.ResourceAlreadyExistsException;
import com.ms.microservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    public Disciplina saveDisciplina(Disciplina disciplina, Long turmaCode ){
        Optional<Turma> turma = Optional.ofNullable(turmaRepository.findByCode(turmaCode));
        turma.orElseThrow(() -> new ResourceNotFoundException(turmaCode));

        disciplina.getTurmas().add(turma.get());
        disciplina.setHorario(disciplina.getHorario().toUpperCase());

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina cadastraTurmaToDisciplina(Long disciplinaCode, Long turmaCode){
        Optional<Disciplina> disciplina = Optional.ofNullable(disciplinaRepository.findByCode(disciplinaCode));
        disciplina.orElseThrow(() -> new ResourceNotFoundException(disciplinaCode));

        Optional<Turma> turma = Optional.ofNullable(turmaRepository.findByCode(turmaCode));
        turma.orElseThrow(() -> new ResourceNotFoundException(turmaCode));

        //se a turma ja estiver na lista nao adiciona
        for(Turma t :  disciplina.get().getTurmas()){
            if(t.getCodigo() == turmaCode){
                throw new ResourceAlreadyExistsException(turmaCode);
            }
        }

        disciplina.get().getTurmas().add(turma.get());
        return disciplinaRepository.save(disciplina.get());
    }

    public List<Disciplina> findAllDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina findDisciplinaByCodigo(Long codigo) {
        Optional<Disciplina> obj = Optional.ofNullable(disciplinaRepository.findByCode(codigo));
        return obj.orElseThrow(() -> new ResourceNotFoundException(codigo));
    }
}
