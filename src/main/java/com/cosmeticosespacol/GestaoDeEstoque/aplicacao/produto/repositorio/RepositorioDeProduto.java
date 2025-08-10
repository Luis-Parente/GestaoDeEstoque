package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeProduto {

    Produto salvarProduto(Produto produto);

    Optional<Produto> buscarProdutoPorUuid(UUID uuid);

    boolean validarNome(String nome);

    List<Produto> buscarProdutoPorNome(String nome);

    List<Produto> buscarProdutoPorCategoria(Categoria categoria);

    List<Produto> buscarTodosProdutos();

    void adicionarQuantidadeDeProduto(Produto produtoComQuantidadeAtualizada);

    void removerQuantidadeDeProduto(Produto produtoComQuantidadeAtualizada);

    void adicionarDescontoPorUuid(Produto produtoComDescontoAtualizada);

    void deletarProdutoPorUuid(UUID uuid);
}
