package com.example.tpFinal.service.estadoFisico;

import com.example.tpFinal.dto.request.EstadoFisicoDto;
import com.example.tpFinal.dto.response.BorrarEstadoFisicoDto;
import com.example.tpFinal.dto.response.EstadoFisicoResponseDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.EstadoFisico;
import com.example.tpFinal.repository.IClienteRepository;
import com.example.tpFinal.repository.IEstadoFisicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoFisicoService implements IEstadoFisicoService {


    @Autowired
    IEstadoFisicoRepository estadoFisicoRepository;

    @Autowired
    IClienteRepository clienteRepository;

    public List<EstadoFisicoDto> mostrarEstadoDeTodos(){
        ModelMapper mapper = new ModelMapper();
        List<EstadoFisico> estadoFisicos = estadoFisicoRepository.findAllEF();

        return estadoFisicos.stream().map(estadoFisico -> mapper.map(estadoFisico,EstadoFisicoDto.class)).collect(Collectors.toList());
    }
    @Override
    public EstadoFisicoDto mostrarEstadoCliente(Long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<EstadoFisico> estadoFisico = estadoFisicoRepository.findById(id);
        if (estadoFisico.isPresent()){
            EstadoFisicoDto optEstadoFisico = mapper.map(estadoFisico.get(),EstadoFisicoDto.class);

            return optEstadoFisico ;
        }else  {
            return null;
        }
    }
    public EstadoFisicoResponseDto agregarEstadoFisico(EstadoFisicoDto estadoFisicoDto) {
     ModelMapper mapper = new ModelMapper();

     estadoFisicoDto.setImc(estadoFisicoDto.getPeso()/ Math.pow(estadoFisicoDto.getAltura(),2));
     EstadoFisico estadoFisico = mapper.map(estadoFisicoDto,EstadoFisico.class);

     List<Cliente> clientes = clienteRepository.findAll();
     Optional<Cliente> optCliente = clientes.stream()
             .filter(cli -> cli.getId().equals(estadoFisico.getId()))
             .findFirst();
     Cliente cliente = optCliente.get();
     cliente.setEstadoFisico(estadoFisico);
     estadoFisicoRepository.save(estadoFisico);

     EstadoFisicoResponseDto estadoFisicoResponseDto = new EstadoFisicoResponseDto("Estado fisico del cliente agregado con exito");
     return estadoFisicoResponseDto;

    }

    @Override
    public EstadoFisicoResponseDto actualizarEstadoFisico(EstadoFisicoDto estadoFisicoDto){
        ModelMapper mapper = new ModelMapper();
        List<EstadoFisico> datosEF = estadoFisicoRepository.findAll();
        Optional<EstadoFisico> estadoFisicoBD = datosEF.stream().filter(cliente -> estadoFisicoDto.getId()
                .equals(cliente.getId())).findFirst();
        if (estadoFisicoBD.isPresent()){
            estadoFisicoDto.setImc(estadoFisicoDto.getPeso()/ Math.pow(estadoFisicoDto.getAltura(),2));
            EstadoFisico estadoFisico = mapper.map(estadoFisicoDto,EstadoFisico.class);
            estadoFisicoRepository.save(estadoFisico);
            return new EstadoFisicoResponseDto("Se actualizo cliente con exito!");
        }else {
            return new EstadoFisicoResponseDto("No hay datos para actualizar");
        }

    }

    public BorrarEstadoFisicoDto borrarEstadoCliente(Long id){
        ModelMapper mapper = new ModelMapper();
        estadoFisicoRepository.deleteById(id);
        return new BorrarEstadoFisicoDto("Cliente eliminado con exito!");
    }

}
