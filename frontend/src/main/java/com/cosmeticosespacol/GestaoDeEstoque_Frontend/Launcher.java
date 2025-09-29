package com.cosmeticosespacol.GestaoDeEstoque_Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/Login/Login.fxml")));
            scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
