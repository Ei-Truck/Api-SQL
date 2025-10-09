package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.MotoristaRequestDTO;
import com.apisql.ApiSQL.dto.MotoristaResponseDTO;
import com.apisql.ApiSQL.openapi.MotoristaOpenApi;
import com.apisql.ApiSQL.service.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController implements MotoristaOpenApi {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MotoristaResponseDTO>> findAll() {
        List<MotoristaResponseDTO> motoristas = motoristaService.findAll();
        return ResponseEntity.ok(motoristas);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> findById(@PathVariable Integer id) {
        MotoristaResponseDTO motorista = motoristaService.findById(id);
        return ResponseEntity.ok(motorista);
    }

    @Override
    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> save(@Valid @RequestBody MotoristaRequestDTO dto) {
        MotoristaResponseDTO savedMotorista = motoristaService.save(dto);
        return new ResponseEntity<>(savedMotorista, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MotoristaRequestDTO dto) {
        MotoristaResponseDTO updatedMotorista = motoristaService.update(id, dto);
        return ResponseEntity.ok(updatedMotorista);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        motoristaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}