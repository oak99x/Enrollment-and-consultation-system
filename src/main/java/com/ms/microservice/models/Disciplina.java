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
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long codigo;

    @NotNull
    private String nome;

    @NotNull
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
