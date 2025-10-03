package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.openapi.StatusOpenApi;
import com.apisql.ApiSQL.openapi.TipoGravidadeOpenApi;
import com.apisql.ApiSQL.service.TipoGravidadeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TipoGravidadeController implements TipoGravidadeOpenApi {

    private final TipoGravidadeService tipoGravidadeService;

    public TipoGravidadeController(TipoGravidadeService tipoGravidadeService) {
        this.tipoGravidadeService = tipoGravidadeService;
    }

    @Override
    @GetMapping
    public List<TipoGravidade> getAll() {
        return tipoGravidadeService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoGravidade> getById(@PathVariable Integer id) {
        return tipoGravidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoGravidade create(@RequestBody TipoGravidade tipoGravidade) {
        return tipoGravidadeService.save(tipoGravidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoGravidadeService.findById(id).isPresent()) {
            tipoGravidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
