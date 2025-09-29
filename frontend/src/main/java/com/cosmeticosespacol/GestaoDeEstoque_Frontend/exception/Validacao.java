package com.cosmeticosespacol.GestaoDeEstoque_Frontend.exception;

import java.util.HashMap;
import java.util.Map;

public class Validacao extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> erros = new HashMap<>();

    public Validacao(String mensagem) {
        super(mensagem);
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void addErro(String campo, String mensagem) {
        erros.put(campo, mensagem);
    }
}
