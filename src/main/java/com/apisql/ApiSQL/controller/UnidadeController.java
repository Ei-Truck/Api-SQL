package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.UnidadeRequestDTO;
import com.apisql.ApiSQL.dto.UnidadeResponseDTO;
import com.apisql.ApiSQL.openapi.UnidadeOpenApi;
import com.apisql.ApiSQL.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController implements UnidadeOpenApi {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UnidadeResponseDTO>> findAll() {
        List<UnidadeResponseDTO> unidades = unidadeService.findAll();
        return ResponseEntity.ok(unidades);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> findById(@PathVariable Integer id) {
        UnidadeResponseDTO unidade = unidadeService.findById(id);
        return ResponseEntity.ok(unidade);
    }

    @Override
    @PostMapping
    public ResponseEntity<UnidadeResponseDTO> save(@Valid @RequestBody UnidadeRequestDTO dto) {
        UnidadeResponseDTO savedUnidade = unidadeService.save(dto);
        return new ResponseEntity<>(savedUnidade, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody UnidadeRequestDTO dto) {
        UnidadeResponseDTO updatedUnidade = unidadeService.update(id, dto);
        return ResponseEntity.ok(updatedUnidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}