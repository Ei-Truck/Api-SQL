package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.service.CaminhaoService;
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
@RequestMapping("/caminhoes")
@Tag(name = "Caminhões", description = "Gerenciamento de caminhões")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    @Operation(summary = "Lista todos os caminhões")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Caminhao> getAll() {
        return caminhaoService.findAll();
    }

    @Operation(summary = "Busca caminhão por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caminhão encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caminhao.class))),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> getById(
            @Parameter(description = "ID do caminhão") @PathVariable Integer id) {
        return caminhaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo caminhão")
    @ApiResponse(responseCode = "201", description = "Caminhão criado com sucesso")
    @PostMapping
    public Caminhao create(@RequestBody Caminhao caminhao) {
        return caminhaoService.save(caminhao);
    }

    @Operation(summary = "Atualiza um caminhão existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caminhão atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> update(
            @PathVariable Integer id,
            @RequestBody Caminhao caminhao) {
        return caminhaoService.findById(id)
                .map(existing -> {
                    existing.setChassi(caminhao.getChassi());
                    existing.setSegmento(caminhao.getSegmento());
                    existing.setUnidade(caminhao.getUnidade());
                    existing.setPlaca(caminhao.getPlaca());
                    existing.setModelo(caminhao.getModelo());
                    existing.setAnoFabricacao(caminhao.getAnoFabricacao());
                    existing.setNumeroFrota(caminhao.getNumeroFrota());
                    existing.setTransactionMade(caminhao.getTransactionMade());
                    existing.setUpdatedAt(caminhao.getUpdatedAt());
                    existing.setIsInactive(caminhao.getIsInactive());
                    return ResponseEntity.ok(caminhaoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um caminhão pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Caminhão removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (caminhaoService.findById(id).isPresent()) {
            caminhaoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
