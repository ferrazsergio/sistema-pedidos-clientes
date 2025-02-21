package io.github.ferrazsergio.gestao.multidb.clientes;

import io.github.ferrazsergio.gestao.multidb.clientes.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail()))
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getEmail())).orElse(null);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getEmail());
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
