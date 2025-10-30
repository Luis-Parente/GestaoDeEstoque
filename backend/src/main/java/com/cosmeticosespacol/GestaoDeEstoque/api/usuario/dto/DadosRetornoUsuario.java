package com.cosmeticosespacol.GestaoDeEstoque.api.usuario.dto;

import java.util.UUID;

public record DadosRetornoUsuario(UUID uuid, String nome, String email, String nivelDeAcesso) {
}
