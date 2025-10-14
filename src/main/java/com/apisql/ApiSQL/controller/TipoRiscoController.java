package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.TipoRiscoRequestDTO;
import com.apisql.ApiSQL.dto.TipoRiscoResponseDTO;
import com.apisql.ApiSQL.openapi.TipoRiscoOpenApi;
import com.apisql.ApiSQL.service.TipoRiscoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-risco")
public class TipoRiscoController implements TipoRiscoOpenApi {

    private final TipoRiscoService tipoRiscoService;

    public TipoRiscoController(TipoRiscoService tipoRiscoService) {
        this.tipoRiscoService = tipoRiscoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TipoRiscoResponseDTO>> findAll() {
        List<TipoRiscoResponseDTO> riscos = tipoRiscoService.findAll();
        return ResponseEntity.ok(riscos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoRiscoResponseDTO> findById(@PathVariable Integer id) {
        TipoRiscoResponseDTO risco = tipoRiscoService.findById(id);
        return ResponseEntity.ok(risco);
    }

    @Override
    @PostMapping
    public ResponseEntity<TipoRiscoResponseDTO> save(@Valid @RequestBody TipoRiscoRequestDTO dto) {
        TipoRiscoResponseDTO savedRisco = tipoRiscoService.save(dto);
        return new ResponseEntity<>(savedRisco, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoRiscoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody TipoRiscoRequestDTO dto) {
        TipoRiscoResponseDTO updatedRisco = tipoRiscoService.update(id, dto);
        return ResponseEntity.ok(updatedRisco);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tipoRiscoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}