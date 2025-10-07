package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.TipoGravidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*; // Adicionado para incluir todos

@RequestMapping("/tipos-gravidade")
@Tag(name = "Tipo Gravidade", description = "Gerenciamento dos Tipos de Gravidade das infrações")
public interface TipoGravidadeOpenApi {

    @Operation(summary = "Lista todos os tipos de gravidade")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    List<TipoGravidade> getAll();

    @Operation(summary = "Busca tipo de gravidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de gravidade encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoGravidade.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de gravidade não encontrado")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<TipoGravidade> getById(@Parameter(description = "ID do tipo de gravidade") @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cria um novo tipo de gravidade")
    @ApiResponse(responseCode = "201", description = "Tipo de gravidade criado com sucesso")
    @PostMapping // ADICIONADO
    TipoGravidade create(@RequestBody TipoGravidade tipoGravidade); // ADICIONADO @RequestBody

    @Operation(summary = "Remove um tipo de gravidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de gravidade removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de gravidade não encontrado")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> delete(@PathVariable Integer id); // ADICIONADO @PathVariable
}