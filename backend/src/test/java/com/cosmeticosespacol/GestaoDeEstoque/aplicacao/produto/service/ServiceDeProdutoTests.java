package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.DadoRepetidoExcecao;
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

    @BeforeEach
    public void setup() {
        produtoTeste = ProdutoFactory.criarProduto();
        nomeInexistente = produtoTeste.getNome();
        nomeExistente = "Produto existente";

        Mockito.when(repositorio.validarNome(eq(nomeInexistente))).thenReturn(false);
        Mockito.when(repositorio.validarNome(eq(nomeExistente))).thenReturn(true);

        Mockito.when(repositorio.salvarProduto(any(Produto.class))).thenReturn(produtoTeste);
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
}
