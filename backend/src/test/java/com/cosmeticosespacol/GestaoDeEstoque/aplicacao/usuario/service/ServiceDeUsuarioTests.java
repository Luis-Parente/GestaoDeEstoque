package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.service;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio.RepositorioDeUsuario;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.DadoRepetidoExcecao;
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

    @BeforeEach
    void setUp() {

        usuarioTeste = UsuarioFactory.criarUsuario();

        idExistente = usuarioTeste.getUuid();
        idInexistente = UUID.randomUUID();

        emailInexistente = usuarioTeste.getEmail();
        emailExistente = "emailtesteexistente@gmail.com";

        senhaCriptografada = "Senha criptografada";

        Mockito.when(repositorio.salvarUsuario(any(Usuario.class))).thenReturn(usuarioTeste);

        Mockito.when(repositorio.validarEmail(eq(emailInexistente))).thenReturn(false);
        Mockito.when(repositorio.validarEmail(eq(emailExistente))).thenReturn(true);

        Mockito.when(passwordEncoder.encode(any(String.class))).thenReturn(senhaCriptografada);
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
}
