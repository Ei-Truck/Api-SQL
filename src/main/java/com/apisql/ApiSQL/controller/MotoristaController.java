package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.service.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/motoristas")
@Tag(name = "Motoristas", description = "Gerenciamento de motoristas")

public class MotoristaController {
    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todos os motoristas", description = "Retorna todos os motoristas cadastrados")
    public ResponseEntity<List<Motorista>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca motorista por ID")
    public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo motorista")
    public ResponseEntity<Motorista> salvar(@RequestBody Motorista motorista) {
        return ResponseEntity.ok(service.salvar(motorista));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um motorista existente")
    public ResponseEntity<Motorista> atualizar(@PathVariable Long id, @RequestBody Motorista motorista) {
        return ResponseEntity.ok(service.atualizar(id, motorista));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um motorista pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
