package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.ProdutoDTO;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.seguranca.GerenciadorTokenAutenticacao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProdutoService {

    private static final String PRODUTO_URL = "http://localhost:8080/produto";

    public List<ProdutoDTO> listarTodosProdutos() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PRODUTO_URL))
                .header("Authorization", "Bearer " + GerenciadorTokenAutenticacao.getToken())
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), new TypeReference<List<ProdutoDTO>>() {});
    }
}
