package com.ms.microservice.services.cadastroServices;


import com.ms.microservice.models.Turma;
import com.ms.microservice.repositories.TurmaRepository;
import com.ms.microservice.services.exceptions.ResourceAlreadyExistsException;
import com.ms.microservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    public Turma saveTurma(Turma turma){
        try{
            return turmaRepository.save(turma);
        }catch (RuntimeException e){
            throw new ResourceAlreadyExistsException(turma.getCodigo());
        }
    }

    public List<Turma> findAllTurmas(){
        return turmaRepository.findAll();
    }

    public Turma findTurmaByCodigo(Long codigo) {
        Optional<Turma> obj = Optional.ofNullable(turmaRepository.findByCode(codigo));
        return obj.orElseThrow(() -> new ResourceNotFoundException(codigo));
    }
}
