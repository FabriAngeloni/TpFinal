package com.example.tpFinal.ClienteServiceTest;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.response.ClienteResponseDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.repository.IClienteRepository;
import com.example.tpFinal.service.cliente.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void nuevoClienteTestOk() {

        ClienteDto clienteDto = new ClienteDto(1L,"Fabricio", "Angeloni", 22, 22424123L);
        ClienteResponseDto clienteResponseDtoEsperado = new ClienteResponseDto("Cliente añadido con exito!");

        ClienteResponseDto clienteResponseDtoRecibido = clienteService.nuevoCliente(clienteDto);

        assertEquals(clienteResponseDtoEsperado,clienteResponseDtoRecibido);
    }


    @Test
    void mostrarlistaClientesTestOk(){
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1L,"Fabricio","Angeloni",22,42563971L));
        listaClientes.add(new Cliente(2L,"Gabriel","Marquez",20,12123123L));
        listaClientes.add(new Cliente(3L,"Ivan","Guttierrez",33,43456456L));
        listaClientes.add(new Cliente(4L,"Roberto","Alvarez",23,65443443L));

        List<ClienteDto> listExpected = new ArrayList<>();
        listExpected.add(new ClienteDto(1L,"Fabricio","Angeloni",22,42563971L));
        listExpected.add(new ClienteDto(2L,"Gabriel","Marquez",20,12123123L));
        listExpected.add(new ClienteDto(3L,"Ivan","Guttierrez",33,43456456L));
        listExpected.add(new ClienteDto(4L,"Roberto","Alvarez",23,65443443L));


        when(clienteRepository.findAll()).thenReturn(listaClientes);
        List<ClienteDto> listReceived = clienteService.mostrarClientes();

        Assertions.assertAll(()->{
            assertEquals(listExpected.size(), listReceived.size());
            assertEquals(listExpected.get(0),listReceived.get(0));
        });
    }



    @Test
    void actualizarClienteTestOk(){
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1L,"Fabricio","Angeloni",22,42563971L));
        listaClientes.add(new Cliente(2L,"Gabriel","Marquez",20,12123123L));
        ClienteDto clienteDto = new ClienteDto(1L,"Fabricio", "Angeloni", 22, 22424123L);
        ClienteResponseDto respuestaEsperada = new ClienteResponseDto("El cliente se actualizo con exito!");


        when(clienteRepository.findAll()).thenReturn(listaClientes);
        ClienteResponseDto responseDtoRecibida = clienteService.actualizarCliente(clienteDto);

        assertEquals(respuestaEsperada,responseDtoRecibida);


    }

    @Test
    void borrarClienteOk(){
        Cliente  clienteRecibido = new Cliente(1L,"Fabricio","Angeloni",22,42563971L);

        ClienteResponseDto responseDtoEsperada = new ClienteResponseDto("Cliente eliminado con exito!");

        Long idRecibido = 1L;
        ClienteResponseDto responseDtoRecibida = clienteService.borrar(idRecibido);


        assertEquals(responseDtoEsperada,responseDtoRecibida);
        assertEquals(clienteRecibido.getId(),1L);
    }


}
