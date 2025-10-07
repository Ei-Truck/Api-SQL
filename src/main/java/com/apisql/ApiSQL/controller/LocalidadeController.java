package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.openapi.LocalidadeOpenApi;
import com.apisql.ApiSQL.service.LocalidadeService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocalidadeController implements LocalidadeOpenApi {

    private final LocalidadeService localidadeService;

    public LocalidadeController(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @Override
    @GetMapping
    public List<Localidade> getAll() {
        return localidadeService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Localidade> getById(
            @Parameter(description = "ID da localidade") @PathVariable Integer id) {
        return localidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Localidade create(@RequestBody Localidade localidade) {
        return localidadeService.save(localidade);
    }

    @Override
    public ResponseEntity<Localidade> update(Integer id, Localidade localidade) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (localidadeService.findById(id).isPresent()) {
            localidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
