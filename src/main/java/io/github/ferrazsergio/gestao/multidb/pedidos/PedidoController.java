package io.github.ferrazsergio.gestao.multidb.pedidos;

import io.github.ferrazsergio.gestao.multidb.historico.PedidoHistoricoService;
import io.github.ferrazsergio.gestao.multidb.historico.dto.PedidoHistoricoDTO;
import io.github.ferrazsergio.gestao.multidb.pedidos.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoHistoricoService pedidoHistoricoService;

    public PedidoController(PedidoService pedidoService, PedidoHistoricoService pedidoHistoricoService) {
        this.pedidoService = pedidoService;
        this.pedidoHistoricoService = pedidoHistoricoService;
    }

    // Listar pedidos
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.buscarPedidoPorId(id);
        return pedidoDTO != null ? ResponseEntity.ok(pedidoDTO) : ResponseEntity.notFound().build();
    }

    // Criar um pedido
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO novoPedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.ok(novoPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
    // 🔥 Novo endpoint para visualizar histórico de pedidos
    @GetMapping("/historico")
    public ResponseEntity<List<PedidoHistoricoDTO>> listarHistorico() {
        return ResponseEntity.ok(pedidoHistoricoService.listarHistorico());
    }
}