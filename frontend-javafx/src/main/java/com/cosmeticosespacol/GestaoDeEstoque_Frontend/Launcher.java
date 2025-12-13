package com.cosmeticosespacol.GestaoDeEstoque_Frontend;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login.LoginController;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login.LoginService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login/Login.fxml"));
            Parent parent = loader.load();

            LoginController controller = loader.getController();
            controller.setLoginService(new LoginService());
            scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
