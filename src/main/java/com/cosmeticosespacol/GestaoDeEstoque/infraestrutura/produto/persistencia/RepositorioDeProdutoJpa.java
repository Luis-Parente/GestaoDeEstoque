package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.persistencia;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.entidade.ProdutoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RepositorioDeProdutoJpa extends JpaRepository<ProdutoEntidade, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_produto WHERE UPPER(nome) LIKE UPPER((CONCAT('%', :nome, '%'))")
    List<ProdutoEntidade> findByNome(String nome);

    List<ProdutoEntidade> findByCategoria(Categoria categoria);
}
