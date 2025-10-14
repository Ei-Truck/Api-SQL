package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.CaminhaoRequestDTO;
import com.apisql.ApiSQL.dto.CaminhaoResponseDTO;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.openapi.CaminhaoOpenApi;
import com.apisql.ApiSQL.service.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController implements CaminhaoOpenApi {

    private final CaminhaoService caminhaoService;

    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CaminhaoResponseDTO>> findAll() {
        List<CaminhaoResponseDTO> caminhoes = caminhaoService.findAll();
        return ResponseEntity.ok(caminhoes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> findById(@PathVariable Integer id) {
            CaminhaoResponseDTO caminhao = caminhaoService.findById(id);
            return ResponseEntity.ok(caminhao);
    }

    @Override
    @PostMapping
    public ResponseEntity<CaminhaoResponseDTO> save(@Valid @RequestBody CaminhaoRequestDTO dto) {
            CaminhaoResponseDTO savedCaminhao = caminhaoService.save(dto);
            return new ResponseEntity<>(savedCaminhao, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> update(@PathVariable Integer id, @RequestBody Caminhao caminhaoAtualizado) {
        CaminhaoResponseDTO updatedCaminhao = caminhaoService.update(id, caminhaoAtualizado);
        return ResponseEntity.ok(updatedCaminhao);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        caminhaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}