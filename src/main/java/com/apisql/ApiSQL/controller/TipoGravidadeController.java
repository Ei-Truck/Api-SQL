package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.TipoGravidadeRequestDTO;
import com.apisql.ApiSQL.dto.TipoGravidadeResponseDTO;
import com.apisql.ApiSQL.openapi.TipoGravidadeOpenApi;
import com.apisql.ApiSQL.service.TipoGravidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-gravidade")
public class TipoGravidadeController implements TipoGravidadeOpenApi {

    private final TipoGravidadeService tipoGravidadeService;

    public TipoGravidadeController(TipoGravidadeService tipoGravidadeService) {
        this.tipoGravidadeService = tipoGravidadeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TipoGravidadeResponseDTO>> findAll() {
        List<TipoGravidadeResponseDTO> gravidades = tipoGravidadeService.findAll();
        return ResponseEntity.ok(gravidades);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoGravidadeResponseDTO> findById(@PathVariable Integer id) {
        TipoGravidadeResponseDTO gravidade = tipoGravidadeService.findById(id);
        return ResponseEntity.ok(gravidade);
    }

    @Override
    @PostMapping
    public ResponseEntity<TipoGravidadeResponseDTO> save(@Valid @RequestBody TipoGravidadeRequestDTO dto) {
        TipoGravidadeResponseDTO savedGravidade = tipoGravidadeService.save(dto);
        return new ResponseEntity<>(savedGravidade, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoGravidadeResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody TipoGravidadeRequestDTO dto) {
        TipoGravidadeResponseDTO updatedGravidade = tipoGravidadeService.update(id, dto);
        return ResponseEntity.ok(updatedGravidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tipoGravidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}