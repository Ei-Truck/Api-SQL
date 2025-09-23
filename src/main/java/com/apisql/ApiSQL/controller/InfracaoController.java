package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.service.InfracaoService;

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
@RequestMapping("/infracoes")
@Tag(name = "Infrações", description = "Gerenciamento de Infrações")
public class InfracaoController {

    private final InfracaoService infracaoService;

    public InfracaoController(InfracaoService infracaoService) {
        this.infracaoService = infracaoService;
    }

    @Operation(summary = "Lista todas as infrações")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Infracao> getAll() {
        return infracaoService.findAll();
    }

    @Operation(summary = "Busca infração por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infração encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Infracao.class))),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Infracao> getById(
            @Parameter(description = "ID da infração") @PathVariable Integer id) {
        return infracaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova infração")
    @ApiResponse(responseCode = "201", description = "Infração criada com sucesso")
    @PostMapping
    public Infracao create(@RequestBody Infracao infracao) {
        return infracaoService.save(infracao);
    }

    @Operation(summary = "Atualiza uma infração existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infração atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Infracao> update(
            @PathVariable Integer id,
            @RequestBody Infracao infracao) {
        return infracaoService.findById(id)
                .map(existing -> {
                    existing.setIdViagem(infracao.getIdViagem());
                    existing.setIdMotorista(infracao.getIdMotorista());
                    existing.setDtHrEvento(infracao.getDtHrEvento());
                    existing.setIdTipoInfracao(infracao.getIdTipoInfracao());
                    existing.setLatitude(infracao.getLatitude());
                    existing.setLongitude(infracao.getLongitude());
                    existing.setVelocidadeKmh(infracao.getVelocidadeKmh());
                    existing.setTransactionMade(infracao.getTransactionMade());
                    existing.setUpdatedAt(infracao.getUpdatedAt());
                    existing.setIsInactive(infracao.getIsInactive());
                    return ResponseEntity.ok(infracaoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove uma infração pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Infração removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (infracaoService.findById(id).isPresent()) {
            infracaoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
