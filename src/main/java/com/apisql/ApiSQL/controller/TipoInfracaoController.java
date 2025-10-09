package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.TipoInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.TipoInfracaoResponseDTO;
import com.apisql.ApiSQL.openapi.TipoInfracaoOpenApi;
import com.apisql.ApiSQL.service.TipoInfracaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-infracao")
public class TipoInfracaoController implements TipoInfracaoOpenApi {

    private final TipoInfracaoService tipoInfracaoService;

    public TipoInfracaoController(TipoInfracaoService tipoInfracaoService) {
        this.tipoInfracaoService = tipoInfracaoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TipoInfracaoResponseDTO>> findAll() {
        List<TipoInfracaoResponseDTO> infracoes = tipoInfracaoService.findAll();
        return ResponseEntity.ok(infracoes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoInfracaoResponseDTO> findById(@PathVariable Integer id) {
        TipoInfracaoResponseDTO infracao = tipoInfracaoService.findById(id);
        return ResponseEntity.ok(infracao);
    }

    @Override
    @PostMapping
    public ResponseEntity<TipoInfracaoResponseDTO> save(@Valid @RequestBody TipoInfracaoRequestDTO dto) {
        TipoInfracaoResponseDTO savedInfracao = tipoInfracaoService.save(dto);
        return new ResponseEntity<>(savedInfracao, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoInfracaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody TipoInfracaoRequestDTO dto) {
        TipoInfracaoResponseDTO updatedInfracao = tipoInfracaoService.update(id, dto);
        return ResponseEntity.ok(updatedInfracao);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tipoInfracaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}