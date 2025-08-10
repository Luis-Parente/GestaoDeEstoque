package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.persistencia;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.entidade.ProdutoEntidade;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.mapper.ProdutoJpaMapper;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public Produto cadastrarProduto(Produto novoProduto) {
        ProdutoEntidade entidade = ProdutoJpaMapper.paraEntidade(novoProduto);
        return ProdutoJpaMapper.paraDominio(repositorio.save(entidade));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Produto> buscarProdutoPorUuid(UUID uuid) {
        return repositorio.findById(uuid).map(ProdutoJpaMapper::paraDominio);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutoPorNome(String nome) {
        return repositorio.findByNome(nome).stream().map(ProdutoJpaMapper::paraDominio).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarProdutoPorCategoria(Categoria categoria) {
        return repositorio.findByCategoria(categoria).stream().map(ProdutoJpaMapper::paraDominio).toList();

    }

    @Transactional(readOnly = true)
    @Override
    public List<Produto> buscarTodosProdutos() {
        return repositorio.findAll().stream().map(ProdutoJpaMapper::paraDominio).toList();
    }

    @Transactional
    @Override
    public Produto atualizarProduto(Produto produtoAtualizado) {
        ProdutoEntidade entidadeAtualizada = ProdutoJpaMapper.paraEntidade(produtoAtualizado);
        return ProdutoJpaMapper.paraDominio(repositorio.save(entidadeAtualizada));
    }

    @Transactional
    @Override
    public void adicionarQuantidadeDeProduto(Produto produtoAtualizado) {
        repositorio.save(ProdutoJpaMapper.paraEntidade(produtoAtualizado));
    }

    @Transactional
    @Override
    public void removerQuantidadeDeProduto(Produto produtoAtualizado) {
        repositorio.save(ProdutoJpaMapper.paraEntidade(produtoAtualizado));
    }

    @Transactional
    @Override
    public void adicionarDescontoPorUuid(Produto produtoAtualizado) {
        repositorio.save(ProdutoJpaMapper.paraEntidade(produtoAtualizado));
    }

    @Transactional
    @Override
    public void adicionarDescontoPorCategoria(List<Produto> produtosAtualizado) {
        for (Produto produto : produtosAtualizado) {
            repositorio.save(ProdutoJpaMapper.paraEntidade(produto));
        }
    }

    @Transactional
    @Override
    public void adicionarDescontroEmTodosProdutos(List<Produto> produtosAtualizado) {
        for (Produto produto : produtosAtualizado) {
            repositorio.save(ProdutoJpaMapper.paraEntidade(produto));
        }
    }

    @Transactional
    @Override
    public void deletarProdutoPorUuid(UUID uuid) {
        repositorio.deleteById(uuid);
    }
}
