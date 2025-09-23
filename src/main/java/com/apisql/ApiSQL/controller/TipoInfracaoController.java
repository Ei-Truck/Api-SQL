package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.service.TipoInfracaoService;

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
@RequestMapping("/tipos-infracao")
@Tag(name = "Tipos de Infração", description = "Gerenciamento dos Tipos de Infrações")
public class TipoInfracaoController {

    private final TipoInfracaoService service;

    public TipoInfracaoController(TipoInfracaoService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os tipos de infração")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<TipoInfracao> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca tipo de infração por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de infração encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoInfracao.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de infração não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoInfracao> getById(
            @Parameter(description = "ID do tipo de infração") @PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo tipo de infração")
    @ApiResponse(responseCode = "201", description = "Tipo de infração criado com sucesso")
    @PostMapping
    public TipoInfracao create(@RequestBody TipoInfracao tipoInfracao) {
        return service.save(tipoInfracao);
    }

    @Operation(summary = "Atualiza um tipo de infração existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de infração atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de infração não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoInfracao> update(
            @PathVariable Integer id,
            @RequestBody TipoInfracao tipoInfracao) {
        return service.findById(id)
                .map(existing -> {
                    existing.setNome(tipoInfracao.getNome());
                    existing.setPontuacao(tipoInfracao.getPontuacao());
                    existing.setIdTipoGravidade(tipoInfracao.getIdTipoGravidade());
                    existing.setTransactionMade(tipoInfracao.getTransactionMade());
                    existing.setUpdatedAt(tipoInfracao.getUpdatedAt());
                    existing.setIsInactive(tipoInfracao.getIsInactive());
                    return ResponseEntity.ok(service.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um tipo de infração pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de infração removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de infração não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
