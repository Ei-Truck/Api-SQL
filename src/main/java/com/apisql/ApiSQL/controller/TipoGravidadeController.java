package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.service.TipoGravidadeService;

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
@RequestMapping("/tipos-gravidade")
@Tag(name = "Tipo Gravidade", description = "Gerenciamento dos Tipos de Gravidade das infrações")
public class TipoGravidadeController {

    private final TipoGravidadeService tipoGravidadeService;

    public TipoGravidadeController(TipoGravidadeService tipoGravidadeService) {
        this.tipoGravidadeService = tipoGravidadeService;
    }

    @Operation(summary = "Lista todos os tipos de gravidade")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<TipoGravidade> getAll() {
        return tipoGravidadeService.findAll();
    }

    @Operation(summary = "Busca tipo de gravidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de gravidade encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoGravidade.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de gravidade não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoGravidade> getById(
            @Parameter(description = "ID do tipo de gravidade") @PathVariable Integer id) {
        return tipoGravidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo tipo de gravidade")
    @ApiResponse(responseCode = "201", description = "Tipo de gravidade criado com sucesso")
    @PostMapping
    public TipoGravidade create(@RequestBody TipoGravidade tipoGravidade) {
        return tipoGravidadeService.save(tipoGravidade);
    }

    @Operation(summary = "Atualiza um tipo de gravidade existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de gravidade atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de gravidade não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoGravidade> update(
            @PathVariable Integer id,
            @RequestBody TipoGravidade tipoGravidade) {
        return tipoGravidadeService.findById(id)
                .map(existing -> {
                    existing.setNome(tipoGravidade.getNome());
                    existing.setIsInactive(tipoGravidade.getIsInactive());
                    return ResponseEntity.ok(tipoGravidadeService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um tipo de gravidade pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de gravidade removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de gravidade não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoGravidadeService.findById(id).isPresent()) {
            tipoGravidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
