package io.github.ferrazsergio.gestao.multidb.historico;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString

@AllArgsConstructor
@Builder
@Document(collection = "pedidos_historico")
public class PedidoHistorico {
    @Id
    private String id;
    private Long pedidoId;
    private Long clienteId;
    private Double valorTotal;
    private LocalDateTime dataPedido;

    public PedidoHistorico() {}

    public PedidoHistorico(Long pedidoId, Long clienteId, Double valorTotal, LocalDateTime dataPedido) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
    }
}
