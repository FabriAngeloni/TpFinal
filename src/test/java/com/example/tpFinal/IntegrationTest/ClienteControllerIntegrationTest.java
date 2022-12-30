package com.example.tpFinal.IntegrationTest;

import com.example.tpFinal.dto.request.ClienteDto;
import com.example.tpFinal.dto.response.ClienteResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE , false)
            .writer().withDefaultPrettyPrinter();


    @Test
    public void nuevoTestOk() throws Exception {
        ClienteDto payload = new ClienteDto(1L,"Fabricio","Angeloni",22,42345345L);

        String payloadJson = writer.writeValueAsString(payload);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cliente/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }

    @Test void actualizarTestOk() throws Exception {
        ClienteDto payload = new ClienteDto(1L,"Fabricio","Angeloni",22,42345345L);
        ClienteResponseDto responseDto = new ClienteResponseDto("El cliente se actualizo con exito!");

        String payloadJson = writer.writeValueAsString(payload);
        String responseDtoJson = writer.writeValueAsString(responseDto);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/cliente/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andReturn();

        assertAll(()->{
            assertEquals(202, response.getResponse().getStatus());
            assertEquals("application/json",response.getResponse().getContentType());
        });
    }

}
