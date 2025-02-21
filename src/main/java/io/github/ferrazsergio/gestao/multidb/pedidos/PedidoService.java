package io.github.ferrazsergio.gestao.multidb.pedidos;


import io.github.ferrazsergio.gestao.multidb.clientes.Cliente;
import io.github.ferrazsergio.gestao.multidb.clientes.ClienteRepository;
import io.github.ferrazsergio.gestao.multidb.historico.PedidoHistorico;
import io.github.ferrazsergio.gestao.multidb.historico.PedidoHistoricoRepository;
import io.github.ferrazsergio.gestao.multidb.pedidos.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("pedidoHistoricoMongoRepository")  // Qualifica o repositório MongoDB
    private final PedidoHistoricoRepository pedidoHistoricoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         @Qualifier("pedidoHistoricoMongoRepository") PedidoHistoricoRepository pedidoHistoricoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoHistoricoRepository = pedidoHistoricoRepository;
    }


    // Criar pedido e salvar no histórico
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido(cliente, pedidoDTO.getValorTotal());
        pedido = pedidoRepository.save(pedido);

        // Salvar no histórico
        PedidoHistorico historico = new PedidoHistorico(pedido.getId(), cliente.getId(), pedido.getValorTotal(), java.time.LocalDateTime.now());
        pedidoHistoricoRepository.save(historico);

        return new PedidoDTO(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal());
    }

    // Buscar pedido por ID
    public PedidoDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoDTO(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal());
    }

    // Listar pedidos
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(p -> new PedidoDTO(p.getId(), p.getCliente().getId(), p.getValorTotal()))
                .collect(Collectors.toList());
    }

    // Deletar pedido por ID e salvar no histórico
    public void deletarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Salvar no histórico antes de deletar
        PedidoHistorico historico = new PedidoHistorico(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal(), java.time.LocalDateTime.now());
        pedidoHistoricoRepository.save(historico);

        pedidoRepository.deleteById(id);
    }
}