package com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto;

public record DadosRetornoProduto(String uuid, String nome, String categoria, String descricao, Double preco,
                                  Integer quantidade, Double desconto) {
}
