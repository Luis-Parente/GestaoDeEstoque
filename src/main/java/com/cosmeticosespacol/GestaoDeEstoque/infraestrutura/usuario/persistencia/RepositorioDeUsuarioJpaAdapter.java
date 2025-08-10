package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.usuario.persistencia;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.usuario.repositorio.RepositorioDeUsuario;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.usuario.entidade.UsuarioEntidade;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.usuario.mapper.UsuarioJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RepositorioDeUsuarioJpaAdapter implements RepositorioDeUsuario {

    private final RepositorioDeUsuarioJpa repositorio;

    @Autowired
    public RepositorioDeUsuarioJpaAdapter(RepositorioDeUsuarioJpa repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        UsuarioEntidade entidade = UsuarioJpaMapper.paraEntidade(usuario);
        return UsuarioJpaMapper.paraDominio(repositorio.save(entidade));
    }

    @Override
    public boolean validarEmail(String email) {
        return repositorio.validarEmail(email);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorUuid(UUID uuid) {
        return repositorio.findById(uuid).map(UsuarioJpaMapper::paraDominio);
    }

    @Override
    public void deletarUsuarioPorUuid(UUID uuid) {
        repositorio.deleteById(uuid);
    }
}
