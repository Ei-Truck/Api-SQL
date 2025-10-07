package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.TipoRisco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tipos-risco")
@Tag(name = "Tipos de Risco", description = "Gerenciamento dos Tipos de Risco")
public interface TipoRiscoOpenApi {
    @Operation(summary = "Lista todos os tipos de risco")
    @GetMapping // ADICIONADO
    List<TipoRisco> getAll();

    @Operation(summary = "Busca tipo de risco por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de risco encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoRisco.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de risco não encontrado")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<TipoRisco> getById(@Parameter(description = "ID do tipo de risco") @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cria um novo tipo de risco")
    @ApiResponse(responseCode = "201", description = "Tipo de risco criado com sucesso")
    @PostMapping // ADICIONADO
    TipoRisco create(@RequestBody TipoRisco tipoRisco); // ADICIONADO @RequestBody

    @Operation(summary = "Remove um tipo de risco pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de risco removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de risco não encontrado")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
