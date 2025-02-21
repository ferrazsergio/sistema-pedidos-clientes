package io.github.ferrazsergio.gestao.multidb.clientes.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

}
