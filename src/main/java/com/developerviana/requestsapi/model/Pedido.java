package com.developerviana.requestsapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo_pedido", nullable = false)
    private Integer codigoPedido;
    
    @Column(name = "codigo_cliente", nullable = false)
    private Integer codigoCliente;
    
    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itens;
    
    // Constructors
    public Pedido() {}
    
    public Pedido(Integer codigoPedido, Integer codigoCliente) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public List<Item> getItens() {
        return itens;
    }
    
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
