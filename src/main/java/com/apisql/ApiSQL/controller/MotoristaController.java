package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.openapi.MotoristaOpenApi;
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

public class MotoristaController implements MotoristaOpenApi {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @Override
    @GetMapping
    public List<Motorista> getAll() {
        return motoristaService.listarTodos();
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Motorista> getById(
            @Parameter(description = "ID do motorista") @PathVariable Integer id) {
        return motoristaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Motorista create(@RequestBody Motorista motorista) {
        return motoristaService.salvar(motorista);
    }

    @Override
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

    @Override
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
