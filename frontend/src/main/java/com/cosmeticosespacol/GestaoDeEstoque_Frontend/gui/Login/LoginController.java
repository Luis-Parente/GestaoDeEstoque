package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.Launcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button btnEntrar;

    @FXML
    private Label labelEmailError;

    @FXML
    private Label labelSenhaError;

    @FXML
    private Label labelLoginError;

    @FXML
    public void onBtnEntrarClick() throws Exception {
        labelEmailError.setVisible(false);
        labelSenhaError.setVisible(false);
        labelLoginError.setVisible(false);

        String email = txtEmail.getText();
        if (email.isEmpty()) {
            labelEmailError.setText("Campo e-mail é obrigatorio");
            labelEmailError.setVisible(true);
        }

        String senha = txtSenha.getText();
        if (senha.isEmpty()) {
            labelSenhaError.setText("Campo senha é obrigatorio");
            labelSenhaError.setVisible(true);
        }

        boolean sucesso = LoginService.authenticate(email, senha);

        if (!sucesso && !email.isEmpty() && !senha.isEmpty()) {
            labelLoginError.setText("Usuário ou senha incorretos!");
            labelLoginError.setVisible(true);
        }
        else if (sucesso) {
            loadView("/gui/mainMenu/mainMenu.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void loadView(String caminho) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
            VBox vbox = loader.load();

            Scene scene = Launcher.getScene();

            AnchorPane anchorPane = (AnchorPane) scene.getRoot();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().addAll(vbox);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
