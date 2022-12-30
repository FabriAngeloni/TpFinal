package com.example.tpFinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Setter @Getter @Data
@NoArgsConstructor
public class SucursalDto {

    private long numeroSucursal;

    @NotBlank
    private String zona;

    @NotNull
    private Time horarioApertura;

    @NotNull
    private Time horarioCierre;

    private List<ClienteDto> clientes;

    public SucursalDto(String zona, Time horarioApertura, Time horarioCierre) {
        this.zona = zona;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public SucursalDto(long numeroSucursal, String zona, Time horarioApertura, Time horarioCierre) {
        this.numeroSucursal = numeroSucursal;
        this.zona = zona;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public SucursalDto(long numeroSucursal, String zona, Time horarioApertura, Time horarioCierre, List<ClienteDto> clientes) {
        this.numeroSucursal = numeroSucursal;
        this.zona = zona;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.clientes = clientes;
    }
}

