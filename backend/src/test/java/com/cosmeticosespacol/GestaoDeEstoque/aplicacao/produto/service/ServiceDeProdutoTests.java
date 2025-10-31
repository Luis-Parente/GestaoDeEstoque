package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.repositorio.RepositorioDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
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

import java.math.BigDecimal;
import java.util.List;
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

    private Categoria categoriaExistente, categoriaInexistente;

    private String mensagemSucesso;

    private Integer quantidadeValida, quantidadeInvalida;

    private BigDecimal descontoValido, descontoInvalido;

    @BeforeEach
    public void setup() {
        produtoTeste = ProdutoFactory.criarProduto();

        nomeInexistente = produtoTeste.getNome();
        nomeExistente = "Produto existente";

        idExistente = produtoTeste.getUuid();
        idInexistente = UUID.randomUUID();

        categoriaExistente = produtoTeste.getCategoria();
        categoriaInexistente = Categoria.KIT;

        quantidadeValida = 5;
        quantidadeInvalida = -1;

        descontoValido = BigDecimal.TEN;
        descontoInvalido = BigDecimal.valueOf(-10.0);

        Mockito.when(repositorio.validarNome(eq(nomeInexistente))).thenReturn(false);
        Mockito.when(repositorio.validarNome(eq(nomeExistente))).thenReturn(true);

        Mockito.when(repositorio.salvarProduto(any(Produto.class))).thenReturn(produtoTeste);

        Mockito.when(repositorio.buscarProdutoPorUuid(eq(idExistente))).thenReturn(Optional.of(produtoTeste));
        Mockito.when(repositorio.buscarProdutoPorUuid(eq(idInexistente))).thenReturn(Optional.empty());

        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeExistente), eq(categoriaExistente))).thenReturn(List.of(produtoTeste));
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeExistente), eq(null))).thenReturn(List.of(produtoTeste));
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(null), eq(categoriaExistente))).thenReturn(List.of(produtoTeste));
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(null), eq(null))).thenReturn(List.of(produtoTeste));

        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeInexistente), eq(categoriaInexistente))).thenReturn(List.of());
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(nomeInexistente), eq(null))).thenReturn(List.of());
        Mockito.when(repositorio.buscarProdutosFiltrados(eq(null), eq(categoriaInexistente))).thenReturn(List.of());
    }

    @Test
    @DisplayName("cadastrarNovoProduto deve cadastrar novo produto se não existir produto com o mesmo nome")
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
    @DisplayName("cadastrarNovoProduto deve lançar exceção DadoRepetidoExcecao se existir produto com o mesmo nome")
    void cadastrarNovoProdutoDeveLancarDadoRepetidoExcecaoSeNomeExistente() {
        produtoTeste.setNome(nomeExistente);

        Assertions.assertThrows(DadoRepetidoExcecao.class, () -> {
            Produto resultado = service.cadastrarNovoProduto(produtoTeste);
        });
    }

    @Test
    @DisplayName("filtrarPorUuid deve retornar produto quando id existir")
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
    @DisplayName("filtrarPorUuid deve lançar NaoEncontradoExcecao quando id não existir")
    void filtrarPorUuidDeveLancarNaoEncontradoExcecaoQuandoIdNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            Produto resultado = service.filtrarPorUuid(idInexistente);
        });
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve retornar lista de produtos quando nome e categoria forem válidos")
    void retornarProdutosFiltradosDeveRetornarListaDeProdutosQuandoNomeECategoriaValidos() {
        List<Produto> resultado = service.retornarProdutosFiltrados(nomeExistente, categoriaExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.getFirst().getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve retornar lista de produtos quando nome for válido")
    void retornarProdutosFiltradosDeveRetornarListaDeProdutosQuandoNomeValido() {
        List<Produto> resultado = service.retornarProdutosFiltrados(nomeExistente, null);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.getFirst().getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve retornar lista de produtos quando categoria for válida")
    void retornarProdutosFiltradosDeveRetornarListaDeProdutosQuandoCategoriaValida() {
        List<Produto> resultado = service.retornarProdutosFiltrados(null, categoriaExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produtoTeste.getUuid(), resultado.getFirst().getUuid());
        Assertions.assertEquals(produtoTeste.getNome(), resultado.getFirst().getNome());
        Assertions.assertEquals(produtoTeste.getDescricao(), resultado.getFirst().getDescricao());
        Assertions.assertEquals(produtoTeste.getPreco(), resultado.getFirst().getPreco());
        Assertions.assertEquals(produtoTeste.getPrecoComDesconto(), resultado.getFirst().getPrecoComDesconto());
        Assertions.assertEquals(produtoTeste.getQuantidade(), resultado.getFirst().getQuantidade());
        Assertions.assertEquals(produtoTeste.getDesconto(), resultado.getFirst().getDesconto());
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve lançar NaoEncontradoExcecao quando nome e categoria inválidos")
    void retornarProdutosFiltradosDeveLancarNaoEncontradoExcecaoQuandoNomeECategoriaInvalidos() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            service.retornarProdutosFiltrados(nomeInexistente, categoriaInexistente);
        });
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve lançar NaoEncontradoExcecao quando nome inválido")
    void retornarProdutosFiltradosDeveLancarNaoEncontradoExcecaoQuandoNomeInvalido() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            service.retornarProdutosFiltrados(nomeInexistente, null);
        });
    }

    @Test
    @DisplayName("retornarProdutosFiltrados deve lançar NaoEncontradoExcecao quando categoria inválida")
    void retornarProdutosFiltradosDeveLancarNaoEncontradoExcecaoQuandoCategoriaInvalida() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            service.retornarProdutosFiltrados(null, categoriaInexistente);
        });
    }

    @Test
    @DisplayName("atualizarProduto deve retornar produto atualizado quando id existir")
    void atualizarProdutoDeveRetornarProdutoQuandoIdExistir() {
        Produto resultado = service.atualizarProduto(idExistente, produtoTeste);

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
    @DisplayName("atualizarProduto deve lançar NaoEncontradoExcecao quando id for inexistente")
    void atualizarProdutoDeveLancarNaoEncontradoExcecaoQuandoIdInexistente() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            service.atualizarProduto(idInexistente, produtoTeste);
        });
    }

    @Test
    @DisplayName("aumentarQuantidadeDeProduto deve retornar mensagem de sucesso quando id existir e quantidade for válida")
    void aumentarQuantidadeDeProdutoDeveRetornarMensagemSucessoQuandoIdExistirEQuantidadeValida() {
        mensagemSucesso = "Estoque atualizado com sucesso";

        String resultado = service.aumentarQuantidadeDeProduto(idExistente, quantidadeValida);

        Assertions.assertEquals(mensagemSucesso, resultado);
    }

    @Test
    @DisplayName("aumentarQuantidadeDeProduto deve lançar IllegalArgumentException quando quantidade for menor que zero")
    void aumentarQuantidadeDeProdutoDeveLancarIllegalArgumentExceptionQuandoQuantidadeMenorQueZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.aumentarQuantidadeDeProduto(idExistente, quantidadeInvalida);
        });
    }

    @Test
    @DisplayName("aumentarQuantidadeDeProduto deve lançar IllegalArgumentException quando quantidade for null")
    void aumentarQuantidadeDeProdutoDeveLancarIllegalArgumentExceptionQuandoQuantidadeNull() {
        quantidadeInvalida = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.aumentarQuantidadeDeProduto(idExistente, quantidadeInvalida);
        });
    }

    @Test
    @DisplayName("aumentarQuantidadeDeProduto deve lançar NaoEncontradoExcecao quando id for inexistente")
    void aumentarQuantidadeDeProdutoDeveLancarNaoEncontradoExcecaoQuandoIdInexistente() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            String resultado = service.aumentarQuantidadeDeProduto(idInexistente, quantidadeValida);
        });
    }

    @Test
    @DisplayName("diminuirQuantidadeDeProduto deve retornar mensagem de sucesso quando id existir e quantidade for válida")
    void diminuirQuantidadeDeProdutoDeveRetornarMensagemSucessoQuandoIdExistirEQuantidadeValida() {
        mensagemSucesso = "Estoque atualizado com sucesso";

        String resultado = service.diminuirQuantidadeDeProduto(idExistente, quantidadeValida);

        Assertions.assertEquals(mensagemSucesso, resultado);
    }

    @Test
    @DisplayName("diminuirQuantidadeDeProduto deve lançar IllegalArgumentException quando quantidade for menor que zero")
    void diminuirQuantidadeDeProdutoDeveLancarIllegalArgumentExceptionQuandoQuantidadeMenorQueZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.diminuirQuantidadeDeProduto(idExistente, quantidadeInvalida);
        });
    }

    @Test
    @DisplayName("diminuirQuantidadeDeProduto deve lançar IllegalArgumentException quando quantidade for maior que o estoque")
    void diminuirQuantidadeDeProdutoDeveLancarIllegalArgumentExceptionQuandoQuantidadeMaiorQueEstoque() {
        quantidadeInvalida = produtoTeste.getQuantidade() + 1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.diminuirQuantidadeDeProduto(idExistente, quantidadeInvalida);
        });
    }

    @Test
    @DisplayName("diminuirQuantidadeDeProduto deve lançar IllegalArgumentException quando quantidade for null")
    void diminuirQuantidadeDeProdutoDeveLancarIllegalArgumentExceptionQuandoQuantidadeNull() {
        quantidadeInvalida = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.diminuirQuantidadeDeProduto(idExistente, quantidadeInvalida);
        });
    }

    @Test
    @DisplayName("diminuirQuantidadeDeProduto deve lançar NaoEncontradoExcecao quando id for inexistente")
    void diminuirQuantidadeDeProdutoDeveLancarNaoEncontradoExcecaoQuandoIdInexistente() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            String resultado = service.diminuirQuantidadeDeProduto(idInexistente, quantidadeValida);
        });
    }

    @Test
    @DisplayName("adicionarDescontoPorUuid deve retornar mensagem de sucesso quando id existir e desconto for válido")
    void adicionarDescontoPorUuidDeveRetornarMensagemSucessoQuandoIdExistirEDescontoValido() {
        mensagemSucesso = "Desconto atualizado com sucesso";

        String resultado = service.adicionarDescontoPorUuid(idExistente, descontoValido);

        Assertions.assertEquals(mensagemSucesso, resultado);
    }

    @Test
    @DisplayName("adicionarDescontoPorUuid deve lançar IllegalArgumentException quando desconto for menor que zero")
    void adicionarDescontoPorUuidDeveLancarIllegalArgumentExceptionQuandoDescontoMenorQueZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.adicionarDescontoPorUuid(idExistente, descontoInvalido);
        });
    }

    @Test
    @DisplayName("adicionarDescontoPorUuid deve lançar IllegalArgumentException quando quantidade for null")
    void adicionarDescontoPorUuidDeveLancarIllegalArgumentExceptionQuandoQuantidadeNull() {
        descontoInvalido = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String resultado = service.adicionarDescontoPorUuid(idExistente, descontoInvalido);
        });
    }
}
