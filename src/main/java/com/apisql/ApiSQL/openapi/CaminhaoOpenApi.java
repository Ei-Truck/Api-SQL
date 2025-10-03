package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Caminhao;
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

@Tag(name = "Caminhões", description = "Gerenciamento de caminhões")
@RequestMapping("/caminhoes")
public interface CaminhaoOpenApi {

    @Operation(summary = "Lista todos os caminhões")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    List<Caminhao> getAll();

    @Operation(summary = "Busca caminhão por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caminhão encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caminhao.class))),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<Caminhao> getById(
            @Parameter(description = "ID do caminhão") @PathVariable Integer id);

    @Operation(summary = "Cria um novo caminhão")
    @ApiResponse(responseCode = "201", description = "Caminhão criado com sucesso")
    @PostMapping
    Caminhao create(@RequestBody Caminhao caminhao);

    @Operation(summary = "Atualiza um caminhão existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caminhão atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<Caminhao> update(@PathVariable Integer id,
                                    @RequestBody Caminhao caminhao);

    @Operation(summary = "Remove um caminhão pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Caminhão removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}