package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Status;
import com.apisql.ApiSQL.service.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "Gerenciamento de Status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }
    @Operation(summary = "Lista todos os status", description = "Retorna todos os status disponíveis")
    @GetMapping
    public List<Status> getAll() {
        return statusService.findAll();
    }
    @Operation(summary = "Busca status por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable Long id) {
        return statusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo status")
    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.save(status);
    }


    @Operation(summary = "Atualiza um status existente")
    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable Long id, @RequestBody Status status) {
        return statusService.findById(id)
                .map(existing -> {
                    existing.setNome(status.getNome());
                    existing.setIsInactive(status.getIsInactive());
                    return ResponseEntity.ok(statusService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um status pelo ID")
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