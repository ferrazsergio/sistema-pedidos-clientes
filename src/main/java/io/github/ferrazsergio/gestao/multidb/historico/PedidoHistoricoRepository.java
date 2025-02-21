package io.github.ferrazsergio.gestao.multidb.historico;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pedidoHistoricoMongoRepository")
public interface PedidoHistoricoRepository extends MongoRepository<PedidoHistorico, String> {
    List<PedidoHistorico> findByClienteId(Long clienteId);

}
