package com.example.tpFinal.service.cliente;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.request.SucursalDto;
import com.example.tpFinal.dto.response.ClienteResponseDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.EstadoFisico;
import com.example.tpFinal.model.Sucursal;
import com.example.tpFinal.repository.IClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService implements IClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> mostrarClientes() {
        ModelMapper modelMapper = new ModelMapper();
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> clienteDtos = new ArrayList<>();
        clientes.stream()
                .forEach(cliente -> clienteDtos.add(modelMapper.map(cliente, ClienteDto.class)));
        return clienteDtos;
    }

    @Override
    public ClienteResponseDto nuevoCliente(ClienteDto clienteDto) {
        ModelMapper mapper = new ModelMapper();
        Cliente cliente = mapper.map(clienteDto, Cliente.class);
        clienteRepository.save(cliente);
        ClienteResponseDto clienteResponseDto = new ClienteResponseDto("Cliente añadido con exito!");
        return clienteResponseDto;
    }


    @Override
    public ClienteResponseDto actualizarCliente(ClienteDto clienteDto) {
        ModelMapper mapper = new ModelMapper();
        List<Cliente> allClientesBd = clienteRepository.findAll();
        Optional<Cliente> clienteBd = allClientesBd.stream().filter(cliente -> clienteDto.getId().equals(cliente.getId())).findFirst();
        if (clienteBd.isPresent()){
            Cliente cliente = mapper.map(clienteDto,Cliente.class);
            clienteRepository.save(cliente);
            return  new ClienteResponseDto("El cliente se actualizo con exito!");
        }else {
            return new ClienteResponseDto("No existe el cliente");
        }

    }

    @Override
    public ClienteResponseDto borrar(Long id) {
        clienteRepository.deleteById(id);
        ClienteResponseDto clienteResponseDto = new ClienteResponseDto("Cliente eliminado con exito!");
        return clienteResponseDto;
    }


}

