package com.ms.microservice.services.cadastroServices;

import com.ms.microservice.models.Disciplina;
import com.ms.microservice.models.Estudante;
import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.DisciplinaRepository;
import com.ms.microservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    //trabalhar as execoes
    //caso não tenha a diciplina
    //caso não tenha a turma
    public Disciplina saveDisciplina(Disciplina disciplina, Long turmaCode ){
        Turma turma = turmaRepository.findByCode(turmaCode);
        disciplina.getTurmas().add(turma);
        disciplina.setHorario(disciplina.getHorario().toUpperCase());

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina cadastraTurmaToDisciplina(Long disciplinaCode, Long turmaCode){
        Turma turma = turmaRepository.findByCode(turmaCode);
        Disciplina disciplina = disciplinaRepository.findByCode(disciplinaCode);
        disciplina.getTurmas().add(turma);
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAllDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina findDisciplinaByCodigo(Long codigo) {
        return disciplinaRepository.findByCode(codigo);
    }
}
