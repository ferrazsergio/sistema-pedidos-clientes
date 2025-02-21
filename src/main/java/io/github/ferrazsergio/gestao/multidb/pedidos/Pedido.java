package io.github.ferrazsergio.gestao.multidb.pedidos;

import io.github.ferrazsergio.gestao.multidb.clientes.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;  // Relacionamento com Cliente

    private Double valorTotal;
    private LocalDateTime dataPedido;

    public Pedido(Cliente cliente, Double valorTotal) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Pedido() {

    }
}
