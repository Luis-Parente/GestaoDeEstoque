package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.MainMenu;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.Launcher;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto.CadastrarProdutoController;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto.GestorProdutoController;
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
        LoadView.loadView(Launcher.getScene(), "/gui/Login/Login.fxml", x -> {
        });
    }

    @FXML
    public void menuItemGerenciarProdutoClick() {
        LoadView.loadView(Launcher.getScene(), "/gui/Produto/GerenciarProduto.fxml", (GestorProdutoController controller) -> {
            controller.setProdutoService(new ProdutoService());
            try {
                controller.updateTableView();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void menuItemCadastrarProdutoClick() {
        LoadView.loadView(Launcher.getScene(), "/gui/Produto/FormularioProduto.fxml", (CadastrarProdutoController controller) -> {
            controller.setProdutoService(new ProdutoService());
        });
    }
}
