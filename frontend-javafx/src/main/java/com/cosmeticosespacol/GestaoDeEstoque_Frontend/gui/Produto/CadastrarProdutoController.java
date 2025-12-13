package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.EntradaProdutoDTO;
import com.cosmeticosespacol.GestaoDeEstoque_Frontend.model.Categoria;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarProdutoController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<Categoria> comboBoxCategoria;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtDesconto;

    @FXML
    private Button btnCadastrar;

    private ProdutoService produtoService;

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxCategoria.getItems().setAll(Categoria.values());
    }

    @FXML
    public void onBtnCadastrarClick() throws Exception {
        String nome = txtNome.getText();
        String categoria = comboBoxCategoria.getSelectionModel().getSelectedItem().toString();
        String descricao = txtDescricao.getText();
        BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(txtPreco.getText()));
        Integer quantidade = Integer.parseInt(txtQuantidade.getText());
        BigDecimal desconto = BigDecimal.valueOf(Double.parseDouble(txtDesconto.getText()));

        EntradaProdutoDTO produtoDTO = new EntradaProdutoDTO(nome, categoria, descricao, preco, quantidade, desconto);

        produtoService.cadastrarProduto(produtoDTO);
    }


}
