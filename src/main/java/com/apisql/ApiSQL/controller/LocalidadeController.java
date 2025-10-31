package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.LocalidadeRequestDTO;
import com.apisql.ApiSQL.dto.LocalidadeResponseDTO;
import com.apisql.ApiSQL.openapi.LocalidadeOpenApi;
import com.apisql.ApiSQL.service.LocalidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
public class LocalidadeController implements LocalidadeOpenApi {

    private final LocalidadeService localidadeService;

    public LocalidadeController(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<LocalidadeResponseDTO>> findAll() {
        List<LocalidadeResponseDTO> localidades = localidadeService.findAll();
        return ResponseEntity.ok(localidades);
    }

    @Override
    @GetMapping("/diff")
    public ResponseEntity<List<LocalidadeResponseDTO>> findDistinct() {
        return ResponseEntity.ok(localidadeService.findAllDistinct());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LocalidadeResponseDTO> findById(@PathVariable Integer id) {
        LocalidadeResponseDTO localidade = localidadeService.findById(id);
        return ResponseEntity.ok(localidade);
    }

    @Override
    @PostMapping
    public ResponseEntity<LocalidadeResponseDTO> save(@Valid @RequestBody LocalidadeRequestDTO dto) {
        LocalidadeResponseDTO savedLocalidade = localidadeService.save(dto);
        return new ResponseEntity<>(savedLocalidade, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<LocalidadeResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody LocalidadeRequestDTO dto) {
        LocalidadeResponseDTO updatedLocalidade = localidadeService.update(id, dto);
        return ResponseEntity.ok(updatedLocalidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        localidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}