package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.SegmentoRequestDTO;
import com.apisql.ApiSQL.dto.SegmentoResponseDTO;
import com.apisql.ApiSQL.openapi.SegmentoOpenApi;
import com.apisql.ApiSQL.service.SegmentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segmentos")
public class SegmentoController implements SegmentoOpenApi {

    private final SegmentoService segmentoService;

    public SegmentoController(SegmentoService segmentoService) {
        this.segmentoService = segmentoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SegmentoResponseDTO>> findAll() {
        List<SegmentoResponseDTO> segmentos = segmentoService.findAll();
        return ResponseEntity.ok(segmentos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SegmentoResponseDTO> findById(@PathVariable Integer id) {
        SegmentoResponseDTO segmento = segmentoService.findById(id);
        return ResponseEntity.ok(segmento);
    }

    @Override
    @PostMapping
    public ResponseEntity<SegmentoResponseDTO> save(@Valid @RequestBody SegmentoRequestDTO dto) {
        SegmentoResponseDTO savedSegmento = segmentoService.save(dto);
        return new ResponseEntity<>(savedSegmento, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SegmentoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody SegmentoRequestDTO dto) {
        SegmentoResponseDTO updatedSegmento = segmentoService.update(id, dto);
        return ResponseEntity.ok(updatedSegmento);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        segmentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}