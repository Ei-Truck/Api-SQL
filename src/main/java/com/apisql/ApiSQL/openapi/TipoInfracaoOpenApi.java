package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.TipoInfracao;
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

@RequestMapping("/tipos-infracao")
@Tag(name = "Tipos de Infração", description = "Gerenciamento dos Tipos de Infrações")
public interface TipoInfracaoOpenApi {

    @Operation(summary = "Lista todos os tipos de infração")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    List<TipoInfracao> getAll();

    @Operation(summary = "Busca tipo de infração por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de infração encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoInfracao.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de infração não encontrado")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<TipoInfracao> getById(@Parameter(description = "ID do tipo de infração") @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cria um novo tipo de infração")
    @ApiResponse(responseCode = "201", description = "Tipo de infração criado com sucesso")
    @PostMapping // ADICIONADO
    TipoInfracao create(@RequestBody TipoInfracao tipoInfracao); // ADICIONADO @RequestBody

    @Operation(summary = "Remove um tipo de infração pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de infração removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de infração não encontrado")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> delete(@PathVariable Integer id); // ADICIONADO @PathVariable
}