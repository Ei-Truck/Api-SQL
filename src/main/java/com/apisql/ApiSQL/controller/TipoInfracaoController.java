package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.openapi.TipoInfracaoOpenApi;
import com.apisql.ApiSQL.service.TipoInfracaoService;

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
public class TipoInfracaoController implements TipoInfracaoOpenApi {

    private final TipoInfracaoService service;

    public TipoInfracaoController(TipoInfracaoService service) {
        this.service = service;
    }


    @GetMapping
    public List<TipoInfracao> getAll() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipoInfracao> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoInfracao create(@RequestBody TipoInfracao tipoInfracao) {
        return service.save(tipoInfracao);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
