package com.developerviana.requestsapi.consumer;

import com.developerviana.requestsapi.dto.PedidoMessage;
import com.developerviana.requestsapi.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @RabbitListener(queues = "pedidos.queue")
    public void processarPedido(String message) {
        try {
            PedidoMessage pedidoMessage = objectMapper.readValue(message, PedidoMessage.class);
            pedidoService.salvarPedido(pedidoMessage);
            System.out.println("Pedido processado com sucesso: " + pedidoMessage.getCodigoPedido());
        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
