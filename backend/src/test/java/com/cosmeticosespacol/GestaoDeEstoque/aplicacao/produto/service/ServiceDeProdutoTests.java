package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.DadoRepetidoExcecao;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;
import com.cosmeticosespacol.GestaoDeEstoque.factory.ProdutoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class ServiceDeProdutoTests {

    @InjectMocks
    private ServiceDeProduto service;

    @Mock
    private RepositorioDeProduto repositorio;

    private Produto produtoTeste;

    private String nomeInexistente, nomeExistente;

    private UUID idExistente, idInexistente;

    @BeforeEach
    public void setup() {
        produtoTeste = ProdutoFactory.criarProduto();

        nomeInexistente = produtoTeste.getNome();
        nomeExistente = "Produto existente";

        idExistente = produtoTeste.getUuid();
        idInexistente = UUID.randomUUID();

        Mockito.when(repositorio.validarNome(eq(nomeInexistente))).thenReturn(false);
        Mockito.when(repositorio.validarNome(eq(nomeExistente))).thenReturn(true);

        Mockito.when(repositorio.salvarProduto(any(Produto.class))).thenReturn(produtoTeste);

        Mockito.when(repositorio.buscarProdutoPorUuid(eq(idExistente))).thenReturn(Optional.of(produtoTeste));
        Mockito.when(repositorio.buscarProdutoPorUuid(eq(idInexistente))).thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("Deve cadastrar novo produto se não existir produto com o mesmo nome")
    void cadastrarNovoProdutoSeNomeInexistente() {
        Produto resultado = service.cadastrarNovoProduto(produtoTeste);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getDesconto());
    }

    @Test
    @DisplayName("Cadastrar novo produto deve lançar exceção DadoRepetidoExcecao se existir produto com o mesmo nome")
    void cadastrarNovoProdutoDeveLancarDadoRepetidoExcecaoSeNomeExistente() {
        produtoTeste.setNome(nomeExistente);

        Assertions.assertThrows(DadoRepetidoExcecao.class, () -> {
            Produto resultado = service.cadastrarNovoProduto(produtoTeste);
        });
    }

    @Test
    @DisplayName("Deve retornar produto quando id existir")
    void filtrarPorUuidDeveRetornarProdutoQuandoIdExistir() {
        Produto resultado = service.filtrarPorUuid(idExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getDesconto());
    }

    @Test
    @DisplayName("Deve lançar NaoEncontradoExcecao quando id não existir")
    void filtrarPorUuidDeveLancarNaoEncontradoExcecaoQuandoIdNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            Produto resultado = service.filtrarPorUuid(idInexistente);
        });
    }
}
