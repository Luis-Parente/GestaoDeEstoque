package com.cosmeticosespacol.GestaoDeEstoque.dominio.produto;

import java.util.UUID;

public class Produto {

    private UUID uuid;
    private String nome;
    private Categoria categoria;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Double desconto;

    public Produto(UUID uuid, String nome, Categoria categoria, String descricao, Double preco, Integer quantidade,
                   Double desconto) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome deve ser preenchido!");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria deve ser preenchida!");
        }

        if (preco == null || preco < 0.0) {
            throw new IllegalArgumentException("Preço inválido!");
        }

        if (quantidade == null || quantidade < 0.0) {
            throw new IllegalArgumentException("Quantidade inválida!");
        }

        if (desconto < 0.0) {
            throw new IllegalArgumentException("Desconto inválido!");
        }

        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.desconto = desconto;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome deve ser preenchido!");
        }
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria deve ser preenchida!");
        }
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (nome == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula!");
        }
        this.descricao = descricao;
    }

    public Double getPreco() {
        if (this.desconto == null || this.desconto == 0) {
            return preco;
        } else {
            return (preco * (1 - (this.desconto / 100)));
        }
    }

    public void setPreco(Double preco) {
        if (preco == null || preco < 0.0) {
            throw new IllegalArgumentException("Preço inválido!");
        }
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void adicionarEstoque(Integer quantidade) {
        if (quantidade == null) {
            throw new IllegalArgumentException("Quantidade inválida!");
        }
        this.quantidade += quantidade;
    }

    public void removerEstoque(Integer quantidade) {
        if (quantidade == null || quantidade > this.quantidade) {
            throw new IllegalArgumentException("Quantidade inválida!");
        }
        this.quantidade -= quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        if (desconto <= 0.0) {
            throw new IllegalArgumentException("Desconto inválido!");
        }
        this.desconto = desconto;
    }
}
