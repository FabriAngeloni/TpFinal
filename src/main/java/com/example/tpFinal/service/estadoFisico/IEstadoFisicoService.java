package com.example.tpFinal.service.estadoFisico;

import com.example.tpFinal.dto.request.EstadoFisicoDto;
import com.example.tpFinal.dto.response.BorrarEstadoFisicoDto;
import com.example.tpFinal.dto.response.EstadoFisicoResponseDto;

import java.util.List;


public interface IEstadoFisicoService {

    EstadoFisicoDto mostrarEstadoCliente(Long id);

    List<EstadoFisicoDto> mostrarEstadoDeTodos();

    EstadoFisicoResponseDto agregarEstadoFisico(EstadoFisicoDto estadoFisicoDto);

    BorrarEstadoFisicoDto borrarEstadoCliente(Long id);

    EstadoFisicoResponseDto actualizarEstadoFisico(EstadoFisicoDto estadoFisicoDto);


    }
