package com.example.tpFinal.service.cliente;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.response.ClienteResponseDto;

import java.util.List;


public interface IClienteService {

    List<ClienteDto> mostrarClientes();

    ClienteResponseDto nuevoCliente(ClienteDto clienteDto);

    ClienteResponseDto actualizarCliente(ClienteDto clienteDto);

    ClienteResponseDto borrar(Long id);


    }
