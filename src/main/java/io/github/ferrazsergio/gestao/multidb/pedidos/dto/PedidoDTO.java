package io.github.ferrazsergio.gestao.multidb.pedidos.dto;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class PedidoDTO {
    private Long id;
    private Long clienteId; // Agora temos referência ao cliente
    private Double valorTotal;

    public PedidoDTO() {}

    public PedidoDTO(Long id, Long clienteId, Double valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
}