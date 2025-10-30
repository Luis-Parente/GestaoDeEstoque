package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RepositorioDeProdutoTests {

    @Mock
    private RepositorioDeProduto repositorio;

    private Produto produtoTeste;

    private UUID idValido, idInvalido;

    private String nomeValido, nomeInvalido;

    private String nomeExistente, nomeInexistente;

    private Categoria categoriaValida, categoriaInvalida;

    @BeforeEach
    void setUp() throws Exception {
        produtoTeste = ProdutoFactory.criarProduto();

        idValido = produtoTeste.getUuid();
        idInvalido = UUID.randomUUID();

        nomeValido = produtoTeste.getNome();
        nomeInvalido = "Nome inválido";

        nomeExistente = produtoTeste.getNome();
        nomeInexistente = "Nome não existente";

        categoriaValida = produtoTeste.getCategoria();
        categoriaInvalida = Categoria.KIT;
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
    @DisplayName("Deve retornar true quando o nome existe")
    void deveRetornarTrueQuandoNomeExiste() {
        Mockito.when(repositorio.validarNome(eq(nomeExistente))).thenReturn(true);

        boolean resultado = repositorio.validarNome(nomeExistente);

        Assertions.assertTrue(resultado);
        Mockito.verify(repositorio, times(1)).validarNome(eq(nomeExistente));
    }

    @Test
    @DisplayName("Deve retornar false quando o nome não existe")
    void deveRetornarFalseQuandoNomeNaoExiste() {
        Mockito.when(repositorio.validarNome(eq(nomeInexistente))).thenReturn(false);

        boolean resultado = repositorio.validarNome(nomeInexistente);

        Assertions.assertFalse(resultado);
        Mockito.verify(repositorio, times(1)).validarNome(eq(nomeInexistente));
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

    @Test
    @DisplayName("Deve retornar lista de produtos quando o nome for válido")
    void deveRetornarListaDeProdutosQuandoNomeForValido() {
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeValido), isNull())).thenReturn(List.of(produtoTeste));

        List<Produto> resultado = repositorio.buscarProdutosFiltrados(nomeValido, null);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(eq(nomeValido), isNull());
    }

    @Test
    @DisplayName("Deve retornar lista de produtos vazia quando o nome for inválido")
    void deveRetornarListaDeProdutosVaziaQuandoNomeForInvalido() {
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeInvalido), isNull())).thenReturn(List.of());

        List<Produto> resultado = repositorio.buscarProdutosFiltrados(nomeInvalido, null);

        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(eq(nomeInvalido), isNull());
    }

    @Test
    @DisplayName("Deve retornar lista de produtos quando a categoria for válida")
    void deveRetornarListaDeProdutosQuandoCategoriaForValido() {
        Mockito.when(repositorio.buscarProdutosFiltrados(anyString(), eq(categoriaValida))).thenReturn(List.of(produtoTeste));

        List<Produto> resultado = repositorio.buscarProdutosFiltrados("", categoriaValida);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(anyString(), eq(categoriaValida));
    }

    @Test
    @DisplayName("Deve retornar lista de produtos vazia quando a categoria for inválida")
    void deveRetornarListaDeProdutosVaziaQuandoCategoriaForInvalido() {
        Mockito.when(repositorio.buscarProdutosFiltrados(anyString(), eq(categoriaInvalida))).thenReturn(List.of());

        List<Produto> resultado = repositorio.buscarProdutosFiltrados("", categoriaInvalida);

        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(anyString(), eq(categoriaInvalida));
    }

    @Test
    @DisplayName("Deve retornar lista de produtos quando nome e categoria forem válidos")
    void deveRetornarListaDeProdutosQuandoNomeECategoriaForemValidos() {
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeValido), eq(categoriaValida))).thenReturn(List.of(produtoTeste));

        List<Produto> resultado = repositorio.buscarProdutosFiltrados(nomeValido, categoriaValida);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(eq(nomeValido), eq(categoriaValida));
    }

    @Test
    @DisplayName("Deve retornar lista de produtos vazia quando nome e categoria forem inválidos")
    void deveRetornarListaDeProdutosVaziaQuandoNomeECategoriaForemInvalidos() {
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeInvalido), eq(categoriaInvalida))).thenReturn(List.of());

        List<Produto> resultado = repositorio.buscarProdutosFiltrados(nomeInvalido, categoriaInvalida);

        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(repositorio, times(1)).buscarProdutosFiltrados(eq(nomeInvalido), eq(categoriaInvalida));
    }

    @Test
    @DisplayName("Deve deletar produto com sucesso")
    void deveDeletarProdutoComSucesso() {
        Mockito.doNothing().when(repositorio).deletarProdutoPorUuid(idValido);

        repositorio.deletarProdutoPorUuid(idValido);

        Mockito.verify(repositorio, times(1)).deletarProdutoPorUuid(idValido);
    }
}
