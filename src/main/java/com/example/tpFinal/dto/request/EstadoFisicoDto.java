package com.example.tpFinal.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoFisicoDto {

    private Long id;

    @NotNull
    private double altura;
    @NotNull
    private double peso;

    private double imc;

    public EstadoFisicoDto(Long id, double altura, double peso) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
    }

    public EstadoFisicoDto(double altura, double peso) {
        this.altura = altura;
        this.peso = peso;
    }
}
