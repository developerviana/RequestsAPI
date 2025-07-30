package com.developerviana.requestsapi.controller;

import com.developerviana.requestsapi.model.Pedido;
import com.developerviana.requestsapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/pedidos/total/{codigo}")
    public ResponseEntity<BigDecimal> getValorTotalPedido(@PathVariable Integer codigo) {
        BigDecimal valorTotal = pedidoService.getValorTotalPedido(codigo);
        if (valorTotal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valorTotal);
    }
    
    @GetMapping("/clientes/{id}/quantidade")
    public ResponseEntity<Long> getQuantidadePedidosCliente(@PathVariable Integer id) {
        Long quantidade = pedidoService.getQuantidadePedidosCliente(id);
        return ResponseEntity.ok(quantidade);
    }
    
    @GetMapping("/clientes/{id}/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosCliente(@PathVariable Integer id) {
        List<Pedido> pedidos = pedidoService.getPedidosCliente(id);
        return ResponseEntity.ok(pedidos);
    }
}
