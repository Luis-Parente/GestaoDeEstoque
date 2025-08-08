package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceDeProduto {

    private final RepositorioDeProduto repositorio;

    @Autowired
    public ServiceDeProduto(RepositorioDeProduto repositorioDeProduto) {
        this.repositorio = repositorioDeProduto;
    }

    public Produto cadastrarNovoProduto(Produto novoProduto) {
        return repositorio.cadastrarProduto(novoProduto);
    }

    public Produto filtrarPorUuid(UUID uuid) {
        return repositorio.buscarProdutoPorUuid(uuid)
                .orElseThrow(() -> new NaoEncontradoExcecao("Produto não encontrado!"));
    }

    public List<Produto> filtrarPorNome(String nome) {
        List<Produto> resultado = repositorio.buscarProdutoPorNome(nome);
        if (resultado.isEmpty()) {
            throw new NaoEncontradoExcecao("Nenhum produto com esse nome encontrado!");
        }
        return resultado;
    }

    public List<Produto> filtrarPorCategoria(Categoria categoria) {
        List<Produto> resultado = repositorio.buscarProdutoPorCategoria(categoria);
        if (resultado.isEmpty()) {
            throw new NaoEncontradoExcecao("Nenhum produto dessa categoria!");
        }
        return resultado;
    }

    public List<Produto> retornarTodosProdutos() {
        List<Produto> resultado = repositorio.buscarTodosProdutos();
        if (resultado.isEmpty()) {
            throw new NaoEncontradoExcecao("Nenhum produto encontrado!");
        }
        return resultado;
    }

    public Produto atualizarProduto(UUID uuid, Produto produtoAtualizado) {
        filtrarPorUuid(uuid);
        return repositorio.atualizarProduto(uuid, produtoAtualizado);
    }

    public void adicionarDescontoPorUuid(UUID uuid) {
        repositorio.adicionarDescontoPorUuid(uuid);
    }

    public void adicionarDescontoPorCategoria(Categoria categoria) {
        repositorio.adicionarDescontoPorCategoria(categoria);
    }

    public void adicionarDescontroEmTodosProdutos() {
        repositorio.adicionarDescontroEmTodosProdutos();
    }

    public void deletarProdutoPorUuid(UUID uuid) {
        filtrarPorUuid(uuid);
        repositorio.deletarProdutoPorUuid(uuid);
    }
}
