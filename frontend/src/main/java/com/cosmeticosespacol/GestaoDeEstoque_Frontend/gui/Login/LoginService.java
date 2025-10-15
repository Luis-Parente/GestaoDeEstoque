package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.RequisicaoLogin;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.seguranca.GerenciadorTokenAutenticacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class LoginService {

    private static final String LOGIN_URL = "http://localhost:8080/login";

    public static boolean authenticate(String email, String senha) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        RequisicaoLogin body = new RequisicaoLogin(email, senha);

        String json = objectMapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, String> responseMap = objectMapper.readValue(response.body(), Map.class);

        GerenciadorTokenAutenticacao.setToken(responseMap.get("token"));

        return response.statusCode() == 200;
    }
}
