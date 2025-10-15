package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.MainMenu;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.Launcher;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto.ProdutoController;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto.ProdutoService;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.util.LoadView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MainMenuController {

    @FXML
    private MenuItem cadastrarProduto;

    @FXML
    private MenuItem gerenciarProduto;

    @FXML
    private Button botaoSair;

    @FXML
    public void botaoSairClick() {
        LoadView.loadView(Launcher.getScene(), "/gui/Login/Login.fxml", x -> {});
    }

    @FXML
    public void menuItemGerenciarProdutoClick() {
        LoadView.loadView(Launcher.getScene(), "/gui/Produto/GerenciarProduto.fxml", (ProdutoController controller) -> {
            controller.setProdutoService(new ProdutoService());
            try {
                controller.updateTableView();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
