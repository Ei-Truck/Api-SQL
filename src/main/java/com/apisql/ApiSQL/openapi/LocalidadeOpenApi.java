package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Localidade;
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

@Tag(name = "Localidades", description = "Gerenciamento de Localidades")
@RequestMapping("/localidades")
public interface LocalidadeOpenApi {

    @Operation(summary = "Lista todas as localidades")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    List<Localidade> getAll();

    @Operation(summary = "Busca localidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Localidade encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Localidade.class))),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @GetMapping("/{id}")
    ResponseEntity<Localidade> getById(
            @Parameter(description = "ID da localidade") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova localidade")
    @ApiResponse(responseCode = "201", description = "Localidade criada com sucesso")
    @PostMapping
    Localidade create(@RequestBody Localidade localidade);

    @Operation(summary = "Remove uma localidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Localidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}

