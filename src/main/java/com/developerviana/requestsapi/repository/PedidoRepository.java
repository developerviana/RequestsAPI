package com.developerviana.requestsapi.repository;

import com.developerviana.requestsapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    Optional<Pedido> findByCodigoPedido(Integer codigoPedido);
    
    List<Pedido> findByCodigoCliente(Integer codigoCliente);
    
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.codigoCliente = :codigoCliente")
    Long countByCodigoCliente(@Param("codigoCliente") Integer codigoCliente);
    
    @Query("SELECT p.valorTotal FROM Pedido p WHERE p.codigoPedido = :codigoPedido")
    BigDecimal findValorTotalByCodigoPedido(@Param("codigoPedido") Integer codigoPedido);
}
