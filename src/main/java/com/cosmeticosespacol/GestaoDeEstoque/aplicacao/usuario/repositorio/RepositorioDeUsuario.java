package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeUsuario {

    Usuario cadastrarUsuario(Usuario novoUsuario);

    Optional<Usuario> buscarUsuarioPorUuid(UUID uuid);

    Usuario atualizarUsuario(UUID uuid, Usuario usuarioAtualizado);

    void deletarUsuarioPorUuid(UUID uuid);
}
