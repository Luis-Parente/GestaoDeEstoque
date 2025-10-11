package com.cosmeticosespacol.GestaoDeEstoque_Frontend.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoadView {

    public static void loadView(Scene scene, String caminho) {
        try {
            FXMLLoader loader = new FXMLLoader(LoadView.class.getResource(caminho));
            VBox vbox = loader.load();
            scene.setRoot(vbox);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
