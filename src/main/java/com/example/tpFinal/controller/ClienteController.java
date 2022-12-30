package com.example.tpFinal.controller;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.response.ClienteResponseDto;
import com.example.tpFinal.service.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/lista")
    public ResponseEntity<?> mostrarClientes() {
        return new ResponseEntity<>(clienteService.mostrarClientes(), HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<ClienteResponseDto> nuevoCliente(@Valid @RequestBody ClienteDto clienteDto) {
        return new ResponseEntity<>(clienteService.nuevoCliente(clienteDto), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ClienteResponseDto> actualizarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        return new ResponseEntity<>(clienteService.actualizarCliente(clienteDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.borrar(id), HttpStatus.ACCEPTED);
    }



}
