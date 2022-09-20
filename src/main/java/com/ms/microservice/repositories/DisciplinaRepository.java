package com.ms.microservice.repositories;

import com.ms.microservice.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    @Query("select d from Disciplina d where d.codigo = ?1")
    Disciplina findByCode(Long codigo);
}
