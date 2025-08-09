package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio.RepositorioDeUsuario;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;

import java.util.UUID;

public class ServiceDeUsuario {

    private final RepositorioDeUsuario repositorio;

    public ServiceDeUsuario(RepositorioDeUsuario repositorioDeUsuario) {
        this.repositorio = repositorioDeUsuario;
    }

    public Usuario cadastrarNovoUsuario(Usuario novoUsuario) {
        return repositorio.cadastrarUsuario(novoUsuario);
    }

    public Usuario filtrarPorUuid(UUID uuid) {
        return repositorio.buscarUsuarioPorUuid(uuid)
                .orElseThrow(() -> new NaoEncontradoExcecao("Usuário não encontrado!"));
    }

    public Usuario atualizarUsuario(UUID uuid, Usuario usuarioAtualizado) {
        filtrarPorUuid(uuid);
        return repositorio.atualizarUsuario(uuid, usuarioAtualizado);
    }

    public void deletarUsuarioPorUuid(UUID uuid) {
        filtrarPorUuid(uuid);
        repositorio.deletarUsuarioPorUuid(uuid);
    }
}
