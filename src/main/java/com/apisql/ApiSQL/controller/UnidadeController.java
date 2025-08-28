package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.service.UnidadeService;

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
@RequestMapping("/unidades")
@Tag(name = "Unidades", description = "Gerenciamento de Unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @Operation(summary = "Lista todas as unidades", description = "Retorna todas as unidades cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Unidade> getAll() {
        return unidadeService.findAll();
    }

    @Operation(summary = "Busca unidade por ID", description = "Retorna os dados de uma unidade específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Unidade.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> getById(
            @Parameter(description = "ID da unidade") @PathVariable Integer id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova unidade")
    @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso")
    @PostMapping
    public Unidade create(@RequestBody Unidade unidade) {
        return unidadeService.save(unidade);
    }

    @Operation(summary = "Atualiza uma unidade existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Unidade> update(
            @PathVariable Integer id,
            @RequestBody Unidade unidade) {
        return unidadeService.findById(id)
                .map(existing -> {
                    existing.setNome(unidade.getNome());
                    existing.setCidade(unidade.getCidade());
                    existing.setUfEstado(unidade.getUfEstado());
                    existing.setSegmento(unidade.getSegmento());
                    existing.setIsInactive(unidade.getIsInactive());
                    return ResponseEntity.ok(unidadeService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove uma unidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Unidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (unidadeService.findById(id).isPresent()) {
            unidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
