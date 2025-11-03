package com.cosmeticosespacol.GestaoDeEstoque.factory;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.NivelDeAcesso;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.usuario.Usuario;

import java.util.UUID;

public class UsuarioFactory {

    public static Usuario criarUsuario() {
        return new Usuario(UUID.randomUUID(), "Nome teste", "emailteste@gmail.com", "senha teste", NivelDeAcesso.ADMINISTRADOR);
    }
}
