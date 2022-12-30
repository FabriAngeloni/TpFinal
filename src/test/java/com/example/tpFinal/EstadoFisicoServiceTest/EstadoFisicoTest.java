package com.example.tpFinal.EstadoFisicoServiceTest;

import com.example.tpFinal.dto.request.EstadoFisicoDto;
import com.example.tpFinal.dto.response.BorrarEstadoFisicoDto;
import com.example.tpFinal.dto.response.EstadoFisicoResponseDto;
import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.EstadoFisico;
import com.example.tpFinal.repository.IClienteRepository;
import com.example.tpFinal.repository.IEstadoFisicoRepository;
import com.example.tpFinal.service.estadoFisico.EstadoFisicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EstadoFisicoTest {

    @Mock
    IEstadoFisicoRepository estadoFisicoRepository;

    @Mock
    IClienteRepository clienteRepository;

    @InjectMocks
    EstadoFisicoService estadoFisicoService;

    @Test
    void agregarEstadoFisicoTestOk() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);

        EstadoFisicoDto estadoFisicoDto = new EstadoFisicoDto(1L, 1.70,70.0,0);
        Double setImc = estadoFisicoDto.getPeso()/ Math.pow(estadoFisicoDto.getAltura(),2);
        estadoFisicoDto.setImc(setImc);
        EstadoFisicoResponseDto responseDtoEsperado = new EstadoFisicoResponseDto("Estado fisico del cliente agregado con exito");

        List<Cliente> findAll = new ArrayList<>();
        findAll.add(new Cliente(1L,"Fabricio","Angeloni",22,42563971L));
        findAll.add(new Cliente(2L,"Gabriel","Gonzalez",22,42563971L));

        EstadoFisicoDto fisicoDtoRecibido = new EstadoFisicoDto(1L, 1.70,70.0,24.23);


        when(clienteRepository.findAll()).thenReturn(findAll);
        EstadoFisicoResponseDto responseDtoRecibido = estadoFisicoService.agregarEstadoFisico(estadoFisicoDto);

        assertAll(()-> {
                    assertEquals(responseDtoEsperado, responseDtoRecibido);
                    assertEquals(df.format(estadoFisicoDto.getImc()), df.format(fisicoDtoRecibido.getImc()));
                }
        );
    }

    @Test
    void mostrarEstadoClienteDeTodosTestOk(){
        List<EstadoFisico> findAll = new ArrayList<>();
        findAll.add((new EstadoFisico(1L,1.70,70.00,25.0,null)));
        findAll.add((new EstadoFisico(2L,1.60,65.00,23.0,null)));
        findAll.add((new EstadoFisico(3L,1.50,60.00,21.0,null)));

        List<EstadoFisicoDto> listaEsperada = new ArrayList<>();
        listaEsperada.add((new EstadoFisicoDto(1L,1.70,70.00,25.0)));
        listaEsperada.add((new EstadoFisicoDto(2L,1.60,65.00,23.0)));
        listaEsperada.add((new EstadoFisicoDto(3L,1.50,60.00,21.0)));

        when(estadoFisicoRepository.findAllEF()).thenReturn(findAll);
        List<EstadoFisicoDto> listaRecibida = estadoFisicoService.mostrarEstadoDeTodos();

        assertAll(()-> {
            assertEquals(listaEsperada.size(),listaRecibida.size());
            assertEquals(listaEsperada,listaRecibida);
        });
    }

    @Test
    void actualizarEstadoFisicoTestOk(){
        EstadoFisicoDto estadoFisicoDto = new EstadoFisicoDto(1L, 1.50,70.0);
        EstadoFisicoResponseDto responseDtoEsperado = new EstadoFisicoResponseDto("Se actualizo cliente con exito!");

        List<EstadoFisico> findAll = new ArrayList<>();
        findAll.add((new EstadoFisico(1L,1.70,70.00,25.0,null)));
        findAll.add((new EstadoFisico(2L,1.60,65.00,23.0,null)));
        findAll.add((new EstadoFisico(3L,1.50,60.00,21.0,null)));

        when(estadoFisicoRepository.findAll()).thenReturn(findAll);
        EstadoFisicoResponseDto responseDtoRecibida = estadoFisicoService.actualizarEstadoFisico(estadoFisicoDto);


        assertAll(()-> {
            assertEquals(estadoFisicoDto.getId(),findAll.get(0).getId());
            assertEquals(responseDtoEsperado,responseDtoRecibida);
        });
    }

    @Test
    void borrarEstadoClienteTestOk(){
        Long id = 1L;
        EstadoFisico estadoFisicoRecibido = new EstadoFisico(id, 1.70,70.0,22.5);
        BorrarEstadoFisicoDto responseDtoEsperado = new BorrarEstadoFisicoDto("Cliente eliminado con exito!");

        Long idRecibido = 1L;
        BorrarEstadoFisicoDto responseDtoRecibida = estadoFisicoService.borrarEstadoCliente(id);

        assertEquals(responseDtoEsperado, responseDtoRecibida);
    }

    @Test
    void mostrarEstadoFisicoDeTestOk(){
        EstadoFisico estadoFisico = new EstadoFisico(1L, 1.70,70.0,22.5);
        EstadoFisicoDto fisicoDtoEsperado= new EstadoFisicoDto(estadoFisico.getId(),estadoFisico.getAltura(),estadoFisico.getPeso(),estadoFisico.getImc());

        when(estadoFisicoRepository.findById(estadoFisico.getId())).thenReturn(Optional.of(estadoFisico));
        EstadoFisicoDto fisicoDtoRecibido = estadoFisicoService.mostrarEstadoCliente(1L);

        assertAll(()-> {
            assertEquals(fisicoDtoEsperado,fisicoDtoRecibido);
            assertEquals(fisicoDtoEsperado.getId(),fisicoDtoRecibido.getId());
        });


    }
}
