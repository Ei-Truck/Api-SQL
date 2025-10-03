package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.openapi.UnidadeOpenApi;
import com.apisql.ApiSQL.service.UnidadeService;

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
public class UnidadeController implements UnidadeOpenApi {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @Override
    @GetMapping
    public List<Unidade> getAll() {
        return unidadeService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> getById(@PathVariable Integer id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Unidade create(@RequestBody Unidade unidade) {
        return unidadeService.save(unidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (unidadeService.findById(id).isPresent()) {
            unidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
