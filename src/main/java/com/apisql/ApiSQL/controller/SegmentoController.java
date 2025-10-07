package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.openapi.SegmentoOpenApi;
import com.apisql.ApiSQL.service.SegmentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class SegmentoController implements SegmentoOpenApi {

    private final SegmentoService segmentoService;

    public SegmentoController(SegmentoService segmentoService) {
        this.segmentoService = segmentoService;
    }


    @Override
    @GetMapping
    public List<Segmento> getAll() {
        return segmentoService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Segmento> getById(
             @PathVariable Integer id) {
        return segmentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Segmento create(@RequestBody Segmento segmento) {
        return segmentoService.save(segmento);
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (segmentoService.findById(id).isPresent()) {
            segmentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
