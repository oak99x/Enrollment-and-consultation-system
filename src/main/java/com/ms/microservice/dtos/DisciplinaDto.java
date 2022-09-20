package com.ms.microservice.dtos;

import lombok.Data;

@Data
public class DisciplinaDto {
    private Long codigo;
    private String nome;
    private String horario;
    private Long turmaCode;
}
