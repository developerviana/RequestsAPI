package com.developerviana.requestsapi.controller;

import com.developerviana.requestsapi.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PedidoService pedidoService;
    
    @Test
    public void testGetValorTotalPedido() throws Exception {
        when(pedidoService.getValorTotalPedido(1001)).thenReturn(new BigDecimal("120.00"));
        
        mockMvc.perform(get("/pedidos/total/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("120.00"));
    }
    
    @Test
    public void testGetQuantidadePedidosCliente() throws Exception {
        when(pedidoService.getQuantidadePedidosCliente(1)).thenReturn(2L);
        
        mockMvc.perform(get("/clientes/1/quantidade"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
    
    @Test
    public void testGetPedidosCliente() throws Exception {
        when(pedidoService.getPedidosCliente(1)).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/clientes/1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
