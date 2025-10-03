package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Motorista;
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

@Tag(name = "Motoristas", description = "Gerenciamento de Motoristas")
@RequestMapping("/motoristas")
public interface MotoristaOpenApi {

    @Operation(summary = "Lista todos os motoristas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    List<Motorista> getAll();

    @Operation(summary = "Busca motorista por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Motorista.class))),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<Motorista> getById(
            @Parameter(description = "ID do motorista") @PathVariable Integer id);

    @Operation(summary = "Cria um novo motorista")
    @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso")
    @PostMapping
    Motorista create(@RequestBody Motorista motorista);

    @Operation(summary = "Atualiza um motorista existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<Motorista> update(@PathVariable Integer id,
                                     @RequestBody Motorista motorista);

    @Operation(summary = "Remove um motorista pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Motorista removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
