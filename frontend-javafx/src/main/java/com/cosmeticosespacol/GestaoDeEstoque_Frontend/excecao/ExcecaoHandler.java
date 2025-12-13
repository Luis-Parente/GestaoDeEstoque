package com.cosmeticosespacol.GestaoDeEstoque_Frontend.excecao;

import javafx.scene.control.Alert;

public class ExcecaoHandler {

    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showException(Exception e) {
        showError("Erro", e.getMessage());
    }
}
