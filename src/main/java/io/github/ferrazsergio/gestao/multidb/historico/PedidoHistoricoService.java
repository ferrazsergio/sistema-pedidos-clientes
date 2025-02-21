package io.github.ferrazsergio.gestao.multidb.historico;

import io.github.ferrazsergio.gestao.multidb.historico.dto.PedidoHistoricoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoHistoricoService {
    @Qualifier("pedidoHistoricoMongoRepository")
    private final PedidoHistoricoRepository pedidoHistoricoRepository;

    public PedidoHistoricoService(PedidoHistoricoRepository pedidoHistoricoRepository) {
        this.pedidoHistoricoRepository = pedidoHistoricoRepository;
    }

    public List<PedidoHistoricoDTO> listarHistorico() {
        return pedidoHistoricoRepository.findAll().stream()
                .map(p -> new PedidoHistoricoDTO(p.getId(), p.getPedidoId(), p.getClienteId(), p.getValorTotal(), p.getDataPedido()))
                .collect(Collectors.toList());
    }

}