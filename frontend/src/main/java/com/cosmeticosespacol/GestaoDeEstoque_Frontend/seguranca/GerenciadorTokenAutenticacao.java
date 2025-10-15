package com.cosmeticosespacol.GestaoDeEstoque_Frontend.seguranca;

public class GerenciadorTokenAutenticacao {

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GerenciadorTokenAutenticacao.token = token;
    }
}
