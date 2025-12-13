package com.cosmeticosespacol.GestaoDeEstoque.aplicacao.seguranca;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio.RepositorioDeUsuario;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.NaoEncontradoExcecao;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.usuario.mapper.UsuarioJpaMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeAutenticacao implements UserDetailsService {

    private final RepositorioDeUsuario repositorio;

    public ServiceDeAutenticacao(RepositorioDeUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario dominio = repositorio.buscarUsuarioPorEmail(username).orElseThrow(() -> new NaoEncontradoExcecao("Usuário não encontrado!"));
        return UsuarioJpaMapper.paraEntidade(dominio);
    }
}
