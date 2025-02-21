package io.github.ferrazsergio.gestao.multidb.clientes;

import io.github.ferrazsergio.gestao.multidb.clientes.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.buscarClientePorId(id);
        return clienteDTO != null ? ResponseEntity.ok(clienteDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.criarCliente(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}