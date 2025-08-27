package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.service.SegmentoService;
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
@RequestMapping("/segmentos")
@Tag(name = "Segmentos", description = "Gerenciamento de Segmentos")
public class SegmentoController {

    private final SegmentoService segmentoService;

    public SegmentoController(SegmentoService segmentoService) {
        this.segmentoService = segmentoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os segmentos", description = "Retorna todos os segmentos cadastrados")
    public List<Segmento> getAll() {
        return segmentoService.findAll();
    }

    @Operation(summary = "Busca segmento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Segmento> getById(@PathVariable Long id) {
        return segmentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo segmento")
    public Segmento create(@RequestBody Segmento segmento) {
        return segmentoService.save(segmento);
    }

    @Operation(summary = "Atualiza um segmento existente")
    @PutMapping("/{id}")
    public ResponseEntity<Segmento> update(@PathVariable Long id, @RequestBody Segmento segmento) {
        return segmentoService.findById(id)
                .map(existing -> {
                    existing.setNome(segmento.getNome());
                    existing.setStatus(segmento.getStatus());
                    existing.setIsInactive(segmento.getIsInactive());
                    return ResponseEntity.ok(segmentoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um segmento pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (segmentoService.findById(id).isPresent()) {
            segmentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
