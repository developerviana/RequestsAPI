package com.developerviana.requestsapi.service;

import com.developerviana.requestsapi.dto.PedidoMessage;
import com.developerviana.requestsapi.model.Pedido;
import com.developerviana.requestsapi.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class PedidoServiceTest {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Test
    public void testSalvarPedido() {
        // Arrange
        PedidoMessage.ItemMessage item1 = new PedidoMessage.ItemMessage("lápis", 100, new BigDecimal("1.10"));
        PedidoMessage.ItemMessage item2 = new PedidoMessage.ItemMessage("caderno", 10, new BigDecimal("1.00"));
        List<PedidoMessage.ItemMessage> itens = Arrays.asList(item1, item2);
        
        PedidoMessage pedidoMessage = new PedidoMessage(1001, 1, itens);
        
        // Act
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedidoMessage);
        
        // Assert
        assertNotNull(pedidoSalvo.getId());
        assertEquals(Integer.valueOf(1001), pedidoSalvo.getCodigoPedido());
        assertEquals(Integer.valueOf(1), pedidoSalvo.getCodigoCliente());
        assertEquals(new BigDecimal("120.00"), pedidoSalvo.getValorTotal());
        assertEquals(2, pedidoSalvo.getItens().size());
    }
    
    @Test
    public void testGetQuantidadePedidosCliente() {
        // Arrange
        PedidoMessage.ItemMessage item = new PedidoMessage.ItemMessage("produto", 1, new BigDecimal("10.00"));
        List<PedidoMessage.ItemMessage> itens = Arrays.asList(item);
        
        pedidoService.salvarPedido(new PedidoMessage(1001, 1, itens));
        pedidoService.salvarPedido(new PedidoMessage(1002, 1, itens));
        pedidoService.salvarPedido(new PedidoMessage(1003, 2, itens));
        
        // Act
        Long quantidade = pedidoService.getQuantidadePedidosCliente(1);
        
        // Assert
        assertEquals(Long.valueOf(2), quantidade);
    }
}
