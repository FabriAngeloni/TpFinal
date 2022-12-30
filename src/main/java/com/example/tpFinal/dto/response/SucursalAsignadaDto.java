package com.example.tpFinal.dto.response;

import com.example.tpFinal.dto.request.SucursalDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SucursalAsignadaDto {
    private SucursalDto sucursal;
    private String mensaje;

    public SucursalAsignadaDto(SucursalDto sucursal, String mensaje) {
        this.sucursal = sucursal;
        this.mensaje = mensaje;
    }
}
