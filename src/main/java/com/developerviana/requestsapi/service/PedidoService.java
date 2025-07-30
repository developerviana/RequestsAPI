package com.developerviana.requestsapi.service;

import com.developerviana.requestsapi.dto.PedidoMessage;
import com.developerviana.requestsapi.model.Item;
import com.developerviana.requestsapi.model.Pedido;
import com.developerviana.requestsapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public Pedido salvarPedido(PedidoMessage pedidoMessage) {
        // Criar pedido
        Pedido pedido = new Pedido(pedidoMessage.getCodigoPedido(), pedidoMessage.getCodigoCliente());
        
        // Criar itens
        List<Item> itens = pedidoMessage.getItens().stream()
                .map(itemMessage -> {
                    Item item = new Item(itemMessage.getProduto(), itemMessage.getQuantidade(), itemMessage.getPreco());
                    item.setPedido(pedido);
                    return item;
                })
                .collect(Collectors.toList());
        
        pedido.setItens(itens);
        
        // Calcular valor total
        BigDecimal valorTotal = itens.stream()
                .map(Item::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        pedido.setValorTotal(valorTotal);
        
        return pedidoRepository.save(pedido);
    }
    
    public BigDecimal getValorTotalPedido(Integer codigoPedido) {
        return pedidoRepository.findValorTotalByCodigoPedido(codigoPedido);
    }
    
    public Long getQuantidadePedidosCliente(Integer codigoCliente) {
        return pedidoRepository.countByCodigoCliente(codigoCliente);
    }
    
    public List<Pedido> getPedidosCliente(Integer codigoCliente) {
        return pedidoRepository.findByCodigoCliente(codigoCliente);
    }
}
