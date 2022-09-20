package com.ms.microservice.repositories;

import com.ms.microservice.models.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    @Query("select e from Estudante e where e.matricula = ?1")
    Estudante findByMatricula(Long matricula);

    @Query("select e from Estudante e where e.nome like %?1%")
    List<Estudante> findByNome(String nome);
}
