package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController implements ViagemOpenApi {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ViagemResponseDTO>> findAll() {
        List<ViagemResponseDTO> viagens = viagemService.findAll();
        return ResponseEntity.ok(viagens);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> findById(@PathVariable Integer id) {
        ViagemResponseDTO viagem = viagemService.findById(id);
        return ResponseEntity.ok(viagem);
    }

    @Override
    @PostMapping
    public ResponseEntity<ViagemResponseDTO> save(@Valid @RequestBody ViagemRequestDTO dto) {
        ViagemResponseDTO savedViagem = viagemService.save(dto);
        return new ResponseEntity<>(savedViagem, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ViagemRequestDTO dto) {
        ViagemResponseDTO updatedViagem = viagemService.update(id, dto);
        return ResponseEntity.ok(updatedViagem);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        viagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}