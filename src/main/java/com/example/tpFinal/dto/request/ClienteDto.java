package com.example.tpFinal.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {


    private Long id;

    @NotBlank
    @Size(min = 1, max = 30)
    private String nombre;

    @Size(min = 1, max = 30)
    @NotBlank
    private String apellido;

    @Min(value = 18, message = "El cliente no puede ser menor de 18 años.")
    private int edad;

    @NotNull
    private Long dni;

    public ClienteDto(String nombre, String apellido, int edad, Long dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
    }
}