package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.usuario.entidade;


import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.NivelDeAcesso;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String nome;
    private String email;
    private String senha;
    private NivelDeAcesso nivelDeAcesso;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntidade that = (UsuarioEntidade) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
