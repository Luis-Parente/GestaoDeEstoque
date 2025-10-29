package com.cosmeticosespacol.GestaoDeEstoque.factory;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoFactory {

    public static Produto criarProduto() {
        return new Produto(UUID.randomUUID(), "Produto de Teste", Categoria.PERFUME, "Descrição do Produto de Teste",
                BigDecimal.valueOf(100.0), 10, BigDecimal.valueOf(10.0));
    }
}
