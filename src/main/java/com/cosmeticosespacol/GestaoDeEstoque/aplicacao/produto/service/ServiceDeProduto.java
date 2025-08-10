package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.DadoRepetidoExcecao;
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
        if (repositorio.validarNome(novoProduto.getNome())) {
            throw new DadoRepetidoExcecao("Já existe produto com esse nome cadastrado!");
        }
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
        dominio.atualizarDesconto(produtoAtualizado.getDesconto());
        return repositorio.atualizarProduto(dominio);
    }

    public String aumentarQuantidadeDeProduto(UUID uuid, Integer quantidade) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.adicionarEstoque(quantidade);
        repositorio.adicionarQuantidadeDeProduto(dominio);
        return "Estoque atualizado com sucesso";
    }

    public String diminuirQuantidadeDeProduto(UUID uuid, Integer quantidade) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.removerEstoque(quantidade);
        repositorio.removerQuantidadeDeProduto(dominio);
        return "Estoque atualizado com sucesso";
    }

    public String adicionarDescontoPorUuid(UUID uuid, BigDecimal desconto) {
        Produto dominio = filtrarPorUuid(uuid);
        dominio.atualizarDesconto(desconto);
        repositorio.adicionarDescontoPorUuid(dominio);
        return "Desconto atualizado com sucesso";
    }

    public String adicionarDescontoPorCategoria(Categoria categoria, BigDecimal desconto) {
        List<Produto> resultado = filtrarPorCategoria(categoria);
        for (Produto produto : resultado) {
            produto.atualizarDesconto(desconto);
            repositorio.adicionarDescontoPorUuid(produto);
        }
        return "Desconto atualizado com sucesso";
    }

    public String adicionarDescontroEmTodosProdutos(BigDecimal desconto) {
        List<Produto> resultado = retornarTodosProdutos();
        for (Produto produto : resultado) {
            produto.atualizarDesconto(desconto);
            repositorio.adicionarDescontoPorUuid(produto);
        }
        return "Desconto atualizado com sucesso";
    }

    public String deletarProdutoPorUuid(UUID uuid) {
        filtrarPorUuid(uuid);
        repositorio.deletarProdutoPorUuid(uuid);
        return "Produto deletado com sucesso";
    }
}
