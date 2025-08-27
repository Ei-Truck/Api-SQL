package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.service.TipoRiscoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@RestController
@RequestMapping("/tipos-risco")
public class TipoRiscoController {

    private final TipoRiscoService tipoRiscoService;

    public TipoRiscoController(TipoRiscoService tipoRiscoService) {
        this.tipoRiscoService = tipoRiscoService;
    }

    @GetMapping
    public List<TipoRisco> getAll() {
        return tipoRiscoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRisco> getById(@PathVariable Long id) {
        return tipoRiscoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoRisco create(@RequestBody TipoRisco tipoRisco) {
        return tipoRiscoService.save(tipoRisco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRisco> update(@PathVariable Long id, @RequestBody TipoRisco tipoRisco) {
        return tipoRiscoService.findById(id)
                .map(existing -> {
                    existing.setNome(tipoRisco.getNome());
                    existing.setDescricao(tipoRisco.getDescricao());
                    existing.setIsInactive(tipoRisco.getIsInactive());
                    return ResponseEntity.ok(tipoRiscoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoRiscoService.findById(id).isPresent()) {
            tipoRiscoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}