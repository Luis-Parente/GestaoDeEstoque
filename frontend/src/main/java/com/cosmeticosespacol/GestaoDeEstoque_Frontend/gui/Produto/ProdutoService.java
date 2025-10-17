package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.EntradaProdutoDTO;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.RetornoProdutoDTO;
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

    ObjectMapper objectMapper = new ObjectMapper();

    public List<RetornoProdutoDTO> listarTodosProdutos() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PRODUTO_URL))
                .header("Authorization", "Bearer " + GerenciadorTokenAutenticacao.getToken())
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), new TypeReference<List<RetornoProdutoDTO>>() {
        });
    }

    public void cadastrarProduto(EntradaProdutoDTO novoProduto) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String json = objectMapper.writeValueAsString(novoProduto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PRODUTO_URL))
                .header("Authorization", "Bearer " + GerenciadorTokenAutenticacao.getToken())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
