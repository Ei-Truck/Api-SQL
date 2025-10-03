package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.openapi.CaminhaoOpenApi;
import com.apisql.ApiSQL.service.CaminhaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CaminhaoController implements CaminhaoOpenApi {

    private final CaminhaoService caminhaoService;

    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    @Override
    @GetMapping
    public List<Caminhao> getAll() {
        return caminhaoService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> getById(
             @PathVariable Integer id) {
        return caminhaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Caminhao create(@RequestBody Caminhao caminhao) {
        return caminhaoService.save(caminhao);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> update(
            @PathVariable Integer id,
            @RequestBody Caminhao caminhao) {
        return caminhaoService.findById(id)
                .map(existing -> {
                    existing.setChassi(caminhao.getChassi());
                    existing.setSegmento(caminhao.getSegmento());
                    existing.setUnidade(caminhao.getUnidade());
                    existing.setPlaca(caminhao.getPlaca());
                    existing.setModelo(caminhao.getModelo());
                    existing.setAnoFabricacao(caminhao.getAnoFabricacao());
                    existing.setNumeroFrota(caminhao.getNumeroFrota());
                    existing.setTransactionMade(caminhao.getTransactionMade());
                    existing.setUpdatedAt(caminhao.getUpdatedAt());
                    existing.setIsInactive(caminhao.getIsInactive());
                    return ResponseEntity.ok(caminhaoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (caminhaoService.findById(id).isPresent()) {
            caminhaoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
