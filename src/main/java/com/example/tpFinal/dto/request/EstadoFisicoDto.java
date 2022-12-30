package com.example.tpFinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter @Setter
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
}
