package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.Launcher;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.excecao.ExcecaoHandler;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.util.LoadView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private Button btnEntrar;

    @FXML
    private Label labelEmailError;

    @FXML
    private Label labelSenhaError;

    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @FXML
    public void onBtnEntrarClick() throws Exception {
        labelEmailError.setVisible(false);
        labelSenhaError.setVisible(false);

        String email = txtEmail.getText();
        if (email.isEmpty()) {
            labelEmailError.setText("Campo e-mail é obrigatorio");
            labelEmailError.setVisible(true);
        }

        String senha = passwordFieldSenha.getText();
        if (senha.isEmpty()) {
            labelSenhaError.setText("Campo senha é obrigatorio");
            labelSenhaError.setVisible(true);
        }

        boolean sucesso = loginService.authenticate(email, senha);

        if (!sucesso && !email.isEmpty() && !senha.isEmpty()) {
            ExcecaoHandler.showError("Login", "Usuário ou senha incorretos!");
        } else if (sucesso) {
            LoadView.loadView(Launcher.getScene(), "/gui/MainMenu/MainMenu.fxml", x-> {});
        }
    }
}
