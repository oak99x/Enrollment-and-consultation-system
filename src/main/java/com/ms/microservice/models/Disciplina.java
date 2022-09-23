package com.ms.microservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private Long codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String horario;

    @JsonIgnore
    @OneToMany//(mappedBy = "disciplina")
    @JoinColumn(name = "disciplina_id")
    private List<Turma> turmas = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "estudante_id")
    private List<Estudante> estudantes = new ArrayList<>();

}
