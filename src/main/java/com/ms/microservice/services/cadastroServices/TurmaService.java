package com.ms.microservice.services.cadastroServices;


import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma saveTurma(Turma turma){
        return turmaRepository.save(turma);
    }

    public List<Turma> findAllTurmas(){
        return turmaRepository.findAll();
    }

    public Turma findTurmaByCodigo(Long codigo) {
        return turmaRepository.findByCode(codigo);
    }
}
