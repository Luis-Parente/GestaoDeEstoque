package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeProduto {

    Produto cadastrarProduto(Produto novoProduto);

    Optional<Produto> buscarProdutoPorUuid(UUID uuid);

    List<Produto> buscarProdutoPorNome(String nome);

    List<Produto> buscarProdutoPorCategoria(Categoria categoria);

    List<Produto> buscarTodosProdutos();

    Produto atualizarProduto(Produto produtoAtualizado);

    void adicionarQuantidadeDeProduto(Produto produtoComQuantidadeAtualizada);

    void removerQuantidadeDeProduto(Produto produtoComQuantidadeAtualizada);

    void adicionarDescontoPorUuid(Produto produtoComDescontoAtualizada);

    void adicionarDescontoPorCategoria(List<Produto> listaDeProdutosComDescontoAtualizado);

    void adicionarDescontroEmTodosProdutos(List<Produto> listaDeProdutosComDescontoAtualizado);

    void deletarProdutoPorUuid(UUID uuid);
}
