package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginService {

    private static final String LOGIN_URL="http://localhost:8080/login";

    public static boolean authenticate(String email, String senha) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Monta o JSON
        String json = String.format("{\"email\":\"%s\", \"senha\":\"%s\"}", email, senha);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200;
    }
}
