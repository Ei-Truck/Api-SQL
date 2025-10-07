package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.openapi.TipoRiscoOpenApi;
import com.apisql.ApiSQL.service.TipoRiscoService;

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

public class TipoRiscoController implements TipoRiscoOpenApi {

    private final TipoRiscoService tipoRiscoService;

    public TipoRiscoController(TipoRiscoService tipoRiscoService) {
        this.tipoRiscoService = tipoRiscoService;
    }

    @Override
    @GetMapping
    public List<TipoRisco> getAll() {
        return tipoRiscoService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoRisco> getById(@PathVariable Integer id) {
        return tipoRiscoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoRisco create(@RequestBody TipoRisco tipoRisco) {
        return tipoRiscoService.save(tipoRisco);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoRiscoService.findById(id).isPresent()) {
            tipoRiscoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
