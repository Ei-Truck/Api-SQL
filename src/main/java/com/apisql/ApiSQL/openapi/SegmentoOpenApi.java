package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Segmento;
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

@RequestMapping("/segmentos")
@Tag(name = "Segmentos", description = "Gerenciamento de segmentos")
public interface SegmentoOpenApi {

    @Operation(summary = "Lista todos os segmentos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    List<Segmento> getAll();

    @Operation(summary = "Busca segmento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Segmento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Segmento.class))),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<Segmento> getById(@Parameter(description = "ID do segmento") @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cria um novo segmento")
    @ApiResponse(responseCode = "201", description = "Segmento criado com sucesso")
    @PostMapping
        // ADICIONADO
    Segmento create(@RequestBody Segmento segmento); // ADICIONADO @RequestBody

    @Operation(summary = "Atualiza um segmento existente") // ADICIONADO (Assumindo PUT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Segmento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @PutMapping("/{id}") // ADICIONADO
    ResponseEntity<Segmento> update(@PathVariable Integer id, @RequestBody Segmento segmento); // ADICIONADO

    @Operation(summary = "Remove um segmento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Segmento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> delete(@PathVariable Integer id); // ADICIONADO @PathVariable
}