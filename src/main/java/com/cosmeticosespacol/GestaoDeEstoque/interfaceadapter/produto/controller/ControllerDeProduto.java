package com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.controller;

import com.cosmeticosespacol.GestaoDeEstoque.aplicacao.produto.service.ServiceDeProduto;
import com.cosmeticosespacol.GestaoDeEstoque.dominio.produto.Produto;
import com.cosmeticosespacol.GestaoDeEstoque.excecao.dto.ErroCustomizado;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosEntradaProduto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.dto.DadosRetornoProduto;
import com.cosmeticosespacol.GestaoDeEstoque.interfaceadapter.produto.mapper.ProdutoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Controller para produto")
public class ControllerDeProduto {

    private final ServiceDeProduto service;

    @Autowired
    public ControllerDeProduto(ServiceDeProduto service) {
        this.service = service;
    }

    @Operation(description = "Cadastra novo produto", summary = "Cadastrar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso", content = @Content(schema = @Schema(implementation = DadosRetornoProduto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErroCustomizado.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(schema = @Schema(implementation = ErroCustomizado.class)))})
    @PostMapping(produces = "application/json")
    public ResponseEntity<DadosRetornoProduto> cadastrarProduto(@RequestBody DadosEntradaProduto dadosEntradaProduto) {
        Produto dominio = service.cadastrarNovoProduto(ProdutoMapper.paraDominio(dadosEntradaProduto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(dominio.getUuid())
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoMapper.paraDto(dominio));
    }

    @Operation(description = "Retorna produto filtrado por uuid", summary = "FIltrar por uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto retornado com sucesso", content = @Content(schema = @Schema(implementation = DadosRetornoProduto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErroCustomizado.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErroCustomizado.class)))})
    @GetMapping(value = "/{uuid}", produces = "application/json")
    public ResponseEntity<DadosRetornoProduto> filtrarProdutoPorUuid(@PathVariable UUID uuid) {
        Produto dominio = service.filtrarPorUuid(uuid);
        return ResponseEntity.ok().body(ProdutoMapper.paraDto(dominio));
    }

    @Operation(description = "Atualiza os dados do produto", summary = "Atualizar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto atualizado com sucesso", content = @Content(schema = @Schema(implementation = DadosRetornoProduto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErroCustomizado.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErroCustomizado.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(schema = @Schema(implementation = ErroCustomizado.class)))})
    @PutMapping(value = "/{uuid}", produces = "application/json")
    public ResponseEntity<DadosRetornoProduto> atualizarProduto(@PathVariable UUID uuid,
                                                                @RequestBody DadosEntradaProduto dadosEntradaProduto) {
        Produto dominio = service.atualizarProduto(uuid, ProdutoMapper.paraDominio(dadosEntradaProduto));
        return ResponseEntity.ok().body(ProdutoMapper.paraDto(dominio));
    }

    @Operation(description = "Deleta produto por uuid", summary = "Deletar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto excluido com sucesso", content = @Content(schema = @Schema(implementation = DadosRetornoProduto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErroCustomizado.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErroCustomizado.class)))})
    @DeleteMapping(value = "/{uuid}", produces = "application/json")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID uuid) {
        service.deletarProdutoPorUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
