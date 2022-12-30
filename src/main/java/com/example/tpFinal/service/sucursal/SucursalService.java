package com.example.tpFinal.service.sucursal;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.request.SucursalDto;
import com.example.tpFinal.dto.response.SucursalAsignadaDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.Sucursal;
import com.example.tpFinal.repository.IClienteRepository;
import com.example.tpFinal.repository.ISucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    IClienteRepository clienteRepository;

    @Autowired
    ISucursalRepository sucursalRepository;

    @Override
    public List<SucursalDto> mostrarSucursales() {
        ModelMapper mapper = new ModelMapper();
        List<Sucursal> sucursales = sucursalRepository.findAll();
        List<SucursalDto>  listaSucursales = new ArrayList<>();
        sucursales.stream()
                .forEach(sucursal -> listaSucursales.add(mapper.map(sucursal,SucursalDto.class)));
        return listaSucursales;
    }

    @Override
    public SucursalDto mostrarSucursalDe(String name) {
        ModelMapper mapper = new ModelMapper();
        Cliente cliente = sucursalRepository.findByName(name);
        SucursalDto sucursalDto = mapper.map(cliente.getSucursal(),SucursalDto.class);

        return sucursalDto ;
    }

    public SucursalAsignadaDto asignarSucursal(Long sucursalId, Long clienteId){
        ModelMapper mapper = new ModelMapper();
        Optional<Sucursal> optSucursal = sucursalRepository.findById(sucursalId);
        Optional<Cliente> optCliente = clienteRepository.findById(clienteId);
        if (optSucursal.isPresent() && optCliente.isPresent()){
            Sucursal sucursal = mapper.map(optSucursal.get(),Sucursal.class);
            Cliente cliente = mapper.map(optCliente.get(),Cliente.class);
            cliente.setSucursal(sucursal);
            clienteRepository.save(cliente);
            List<Cliente> clientes = new ArrayList<>();
            clientes.add(cliente);
            sucursal.setClientes(clientes);
            SucursalDto sucursalDto = mapper.map(optSucursal.get(),SucursalDto.class);
            ClienteDto clienteDto = mapper.map(optCliente.get(),ClienteDto.class);
            return  new SucursalAsignadaDto(sucursalDto, "Sucursal asignada correctamente!");
        }
        SucursalAsignadaDto sucursalAsignadaDto = new SucursalAsignadaDto(null,null);
        sucursalAsignadaDto.setMensaje("La sucursal no pudo ser asignada!");
        return sucursalAsignadaDto;
    }
}
