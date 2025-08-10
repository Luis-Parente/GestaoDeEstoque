package com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.mapper;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosEntradaProduto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosRetornoProduto;

import java.math.RoundingMode;

public class ProdutoMapper {

    public static Produto paraDominio(DadosEntradaProduto dto) {
        return new Produto(null, dto.nome(), Categoria.valueOf(dto.categoria()), dto.descricao(),
                dto.preco(), dto.quantidade(), dto.desconto());
    }

    public static DadosRetornoProduto paraDto(Produto dominio) {
        return new DadosRetornoProduto(dominio.getUuid().toString(), dominio.getNome(),
                dominio.getCategoria().toString(),
                dominio.getDescricao(), dominio.getPreco().setScale(2, RoundingMode.HALF_UP), dominio.getQuantidade(),
                dominio.getDesconto().setScale(2, RoundingMode.HALF_UP));
    }
}
