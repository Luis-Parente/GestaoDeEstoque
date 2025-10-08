package com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.persistencia;

import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Categoria;
import com.cosmeticosespacol.GestaoDeEstoque.infraestrutura.produto.entidade.ProdutoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDeProdutoJpa extends JpaRepository<ProdutoEntidade, UUID> {

    @Query(nativeQuery = true, value = "SELECT EXISTS (SELECT 1 FROM tb_produto WHERE nome = :nome)")
    boolean validarNome(String nome);

    @Query(""" 
            SELECT p FROM ProdutoEntidade p 
            WHERE (cast(:nome as string) IS NULL OR UPPER(p.nome) LIKE CONCAT('%', UPPER(cast(:nome as string)), '%')) 
            AND (:categoria IS NULL OR p.categoria = :categoria)
           """)
    List<ProdutoEntidade> filtrarPorNomeECategoria(@Param("nome") String nome,
                                                   @Param("categoria") Categoria categoria);
}
