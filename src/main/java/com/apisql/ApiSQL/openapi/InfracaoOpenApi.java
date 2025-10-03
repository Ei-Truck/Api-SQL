package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Infracao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Infrações", description = "Gerenciamento de Infrações")
@RequestMapping("/infracoes")
public interface InfracaoOpenApi {

    @Operation(summary = "Lista todas as infrações")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    List<Infracao> getAll();

    @Operation(summary = "Busca infração por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infração encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Infracao.class))),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @GetMapping("/{id}")
    ResponseEntity<Infracao> getById(
            @Parameter(description = "ID da infração") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova infração")
    @ApiResponse(responseCode = "201", description = "Infração criada com sucesso")
    @PostMapping
    Infracao create(@RequestBody Infracao infracao);

    @Operation(summary = "Atualiza uma infração existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infração atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @PutMapping("/{id}")
    ResponseEntity<Infracao> update(@PathVariable Integer id,
                                    @RequestBody Infracao infracao);

    @Operation(summary = "Remove uma infração pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Infração removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}

