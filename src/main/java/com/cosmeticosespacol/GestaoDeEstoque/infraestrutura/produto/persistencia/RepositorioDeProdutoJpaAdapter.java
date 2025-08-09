package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.persistencia;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.entidade.ProdutoEntidade;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.mapper.ProdutoJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepositorioDeProdutoJpaAdapter implements RepositorioDeProduto {

    private final RepositorioDeProdutoJpa repositorio;

    @Autowired
    public RepositorioDeProdutoJpaAdapter(RepositorioDeProdutoJpa repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Produto cadastrarProduto(Produto novoProduto) {
        ProdutoEntidade entidade = ProdutoJpaMapper.paraEntidade(novoProduto);
        return ProdutoJpaMapper.paraDominio(repositorio.save(entidade));
    }

    @Override
    public Optional<Produto> buscarProdutoPorUuid(UUID uuid) {
        return repositorio.findById(uuid).map(ProdutoJpaMapper::paraDominio);
    }

    @Override
    public List<Produto> buscarProdutoPorNome(String nome) {
        return repositorio.findByNome(nome).stream().map(ProdutoJpaMapper::paraDominio).toList();
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(Categoria categoria) {
        return repositorio.findByCategoria(categoria).stream().map(ProdutoJpaMapper::paraDominio).toList();

    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        return repositorio.findAll().stream().map(ProdutoJpaMapper::paraDominio).toList();
    }

    @Override
    public Produto atualizarProduto(UUID uuid, Produto produtoAtualizado) {
        ProdutoEntidade entidadeAtualizada = ProdutoJpaMapper.paraEntidade(produtoAtualizado);
        entidadeAtualizada.setUuid(uuid);
        return ProdutoJpaMapper.paraDominio(repositorio.save(entidadeAtualizada));
    }

    @Override
    public void adicionarDescontoPorUuid(UUID uuid, Double desconto) {
        Optional<Produto> dominio = buscarProdutoPorUuid(uuid);
        dominio.get().setDesconto(desconto);
        repositorio.save(ProdutoJpaMapper.paraEntidade(dominio.get()));
    }

    @Override
    public void adicionarDescontoPorCategoria(Categoria categoria, Double desconto) {
        List<Produto> resultado = buscarProdutoPorCategoria(categoria);
        for (Produto produto : resultado) {
            produto.setDesconto(desconto);
            repositorio.save(ProdutoJpaMapper.paraEntidade(produto));
        }
    }

    @Override
    public void adicionarDescontroEmTodosProdutos(Double desconto) {
        List<Produto> resultado = buscarTodosProdutos();
        for (Produto produto : resultado) {
            produto.setDesconto(desconto);
            repositorio.save(ProdutoJpaMapper.paraEntidade(produto));
        }
    }

    @Override
    public void deletarProdutoPorUuid(UUID uuid) {
        repositorio.deleteById(uuid);
    }
}
