package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Unidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*; // Adicionado para incluir todos

@RequestMapping("/unidades")
@Tag(name = "Unidades", description = "Gerenciamento de Unidades")
public interface UnidadeOpenApi {

    @Operation(summary = "Lista todas as unidades", description = "Retorna todas as unidades cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    List<Unidade> getAll();

    @Operation(summary = "Busca unidade por ID", description = "Retorna os dados de uma unidade específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Unidade.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<Unidade> getById(@Parameter(description = "ID da unidade") @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cria uma nova unidade")
    @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso")
    @PostMapping // ADICIONADO
    Unidade create(@RequestBody Unidade unidade); // ADICIONADO @RequestBody

    @Operation(summary = "Remove uma unidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Unidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> delete(@PathVariable Integer id); // ADICIONADO @PathVariable
}