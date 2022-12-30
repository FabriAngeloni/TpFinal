package com.example.tpFinal.service.sucursal;

import com.example.tpFinal.dto.request.SucursalDto;
import com.example.tpFinal.dto.response.SucursalAsignadaDto;

import java.util.List;

public interface ISucursalService {

   List<SucursalDto> mostrarSucursales();
   SucursalDto mostrarSucursalDe(String name);

   SucursalAsignadaDto asignarSucursal(Long sucursalId, Long clienteId);

}

