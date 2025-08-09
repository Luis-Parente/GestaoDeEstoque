package com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.mapper;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosEntradaProduto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosRetornoProduto;

public class ProdutoMapper {

    public static Produto paraDominio(DadosEntradaProduto dto) {
        return new Produto(null, dto.nome(), Categoria.valueOf(dto.categoria()), dto.descricao(),
                dto.preco(), dto.quantidade(), dto.desconto());
    }

    public static DadosRetornoProduto paraDto(Produto dominio) {
        return new DadosRetornoProduto(dominio.getUuid().toString(), dominio.getNome(),
                dominio.getCategoria().toString(),
                dominio.getDescricao(), dominio.getPreco(), dominio.getQuantidade(), dominio.getDesconto());
    }
}
