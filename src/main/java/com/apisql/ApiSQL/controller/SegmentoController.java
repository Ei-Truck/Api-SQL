package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.service.SegmentoService;

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
@RequestMapping("/segmentos")
@Tag(name = "Segmentos", description = "Gerenciamento de Segmentos")
public class SegmentoController {

    private final SegmentoService segmentoService;

    public SegmentoController(SegmentoService segmentoService) {
        this.segmentoService = segmentoService;
    }

    @Operation(summary = "Lista todos os segmentos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Segmento> getAll() {
        return segmentoService.findAll();
    }

    @Operation(summary = "Busca segmento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Segmento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Segmento.class))),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Segmento> getById(
            @Parameter(description = "ID do segmento") @PathVariable Long id) {
        return segmentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo segmento")
    @ApiResponse(responseCode = "201", description = "Segmento criado com sucesso")
    @PostMapping
    public Segmento create(@RequestBody Segmento segmento) {
        return segmentoService.save(segmento);
    }

    @Operation(summary = "Atualiza um segmento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Segmento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Segmento> update(
            @PathVariable Long id,
            @RequestBody Segmento segmento) {
        return segmentoService.findById(id)
                .map(existing -> {
                    existing.setNome(segmento.getNome());
                    existing.setIsInactive(segmento.getIsInactive());
                    return ResponseEntity.ok(segmentoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um segmento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Segmento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (segmentoService.findById(id).isPresent()) {
            segmentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
