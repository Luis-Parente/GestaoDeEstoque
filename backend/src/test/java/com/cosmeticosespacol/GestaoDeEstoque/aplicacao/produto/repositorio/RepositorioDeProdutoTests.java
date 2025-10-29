package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.factory.ProdutoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RepositorioDeProdutoTests {

    @Mock
    private RepositorioDeProduto repositorio;

    private Produto produtoTeste;

    private UUID idValido, idInvalido;

    private String nomeValido, nomeInvalido;

    @BeforeEach
    void setUp() throws Exception {
        produtoTeste = ProdutoFactory.criarProduto();

        idValido = produtoTeste.getUuid();
        idInvalido = UUID.randomUUID();

        nomeValido = produtoTeste.getNome();
        nomeInvalido = "Nome inválido";
    }

    @Test
    @DisplayName("Deve salvar produto com sucesso")
    void deveSalvarProdutoComSucesso() {
        Mockito.when(repositorio.salvarProduto(any(Produto.class))).thenReturn(produtoTeste);

        Produto produtoSalvo = repositorio.salvarProduto(produtoTeste);

        Assertions.assertNotNull(produtoSalvo);
        Assertions.assertEquals(produtoTeste.getUuid(), produtoSalvo.getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), produtoSalvo.getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), produtoSalvo.getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), produtoSalvo.getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), produtoSalvo.getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), produtoSalvo.getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), produtoSalvo.getDesconto());
        Mockito.verify(repositorio, times(1)).salvarProduto(produtoTeste);
    }

    @Test
    @DisplayName("Deve retornar produto quando o id for válido")
    void deveRetornarProdutoQuandoIdForValido() {
        Mockito.when(repositorio.buscarProdutoPorUuid(idValido)).thenReturn(Optional.of(produtoTeste));

        Optional<Produto> resultado = repositorio.buscarProdutoPorUuid(idValido);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.get().getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.get().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.get().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.get().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.get().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.get().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.get().getDesconto());
        Mockito.verify(repositorio, times(1)).buscarProdutoPorUuid(idValido);
    }

    @Test
    @DisplayName("Deve retornar Optional vazio quando o id for inválido")
    void deveRetornarOptionalVazioQuandoIdForInvalido() {
        Mockito.when(repositorio.buscarProdutoPorUuid(idInvalido)).thenReturn(Optional.empty());

        Optional<Produto> resultado = repositorio.buscarProdutoPorUuid(idInvalido);

        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(repositorio, times(1)).buscarProdutoPorUuid(idInvalido);
    }
}
