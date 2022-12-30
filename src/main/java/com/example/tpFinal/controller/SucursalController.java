package com.example.tpFinal.controller;

import com.example.tpFinal.service.sucursal.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private ISucursalService sucursalService;


    @GetMapping("/todas")
    public ResponseEntity<?> listOfSucursales(){
        return new ResponseEntity<>(sucursalService.mostrarSucursales(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/sucursalDe/{name}")
    public ResponseEntity<?> sucursarlDe(@PathVariable String name){
        return new ResponseEntity<>(sucursalService.mostrarSucursalDe(name),HttpStatus.OK);
    }

    @PostMapping("/asignarSucursal/sucursal/{id0}/cliente/{id1}")
    public ResponseEntity<?> asignarSucursal(@PathVariable Long id0, @PathVariable Long id1){
       return new ResponseEntity<>(sucursalService.asignarSucursal(id0,id1),HttpStatus.ACCEPTED);
    }

}
