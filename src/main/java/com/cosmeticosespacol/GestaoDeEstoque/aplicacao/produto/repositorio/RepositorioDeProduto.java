package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeProduto {

    Produto cadastrarProduto(Produto novoProduto);

    Optional<Produto> buscarProdutoPorUuid(UUID uuid);

    List<Produto> buscarProdutoPorNome(String nome);

    List<Produto> buscarProdutoPorCategoria(Categoria categoria);

    List<Produto> buscarTodosProdutos();

    Produto atualizarProduto(UUID uuid, Produto produtoAtualizado);

    void adicionarDescontoPorUuid(UUID uuid);

    void adicionarDescontoPorCategoria(Categoria categoria);

    void adicionarDescontroEmTodosProdutos();

    void deletarProdutoPorUuid(UUID uuid);
}
