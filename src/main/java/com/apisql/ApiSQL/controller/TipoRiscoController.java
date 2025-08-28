package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.service.TipoRiscoService;

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

@RestController
@RequestMapping("/tipos-risco")
@Tag(name = "Tipos de Risco", description = "Gerenciamento dos Tipos de Risco")
public class TipoRiscoController {

    private final TipoRiscoService tipoRiscoService;

    public TipoRiscoController(TipoRiscoService tipoRiscoService) {
        this.tipoRiscoService = tipoRiscoService;
    }

    @Operation(summary = "Lista todos os tipos de risco")
    @GetMapping
    public List<TipoRisco> getAll() {
        return tipoRiscoService.findAll();
    }

    @Operation(summary = "Busca tipo de risco por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de risco encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoRisco.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de risco não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoRisco> getById(
            @Parameter(description = "ID do tipo de risco") @PathVariable Integer id) {
        return tipoRiscoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo tipo de risco")
    @ApiResponse(responseCode = "201", description = "Tipo de risco criado com sucesso")
    @PostMapping
    public TipoRisco create(@RequestBody TipoRisco tipoRisco) {
        return tipoRiscoService.save(tipoRisco);
    }

    @Operation(summary = "Atualiza um tipo de risco existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de risco atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de risco não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoRisco> update(
            @PathVariable Integer id,
            @RequestBody TipoRisco tipoRisco) {
        return tipoRiscoService.findById(id)
                .map(existing -> {
                    existing.setNome(tipoRisco.getNome());
                    existing.setDescricao(tipoRisco.getDescricao());
                    existing.setIsInactive(tipoRisco.getIsInactive());
                    return ResponseEntity.ok(tipoRiscoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um tipo de risco pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de risco removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de risco não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoRiscoService.findById(id).isPresent()) {
            tipoRiscoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
