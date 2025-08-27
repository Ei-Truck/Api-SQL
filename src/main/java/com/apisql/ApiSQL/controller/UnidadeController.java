package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.service.UnidadeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public List<Unidade> getAll() {
        return unidadeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> getById(@PathVariable Long id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Unidade create(@RequestBody Unidade unidade) {
        return unidadeService.save(unidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> update(@PathVariable Long id, @RequestBody Unidade unidade) {
        return unidadeService.findById(id)
                .map(existing -> {
                    existing.setNome(unidade.getNome());
                    existing.setCidade(unidade.getCidade());
                    existing.setUfEstado(unidade.getUfEstado());
                    existing.setSegmento(unidade.getSegmento());
                    existing.setIsInactive(unidade.getIsInactive());
                    return ResponseEntity.ok(unidadeService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (unidadeService.findById(id).isPresent()) {
            unidadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}