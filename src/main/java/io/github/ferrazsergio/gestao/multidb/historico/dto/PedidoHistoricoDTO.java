package io.github.ferrazsergio.gestao.multidb.historico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PedidoHistoricoDTO {
    private String id;
    private Long pedidoId;
    private Long clienteId;
    private Double valorTotal;
    private LocalDateTime dataPedido;

    public PedidoHistoricoDTO(String id, Long pedidoId, Long clienteId, Double valorTotal, LocalDateTime dataPedido) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
    }

}