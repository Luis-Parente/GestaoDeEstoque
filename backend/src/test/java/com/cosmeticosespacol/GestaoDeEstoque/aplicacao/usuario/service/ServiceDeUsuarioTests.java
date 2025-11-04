package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio.RepositorioDeUsuario;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.DadoRepetidoExcecao;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;
import com.cosmeticosespacol.GestaoDeEstoque.factory.UsuarioFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class ServiceDeUsuarioTests {

    @InjectMocks
    private ServiceDeUsuario service;

    @Mock
    private RepositorioDeUsuario repositorio;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Usuario usuarioTeste;

    private UUID idExistente, idInexistente;

    private String emailExistente, emailInexistente;

    private String senhaCriptografada;

    private String mensagemDeSucesso;

    @BeforeEach
    void setUp() {
        usuarioTeste = UsuarioFactory.criarUsuario();

        idExistente = usuarioTeste.getUuid();
        idInexistente = UUID.randomUUID();

        emailInexistente = usuarioTeste.getEmail();
        emailExistente = "emailtesteexistente@gmail.com";

        senhaCriptografada = "Senha criptografada";

        mensagemDeSucesso = "Usuário deletado com sucesso!";

        Mockito.when(repositorio.salvarUsuario(any(Usuario.class))).thenReturn(usuarioTeste);

        Mockito.when(repositorio.validarEmail(eq(emailInexistente))).thenReturn(false);
        Mockito.when(repositorio.validarEmail(eq(emailExistente))).thenReturn(true);

        Mockito.when(passwordEncoder.encode(any(String.class))).thenReturn(senhaCriptografada);

        Mockito.when(repositorio.buscarUsuarioPorUuid(eq(idExistente))).thenReturn(Optional.of(usuarioTeste));
        Mockito.when(repositorio.buscarUsuarioPorUuid(eq(idInexistente))).thenReturn(Optional.empty());

        Mockito.when(repositorio.buscarUsuarioPorEmail(eq(emailExistente))).thenReturn(Optional.of(usuarioTeste));
        Mockito.when(repositorio.buscarUsuarioPorEmail(eq(emailInexistente))).thenReturn(Optional.empty());

        Mockito.doNothing().when(repositorio).deletarUsuarioPorUuid(eq(idExistente));
    }

    @DisplayName("cadastrarNovoUsuario deve retornar usuário quando e-mail não existir")
    @Test
    void cadastrarNovoUsuarioDeveRetornarUsuarioQuandoEmailNaoExistir() {
        Usuario resultado = service.cadastrarNovoUsuario(usuarioTeste);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuarioTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(usuarioTeste.getEmail(), resultado.getEmail());
        Assertions.assertEquals(senhaCriptografada, resultado.getSenha());
        Assertions.assertEquals(usuarioTeste.getNivelDeAcesso(), resultado.getNivelDeAcesso());
    }

    @DisplayName("cadastrarNovoUsuario deve lançar DadoRepetidoExcecao quando e-mail existir")
    @Test
    void cadastrarNovoUsuarioDeveLancarDadoRepetidoExcecaoQuandoEmailExistir() {
        usuarioTeste.setEmail(emailExistente);

        Assertions.assertThrows(DadoRepetidoExcecao.class, () -> {
            Usuario resultado = service.cadastrarNovoUsuario(usuarioTeste);
        });
    }

    @DisplayName("filtrarPorUuid deve retornar usuário quando o id existir")
    @Test
    void filtrarPorUuidDeveRetornarUsuarioQuandoIdExistir() {
        Usuario resultado = service.filtrarPorUuid(idExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuarioTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(usuarioTeste.getEmail(), resultado.getEmail());
        Assertions.assertEquals(usuarioTeste.getSenha(), resultado.getSenha());
        Assertions.assertEquals(usuarioTeste.getNivelDeAcesso(), resultado.getNivelDeAcesso());
    }

    @DisplayName("filtrarPorUuid deve lançar NaoEncontradoExcecao quando id não existir")
    @Test
    void filtrarPorUuidDeveLancarNaoEncontradoExcecaoQuandoIdNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            Usuario resultado = service.filtrarPorUuid(idInexistente);
        });
    }

    @DisplayName("filtrarPorEmail deve retornar usuário quando o e-mail existir")
    @Test
    void filtrarPorEmailDeveRetornarUsuarioQuandoEmailExistir() {
        Usuario resultado = service.filtrarPorEmail(emailExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuarioTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(usuarioTeste.getEmail(), resultado.getEmail());
        Assertions.assertEquals(usuarioTeste.getSenha(), resultado.getSenha());
        Assertions.assertEquals(usuarioTeste.getNivelDeAcesso(), resultado.getNivelDeAcesso());
    }

    @DisplayName("filtrarPorEmail deve lançar NaoEncontradoExcecao quando e-mail não existir")
    @Test
    void filtrarPorEmailDeveLancarNaoEncontradoExcecaoQuandoEmailNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            Usuario resultado = service.filtrarPorEmail(emailInexistente);
        });
    }

    @DisplayName("atualizarUsuario deve retornar usuário quando id existir")
    @Test
    void atualizarUsuarioDeveRetornarUsuarioQuandoIdExistir() {
        Usuario resultado = service.atualizarUsuario(idExistente, usuarioTeste);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuarioTeste.getNome(), resultado.getNome());
        Assertions.assertEquals(usuarioTeste.getEmail(), resultado.getEmail());
        Assertions.assertEquals(senhaCriptografada, resultado.getSenha());
        Assertions.assertEquals(usuarioTeste.getNivelDeAcesso(), resultado.getNivelDeAcesso());
    }

    @DisplayName("atualizarUsuario deve lançar NaoEncontradoExcecao quando id não existir")
    @Test
    void atualizarUsuarioDeveLancarNaoEncontradoExcecaoQuandoIdNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            Usuario resultado = service.atualizarUsuario(idInexistente, usuarioTeste);
        });
    }

    @DisplayName("deletarUsuarioPorUuid deve retornar Mensagem de sucesso quando id existir")
    @Test
    void deletarUsuarioPorUuidDeveRetornarMensagemDeSucessoQuandoIdExistir() {
        String resultado = service.deletarUsuarioPorUuid(idExistente);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(mensagemDeSucesso, resultado);
    }

    @DisplayName("deletarUsuarioPorUuid deve lançar NaoEncontradoExcecao quando id não existir")
    @Test
    void deletarUsuarioPorUuidDeveLancarNaoEncontradoExcecaoQuandoIdNaoExistir() {
        Assertions.assertThrows(NaoEncontradoExcecao.class, () -> {
            String resultado = service.deletarUsuarioPorUuid(idInexistente);
        });
    }
}
