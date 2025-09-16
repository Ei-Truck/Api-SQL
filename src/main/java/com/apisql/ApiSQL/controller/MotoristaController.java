package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.service.MotoristaService;

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
@RequestMapping("/motoristas")
@Tag(name = "Motoristas", description = "Gerenciamento de Motoristas")
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @Operation(summary = "Lista todos os motoristas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Motorista> getAll() {
        return motoristaService.listarTodos();
    }

    @Operation(summary = "Busca motorista por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Motorista.class))),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Motorista> getById(
            @Parameter(description = "ID do motorista") @PathVariable Integer id) {
        return motoristaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo motorista")
    @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso")
    @PostMapping
    public Motorista create(@RequestBody Motorista motorista) {
        return motoristaService.salvar(motorista);
    }

    @Operation(summary = "Atualiza um motorista existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Motorista> update(
            @PathVariable Integer id,
            @RequestBody Motorista motorista) {
        return motoristaService.buscarPorId(id)
                .map(existing -> {
                    existing.setNomeCompleto(motorista.getNomeCompleto());
                    existing.setCnh(motorista.getCnh());
                    existing.setIsInactive(motorista.getIsInactive());
                    return ResponseEntity.ok(motoristaService.salvar(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um motorista pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Motorista removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (motoristaService.buscarPorId(id).isPresent()) {
            motoristaService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
