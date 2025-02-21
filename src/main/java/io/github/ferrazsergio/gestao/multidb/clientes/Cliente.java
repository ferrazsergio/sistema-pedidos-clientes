package io.github.ferrazsergio.gestao.multidb.clientes;

import io.github.ferrazsergio.gestao.multidb.pedidos.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relacionamento com Pedido

    private String nome;
    private String email;

    public Cliente(Long id, List<Pedido> pedidos, String nome, String email) {
        this.id = id;
        this.pedidos = pedidos;
        this.nome = nome;
        this.email = email;
    }

    // Construtores existentes
    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    // Construtor padrão
    public Cliente() {
    }
}
