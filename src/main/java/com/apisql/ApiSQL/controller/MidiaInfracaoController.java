package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.MidiaInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoResponseDTO;
import com.apisql.ApiSQL.openapi.MidiaInfracaoOpenApi;
import com.apisql.ApiSQL.service.MidiaInfracaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/midias-infracoes")
public class MidiaInfracaoController implements MidiaInfracaoOpenApi {

    private final MidiaInfracaoService service;

    public MidiaInfracaoController(MidiaInfracaoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MidiaInfracaoResponseDTO>> findAll() {
        List<MidiaInfracaoResponseDTO> midias = service.findAll();
        return ResponseEntity.ok(midias);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MidiaInfracaoResponseDTO> findById(@PathVariable Integer id) {
        MidiaInfracaoResponseDTO midia = service.findById(id);
        return ResponseEntity.ok(midia);
    }

    @Override
    @PostMapping
    public ResponseEntity<MidiaInfracaoResponseDTO> save(@Valid @RequestBody MidiaInfracaoRequestDTO dto) {
        MidiaInfracaoResponseDTO savedMidia = service.save(dto);
        return new ResponseEntity<>(savedMidia, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MidiaInfracaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MidiaInfracaoRequestDTO dto) {
        MidiaInfracaoResponseDTO updatedMidia = service.update(id, dto);
        return ResponseEntity.ok(updatedMidia);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}