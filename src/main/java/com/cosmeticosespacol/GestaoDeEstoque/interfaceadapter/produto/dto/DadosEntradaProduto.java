package com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto;

public record DadosEntradaProduto(String nome, String categoria, String descricao, Double preco,
                                  Integer quantidade, Double desconto) {
}
