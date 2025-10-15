package com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto;

import com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto.ProdutoDTO;
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

public class ProdutoController implements Initializable {

    private ProdutoService  produtoService;

    @FXML
    private TableView<ProdutoDTO> tableViewProdutoDto;

    @FXML
    private TableColumn<ProdutoDTO, String> tableColumnProdutoId;

    @FXML
    private TableColumn<ProdutoDTO, String> tableColumnProdutoNome;

    @FXML
    private TableColumn<ProdutoDTO, Integer> tableColumnProdutoQuantidade;

    @FXML
    private TableColumn<ProdutoDTO, BigDecimal> tableColumnProdutoPreco;

    @FXML
    private TableColumn<ProdutoDTO, String> tableColumnProdutoCategoria;

    private ObservableList<ProdutoDTO> observableListProdutoDto;

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
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos();
        observableListProdutoDto = FXCollections.observableArrayList(produtos);
        tableViewProdutoDto.setItems(observableListProdutoDto);
    }
}
