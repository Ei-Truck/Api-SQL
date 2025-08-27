package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Status;
import com.apisql.ApiSQL.service.StatusService;

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
@RequestMapping("/status")
@Tag(name = "Status", description = "Gerenciamento de Status dos usuários")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Operation(summary = "Lista todos os status")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Status> getAll() {
        return statusService.findAll();
    }

    @Operation(summary = "Busca status por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Status.class))),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(
            @Parameter(description = "ID do status") @PathVariable Long id) {
        return statusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo status")
    @ApiResponse(responseCode = "201", description = "Status criado com sucesso")
    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.save(status);
    }

    @Operation(summary = "Atualiza um status existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Status> update(
            @PathVariable Long id,
            @RequestBody Status status) {
        return statusService.findById(id)
                .map(existing -> {
                    existing.setNome(status.getNome());
                    existing.setIsInactive(status.getIsInactive());
                    return ResponseEntity.ok(statusService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um status pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Status removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (statusService.findById(id).isPresent()) {
            statusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
