package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.service.LocalidadeService;

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
@RequestMapping("/localidades")
@Tag(name = "Localidades", description = "Gerenciamento de Localidades")
public class LocalidadeController {

    private final LocalidadeService localidadeService;

    public LocalidadeController(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @Operation(summary = "Lista todas as localidades")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Localidade> getAll() {
        return localidadeService.findAll();
    }

    @Operation(summary = "Busca localidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Localidade encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Localidade.class))),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Localidade> getById(
            @Parameter(description = "ID da localidade") @PathVariable Integer id) {
        return localidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova localidade")
    @ApiResponse(responseCode = "201", description = "Localidade criada com sucesso")
    @PostMapping
    public Localidade create(@RequestBody Localidade localidade) {
        return localidadeService.save(localidade);
    }

    @Operation(summary = "Atualiza uma localidade existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Localidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Localidade> update(
            @PathVariable Integer id,
            @RequestBody Localidade localidade) {
        return localidadeService.findById(id)
                .map(existing -> {
                    existing.setCep(localidade.getCep());
                    existing.setNumeroRua(localidade.getNumeroRua());
                    existing.setUfEstado(localidade.getUfEstado());
                    existing.setNome(localidade.getNome());
                    existing.setTransactionMade(localidade.getTransactionMade());
                    existing.setUpdatedAt(localidade.getUpdatedAt());
                    existing.setIsInactive(localidade.getIsInactive());
                    return ResponseEntity.ok(localidadeService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove uma localidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Localidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (localidadeService.findById(id).isPresent()) {
            localidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
