package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Produto dominio = filtrarPorUuid(uuid);
        dominio.setNome(produtoAtualizado.getNome());
        dominio.setCategoria(produtoAtualizado.getCategoria());
        dominio.setDescricao(produtoAtualizado.getDescricao());
        dominio.setPreco(produtoAtualizado.getPreco());
        dominio.setDesconto(produtoAtualizado.getDesconto());
        return repositorio.atualizarProduto(dominio);
    }

    public void aumentarQuantidadeDeProduto(UUID uuid, Integer quantidade) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.adicionarEstoque(quantidade);
        repositorio.adicionarQuantidadeDeProduto(dominio);
    }

    public void removerQuantidadeDeProduto(UUID uuid, Integer quantidade) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.removerEstoque(quantidade);
        repositorio.removerQuantidadeDeProduto(dominio);
    }

    public void adicionarDescontoPorUuid(UUID uuid, BigDecimal desconto) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.setDesconto(desconto);
        repositorio.adicionarDescontoPorUuid(dominio);
    }

    public void adicionarDescontoPorCategoria(Categoria categoria, BigDecimal desconto) {
        List<Produto> resultado = filtrarPorCategoria(categoria);
        for(Produto produto : resultado){
            produto.setDesconto(desconto);
        }
        repositorio.adicionarDescontoPorCategoria(resultado);
    }

    public void adicionarDescontroEmTodosProdutos(BigDecimal desconto) {
        List<Produto> resultado = retornarTodosProdutos();
        for(Produto produto : resultado){
            produto.setDesconto(desconto);
        }
        repositorio.adicionarDescontroEmTodosProdutos(resultado);
    }

    public void deletarProdutoPorUuid(UUID uuid) {
        filtrarPorUuid(uuid);
        repositorio.deletarProdutoPorUuid(uuid);
    }
}
