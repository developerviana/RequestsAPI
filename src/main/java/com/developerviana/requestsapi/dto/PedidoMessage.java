package com.developerviana.requestsapi.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoMessage {
    
    private Integer codigoPedido;
    private Integer codigoCliente;
    private List<ItemMessage> itens;
    
    // Constructors
    public PedidoMessage() {}
    
    public PedidoMessage(Integer codigoPedido, Integer codigoCliente, List<ItemMessage> itens) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
        this.itens = itens;
    }
    
    // Getters and Setters
    public Integer getCodigoPedido() {
        return codigoPedido;
    }
    
    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    
    public Integer getCodigoCliente() {
        return codigoCliente;
    }
    
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    public List<ItemMessage> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemMessage> itens) {
        this.itens = itens;
    }
    
    public static class ItemMessage {
        private String produto;
        private Integer quantidade;
        private BigDecimal preco;
        
        // Constructors
        public ItemMessage() {}
        
        public ItemMessage(String produto, Integer quantidade, BigDecimal preco) {
            this.produto = produto;
            this.quantidade = quantidade;
            this.preco = preco;
        }
        
        // Getters and Setters
        public String getProduto() {
            return produto;
        }
        
        public void setProduto(String produto) {
            this.produto = produto;
        }
        
        public Integer getQuantidade() {
            return quantidade;
        }
        
        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
        
        public BigDecimal getPreco() {
            return preco;
        }
        
        public void setPreco(BigDecimal preco) {
            this.preco = preco;
        }
    }
}
