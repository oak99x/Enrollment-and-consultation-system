package com.ms.microservice.repositories;

import com.ms.microservice.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("select t from Turma t where t.codigo = ?1")
    Turma findByCode(Long codigo);
}

