package com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO {

    private String uuid;
    private String nome;
    private String categoria;
    private Integer quantidade;
    private BigDecimal preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String uuid, String nome, String categoria, Integer quantidade, BigDecimal preco) {
        this.uuid = uuid;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
