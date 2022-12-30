package com.example.tpFinal.controller;

import com.example.tpFinal.dto.request.EstadoFisicoDto;
import com.example.tpFinal.service.estadoFisico.IEstadoFisicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/EstadoFisico")
public class EstadoFisicoController {
    private IEstadoFisicoService estadoFisicoService;

    public EstadoFisicoController(IEstadoFisicoService estadoFisicoService) {
        this.estadoFisicoService = estadoFisicoService;
    }


    @GetMapping("/mostrarPor/{id}")
    public ResponseEntity<EstadoFisicoDto> mostrarEstadoFisico(@PathVariable Long id) {
        return new ResponseEntity<>(estadoFisicoService.mostrarEstadoCliente(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> nuevoEstadoCliente(@Valid @RequestBody EstadoFisicoDto estadoFisicoDto) {
        return new ResponseEntity<>(estadoFisicoService.agregarEstadoFisico(estadoFisicoDto), HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EstadoFisicoDto>> todos() {
        return new ResponseEntity<>(estadoFisicoService.mostrarEstadoDeTodos(), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarEstadoCliente(@Valid @PathVariable Long id){
        return new ResponseEntity<>(estadoFisicoService.borrarEstadoCliente(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/actualizar")
    public  ResponseEntity<?> actualizarEstadoFisico(@Valid @RequestBody EstadoFisicoDto estadoFisicoDto ){
        return new ResponseEntity<>(estadoFisicoService.actualizarEstadoFisico(estadoFisicoDto),HttpStatus.ACCEPTED);
    }
}
