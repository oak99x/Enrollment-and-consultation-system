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
public class Estudante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, unique = true)
    private Long matricula;

    @JsonIgnore
    @ManyToMany(mappedBy = "estudantes")
    private List<Disciplina> diciplinas = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "estudantes")
    private List<Turma> turmas = new ArrayList<>();
}
