package com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto;

import java.math.BigDecimal;

public record EntradaProdutoDTO(String nome, String categoria, String descricao, BigDecimal preco, Integer quantidade,
                                BigDecimal desconto) {
}
