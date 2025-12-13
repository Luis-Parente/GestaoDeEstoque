package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.RetornoProdutoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestorProdutoController implements Initializable {

    private ProdutoService  produtoService;

    @FXML
    private TableView<RetornoProdutoDTO> tableViewProdutoDto;

    @FXML
    private TableColumn<RetornoProdutoDTO, String> tableColumnProdutoId;

    @FXML
    private TableColumn<RetornoProdutoDTO, String> tableColumnProdutoNome;

    @FXML
    private TableColumn<RetornoProdutoDTO, Integer> tableColumnProdutoQuantidade;

    @FXML
    private TableColumn<RetornoProdutoDTO, BigDecimal> tableColumnProdutoPreco;

    @FXML
    private TableColumn<RetornoProdutoDTO, String> tableColumnProdutoCategoria;

    private ObservableList<RetornoProdutoDTO> observableListRetornoProdutoDto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    private void initializeNodes() {
        tableColumnProdutoId.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnProdutoQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnProdutoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnProdutoCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    public void updateTableView() throws Exception {
        List<RetornoProdutoDTO> produtos = produtoService.listarTodosProdutos();
        observableListRetornoProdutoDto = FXCollections.observableArrayList(produtos);
        tableViewProdutoDto.setItems(observableListRetornoProdutoDto);
    }
}
