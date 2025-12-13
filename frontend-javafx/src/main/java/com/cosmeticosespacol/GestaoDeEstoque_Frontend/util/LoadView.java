package com.cosmeticosespacol.GestaoDeEstoque_Frontend.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.function.Consumer;

public class LoadView {

    public static <T> void loadView(Scene scene, String caminho, Consumer<T> acaoInicial) {
        try {
            FXMLLoader loader = new FXMLLoader(LoadView.class.getResource(caminho));
            VBox vbox = loader.load();
            scene.setRoot(vbox);

            T controller = loader.getController();
            acaoInicial.accept(controller);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
