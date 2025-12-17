package com.cosmeticosespacol.GestaoDeEstoque.api.seguranca.dto;

import java.util.Date;

public record RetornoTokenValidado(String email, Date expiracao) {
}
