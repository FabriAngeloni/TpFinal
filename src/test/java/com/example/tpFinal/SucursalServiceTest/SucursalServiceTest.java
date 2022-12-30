package com.example.tpFinal.SucursalServiceTest;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.request.SucursalDto;
import com.example.tpFinal.dto.response.SucursalAsignadaDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.Sucursal;
import com.example.tpFinal.repository.IClienteRepository;
import com.example.tpFinal.repository.ISucursalRepository;
import com.example.tpFinal.service.sucursal.SucursalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    ISucursalRepository sucursalRepository;

    @Mock
    IClienteRepository clienteRepository;

    @InjectMocks
    SucursalService sucursalService;

    @Test
    void mostrarSucursalesTestOk() {
        List<Sucursal> listaSucursales = new ArrayList<>();
        listaSucursales.add(new Sucursal(1L, "Villa Dalmine", null, null));
        listaSucursales.add(new Sucursal(2L, "Villa Lugano", null, null));
        listaSucursales.add(new Sucursal(3L, "Mataderos", null, null));

        List<SucursalDto> sucursalesDto = new ArrayList<>();
        sucursalesDto.add(new SucursalDto(1L,"Villa Dalmine", null, null));
        sucursalesDto.add(new SucursalDto(2L,"Villa Lugano", null, null));
        sucursalesDto.add(new SucursalDto(3L,"Mataderos", null, null));

        when(sucursalRepository.findAll()).thenReturn(listaSucursales);
        List<SucursalDto> listaRecibida = sucursalService.mostrarSucursales();

        assertAll(()->{
            assertEquals(sucursalesDto.get(0),listaRecibida.get(0));
            assertEquals(sucursalesDto,listaRecibida);
            assertEquals(sucursalesDto.size(),listaRecibida.size());
        });
    }

    @Test
    void mostrarSucursalDeTestOk(){
        String nombreEnviado = "Fabricio";
        SucursalDto sucursalDtoEsperado = new SucursalDto(1L,"Villa Dalmine", null, null);
        Cliente cliente = new Cliente(1L,"Fabricio","Angeloni",22, 12123123);
        String name = cliente.getNombre();
        cliente.setSucursal(new Sucursal(sucursalDtoEsperado.getNumeroSucursal(),sucursalDtoEsperado.getZona(),sucursalDtoEsperado.getHorarioApertura(),sucursalDtoEsperado.getHorarioCierre()));

        when(sucursalRepository.findByName(cliente.getNombre())).thenReturn(cliente);
        SucursalDto sucursalDtoRespuesta = sucursalService.mostrarSucursalDe(nombreEnviado);

        assertAll(()-> {
            assertEquals(nombreEnviado,cliente.getNombre());
            assertEquals(sucursalDtoEsperado,sucursalDtoRespuesta);
        });
    }

    @Test
    void asignarSucursalTestOk() {
        Long idClienteEnviado = 1L;
        Long idSucursalEnviado = 1L;
        Cliente cliente = new Cliente(1L, "Fabricio", "Angeloni", 22, 12123123);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        Sucursal sucursal = new Sucursal(1L, "Villa Dalmine", null, null);
        ClienteDto clienteDto = new ClienteDto(1L, "Fabricio", "Angeloni", 22, 12123123L);
        List<ClienteDto> clienteDtos = new ArrayList<>();
        clienteDtos.add(clienteDto);
        SucursalDto sucursalDto= new SucursalDto(1L, "Villa Dalmine", null, null);
        sucursal.setClientes(clientes);
        cliente.setSucursal(sucursal);
        sucursalDto.setClientes(clienteDtos);
        SucursalDto sucursalDtoEnviada = new SucursalDto(sucursal.getNumeroSucursal(), sucursal.getZona(), sucursal.getHorarioApertura(),sucursal.getHorarioCierre(),sucursalDto.getClientes());
        SucursalAsignadaDto sucursalEsperada = new SucursalAsignadaDto(sucursalDtoEnviada, "Sucursal asignada correctamente!");

        when(clienteRepository.findById(idClienteEnviado)).thenReturn(Optional.of(cliente));
        when(sucursalRepository.findById(idSucursalEnviado)).thenReturn(Optional.of(sucursal));
        SucursalAsignadaDto respuestaRecibida = sucursalService.asignarSucursal(idSucursalEnviado,idClienteEnviado);

        assertAll(()->{
            assertEquals(sucursalEsperada.getSucursal(),respuestaRecibida.getSucursal());
            assertEquals(sucursalEsperada.getMensaje(),respuestaRecibida.getMensaje());
        });
    }

}


