package com.ms.microservice.services.cadastroServices;

import com.ms.microservice.models.Estudante;
import com.ms.microservice.repositories.EstudanteRepository;
import com.ms.microservice.services.exceptions.ResourceNotFoundException;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteRepository estudanteRepository;

    public Estudante saveEstudante(Estudante estudante) {
        //gerador de matricula
        UniformRandomProvider rng = RandomSource.MT.create();
        long matricula = rng.nextLong(999999);

        estudante.setMatricula(matricula);
        estudante.setNome(estudante.getNome().toUpperCase());
        return estudanteRepository.save(estudante);
    }

    public List<Estudante> findAllStudent(){
        return  estudanteRepository.findAll();
    }

    public Estudante findStudentByMatricula(Long matricula) {
        Optional<Estudante> obj = Optional.ofNullable(estudanteRepository.findByMatricula(matricula));
        return obj.orElseThrow(() -> new ResourceNotFoundException(matricula));
    }

    public List<Estudante> findStudentByName(String nome) {
        List<Estudante> list = estudanteRepository.findByNome(nome.toUpperCase());
        if(list.isEmpty()){
            throw new ResourceNotFoundException(nome);
        }
        return list;
    }
}
