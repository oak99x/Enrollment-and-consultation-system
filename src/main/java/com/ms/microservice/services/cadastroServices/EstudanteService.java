package com.ms.microservice.services.cadastroServices;

import com.ms.microservice.models.Estudante;
import com.ms.microservice.repositories.EstudanteRepository;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public Estudante saveEstudante(Estudante estudante) {
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
        return estudanteRepository.findByMatricula(matricula);
    }

    public List<Estudante> findStudentByName(String nome) {
        return estudanteRepository.findByNome(nome.toUpperCase());
    }
}
