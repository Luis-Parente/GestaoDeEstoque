package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeUsuario {

    Usuario salvarUsuario(Usuario usuario);

    boolean validarEmail(String email);

    Optional<Usuario> buscarUsuarioPorUuid(UUID uuid);

    void deletarUsuarioPorUuid(UUID uuid);
}
